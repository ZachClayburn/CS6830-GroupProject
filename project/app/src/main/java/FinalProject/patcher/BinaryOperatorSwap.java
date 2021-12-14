package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;

public class BinaryOperatorSwap implements IFixTemplate {
    private final BinaryExpr.Operator targetOperator;

    BinaryOperatorSwap(BinaryExpr.Operator targetOperator) {
        this.targetOperator = targetOperator;
    }

    @Override
    public boolean checkNode(Node nodeToCheck) {
        return nodeToCheck instanceof BinaryExpr && !((BinaryExpr) nodeToCheck).getOperator().equals(targetOperator);
    }

    @Override
    public void applyPatch(Node patchLocation) {
        var binOpNode = (BinaryExpr) patchLocation;
        binOpNode.setOperator(targetOperator);
    }
}
