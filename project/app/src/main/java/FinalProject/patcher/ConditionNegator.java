package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithCondition;

public class ConditionNegator implements IFixTemplate {
    @Override
    public boolean checkNode(Node nodeToCheck) {
        return nodeToCheck instanceof NodeWithCondition;
    }

    @Override
    public void applyPatch(Node patchLocation) {
        var castNode = (NodeWithCondition) patchLocation;
        var condition = castNode.getCondition();
        var newCondition = new UnaryExpr(condition, UnaryExpr.Operator.LOGICAL_COMPLEMENT);
        castNode.setCondition(newCondition);
    }
}
