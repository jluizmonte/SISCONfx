package siscon.controller;

import br.com.supremeforever.suprememdiwindow.MDICanvas;
import br.com.supremeforever.suprememdiwindow.MDIWindow;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import siscon.model.SessaoUsuarioModel;
import siscon.model.UsuarioModel;
import siscon.service.UsuarioService;

/**
 *
 * @author jluiz
 */
public class LoginController implements Initializable {

    int i = 1;

    @FXML
    private GridPane painel;

    @FXML
    private TextField jtfLogin;

    @FXML
    private PasswordField jtfSenha;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnEntrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void handleEntrar(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        UsuarioModel usuarioModel = new UsuarioModel();
        UsuarioService usuarioService = new UsuarioService();

        usuarioModel.setUsuLogin(jtfLogin.getText());
        usuarioModel.setUsuSenha(jtfSenha.getText());
        if (usuarioService.getValidarUsuarioDAO(usuarioModel)) {

            usuarioModel = usuarioService.getUsuarioDAO(jtfLogin.getText());

            SessaoUsuarioModel.codigoUsuario = usuarioModel.getIdUsuario();
            SessaoUsuarioModel.nomeUsuario = usuarioModel.getUsuNome();
            SessaoUsuarioModel.loginUsuario = usuarioModel.getUsuLogin();

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PrincipalView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Menu principal");
            stage.setMaximized(true);
            stage.setResizable(true);
            stage.show();
            fechaTela();
//            exibirTelaPrincipal();
            fechaTela();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao fazer login");
            alert.setHeaderText(null);
            alert.setContentText("Revise as informações inseridas e tente novamente!");
            alert.show();
            jtfLogin.requestFocus();
        }

    }

    public void fechaTela() throws IOException {
        Stage stage = (Stage) btnEntrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleProximoCampo() throws IOException {
        jtfSenha.requestFocus();
    }

    @FXML
    public void handleSair() throws IOException {
        System.exit(0);
    }

    private void exibirTelaPrincipal() {
        Stage stage = new Stage();
        MDICanvas canvas = new MDICanvas();
        canvas.setId("Teste");
        canvas.setPrefSize(5000, 5000);
        Button btnCliente = new Button("+");
        Button btnProdutos = new Button();
        btnCliente.setOnAction((ActionEvent event) -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/siscon/view/ClienteView.fxml"));
                MDIWindow mDIWindow = new MDIWindow("myId" + i, new ImageView(), "Cliente" + i, root);
                mDIWindow.setCenter(root);

                canvas.addMDIWindow(mDIWindow);

                i++;
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox box = new VBox(btnCliente, canvas);
        AnchorPane.setBottomAnchor(box, 0d);
        AnchorPane.setTopAnchor(box, 0d);
        AnchorPane.setLeftAnchor(box, 0d);
        AnchorPane.setRightAnchor(box, 0d);

        AnchorPane pane = new AnchorPane(box);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }
}
