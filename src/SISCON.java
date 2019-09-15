
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jluiz
 */
public class SISCON extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/LoginView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PrincipalView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/UsuarioView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/ClienteView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/FornecedorView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/ProdutoView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/ExibirDadosView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PdvView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PdvMetroView.fxml"));

        Scene scene = new Scene(root);
//        stage.initStyle(StageStyle.TRANSPARENT); //remove a barra de título da tela de login
//        stage.setMaximized(true); //apenas teste da tela principal
//        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Produtos");
//        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
