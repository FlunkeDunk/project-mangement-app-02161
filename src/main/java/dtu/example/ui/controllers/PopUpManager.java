package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.example.ui.Navigator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


public class PopUpManager {

    Pane popUpPane;
    Parent toDisable;
    Navigator navigator;

    public PopUpManager(Pane popUpPane, Parent toDisable, Navigator navigator) {
        this.popUpPane = popUpPane;
        this.toDisable = toDisable;
        this.navigator = navigator;
    }

    
    public void popUp(String fxml) throws IOException {
        FXMLLoader loader = navigator.loadFXML(fxml);
        Parent currentPopUp = loader.load();
        popUpPane.getChildren().add(currentPopUp);
        setup(true);
    }
    
    public void popDown() {
        popUpPane.getChildren().clear();
        setup(false);
    }

    private void setup(boolean poppingUp) {
        popUpPane.setMouseTransparent(!poppingUp);
        popUpPane.getParent().setMouseTransparent(!poppingUp);
        popUpPane.setVisible(poppingUp);
        toDisable.setDisable(poppingUp);
    }

}
