package FinalProject.patcher;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class NullFix implements IFixTemplate {
    @Override
    public boolean checkNode(Node nodeToCheck) {
        return nodeToCheck instanceof ExpressionStmt expressionStmt &&
                expressionStmt.getExpression() instanceof VariableDeclarationExpr variableDeclarationExpr &&
                variableDeclarationExpr.getVariables().size() == 1 &&
                variableDeclarationExpr.getVariables().get(0).getInitializer().isPresent() &&
                variableDeclarationExpr.getVariables().get(0).getInitializer().get() instanceof MethodCallExpr;
    }

    @Override
    public List<Statement> applyPatch(Node patchLocation) {
        var expressionStatement = (ExpressionStmt) patchLocation;
        var variableDeclaration = (VariableDeclarationExpr) expressionStatement.getExpression();
        var variable = variableDeclaration.getVariables().get(0);
        var name = variable.getName();
        var type = variable.getType();
        var initializer = (MethodCallExpr) variable.getInitializer().get();
        var scope = initializer.getScope();
        var newIf = new IfStmt();
        var condition = scope.orElse(initializer);
        newIf.setCondition(new BinaryExpr(condition, new NullLiteralExpr(), BinaryExpr.Operator.NOT_EQUALS));
        newIf.setThenStmt(new ExpressionStmt(initializer));
        var newVariableDeclaration = StaticJavaParser.parseStatement("%s %s = new %s();".formatted(type.toString(), name.toString(), type.toString()));
        return List.of(newVariableDeclaration, newIf);
    }

    public static void main(String[] args) {
        var a = StaticJavaParser.parseStatement("String a = foo.hello();");
        var b = StaticJavaParser.parseStatement("int a = x + y;");
        var c = StaticJavaParser.parseStatement("if(a) {b= 1 + a;}");
        var nullFix = new NullFix();
        var isa = nullFix.checkNode(a);
        var isb = nullFix.checkNode(b);
        var fixedA = nullFix.applyPatch(a);
        System.out.println(a);
    }
}
