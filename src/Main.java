import br.com.supremeforever.suprememdiwindow.MDICanvas;
import br.com.supremeforever.suprememdiwindow.MDIWindow;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import siscon.controller.LoginController;

/**
 *
 * @author joseluiz
 */
public class Main extends Application {

    int i = 1;

    @Override
    public void start(Stage primaryStage) {
        Stage stage = new Stage();

        MDICanvas canvas = new MDICanvas();
        canvas.setPrefSize(5000, 5000);
        Button b = new Button("Cliente");
        b.setOnAction((ActionEvent event) -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/siscon/view/ClienteView.fxml"));
                MDIWindow mDIWindow = new MDIWindow("myId" + i, new ImageView(), "Cliente", root);
                mDIWindow.setCenter(root);
                canvas.addMDIWindow(mDIWindow);

                i++;
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox box = new VBox(b, canvas);
        AnchorPane.setBottomAnchor(box, 0d);
        AnchorPane.setTopAnchor(box, 0d);
        AnchorPane.setLeftAnchor(box, 0d);
        AnchorPane.setRightAnchor(box, 0d);

        AnchorPane pane = new AnchorPane(box);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
