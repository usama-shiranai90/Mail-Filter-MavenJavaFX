package Application;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;

public class PreLoadingWelcomeStartUp extends Application {

    private Stage primaryStage;
    private static Stage stageStatic;  // is use for calling the titleBar class.
    private static final int COUNT_LIMIT = 10;


    @Override
    public void start(Stage stage) throws Exception {
        stageStatic = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/View/LoginPage.fxml").toURI().toURL());
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Mail-Filter");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {

        // Perform some heavy lifting ( progress , Even Database startup or other things. )
        for (int i = 1; i <= COUNT_LIMIT; i++) {
            double progress = (double) i / 10;
            System.out.println("progress: " + progress);
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) {

        LauncherImpl.launchApplication(PreLoadingWelcomeStartUp.class, PreloadingWelcome.class, args);
    }

    public static Stage getMainStage()
    {
        return stageStatic;
    }

    public static void close() {
        stageStatic.close();
    }
}
