package Controller;

import Implementation.ConnectionThreadSingleton;
import Implementation.EmailFilter;
import Implementation.FilterChecks;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.raumzeitfalle.fx.filechooser.FXFileChooserDialog;
import net.raumzeitfalle.fx.filechooser.Skin;
import org.controlsfx.control.Notifications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class MailController implements Initializable {


    private File file;
    @FXML
    private ComboBox<String> noOfThreadsComboBox;

    @FXML
    private javafx.scene.control.ListView<String> ListView;
    EmailFilter emailFilter = new EmailFilter();
    @FXML
    private Button popButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (noOfThreadsComboBox != null) {

            noOfThreadsComboBox.getItems().add("1");
            noOfThreadsComboBox.getItems().add("5");
            noOfThreadsComboBox.getItems().add("10");
            noOfThreadsComboBox.getItems().add("15");
        }
//    popButton.fire();
        Runnable connectionEstablishmentThread = () -> {
            ConnectionThreadSingleton connectionThreadSingleton = ConnectionThreadSingleton.getInstance();
        };
        Thread connection = new Thread(connectionEstablishmentThread);
        connection.start();
    }

    @FXML
    void popupClicked(MouseEvent event) throws IOException {
/*        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/View/ConnectionDialogue.fxml").toURI().toURL());

        root = loader.load();

        stage.setTitle("Police Station Management System");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.initOwner(popButton.getScene().getWindow());
        stage.centerOnScreen();
//        stage.setX(popButton.getScene().getWindow().getX() + 200);
//        stage.setY(popButton.getScene().getWindow().getY() + 200);
        stage.show();

        Runnable connectionEstablishmentThread = () -> {
            ConnectionThreadSingleton connectionThreadSingleton = ConnectionThreadSingleton.getInstance();
        };
        Thread connection = new Thread(connectionEstablishmentThread);
        connection.start();*/
    }

    public Window getStage() {
        return popButton.getScene().getWindow();
    }


    private void showSelection(Path selectedPath) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("File Selection");
        alert.setContentText(selectedPath.toString());
        alert.show();
    }

    @FXML
    void OnClick(MouseEvent event) throws IOException {
//        JFileChooser chooser = new JFileChooser();

        try {
            FXFileChooserDialog dialog = FXFileChooserDialog.create(Skin.DEFAULT);
            dialog.showOpenDialog(null);
            file = dialog.getResult().toFile();
            String filename = file.getAbsolutePath();
            String filePath = file.getPath();
            FilterChecks.FILENAME = filename;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(filename);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String ref;
                while ((ref = bufferedReader.readLine()) != null) {
                    ListView.getItems().add(ref);
                }


            } catch (IOException e) {
                System.out.println("Sorry....");
            }
        } catch (NullPointerException e) {

        }


    }


    int hopCounter = 0;

    @FXML
    void generateFilter(MouseEvent event) {
        int totalEmails = 0;
        totalEmails = emailFilter.totalNumberOfEmails();

        Runnable runnable = () -> emailFilter.filterThemAll(hopCounter++);
        System.out.println("totalEmails = " + totalEmails);  // 5..
        Thread[] filterThreads = new Thread[totalEmails];

        if (totalEmails <= FilterChecks.selectedThreadNumber) { // 15

            if (totalEmails == 1) {
                filterThreads[0] = new Thread(runnable);
                filterThreads[0].start();
            } else {
                for (int i = 0; i < 2; i++) {
                    filterThreads[i] = new Thread(runnable);
                    filterThreads[i].start();
                }
            }

        } else {
            for (int i = 0; i < FilterChecks.selectedThreadNumber; i++) {
                filterThreads[i] = new Thread(runnable);
                filterThreads[i].start();
            }
        }

        notifier("Good!", "The Filter has process fined , check your directory");

    }


    private static void notifier(String pTitle, String pMessage) {
        Platform.runLater(() -> {
                    Stage owner = new Stage(StageStyle.TRANSPARENT);
                    StackPane root = new StackPane();
                    root.setStyle("-fx-background-color: TRANSPARENT");
                    Scene scene = new Scene(root, 1, 1);
                    scene.setFill(Color.TRANSPARENT);
                    owner.setScene(scene);
                    owner.setWidth(1);
                    owner.setHeight(1);
                    owner.toBack();
                    owner.show();
                    Notifications.create().title(pTitle).text(pMessage).showInformation();
                }
        );
    }


    @FXML
    void selectThreadAction(MouseEvent event) {
        System.out.println("button was pressed.");


        noOfThreadsComboBox.setOnAction(actionEvent -> {
            if (noOfThreadsComboBox.getValue() != null) {
                FilterChecks.selectedThreadNumber = Integer.parseInt(noOfThreadsComboBox.getValue());

                System.out.println("index = " + FilterChecks.selectedThreadNumber);
            } else {
                System.out.println("check value");
            }
        });


    }


}
