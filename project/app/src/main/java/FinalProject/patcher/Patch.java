package FinalProject.patcher;

import FinalProject.files.SourceFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Patch {
    List<SourceFile> modifiedFiles = new ArrayList<>();

    public void writeOutPatch() throws IOException {
        for (SourceFile file : modifiedFiles) file.writeBackFile();
    }
}
