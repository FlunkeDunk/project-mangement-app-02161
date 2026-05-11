package dtu.planner.ui;

import dtu.planner.ui.interfaces.PopupDisplayer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Arthur
 */

public class PopupDisplayerImplementation implements PopupDisplayer{

    private final AnchorPane POPUP_PANE;
    private final Node BACKGROUND;
    private final Pane POPUP_PANE_CONTAINER;
    private Parent currentPopUp;


    public PopupDisplayerImplementation(AnchorPane popupPane, Pane popupContainer, Node background) {
        this.POPUP_PANE = popupPane;
        this.BACKGROUND = background;
        this.POPUP_PANE_CONTAINER = popupContainer;
    }

        private void anchorPopup(Parent popup) {
        AnchorPane.setTopAnchor(popup, 0.0);
        AnchorPane.setBottomAnchor(popup, 0.0);
        AnchorPane.setLeftAnchor(popup, 0.0);
        AnchorPane.setRightAnchor(popup, 0.0);
    }


    @Override
    public void display(Parent popup) {
        removePopup();
        POPUP_PANE.getChildren().add(popup);
        configurePopupState(true);
        anchorPopup(popup);
        
        currentPopUp = popup;
    }
    
    @Override
    public void removePopup() {
    if (currentPopUp != null) {
            POPUP_PANE.getChildren().remove(currentPopUp);
            currentPopUp = null;
        }
    
    configurePopupState(false);
    }
        private void configurePopupState(boolean poppingUp) {
        POPUP_PANE_CONTAINER.setMouseTransparent(!poppingUp);
        POPUP_PANE_CONTAINER.setVisible(poppingUp);
        BACKGROUND.setDisable(poppingUp);
    }
}
