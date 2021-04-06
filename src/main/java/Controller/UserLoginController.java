package Controller;

import Application.PreLoadingWelcomeStartUp;
import DBProxy.Database;
import DBProxy.DatabaseProxy;
import Utility.MessageDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginController implements Initializable {

    @FXML
    private Label lblErrors;
    @FXML
    private Hyperlink signError;
    @FXML
    private Button signinButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static Stage stage;
    private boolean loginAuthFlag;


    @FXML
    private Button btnSignup;

    @FXML
    void onSignClick(MouseEvent event) {
        Database database = new DatabaseProxy();
        try {
            stage = new Stage();

            loginAuthFlag = database.authentication(usernameField.getText(), passwordField.getText());
            System.out.println("loginAuthFlag = " + loginAuthFlag);
            if (loginAuthFlag) {

                MessageDialog uti = new MessageDialog();
                uti.erorrmessageBox(stage,"Credentials issue ", "You have entered wrong-Credential ..");


            } else {
//                FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/View/MailFilter.fxml").toURI().toURL());
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MailFilter.fxml"));

                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                stage.setTitle("Mail-Filter");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                PreLoadingWelcomeStartUp.close();
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void signUpClickButtonAction(MouseEvent event) {
        signError.setText("Visit fudscams.com");
        signError.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.fudscams.com/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static Stage getMainStage() {
        return stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        signError.setText("");
    }


    private void showSelection() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error, No selection");
        alert.setContentText("You have entered wrong-Credential or MAC address found of other system.");
        alert.show();
    }
}
