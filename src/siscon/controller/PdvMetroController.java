package siscon.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
public class PdvMetroController implements Initializable {

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
    private MenuItem jmiExcluirProduto;

    @FXML
    private MenuItem jmiAlterarQuantidade;

    @FXML
    private MenuItem jmiSobre;

    @FXML
    private TextField jtfDescricao;

    @FXML
    private TextField jtfQuantidade;

    @FXML
    private MenuItem jmiFecharCaixa;

    @FXML
    private Label lblStatus;

    @FXML
    private MenuItem jmiInserirProduto;

    @FXML
    private Label lblOperador;

    @FXML
    private MenuItem jmiPesquisarPoduto;

    @FXML
    private TextField jtfValorSubTotal;

    @FXML
    private MenuItem jmiFinalizarVenda;

    @FXML
    private Label lblDataHora;

    @FXML
    private MenuItem jmiCancelar;

    @FXML
    private TextField jtfValorTotal;

    @FXML
    private TextField jtfValorUnitario;

    @FXML
    private MenuItem jmiSair;

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
        jtfDescricao.requestFocus();
        lblStatus.setText("OCUPADO");
        lblStatus.setTextFill(Color.web("#FF0000"));

        setarHoraAtual();
        setarOperador();
    }

    public void setarHoraAtual() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        LocalDateTime timepoint = LocalDateTime.now();
        lblDataHora.setText(timepoint.format(fmt));
    }

    public void setarOperador() {
        lblOperador.setText(" " + SessaoUsuarioModel.codigoUsuario + " - " + SessaoUsuarioModel.nomeUsuario);
    }

    public void limparCampos() {
        jtfDescricao.setText("");
        jtfQuantidade.setText("");
        jtfValorSubTotal.setText("");
        jtfValorTotal.setText("");
        jtfValorUnitario.setText("");
    }

}
