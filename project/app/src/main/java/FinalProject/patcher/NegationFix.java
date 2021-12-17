package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;

import java.util.List;

public class NegationFix implements IFixTemplate{

    @Override
    public boolean checkNode(Node nodeToCheck) {
        return nodeToCheck instanceof IfStmt;
    }

    @Override
    public List<Node> applyPatch(Node patchLocation) {
        Expression condition = ((IfStmt)patchLocation).getCondition();
        EnclosedExpr enclosedExpr = new EnclosedExpr();
        enclosedExpr.setInner(condition);
        UnaryExpr unaryExpr = new UnaryExpr();
        unaryExpr.setOperator(UnaryExpr.Operator.LOGICAL_COMPLEMENT);
        unaryExpr.setExpression(enclosedExpr);
        ((IfStmt)patchLocation).setCondition(unaryExpr);
        return List.of(patchLocation);
    }
}
