package FinalProject.patcher;

import FinalProject.files.SourceFile;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.google.common.collect.MoreCollectors;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;

public interface IFixTemplate {

    /**
     * This method check if the current node is a valid candidate for this current fix template
     *
     * @param nodeToCheck The node to be checked
     * @return True if the node is a valid candidate, false otherwise
     */
    boolean checkNode(Node nodeToCheck);

    /**
     * This method applies the template fix to the Node found in selectPatchLocation.
     *
     * @param patchLocation The Node that has been verified to be a proper candidate for the fix template
     * @return a list of the nodes to replace the provided node
     */
    List<Statement> applyPatch(Node patchLocation);
}
