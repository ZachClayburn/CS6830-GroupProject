package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class BinaryOperatorSwap implements IFixTemplate {
    private final BinaryExpr.Operator targetOperator;
    private final List<BinaryExpr.Operator> candidates;

    BinaryOperatorSwap(BinaryExpr.Operator targetOperator, List<BinaryExpr.Operator> candidates) {
        this.targetOperator = targetOperator;
        this.candidates = candidates;
    }

    @Override
    public boolean checkNode(Node nodeToCheck) {
        var optBinaryExpr = nodeToCheck.findFirst(BinaryExpr.class);
        if (optBinaryExpr.isEmpty()) return false;
        var expression = optBinaryExpr.get();
        return expression.getOperator() != targetOperator && candidates.contains(expression.getOperator());
    }

    @Override
    public List<Statement> applyPatch(Node patchLocation) {
        var optBinaryExpr = patchLocation.findFirst(BinaryExpr.class);
        var binOpNode = optBinaryExpr.get();
        binOpNode.setOperator(targetOperator);
        return List.of((Statement) patchLocation);
    }
}
