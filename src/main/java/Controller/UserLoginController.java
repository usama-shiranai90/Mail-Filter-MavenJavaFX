package Controller;

import Application.PreLoadingWelcomeStartUp;
import DBProxy.Database;
import DBProxy.DatabaseProxy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    void onSignClick(MouseEvent event) {
        Database database = new DatabaseProxy();
        try {
            stage = new Stage();
            database.authentication(usernameField.getText(), passwordField.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/View/MailFilter.fxml").toURI().toURL());
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            stage.setTitle("Mail-Filter");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            PreLoadingWelcomeStartUp.close();
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Stage getMainStage()
    {
        return stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
