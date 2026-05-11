package dtu.planner.ui;

import javafx.scene.Node;

/**
 * @author Arthur
 */

public final class NodeUtils {

    private NodeUtils() {}

    public static boolean isDescendant(Node child, Node parent) {
        while (child != null) {
            if (child == parent) return true;
            child = child.getParent();
        }
        return false;
    }
}
