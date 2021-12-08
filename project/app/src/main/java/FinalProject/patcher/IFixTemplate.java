package FinalProject.patcher;

import FinalProject.files.SourceFile;
import com.github.javaparser.ast.Node;

import javax.annotation.Nullable;

public interface IFixTemplate {

    /**
     * This method searches for a potential location that the particular fix pattern can be applied in a given file. If
     * multiple possible locations exist in the file, it returns one of them at random.
     *
     * @param fileToSearch A source file to search for potential patch locations.
     * @return The Node that is a potential candidate for the patch, if one exists, otherwise null.
     */
    @Nullable
    Node selectPatchLocation(SourceFile fileToSearch);

    /**
     * This method applies the template fix to the Node found in selectPatchLocation.
     * @param patchLocation A node found by
     */
    void applyPatch(Node patchLocation);

    @Nullable
    default SourceFile generateFixes(SourceFile fileToChange) {
        var clonedFile = fileToChange.clone();
        var potentialPatchesLocation = selectPatchLocation(clonedFile);
        if (potentialPatchesLocation == null) return null;
        applyPatch(potentialPatchesLocation);
        return clonedFile;
    }
}
