package FinalProject.patcher;

import com.github.javaparser.ast.expr.BinaryExpr;

import java.util.ArrayList;
import java.util.List;

import static com.github.javaparser.ast.expr.BinaryExpr.Operator.*;

public class FixTemplates {
    public static List<IFixTemplate> getPatches() {
        return List.of(
                new BinaryOperatorSwap(BINARY_OR, List.of(BINARY_AND, XOR, LEFT_SHIFT, SIGNED_RIGHT_SHIFT, UNSIGNED_RIGHT_SHIFT)),
                new BinaryOperatorSwap(BINARY_AND, List.of(BINARY_OR, XOR, LEFT_SHIFT, SIGNED_RIGHT_SHIFT, UNSIGNED_RIGHT_SHIFT)),
                new BinaryOperatorSwap(XOR, List.of(BINARY_AND, BINARY_OR, LEFT_SHIFT, SIGNED_RIGHT_SHIFT, UNSIGNED_RIGHT_SHIFT)),
                new BinaryOperatorSwap(LEFT_SHIFT, List.of(BINARY_AND, XOR, BINARY_OR, SIGNED_RIGHT_SHIFT, UNSIGNED_RIGHT_SHIFT)),
                new BinaryOperatorSwap(SIGNED_RIGHT_SHIFT, List.of(BINARY_AND, XOR, LEFT_SHIFT, BINARY_OR, UNSIGNED_RIGHT_SHIFT)),
                new BinaryOperatorSwap(UNSIGNED_RIGHT_SHIFT, List.of(BINARY_AND, XOR, LEFT_SHIFT, SIGNED_RIGHT_SHIFT, BINARY_OR)),
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
                new NullFix(),
                new NegationFix()
        );
    }
}
