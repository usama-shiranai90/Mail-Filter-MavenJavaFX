package Application;


import Controller.WelcomeLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class MainClassWithTransition extends Application {

    private Stage primaryStage;
    private static Stage stageStatic;  // is use for calling the titleBar class.

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        executeWelcome();
    }

    public void executeWelcome() {

        try {
            FXMLLoader root = new FXMLLoader(new File("src/main/java/View/Welcome.fxml").toURI().toURL());
            AnchorPane pane = (AnchorPane) root.load();
            Scene scene = new Scene(pane);
            WelcomeLogin welcomeLogin = root.getController();
            welcomeLogin.main(primaryStage, this);

            primaryStage.setTitle("Welcome");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginPage(Stage stage) throws IOException, InterruptedException {
        stageStatic = primaryStage;
        FXMLLoader root = new FXMLLoader(new File("src/main/java/View/MailFilter.fxml").toURI().toURL());
        AnchorPane pane = (AnchorPane) root.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    public static Stage getMainStage()
    {
        return stageStatic;
    }

    public void close() {
        primaryStage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}





/*
* --add-modules
javafx.base,javafx.controls,javafx.fxml,javafx.graphics
--add-opens
javafx.graphics/javafx.css=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.runtime=ALL-UNNAMED
--add-opens
javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED
--add-opens
javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.binding=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.event=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED
--add-exports
javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix
--add-exports
javafx.controls/com.sun.javafx.scene.control=com.jfoenix
--add-exports
javafx.base/com.sun.javafx.binding=com.jfoenix
--add-exports
javafx.base/com.sun.javafx.event=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.stage=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.scene=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.scene.text=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.geom=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.util=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.scene.traversal=com.jfoenix*/