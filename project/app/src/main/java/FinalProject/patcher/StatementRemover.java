package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;

import java.util.ArrayList;
import java.util.List;

public class StatementRemover implements IFixTemplate {
    @Override
    public boolean checkNode(Node nodeToCheck) {
        return nodeToCheck instanceof Statement;
    }

    @Override
    public List<Node> applyPatch(Node patchLocation) {
        return new ArrayList<>();
    }
}
