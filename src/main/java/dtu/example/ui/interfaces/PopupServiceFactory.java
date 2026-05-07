package dtu.example.ui.interfaces;

import dtu.example.ui.Navigator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public interface PopupServiceFactory {
    public PopupService create(
        AnchorPane popUpPane,
        Pane popUpContainer,
        VBox rootVBox,
        Navigator navigator
    );
}
