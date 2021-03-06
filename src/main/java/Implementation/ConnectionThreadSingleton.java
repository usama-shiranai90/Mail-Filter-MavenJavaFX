package Implementation;

import Controller.UserLoginController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class ConnectionThreadSingleton {

    private int connectionTries = 0;
    private static ConnectionThreadSingleton instance;
    private URL url;
    private URLConnection urlConnection;
    private boolean connectionWindowIsOpen = false;
    Stage stage;

    private ConnectionThreadSingleton() {
        establishedConnection();
    }

    public void establishedConnection() {

        while (true){
            try {
                String httpStartup = "http://";
                String domainName = "google.com";
                url = new URL(httpStartup + domainName);
                urlConnection = url.openConnection();
                urlConnection.connect();
//                System.out.println("Connection maintained" + "  ,  Thread name : " + Thread.currentThread().getName() + Thread.currentThread().getId());
                TimeUnit.SECONDS.sleep(3);
                if (connectionTries > 0) {
                    connectionTries = 0;
                    FilterChecks.connectionLost = false;
                    connectionWindowIsOpen = false;
                    Platform.runLater(() -> stage.close());
                }

            }
            catch (IOException | InterruptedException e) {
                System.out.println("Internet not connected");
                FilterChecks.connectionLost = true;
                if(!connectionWindowIsOpen){
                    reEstablishingNetwork();
                }
                else {
                    closeIt();
                }
            }
        }
    }

    synchronized public void reEstablishingNetwork() {

        if (connectionTries == 3) {
            System.out.println("Exiting");
            System.exit(1);
        }

        if(!connectionWindowIsOpen) {
            Platform.setImplicitExit(false);
            Platform.runLater(() -> {
                stage = new Stage();
                Parent root;
                FXMLLoader loader = null;
                try {
                    loader = new FXMLLoader(new File("src/main/java/View/ConnectionDialogue.fxml").toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    root = loader.load();
                    stage.setTitle("Connection Lost");
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(UserLoginController.getMainStage());
                    stage.centerOnScreen();
                    connectionWindowIsOpen = true;
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }

        System.out.println("Re-establishing Network....");
        connectionTries++;

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        establishedConnection();
    }

    private void closeIt(){
        if (connectionTries == 3) {
            System.out.println("Exiting");
            System.exit(1);
        }

        System.out.println("Re-establishing Network....");
        connectionTries++;

        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        establishedConnection();
    }

    public static synchronized ConnectionThreadSingleton getInstance() {
        if (instance == null) {
            return instance = new ConnectionThreadSingleton();
        }
        else
            return instance;
    }


}