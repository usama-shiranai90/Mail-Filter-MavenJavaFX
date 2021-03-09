package Utility;

import Application.PreLoadingWelcomeStartUp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Objects;

public class MessageDialog {


    private String dialogHeading;
    private String dialog;


    public void erorrmessageBox(Stage stage , String errorHeader, String errorMessage ) {
        JFXDialogLayout content = new JFXDialogLayout();

        content.setBody();
        Text text = new Text(errorHeader);
        text.setFont(Font.font ("Century Gothic", 16 ));
        if (errorHeader.equalsIgnoreCase("successful")){
            text.setFill(Color.DARKCYAN);
        }
        else{
            text.setFill(Color.RED);
        }


        content.setHeading(text);

        Text bodyText = new Text(errorMessage);
        bodyText.setFont(Font.font ("HoloLens MDL2 Assets", 14));
        content.setBody(bodyText);
        StackPane stackpane = new StackPane();

//        content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/Frontend/style.css")).toExternalForm());
//        content.getStylesheets().add(String.valueOf(new File("src/main/java/Frontend/style.css").toURI().toURL()));

        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Okay");
        button.setStyle("-fx-background-color: #bfbfbf");
        button.setFont(Font.font ("HoloLens MDL2 Assets", 10));
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
    }

    protected void showSelection(Path selectedPath) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("File Selection");
        alert.setContentText(selectedPath.toString());
        alert.show();
    }
}
