package FinalProject.patcher;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;

public class StatementRemover implements IFixTemplate {
    @Override
    public boolean checkNode(Node nodeToCheck) {
        return nodeToCheck instanceof Statement;
    }

    @Override
    public void applyPatch(Node patchLocation) {
        patchLocation.remove();
    }
}
