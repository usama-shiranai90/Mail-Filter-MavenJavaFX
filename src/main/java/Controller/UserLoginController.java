package Controller;

import Application.PreLoadingWelcomeStartUp;
import DBProxy.Database;
import DBProxy.DatabaseProxy;
import Utility.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private Button signinButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static Stage stage;
    private boolean loginAuthFlag;

    @FXML
    void onSignClick(MouseEvent event) {
        Database database = new DatabaseProxy();
        try {
            stage = new Stage();


            loginAuthFlag = database.authentication(usernameField.getText(), passwordField.getText());
            System.out.println("loginAuthFlag = " + loginAuthFlag);
            if (loginAuthFlag) {

                Utilities uti =  new Utilities();
                uti.erorrmessageBox(stage, "You have entered wrong-Credential or MAC address found of other system..");


            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/View/MailFilter.fxml").toURI().toURL());
                Parent parent = fxmlLoader.load();
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

    public static Stage getMainStage() {
        return stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    private void showSelection() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error, No selection");
        alert.setContentText("You have entered wrong-Credential or MAC address found of other system.");
        alert.show();
    }
}
