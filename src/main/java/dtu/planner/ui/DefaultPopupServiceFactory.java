package dtu.planner.ui;

import dtu.planner.ui.interfaces.PopupDisplayer;
import dtu.planner.ui.interfaces.PopupService;
import dtu.planner.ui.interfaces.PopupServiceFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Arthur
 */
public class DefaultPopupServiceFactory implements PopupServiceFactory {

    @Override
    public PopupService create(AnchorPane popUpPane, Pane popUpContainer, VBox rootVBox, Navigator navigator) {

        PopupDisplayer displayer =
            new PopupDisplayerImplementation(
                popUpPane,
                popUpContainer,
                rootVBox
            );

        return new PopupManager(displayer, navigator);
    }
}
