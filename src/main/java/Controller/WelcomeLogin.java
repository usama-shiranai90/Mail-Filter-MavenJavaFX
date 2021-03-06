package Controller;

import Application.MainClassWithTransition;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeLogin implements Initializable {



    @FXML
    private AnchorPane rootpane;

    MainClassWithTransition main;
    Stage stage;

    @FXML
    private JFXProgressBar progressbar;
    public static ProgressBar statProgressBar;

    public WelcomeLogin() {
    }

    // use to pass the stage and main class instance For-Class --> MainClassDLink ,MainClassWithTransition
    public void main(Stage s, MainClassWithTransition m) {
        main = m;
        stage = s;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statProgressBar = progressbar;

//        JFXSpinner
/*       Change the implementation based on PreloadingWelcome Class and PreloadingWelcomeStartUp Class.

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(6), rootpane);
        fadeTransition1.setFromValue(1);
        fadeTransition1.setToValue(1);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event5 -> {
            main.close();
//            System.out.println("------- splash screen is closed --------");
            try {
                main.loginPage(stage);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });*/


    }


}
