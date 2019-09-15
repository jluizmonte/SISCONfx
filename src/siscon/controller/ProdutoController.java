package siscon.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import siscon.model.ProdutoModel;
import siscon.service.ProdutoService;
import siscon.util.Formatador;
import siscon.util.MascaraDatas;

/**
 *
 * @author jluiz
 */
public class ProdutoController implements Initializable {

    MascaraDatas mascaraDatas = new MascaraDatas();
    ProdutoModel produtoModel = new ProdutoModel();
    ProdutoService produtoService = new ProdutoService();
    ArrayList<ProdutoModel> listaProdutoModel = new ArrayList<>();
    Formatador formatador = new Formatador();

    String alterarSalvar;

    @FXML
    private DatePicker dpDataEntrada;

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
    private Button btnSalvar;

    @FXML
    private TextField jtfDescricaoProduto;

    @FXML
    private TextField jtfCodigoBarras;

    @FXML
    private TextField jtfQuantidadeProduto;

    @FXML
    private TextArea jtfObservacao;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField jtfValorTotal;

    @FXML
    private TextField jtfValorUnitario;

    @FXML
    private Button btnNovo;

    @FXML
    public Tab tbProdutos;

    @FXML
    public void handleCancelar(ActionEvent event) {
        btnSalvar.setText("Salvar");
        limparCampos();
        desabilitarCampos(true);
        desabilitarEdicaoCampos(true);
    }

    @FXML
    public void handleNovo(ActionEvent event) {
        btnSalvar.setText("Salvar");
        alterarSalvar = "salvar";
        desabilitarCampos(false);
        desabilitarEdicaoCampos(true);
        jtfDescricaoProduto.requestFocus();
    }

    @FXML
    public void handleSalvarProduto(ActionEvent event) throws IOException {
        if (alterarSalvar.equals("salvar")) {
            this.salvarProduto();
        } else if (alterarSalvar.equals("alterar")) {
            this.alterarProduto();
        }
    }

    @FXML
    public void handleExibirDadosProduto(ActionEvent event) throws IOException {
        desabilitarCampos(false);
        desabilitarEdicaoCampos(false);

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(null);
        alerta.setHeaderText("Modo consulta: Você apenas está consultando os dados completos do produto\n"
                + "Para alterar algum dado clique no botão alterar e siga as instruções.");
        alerta.setContentText(null);

        alerta.show();
        int codigoProduto = Integer.parseInt(jtProduto.getSelectionModel().getSelectedItem().getProCodigoProduto());
        //recupera os dados do BD
        produtoModel = produtoService.getProdutoDAO(codigoProduto);
        //colocar os dados do BD na tabela
        jtfCodigoBarras.setText(produtoModel.getProCodigoBarras());
        jtfDescricaoProduto.setText(produtoModel.getProDescricao().toUpperCase());
        jtfObservacao.setText(produtoModel.getProObservacao().toUpperCase());
        jtfQuantidadeProduto.setText(String.valueOf(produtoModel.getProQuantidadeEstoque()));
        jtfValorTotal.setText(String.valueOf(produtoModel.getProValorTotal()));
        jtfValorUnitario.setText(String.valueOf(produtoModel.getProValorUnitario()));
        dpDataEntrada.setValue(LocalDate.now());

    }

    @FXML
    public void handleExcluirProduto(ActionEvent event) throws IOException {

        int codigoProduto = Integer.parseInt(jtProduto.getSelectionModel().getSelectedItem().getProCodigoProduto());
        String descricaoProduto = jtProduto.getSelectionModel().getSelectedItem().getProDescricao();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja excluir o produto: " + descricaoProduto + "?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (produtoService.excluirProdutoDAO(codigoProduto)) {

                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                alerta2.setTitle("Sucesso");
                alerta2.setHeaderText(null);
                alerta2.setContentText("Produto exlcuido com sucesso!");
                alerta2.show();
                limparCampos();
                carregarProdutos();
                jtfDescricaoProduto.requestFocus();
            } else {
                Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                alerta3.setTitle("Erro");
                alerta3.setHeaderText(null);
                alerta3.setContentText("Erro ao excluir o produto");
                alerta3.show();
                carregarProdutos();
                jtfDescricaoProduto.requestFocus();
            }
        }
    }

    @FXML
    public void handleAlterarProduto(ActionEvent event) throws IOException {

        alterarSalvar = "alterar";
        btnSalvar.setText("Alterar");
        jtfDescricaoProduto.requestFocus();
        desabilitarEdicaoCampos(true);
        desabilitarCampos(false);
        try {
            int codigoProduto = Integer.parseInt(jtProduto.getSelectionModel().getSelectedItem().getProCodigoProduto());
            //recupera os dados do BD
            produtoModel = produtoService.getProdutoDAO(codigoProduto);
            //colocar os dados do BD na tabela
            jtfCodigoBarras.setText(produtoModel.getProCodigoBarras());
            jtfDescricaoProduto.setText(produtoModel.getProDescricao().toUpperCase());
            jtfObservacao.setText(produtoModel.getProObservacao().toUpperCase());
            jtfQuantidadeProduto.setText(String.valueOf(produtoModel.getProQuantidadeEstoque()));
            jtfValorTotal.setText(String.valueOf(produtoModel.getProValorTotal()));
            jtfValorUnitario.setText(String.valueOf(produtoModel.getProValorUnitario()));
            dpDataEntrada.setValue(LocalDate.now());
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText(null);
            alerta.setContentText("Código invalido ou nenhum registro selecionado");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpDataEntrada.setValue(LocalDate.now());
        desabilitarCampos(true);
        carregarProdutos();
    }

    private void desabilitarCampos(boolean condicao) {
        jtfCodigoBarras.setDisable(condicao);
        jtfDescricaoProduto.setDisable(condicao);
        jtfQuantidadeProduto.setDisable(condicao);
        jtfValorTotal.setDisable(condicao);
        jtfValorUnitario.setDisable(condicao);
        jtfObservacao.setDisable(condicao);
        dpDataEntrada.setDisable(condicao);
        btnSalvar.setDisable(condicao);

    }

    private void desabilitarEdicaoCampos(boolean condicao) {
        jtfCodigoBarras.setEditable(condicao);
        jtfDescricaoProduto.setEditable(condicao);
        jtfQuantidadeProduto.setEditable(condicao);
        jtfValorTotal.setEditable(condicao);
        jtfValorUnitario.setEditable(condicao);
        jtfObservacao.setEditable(condicao);
        dpDataEntrada.setEditable(condicao);
    }

    private void limparCampos() {
        jtfCodigoBarras.setText("");
        jtfDescricaoProduto.setText("");
        jtfQuantidadeProduto.setText("");
        jtfValorTotal.setText("");
        jtfValorUnitario.setText("");
        jtfObservacao.setText("");
        dpDataEntrada.setValue(LocalDate.now());
    }

    private void salvarProduto() {
        LocalDate dataEntrada = dpDataEntrada.getValue();

        if (jtfObservacao.getText().equals("")) {
            jtfObservacao.setText("Nenhuma Observação.");
        }
        Double valorTotal = formatador.converterVirgulaParaPonto(this.jtfValorTotal.getText());
        Double valorUnitario = formatador.converterVirgulaParaPonto(this.jtfValorUnitario.getText());
        produtoModel.setProCodigoBarras(jtfCodigoBarras.getText());
        produtoModel.setProDataEntrada(String.valueOf(dataEntrada));
        produtoModel.setProDescricao(jtfDescricaoProduto.getText().toUpperCase());
        produtoModel.setProObservacao(jtfObservacao.getText().toUpperCase());
        produtoModel.setProQuantidadeEstoque(Integer.parseInt(jtfQuantidadeProduto.getText()));
        produtoModel.setProValorTotal(valorTotal);
        produtoModel.setProValorUnitario(valorUnitario);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja cadastrar o produto?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (produtoService.salvarProdutoDAO(produtoModel) > 0) {
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                alerta2.setTitle("Sucesso");
                alerta2.setHeaderText(null);
                alerta2.setContentText("Produto: " + produtoModel.getProDescricao() + " cadastrado com sucesso!");
                alerta2.show();
                limparCampos();
                carregarProdutos();
                jtfDescricaoProduto.requestFocus();
            } else {
                Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                alerta3.setTitle("Erro");
                alerta3.setHeaderText(null);
                alerta3.setContentText("Erro ao cadastrar o produto");
                alerta3.show();
                jtfDescricaoProduto.requestFocus();
            }
        }
    }

    private void alterarProduto() {
        LocalDate dataEntrada = dpDataEntrada.getValue();

        if (jtfObservacao.getText().equals("")) {
            jtfObservacao.setText("Nenhuma Observação.");
        }

        Double valorTotal = formatador.converterVirgulaParaPonto(this.jtfValorTotal.getText());
        Double valorUnitario = formatador.converterVirgulaParaPonto(this.jtfValorUnitario.getText());

        produtoModel.setProCodigoBarras(jtfCodigoBarras.getText().toUpperCase());
        produtoModel.setProDataEntrada(String.valueOf(dataEntrada));
        produtoModel.setProDescricao(jtfDescricaoProduto.getText().toUpperCase());
        produtoModel.setProObservacao(jtfObservacao.getText().toUpperCase());
        produtoModel.setProQuantidadeEstoque(Integer.parseInt(jtfQuantidadeProduto.getText()));
        produtoModel.setProValorTotal(valorTotal);
        produtoModel.setProValorUnitario(valorUnitario);
        /**
         * chama o metodo de alterar no controller salva o produto altera o nome
         * do botao salvar recarrega os produtos exibidos na tabela limpa os
         * campos do formulario desabilita os campos do formluario se houver
         * algum erro exibe uma mensagem na tela
         */

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja alterar o produto?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            desabilitarCampos(false);
            if (produtoService.atualizarProdutoDAO(produtoModel)) {
                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
                alerta1.setTitle(null);
                alerta1.setHeaderText(null);
                alerta1.setContentText("Produto alterado com sucesso!");
                alerta1.show();
                btnSalvar.setText("Salvar");
                carregarProdutos();
                limparCampos();
                desabilitarCampos(true);
                this.btnSalvar.setText("Salvar");
            }
        } else {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle(null);
            alerta2.setHeaderText(null);
            alerta2.setContentText("Erro ao alterar o produto!");
            alerta2.show();
        }
    }

    private void carregarProdutos() {
        listaProdutoModel = produtoService.getListaProdutoDAO();
        colunaDataEntrada.setCellValueFactory(new PropertyValueFactory<>("proDataEntrada"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("proDescricao"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("proQuantidadeEstoque"));
        colunaValorTotal.setCellValueFactory(new PropertyValueFactory<>("proValorTotal"));
//        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("proCodigoBarras"));
//        colunaObservacoes.setCellValueFactory(new PropertyValueFactory<>("proObservacao"));
//        colunaValorUnitario.setCellValueFactory(new PropertyValueFactory<>("proValorUnitario"));

        colunaDataEntrada.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaDescricao.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaObservacoes.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaQuantidade.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaCodigo.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaValorTotal.setCellFactory(TextFieldTableCell.forTableColumn());
//        colunaValorUnitario.setCellFactory(TextFieldTableCell.forTableColumn());

        jtProduto.setItems(FXCollections.observableArrayList(listaProdutoModel));

    }
}
