/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import siscon.model.FornecedorModel;
import siscon.service.FornecedorService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author jluiz
 */
public class FornecedorController implements Initializable {

    FornecedorModel fornecedorModel = new FornecedorModel();
    FornecedorService fornecedorService = new FornecedorService();
    ArrayList<FornecedorModel> listaFornecedorModel = new ArrayList<>();
    String alterarSalvar;

    @FXML
    private TextField jtfCep;

    @FXML
    private TextField jtfBairro;

    @FXML
    private TextField jtfCidade;

    @FXML
    private TextField jtfTelefone;

    @FXML
    private Button btnConsulta;

    @FXML
    private TextField jtfNumero;

    @FXML
    private TableView<FornecedorModel> jtFornecedor;

    @FXML
    private TextField jtfCelular;

    @FXML
    private TextField jtfRua;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAlterar;

    @FXML
    private TableColumn<FornecedorModel, String> colunaTelefone;

    @FXML
    private ComboBox<String> jcbEstado;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField jtfNome;

    @FXML
    private TableColumn<FornecedorModel, String> colunaNome;

    @FXML
    private TextField jtfEmail;

    @FXML
    private Button btnNovo;

    public void handleNovo(ActionEvent event) {
        btnSalvar.setText("Salvar");
        alterarSalvar = "salvar";
        desabilitarCampos(false);
        desabilitarEdicaoCampos(true);
        jtfNome.requestFocus();
    }

    @FXML
    public void handleCancelar(ActionEvent event) {
        btnSalvar.setText("Salvar");
        limparCampos();
        desabilitarCampos(true);
        desabilitarEdicaoCampos(true);
    }

    @FXML
    public void handleSalvarFornecedor(ActionEvent event) {

        if (alterarSalvar.equals("salvar")) {
            this.salvarFornecedor();
        } else if (alterarSalvar.equals("alterar")) {
            this.alterarFornecedor();
        }
    }

    @FXML
    public void handleExcluirFornecedor(ActionEvent event) {
        int codigoFornecedor = jtFornecedor.getSelectionModel().getSelectedItem().getCodigoFornecedor();
        String nomeFornecedor = jtFornecedor.getSelectionModel().getSelectedItem().getNomeFornecedor();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja excluir o fornecedor: " + nomeFornecedor + "?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (fornecedorService.excluirFornecedorDAO(codigoFornecedor)) {

                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                alerta2.setTitle("Sucesso");
                alerta2.setHeaderText(null);
                alerta2.setContentText("Fornecedor exlcuido com sucesso!");
                alerta2.show();
                limparCampos();
                carregarFornecedor();
                jtfNome.requestFocus();
            } else {
                Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                alerta3.setTitle("Erro");
                alerta3.setHeaderText(null);
                alerta3.setContentText("Erro ao excluir o fornecedor");
                alerta3.show();
                carregarFornecedor();
                jtfNome.requestFocus();
            }
        }
    }

    @FXML
    public void handleExibirDadosFornecedor(ActionEvent event) {
        desabilitarCampos(false);
        desabilitarEdicaoCampos(false);

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(null);
        alerta.setHeaderText("Modo consulta: Você apenas está consultando os dados completos do fornecedor\n"
                + "Para alterar algum dado clique no botão alterar e siga as instruções.");
        alerta.setContentText(null);

        alerta.show();
        int codigoFornecedor = jtFornecedor.getSelectionModel().getSelectedItem().getCodigoFornecedor();
        //recupera os dados do BD
        fornecedorModel = fornecedorService.getFornecedorDAO(codigoFornecedor);
        //colocar os dados do BD na tabela
        jtfBairro.setText(fornecedorModel.getBairroFornecedor());
        jtfCelular.setText(fornecedorModel.getCelularFornecedor());
        jtfCep.setText(fornecedorModel.getCepFornecedor());
        jtfCidade.setText(fornecedorModel.getCidadeFornecedor());
        jtfEmail.setText(fornecedorModel.getEmailFornecedor());
        jtfNome.setText(fornecedorModel.getNomeFornecedor());
        jtfNumero.setText(fornecedorModel.getNumeroFornecedor());
        jtfRua.setText(fornecedorModel.getRuaFornecedor());
        jtfTelefone.setText(fornecedorModel.getTelefoneFornecedor());
    }

    @FXML
    public void handleAlterarFornecedor(ActionEvent event) {
        alterarSalvar = "alterar";
        btnSalvar.setText("Alterar");
        jtfNome.requestFocus();
        desabilitarEdicaoCampos(true);
        desabilitarCampos(false);
        try {
            int codigoFornecedor = jtFornecedor.getSelectionModel().getSelectedItem().getCodigoFornecedor();
            //recupera os dados do BD
            fornecedorModel = fornecedorService.getFornecedorDAO(codigoFornecedor);
            //colocar os dados do BD na tabela
            jtfBairro.setText(fornecedorModel.getBairroFornecedor());
            jtfCelular.setText(fornecedorModel.getCelularFornecedor());
            jtfCep.setText(fornecedorModel.getCepFornecedor());
            jtfCidade.setText(fornecedorModel.getCidadeFornecedor());
            jtfEmail.setText(fornecedorModel.getEmailFornecedor());
            jtfNome.setText(fornecedorModel.getNomeFornecedor());
            jtfNumero.setText(fornecedorModel.getNumeroFornecedor());
            jtfRua.setText(fornecedorModel.getRuaFornecedor());
            jtfTelefone.setText(fornecedorModel.getTelefoneFornecedor());
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(null);
            alerta.setHeaderText(null);
            alerta.setContentText("Código invalido ou nenhum registro selecionado");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        desabilitarCampos(true);
        jcbEstado.setItems(listaDeEstados);
        carregarFornecedor();
    }

    private void desabilitarCampos(boolean condicao) {
        jtfNome.setDisable(condicao);
        jtfRua.setDisable(condicao);
        jtfNumero.setDisable(condicao);
        jtfBairro.setDisable(condicao);
        jtfCidade.setDisable(condicao);
        jcbEstado.setDisable(condicao);
        jtfCep.setDisable(condicao);
        jtfTelefone.setDisable(condicao);
        jtfCelular.setDisable(condicao);
        jtfEmail.setDisable(condicao);
        btnSalvar.setDisable(condicao);

    }

    private void desabilitarEdicaoCampos(boolean condicao) {
        jtfNome.setEditable(condicao);
        jtfRua.setEditable(condicao);
        jtfNumero.setEditable(condicao);
        jtfBairro.setEditable(condicao);
        jtfCidade.setEditable(condicao);
        jcbEstado.setEditable(condicao);
        jtfCep.setEditable(condicao);
        jtfTelefone.setEditable(condicao);
        jtfCelular.setEditable(condicao);
        jtfEmail.setEditable(condicao);
        jcbEstado.setEditable(condicao);
    }

    private void limparCampos() {
        jtfNome.setText("");
        jtfBairro.setText("");
        jtfCelular.setText("");
        jtfCep.setText("");
        jtfCidade.setText("");
        jtfEmail.setText("");
        jtfEmail.setText("");
        jtfNome.setText("");
        jtfNumero.setText("");
        jtfRua.setText("");
        jtfTelefone.setText("");
    }

    public void salvarFornecedor() {
        String estado = String.valueOf(jcbEstado.getSelectionModel().getSelectedItem());

        fornecedorModel.setBairroFornecedor(jtfBairro.getText().toUpperCase());
        fornecedorModel.setCepFornecedor(jtfCep.getText());
        fornecedorModel.setCidadeFornecedor(jtfCidade.getText().toUpperCase());
        fornecedorModel.setEmailFornecedor(jtfEmail.getText());
        fornecedorModel.setRuaFornecedor(jtfRua.getText().toUpperCase());
        fornecedorModel.setNomeFornecedor(jtfNome.getText().toUpperCase());
        fornecedorModel.setTelefoneFornecedor(jtfTelefone.getText());
        fornecedorModel.setCelularFornecedor(jtfCelular.getText());
        fornecedorModel.setUfFornecedor(estado);
        fornecedorModel.setNumeroFornecedor(jtfNumero.getText());

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja cadastrar o fornecedor?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            /**
             * chama o metodo de salvar no controller salva o usuario altera o
             * nome do botao salvar recarrega os usuarios exibidos na tabela
             * limpa os campos do formulario desabilita os campos do formluario
             * se houver algum erro exibe uma mensagem na tela
             */
            if (jtfNome.getText().equals("") || jtfTelefone.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Nenhum campo pode ficar em branco!");
                alert.show();
                jtfNome.requestFocus();
            } else {
                if (fornecedorService.salvarFornecedorDAO(fornecedorModel) > 0) {
                    Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                    alerta2.setTitle("Sucesso");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("Fornecedor: " + fornecedorModel.getNomeFornecedor() + " cadastrado com sucesso!");
                    alerta2.show();
                    limparCampos();
                    carregarFornecedor();
                    jtfNome.requestFocus();
                } else {
                    Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                    alerta3.setTitle("Erro");
                    alerta3.setHeaderText(null);
                    alerta3.setContentText("Erro ao cadastrar o fornecedor");
                    alerta3.show();
                    jtfNome.requestFocus();
                }
            }
        }
    }

    private void alterarFornecedor() {
        // altera e salva o  cliente no BD e atualiza a tabela
        String estado = String.valueOf(jcbEstado.getSelectionModel().getSelectedItem());
        int codigoFornecedor = jtFornecedor.getSelectionModel().getSelectedItem().getCodigoFornecedor();
        fornecedorModel.setBairroFornecedor(jtfBairro.getText().toUpperCase());
        fornecedorModel.setCepFornecedor(jtfCep.getText());
        fornecedorModel.setCidadeFornecedor(jtfCidade.getText().toUpperCase());
        fornecedorModel.setEmailFornecedor(jtfEmail.getText());
        fornecedorModel.setRuaFornecedor(jtfRua.getText().toUpperCase());
        fornecedorModel.setNomeFornecedor(jtfNome.getText().toUpperCase());
        fornecedorModel.setTelefoneFornecedor(jtfTelefone.getText());
        fornecedorModel.setCelularFornecedor(jtfCelular.getText());
        fornecedorModel.setUfFornecedor(estado);
        fornecedorModel.setNumeroFornecedor(jtfNumero.getText());
        fornecedorModel.setCodigoFornecedor(codigoFornecedor);
        /**
         * chama o metodo de alterar no controller salva o produto altera o nome
         * do botao salvar recarrega os produtos exibidos na tabela limpa os
         * campos do formulario desabilita os campos do formluario se houver
         * algum erro exibe uma mensagem na tela
         */
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja alterar o fornecedor?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            desabilitarCampos(false);
            if (fornecedorService.atualizarFornecedorDAO(fornecedorModel)) {
                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
                alerta1.setTitle(null);
                alerta1.setHeaderText(null);
                alerta1.setContentText("Fornecedor alterado com sucesso!");
                alerta1.show();
                btnSalvar.setText("Salvar");
                carregarFornecedor();
                limparCampos();
                desabilitarCampos(true);
                this.btnSalvar.setText("Salvar");
            }
        } else {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle(null);
            alerta2.setHeaderText(null);
            alerta2.setContentText("Erro ao alterar o fornecedor!");
            alerta2.show();
        }
    }

    public void carregarFornecedor() {
        listaFornecedorModel = fornecedorService.getListaFornecedorDAO();

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneFornecedor"));

        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaTelefone.setCellFactory(TextFieldTableCell.forTableColumn());

        jtFornecedor.setItems(FXCollections.observableArrayList(listaFornecedorModel));
    }

    ObservableList<String> listaDeEstados = FXCollections.observableArrayList(
            "ACRE",
            "ALAGOAS",
            "AMAPÁ",
            "AMAZONAS",
            "BAHIA",
            "CEARÁ",
            "DISTRITO FEDERAL",
            "ESPÍRITO SANTO",
            "GOIÁS",
            "MARANHÃO",
            "MATO GROSSO",
            "MATO GROSSO DO SUL",
            "MINAS GERAIS",
            "PARÁ",
            "PARAÍBA",
            "PARANÁ",
            "PERNAMBUCO",
            "PIAUÍ",
            "RIO DE JANEIRO",
            "RIO GRANDE DO NORTE",
            "RIO GRANDE DO SUL",
            "RONDÔNIA",
            "RORAIMA",
            "SANTA CATARINA",
            "SÃO PAULO  ",
            "SERGIPE",
            "TOCANTINS"
    );
}
