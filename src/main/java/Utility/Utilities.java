package Utility;

import Application.PreLoadingWelcomeStartUp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Utilities {


    private String dialogHeading ;
    private String dialog ;


    public void erorrmessageBox(Stage stage, String errorMessage){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Error, No selection"));

        content.setBody(new Text(errorMessage));
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
    }
}
