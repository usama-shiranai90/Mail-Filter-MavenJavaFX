package Controller;

import Application.PreLoadingWelcomeStartUp;
import DBProxy.Database;
import DBProxy.DatabaseProxy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
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

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error, No selection"));

                content.setBody(new Text("You have entered wrong-Credential or MAC address found of other system.."));
                StackPane stackpane = new StackPane();
                JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
                JFXButton button = new JFXButton("Okay");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();
                        stage.close();
                    }
                });
                content.setActions(button);
                Scene scene = new Scene(stackpane);
                stage.setTitle("Error-Message");
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(PreLoadingWelcomeStartUp.getMainStage());
                stage.centerOnScreen();
                dialog.show();
                stage.show();

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
