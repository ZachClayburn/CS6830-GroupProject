package FinalProject.patcher;

import com.github.javaparser.ast.expr.BinaryExpr;

import java.util.ArrayList;
import java.util.List;

import static com.github.javaparser.ast.expr.BinaryExpr.Operator.OR;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.AND;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.NOT_EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.LESS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.GREATER;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.LESS_EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.GREATER_EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.PLUS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.MINUS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.MULTIPLY;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.DIVIDE;

public class FixTemplates {
    public static List<IFixTemplate> getPatches() {
        return List.of(
                new BinaryOperatorSwap(AND, List.of(OR)),
                new BinaryOperatorSwap(OR, List.of(AND)),
                new BinaryOperatorSwap(EQUALS, List.of(NOT_EQUALS, LESS_EQUALS, LESS, GREATER, GREATER_EQUALS)),
                new BinaryOperatorSwap(NOT_EQUALS, List.of(EQUALS, LESS_EQUALS, LESS, GREATER, GREATER_EQUALS)),
                new BinaryOperatorSwap(LESS_EQUALS, List.of(EQUALS, NOT_EQUALS, LESS, GREATER, GREATER_EQUALS)),
                new BinaryOperatorSwap(LESS, List.of(EQUALS, NOT_EQUALS, LESS_EQUALS, GREATER, GREATER_EQUALS)),
                new BinaryOperatorSwap(GREATER, List.of(EQUALS, NOT_EQUALS, LESS_EQUALS, LESS, GREATER_EQUALS)),
                new BinaryOperatorSwap(GREATER_EQUALS, List.of(EQUALS, NOT_EQUALS, LESS_EQUALS, LESS, GREATER)),
                new BinaryOperatorSwap(PLUS, List.of(MINUS, MULTIPLY, DIVIDE)),
                new BinaryOperatorSwap(MINUS, List.of(PLUS, MULTIPLY, DIVIDE)),
                new BinaryOperatorSwap(MULTIPLY, List.of(PLUS, MINUS, DIVIDE)),
                new BinaryOperatorSwap(DIVIDE, List.of(PLUS, MINUS, MULTIPLY)),
                new StatementRemover(),
                new NullFix()
        );
    }
}
