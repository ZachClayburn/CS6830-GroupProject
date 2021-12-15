package FinalProject.patcher;

import FinalProject.files.SourceFile;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.google.common.collect.MoreCollectors;

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
        var rootNode = node.findRootNode();
        if (!(rootNode instanceof CompilationUnit)) {
            throw new IllegalArgumentException("The root not was not a compilation unit!");
        }

        var clonedRoot = (CompilationUnit) rootNode.clone();
        var nodeInCloned = clonedRoot.stream()
                                     .filter(n -> n.equals(node) && n.getRange().equals(node.getRange()))
                                     .collect(MoreCollectors.onlyElement());

        applyPatch(nodeInCloned);
        return new SourceFile(parentFile, clonedRoot);
    }
}
