package siscon.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import siscon.model.SessaoUsuarioModel;
import siscon.model.UsuarioModel;
import siscon.service.UsuarioService;

/**
 * FXML Controller class
 *
 * @author Felipe Alves
 */
public class PrincipalController implements Initializable {

    UsuarioModel usuarioModel = new UsuarioModel();
    UsuarioService usuarioService = new UsuarioService();
    SessaoUsuarioModel sessaoUsuarioModel = new SessaoUsuarioModel();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblUsuario;

    @FXML
    public void handleUsuario(ActionEvent event) throws IOException {
        anchorPane.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/UsuarioView.fxml"));
        anchorPane.getChildren().add(root);
    }

    @FXML
    public void handleCliente() throws IOException{
        anchorPane.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/ClienteView.fxml"));
        anchorPane.getChildren().add(root);
    }

    @FXML
    public void handleFornecedor(ActionEvent event) throws IOException {
        anchorPane.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/FornecedorView.fxml"));
        anchorPane.getChildren().add(root);
    }

    @FXML
    public void handleProduto(ActionEvent event) throws IOException {
        anchorPane.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/ProdutoView.fxml"));
        anchorPane.getChildren().add(root);
    }

    @FXML
    public void handleExibirDados(ActionEvent event) throws IOException {
        anchorPane.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/ExibirDadosView.fxml"));
        anchorPane.getChildren().add(root);
    }

    @FXML
    public void handlePdv(ActionEvent event) throws IOException {
//        anchorPane.getChildren();
//        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PdvView.fxml"));
//        anchorPane.getChildren().add(root);
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PdvMetroView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true); //apenas teste da tela principal
        stage.setTitle("PDV");
        stage.show();
    }

    @FXML
    public void handleSair(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUsuario.setText(" " + SessaoUsuarioModel.codigoUsuario + " - " + SessaoUsuarioModel.nomeUsuario);
    }

}
