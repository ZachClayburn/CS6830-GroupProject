package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class BinaryOperatorSwap implements IFixTemplate {
    private final BinaryExpr.Operator targetOperator;

    BinaryOperatorSwap(BinaryExpr.Operator targetOperator) {
        this.targetOperator = targetOperator;
    }

    @Override
    public boolean checkNode(Node nodeToCheck) {
        if (!(nodeToCheck instanceof ExpressionStmt)) {
            return false;
        }
        var expression = ((ExpressionStmt) nodeToCheck).getExpression();
        return expression instanceof BinaryExpr && ((BinaryExpr) expression).getOperator() != targetOperator;
    }

    @Override
    public List<Statement> applyPatch(Node patchLocation) {
        var expressionStmt = (ExpressionStmt) patchLocation;
        var binOpNode = (BinaryExpr) expressionStmt.getExpression();
        binOpNode.setOperator(targetOperator);
        return List.of((Statement) patchLocation);
    }
}
