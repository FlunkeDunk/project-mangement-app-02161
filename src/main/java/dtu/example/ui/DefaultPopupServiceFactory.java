package dtu.example.ui;

import dtu.example.ui.interfaces.PopupDisplayer;
import dtu.example.ui.interfaces.PopupService;
import dtu.example.ui.interfaces.PopupServiceFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
