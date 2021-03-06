package Application;

import Controller.WelcomeLogin;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class PreloadingWelcome extends Preloader{

    private Stage preloaderStage;
    private Scene scene;


    @Override
    public void init() throws Exception {

        FXMLLoader root = new FXMLLoader(new File("src/main/java/View/Welcome.fxml").toURI().toURL());
        AnchorPane pane = (AnchorPane) root.load();
         scene = new Scene(pane);

    }

    @Override
    public void start(Stage stage) {
        this.preloaderStage = stage;
        // Set preloader scene and show stage.
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification notification) {

        if (notification instanceof ProgressNotification) {
//            System.out.println("Value@ :" + ((ProgressNotification) notification).getProgress());
            WelcomeLogin.statProgressBar.setProgress(((ProgressNotification) notification).getProgress());
        }
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {

            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
//                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }
    }
}
