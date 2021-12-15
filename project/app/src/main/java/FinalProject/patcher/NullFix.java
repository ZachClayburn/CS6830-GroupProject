package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class NullFix implements IFixTemplate {
    @Override
    public boolean checkNode(Node nodeToCheck) {
        if (!(nodeToCheck instanceof ExpressionStmt)) return false;
        var expressionStmt = (ExpressionStmt) nodeToCheck;
        if (!(expressionStmt.getExpression() instanceof AssignExpr)) return false;
        var assignExpr = (AssignExpr) ((ExpressionStmt) nodeToCheck).getExpression();
        //TODO try to fix this
        return false;
    }

    @Override
    public List<Statement> applyPatch(Node patchLocation) {
        var assignExpression = (AssignExpr) patchLocation;
        return null;
    }
}
