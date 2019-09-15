package siscon.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import siscon.model.UsuarioModel;
import siscon.service.UsuarioService;

/**
 *
 * @author jluiz
 */
public class UsuarioController implements Initializable {

    UsuarioModel usuarioModel = new UsuarioModel();
    UsuarioService usuarioService = new UsuarioService();
    ArrayList<UsuarioModel> listaUsuarioModel = new ArrayList<>();
    String alterarSalvar;

    @FXML
    private TextField jtfNomeUsuario;

    @FXML
    private TextField jtfLoginUsuario;

    @FXML
    private TextField jtfSenhaUsuario;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableView<UsuarioModel> jtUsuario;

    @FXML
    private TableColumn<UsuarioModel, Integer> colunaId;

    @FXML
    private TableColumn<UsuarioModel, String> colunaNome;

    @FXML
    private TableColumn<UsuarioModel, String> colunaLogin;

    @FXML
    private TableColumn<UsuarioModel, String> colunaSenha;

    @FXML
    public void handleCancelar(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @FXML
    public void handleSair(ActionEvent event) throws IOException {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Encerrando o sistema...");
        alerta.setHeaderText("Sair?");
        alerta.setContentText("Deseja encerrar o sistema?");
        alerta.show();
        System.exit(0);
    }

    @FXML
    public void handleSalvarUsuario(ActionEvent event) throws IOException {
        usuarioModel.setUsuLogin(jtfLoginUsuario.getText());
        usuarioModel.setUsuNome(jtfNomeUsuario.getText().toUpperCase());
        usuarioModel.setUsuSenha(jtfSenhaUsuario.getText());

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja cadastrar o usuário?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            /**
             * chama o metodo de salvar no controller salva o usuario altera o
             * nome do botao salvar recarrega os usuarios exibidos na tabela
             * limpa os campos do formulario desabilita os campos do formluario
             * se houver algum erro exibe uma mensagem na tela
             */
            if (jtfNomeUsuario.getText().equals("") || jtfLoginUsuario.getText().equals("") || jtfSenhaUsuario.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Nenhum campo pode ficar em branco!");
                alert.show();
                jtfNomeUsuario.requestFocus();
            } else {
                if (usuarioService.salvarUsuarioDAO(usuarioModel) > 0) {
                    Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                    alerta2.setTitle("Sucesso");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("Usuário: " + usuarioModel.getUsuNome() + " cadastrado com sucesso!");
                    alerta2.show();
                    limparCampos();
                    carregarUsuarios();
                    jtfNomeUsuario.requestFocus();
                } else {
                    Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                    alerta3.setTitle("Erro");
                    alerta3.setHeaderText(null);
                    alerta3.setContentText("Erro ao cadastrar o usuário");
                    alerta3.show();
                    carregarUsuarios();
                    jtfNomeUsuario.requestFocus();
                }
            }
        }
    }

    @FXML
    public void handleExcluirUsuario(ActionEvent event) throws IOException {
        int codigoUsuario = jtUsuario.getSelectionModel().getSelectedItem().getIdUsuario();
        String nomeUsuario = jtUsuario.getSelectionModel().getSelectedItem().getUsuNome();
        String qtdeUsuario = String.valueOf(usuarioService.getUltimoCodigo().getUsuQuantidade());

        if (qtdeUsuario.equals(1)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText(null);
            alerta.setContentText("Há apenas um usuário cadastrado, não é possível excluir pois perderá o acesso ao sistema");
        } else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle(null);
            alerta.setHeaderText(null);
            alerta.setContentText("Deseja excluir o usuário: " + nomeUsuario + "?");

            Optional<ButtonType> result = alerta.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (usuarioService.excluirUsuarioDAO(codigoUsuario)) {

                    Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                    alerta2.setTitle("Sucesso");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("Usuário exlcuido com sucesso!");
                    alerta2.show();
                    limparCampos();
                    carregarUsuarios();
                    jtfNomeUsuario.requestFocus();
                } else {
                    Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                    alerta3.setTitle("Erro");
                    alerta3.setHeaderText(null);
                    alerta3.setContentText("Erro ao excluir o usuário");
                    alerta3.show();
                    carregarUsuarios();
                    jtfNomeUsuario.requestFocus();
                }
            }
        }
    }

    @FXML
    public void handleProximoCampoLogin(ActionEvent event) throws IOException {
        jtfLoginUsuario.requestFocus();
    }

    @FXML
    public void handleProximoCampoSenha(ActionEvent event) throws IOException {
        jtfSenhaUsuario.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jtfNomeUsuario.requestFocus();
        carregarUsuarios();
    }

    public void carregarUsuarios() {
        listaUsuarioModel = usuarioService.getListaUsuarioDAO();
        colunaLogin.setCellValueFactory(new PropertyValueFactory<>("usuLogin"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("usuNome"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("usuSenha"));

        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaSenha.setCellFactory(TextFieldTableCell.forTableColumn());
        jtUsuario.setItems(FXCollections.observableArrayList(listaUsuarioModel));
    }

    public void limparCampos() {
        jtfLoginUsuario.setText("");
        jtfNomeUsuario.setText("");
        jtfSenhaUsuario.setText("");
    }

}
