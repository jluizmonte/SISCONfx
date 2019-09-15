package siscon.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.InputEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import siscon.model.ClienteModel;
import siscon.model.ProdutoModel;
import siscon.model.SessaoUsuarioModel;
import siscon.model.UsuarioModel;
import siscon.service.ClienteService;
import siscon.service.ProdutoService;
import siscon.service.UsuarioService;
import siscon.util.MascaraDatas;
import utilidades.Mascaras;

/**
 *
 * @author joseluiz
 */
public class PdvController implements Initializable {

    ProdutoModel produtoModel = new ProdutoModel();
    ProdutoService produtoService = new ProdutoService();
    ArrayList<ProdutoModel> listaProdutoModel = new ArrayList<>();
    UsuarioModel usuarioModel = new UsuarioModel();
    UsuarioService usuarioService = new UsuarioService();
    ArrayList<UsuarioModel> listaUsuarioModel = new ArrayList<>();
    ClienteModel clienteModel = new ClienteModel();
    ClienteService clienteService = new ClienteService();
    ArrayList<ClienteModel> listaClienteModel = new ArrayList<>();
    SessaoUsuarioModel sessaoUsuarioModel = new SessaoUsuarioModel();
    int quantidade;
    String produtoPesquisado;
    MascaraDatas blDatas = new MascaraDatas();
    Mascaras bLMascaras = new Mascaras();

    @FXML
    private TableColumn<ProdutoModel, String> colunaItem;

    @FXML
    private TableColumn<ProdutoModel, String> colunaCodigoBarras;

    @FXML
    private TableColumn<ProdutoModel, String> colunaDescricaoProduto;

    @FXML
    private TableColumn<ProdutoModel, String> colunaQuantidade;

    @FXML
    private TableColumn<ProdutoModel, String> colunaValorTotal;

    @FXML
    private TableColumn<ProdutoModel, String> colunaValorUnitario;

    @FXML
    private TableView<ProdutoModel> jtProdutoPdv;

    @FXML
    private TextField jtfValorPagar;

    @FXML
    private TextField jtfQuantidadeItens;

    @FXML
    private TextField jtfValorTotal;

    @FXML
    private TextField jtfValorRecebido;

    @FXML
    private Label lblFormaPagamento;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblOperador;

    @FXML
    private TextField jtfDescricao;

    @FXML
    public void handleSair() {
        System.exit(0);
    }

    @FXML
    public void handlePausarPdv() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/siscon/view/PausaPdvView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("PDV");
        stage.setAlwaysOnTop(true);
        stage.show();;
    }

    @FXML
    public void handleAlterarQuantidadeProduto() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Quantidade do produto: ");

        Optional<String> resultado = dialog.showAndWait();

        resultado.ifPresent((String name) -> {
            quantidade = Integer.parseInt(name);
        });
    }

    @FXML
    public void handleCancelar() {
        limparCampos();
        lblStatus.setText("LIVRE");
        lblStatus.setTextFill(Color.web("#008000"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleCancelar();
        lblOperador.setText(" " + SessaoUsuarioModel.codigoUsuario + " - " + SessaoUsuarioModel.nomeUsuario);
    }

    public void limparCampos() {
        jtfQuantidadeItens.setText("");
        jtfValorPagar.setText("");
        jtfValorRecebido.setText("");
        jtfValorTotal.setText("");
    }
}
