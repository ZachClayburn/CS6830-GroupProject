package FinalProject.patcher;

import com.github.javaparser.ast.expr.BinaryExpr;

import java.util.ArrayList;
import java.util.List;

public class FixTemplates {
    public static List<IFixTemplate> getPatches() {
        var patchList = new ArrayList<IFixTemplate>();
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.OR));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.AND));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.BINARY_OR));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.BINARY_AND));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.XOR));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.EQUALS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.NOT_EQUALS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.LESS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.GREATER));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.LESS_EQUALS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.GREATER_EQUALS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.LEFT_SHIFT));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.SIGNED_RIGHT_SHIFT));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.PLUS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.MINUS));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.MULTIPLY));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.DIVIDE));
        patchList.add(new BinaryOperatorSwap(BinaryExpr.Operator.REMAINDER));
        patchList.add(new StatementRemover());
        patchList.add(new NullFix());
        return patchList;
    }
}
