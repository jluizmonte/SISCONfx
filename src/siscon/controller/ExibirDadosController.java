package siscon.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import siscon.model.FornecedorModel;
import siscon.model.ProdutoModel;
import siscon.service.FornecedorService;
import siscon.service.ProdutoService;

/**
 *
 * @author joseluiz
 */
public class ExibirDadosController implements Initializable {

    ProdutoModel produtoModel = new ProdutoModel();
    ProdutoService produtoService = new ProdutoService();
    ArrayList<ProdutoModel> listaProdutoModel = new ArrayList<>();

    FornecedorModel fornecedorModel = new FornecedorModel();
    FornecedorService fornecedorService = new FornecedorService();
    ArrayList<FornecedorModel> listaFornecedorModel = new ArrayList<>();

    @FXML
    private TableColumn<ProdutoModel, String> colunaQuantidade;

    @FXML
    private TableColumn<ProdutoModel, String> colunaValorTotal;

    @FXML
    private TableColumn<ProdutoModel, String> colunaDescricao;

    @FXML
    private TableColumn<ProdutoModel, String> colunaObservacoes;

    @FXML
    private TableColumn<ProdutoModel, String> colunaValorUnitario;

    @FXML
    private TableColumn<ProdutoModel, String> colunaCodigo;

    @FXML
    private TableColumn<ProdutoModel, String> colunaDataEntrada;

    @FXML
    private TableView<ProdutoModel> jtProduto;

    @FXML
    private TableColumn<FornecedorModel, String> colunaNomeFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaCepFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaNumeroFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaTelefoneFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaEstadoFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaBairroFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaCidadeFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaRuaFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaEmailFornecedor;

    @FXML
    private TableColumn<FornecedorModel, String> colunaCelularFornecedor;

    @FXML
    private TableView<FornecedorModel> jtFornecedor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarProdutos();
        carregarFornecedor();
    }

    private void carregarProdutos() {
        listaProdutoModel = produtoService.getListaProdutoDAO();
        colunaDataEntrada.setCellValueFactory(new PropertyValueFactory<>("proDataEntrada"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("proDescricao"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("proQuantidadeEstoque"));
        colunaValorTotal.setCellValueFactory(new PropertyValueFactory<>("proValorTotal"));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("proCodigoBarras"));
        colunaObservacoes.setCellValueFactory(new PropertyValueFactory<>("proObservacao"));
        colunaValorUnitario.setCellValueFactory(new PropertyValueFactory<>("proValorUnitario"));

        colunaDataEntrada.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaDescricao.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaObservacoes.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaQuantidade.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaCodigo.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaValorTotal.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaValorUnitario.setCellFactory(TextFieldTableCell.forTableColumn());

        jtProduto.setItems(FXCollections.observableArrayList(listaProdutoModel));

    }

    public void carregarFornecedor() {
        listaFornecedorModel = fornecedorService.getListaFornecedorDAO();

        colunaNomeFornecedor.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
        colunaTelefoneFornecedor.setCellValueFactory(new PropertyValueFactory<>("telefoneFornecedor"));
        colunaBairroFornecedor.setCellValueFactory(new PropertyValueFactory<>("bairroFornecedor"));
        colunaCelularFornecedor.setCellValueFactory(new PropertyValueFactory<>("celularFornecedor"));
        colunaCepFornecedor.setCellValueFactory(new PropertyValueFactory<>("cepFornecedor"));
        colunaCidadeFornecedor.setCellValueFactory(new PropertyValueFactory<>("cidadeFornecedor"));
        colunaEmailFornecedor.setCellValueFactory(new PropertyValueFactory<>("emailFornecedor"));
        colunaEstadoFornecedor.setCellValueFactory(new PropertyValueFactory<>("ufFornecedor"));
        colunaNomeFornecedor.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
        colunaNumeroFornecedor.setCellValueFactory(new PropertyValueFactory<>("numeroFornecedor"));
        colunaRuaFornecedor.setCellValueFactory(new PropertyValueFactory<>("ruaFornecedor"));
        colunaTelefoneFornecedor.setCellValueFactory(new PropertyValueFactory<>("telefoneFornecedor"));

        colunaNomeFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaTelefoneFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaBairroFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaCelularFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaCepFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaCidadeFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaEmailFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaEstadoFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaNomeFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaNumeroFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaRuaFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaTelefoneFornecedor.setCellFactory(TextFieldTableCell.forTableColumn());

        jtFornecedor.setItems(FXCollections.observableArrayList(listaFornecedorModel));
    }
}
