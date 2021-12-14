package FinalProject.patcher;

import FinalProject.files.SourceFile;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import javax.annotation.Nullable;
import java.io.File;

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
     */
    void applyPatch(Node patchLocation);

    @Nullable
    default SourceFile generateFixes(Node node, File parentFile) {
        if (!checkNode(node)) return null;
        var clonedNode = node.clone();
        var parentNode = clonedNode.findRootNode();
        if (!(parentNode instanceof CompilationUnit)) {
            throw new IllegalArgumentException("The root not was not a compilation unit!");
        }
        var parent = (CompilationUnit) parentNode;
        applyPatch(clonedNode);
        return new SourceFile(parentFile, parent);
    }
}
