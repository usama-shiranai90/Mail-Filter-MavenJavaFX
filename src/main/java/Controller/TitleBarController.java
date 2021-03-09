package Controller;

import Application.PreLoadingWelcomeStartUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleBarController implements Initializable {
    @FXML
    private AnchorPane titleBar;

    @FXML
    private ImageView closeButton, minimizeButton, closeButtonFilled, minimizeButtonFilled;

    private double xOffset = 0;  // ima gonna use this for moving it towards left or right
    private double yOffset = 0;  //gonna use this for moving it towards up  up and down.

    @FXML
    private void titleBarClicked(MouseEvent event) {

    }

    // screen > scene , scene insdide screen.
    @FXML
    private void titleBarPressed(MouseEvent event) {
        xOffset = event.getSceneX();  // scene is obv inside screen so , we are gonna use getScene--> the offset value of x-axis
        yOffset = event.getSceneY();

    }

    @FXML
    private void titleBarDragged(MouseEvent event) throws IOException {
        //Makes the title bar movable when dragging it.
        if (PreLoadingWelcomeStartUp.getMainStage() != null && PreLoadingWelcomeStartUp.getMainStage().isShowing()) {
            Stage stage = PreLoadingWelcomeStartUp.getMainStage();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
//            System.out.println("PreLoadingWelcomeStartUp.getMainStage().getTitle() = " + PreLoadingWelcomeStartUp.getMainStage().getTitle());

        } else if (UserLoginController.getMainStage() != null && UserLoginController.getMainStage().isShowing()) {
            Stage stage = UserLoginController.getMainStage();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
//            System.out.println("UserLoginController.getMainStage().getTitle() = " + UserLoginController.getMainStage().getTitle());
        }

    }

    @FXML
    private void closeButtonClicked(MouseEvent event) {
        System.exit(0);
    }

    //Adds highlighting effect for close button at top right.
    @FXML
    private void closeButtonEntered(MouseEvent event) {
        closeButtonFilled.setVisible(true);
    }

    @FXML
    public void closeButtonExited(MouseEvent event) {
        closeButtonFilled.setVisible(false);
    }

    @FXML
    private void minimizeButtonClicked(MouseEvent event) {
        if (PreLoadingWelcomeStartUp.getMainStage() != null && PreLoadingWelcomeStartUp.getMainStage().isShowing()) {
            Stage stage = PreLoadingWelcomeStartUp.getMainStage();
            stage.setIconified(true);
        } else if (UserLoginController.getMainStage() != null && UserLoginController.getMainStage().isShowing()) {
            Stage stage = UserLoginController.getMainStage();
            stage.setIconified(true);
        }
    }

    //Adds highlighting effect for minimize button at top right.
    @FXML
    private void minimizeButtonEntered(MouseEvent event) {
        minimizeButtonFilled.setVisible(true);
    }

    @FXML
    public void minimizeButtonExited(MouseEvent event) {
        minimizeButtonFilled.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
