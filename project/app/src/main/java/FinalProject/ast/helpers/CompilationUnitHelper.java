package FinalProject.ast.helpers;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompilationUnitHelper {
    public static CompilationUnit createCompilationUnit(String fullPath) {
        FileInputStream in;
        CompilationUnit cu = null;

        try {
            in = new FileInputStream(fullPath);
        } catch (FileNotFoundException fex) {
            fex.printStackTrace();
            return null;
        }

        try {
            // parse the file
            cu = StaticJavaParser.parse(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return cu;
    }

//    public static boolean applyNodesToOriginalCu(Node originalNode, List<Node> newNodes) {
//        Node parentNode = originalNode.getParentNode().orElse(null);
//
//        if (parentNode == null) {
//            System.err.println("No parent node for node to fix");
//            return false;
//        }
//
//        int nodeIdx = parentNode.getChildNodes().indexOf(originalNode);
//
//        if (nodeIdx == -1) {
//            System.err.println("Could not find node in parent");
//            return false;
//        }
//
//        List<Node> originalChildren = parentNode.getChildNodes();
//        NodeList<Node> newChildren = new NodeList<>();
//
//        for (int i = 0; i < originalChildren.size(); i++) {
//
//            if (i == nodeIdx) {
//                newChildren.addAll(newNodes);
//            } else {
//                newChildren.add(originalChildren.get(i));
//            }
//        }
//
//        for (Node n : originalChildren) {
//            parentNode.remove(n);
//        }
//
//        for (Node node : newChildren) {
//            node.setParentNode(parentNode);
//        }
//        return true;
//    }
}
