package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class MainClassDLink extends Application {
    private static final String BOOTSTRAP_PREFIX = "http://getbootstrap.com/components/#";

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        setPrimaryStage(stage);
        setPrimaryScene(scene);
//        FXMLLoader loader = new FXMLLoader(new File("src/main/java/View/MailFilter.fxml").toURI().toURL());
//        Parent root = loader.load();
        FXMLLoader root = new FXMLLoader(new File("src/main/java/View/MailFilter.fxml").toURI().toURL());
        AnchorPane pane = (AnchorPane) root.load();
        scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setPrimaryStage(Stage stage) {
        MainClassDLink.stage = stage;
    }

    public static Stage getMainStage() {
        return MainClassDLink.stage;
    }

    private void setPrimaryScene(Scene scene) {
        MainClassDLink.scene = scene;
    }

    public static Scene getMainScene() {
        return MainClassDLink.scene;
    }
}



