package siscon.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import siscon.model.ClienteModel;
import siscon.service.ClienteService;

/**
 *
 * @author jluiz
 */
public class ClienteController implements Initializable {

    ClienteModel clienteModel = new ClienteModel();
    ClienteService clienteService = new ClienteService();
    ArrayList<ClienteModel> listaClienteModel = new ArrayList<>();
    String alterarSalvar;

    @FXML
    private TableView<ClienteModel> jtCliente;

    @FXML
    private TableColumn<ClienteModel, String> colunaTelefone;

    @FXML
    private TableColumn<ClienteModel, String> colunaNome;

    @FXML
    private TextField jtfCep;

    @FXML
    private TextField jtfBairro;

    @FXML
    private TextField jtfCidade;

    @FXML
    private TextField jtfTelefone;

    @FXML
    private TextField jtfNumero;

    @FXML
    private TextField jtfCelular;

    @FXML
    private TextField jtfRua;

    @FXML
    private Button btnSalvar;

    @FXML
    private ComboBox<String> jcbEstado;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField jtfNome;

    @FXML
    private TextField jtfEmail;

    @FXML
    private Button btnNovo;

    @FXML
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
    public void handleSalvarCliente(ActionEvent event) {

        if (alterarSalvar.equals("salvar")) {
            this.salvarCliente();
        } else if (alterarSalvar.equals("alterar")) {
            this.alterarCliente();
        }
    }

    @FXML
    public void handleExcluirCliente(ActionEvent event) {
        int codigoCliente = jtCliente.getSelectionModel().getSelectedItem().getIdCliente();
        String nomeCliente = jtCliente.getSelectionModel().getSelectedItem().getCliNome();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja excluir o cliente: " + nomeCliente + "?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (clienteService.excluirClienteDAO(codigoCliente)) {

                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                alerta2.setTitle("Sucesso");
                alerta2.setHeaderText(null);
                alerta2.setContentText("Cliente exlcuido com sucesso!");
                alerta2.show();
                limparCampos();
                carregarClientes();
                jtfNome.requestFocus();
            } else {
                Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                alerta3.setTitle("Erro");
                alerta3.setHeaderText(null);
                alerta3.setContentText("Erro ao excluir o cliente");
                alerta3.show();
                carregarClientes();
                jtfNome.requestFocus();
            }
        }
    }

    @FXML
    public void handleExibirDadosCliente(ActionEvent event) {
        desabilitarCampos(false);
        desabilitarEdicaoCampos(false);

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(null);
        alerta.setHeaderText("Modo consulta: Você apenas está consultando os dados completos do cliente\n"
                + "Para alterar algum dado clique no botão alterar e siga as instruções.");
        alerta.setContentText(null);

        alerta.show();
        int codigoCliente = jtCliente.getSelectionModel().getSelectedItem().getIdCliente();
        //recupera os dados do BD
        clienteModel = clienteService.getClienteDAO(codigoCliente);
        //colocar os dados do BD na tabela
        jtfBairro.setText(clienteModel.getCliBairro());
        jtfCelular.setText(clienteModel.getCliCelular());
        jtfCep.setText(clienteModel.getCliCep());
        jtfCidade.setText(clienteModel.getCliCidade());
        jtfEmail.setText(clienteModel.getCliEmail());
        jtfNome.setText(clienteModel.getCliNome());
        jtfNumero.setText(clienteModel.getCliNumero());
        jtfRua.setText(clienteModel.getCliRua());
        jtfTelefone.setText(clienteModel.getCliTelefone());
    }

    @FXML
    public void handleAlterarCliente(ActionEvent event) {
        alterarSalvar = "alterar";
        btnSalvar.setText("Alterar");
        jtfNome.requestFocus();
        desabilitarEdicaoCampos(true);
        desabilitarCampos(false);
        try {
            int codigoCliente = jtCliente.getSelectionModel().getSelectedItem().getIdCliente();
            //recupera os dados do BD
            clienteModel = clienteService.getClienteDAO(codigoCliente);
            //colocar os dados do BD na tabela
            jtfBairro.setText(clienteModel.getCliBairro());
            jtfCelular.setText(clienteModel.getCliCelular());
            jtfCep.setText(clienteModel.getCliCep());
            jtfCidade.setText(clienteModel.getCliCidade());
            jtfEmail.setText(clienteModel.getCliEmail());
            jtfNome.setText(clienteModel.getCliNome());
            jtfNumero.setText(clienteModel.getCliNumero());
            jtfRua.setText(clienteModel.getCliRua());
            jtfTelefone.setText(clienteModel.getCliTelefone());
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
        carregarClientes();
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

    public void salvarCliente() {
        String estado = jcbEstado.getSelectionModel().getSelectedItem();

        clienteModel.setCliBairro(jtfBairro.getText().toUpperCase());
        clienteModel.setCliCep(jtfCep.getText());
        clienteModel.setCliCidade(jtfCidade.getText().toUpperCase());
        clienteModel.setCliEmail(jtfEmail.getText());
        clienteModel.setCliRua(jtfRua.getText().toUpperCase());
        clienteModel.setCliNome(jtfNome.getText().toUpperCase());
        clienteModel.setCliTelefone(jtfTelefone.getText());
        clienteModel.setCliCelular(jtfCelular.getText());
        clienteModel.setCliUf(estado);
        clienteModel.setCliNumero(jtfNumero.getText());

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja cadastrar o cliente?");

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
                if (clienteService.salvarClienteDAO(clienteModel) > 0) {
                    Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                    alerta2.setTitle("Sucesso");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("Cliente: " + clienteModel.getCliNome() + " cadastrado com sucesso!");
                    alerta2.show();
                    limparCampos();
                    carregarClientes();
                    jtfNome.requestFocus();
                } else {
                    Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                    alerta3.setTitle("Erro");
                    alerta3.setHeaderText(null);
                    alerta3.setContentText("Erro ao cadastrar o cliente");
                    alerta3.show();
                    jtfNome.requestFocus();
                }
            }
        }
    }

    private void alterarCliente() {
        // altera e salva o  cliente no BD e atualiza a tabela
        String estado = jcbEstado.getSelectionModel().getSelectedItem();
        clienteModel.setCliBairro(jtfBairro.getText().toUpperCase());
        clienteModel.setCliCep(jtfCep.getText().toUpperCase());
        clienteModel.setCliCidade(jtfCidade.getText().toUpperCase());
        clienteModel.setCliEmail(jtfEmail.getText());
        clienteModel.setCliRua(jtfRua.getText().toUpperCase());
        clienteModel.setCliNome(jtfNome.getText().toUpperCase());
        clienteModel.setCliTelefone(jtfTelefone.getText());
        clienteModel.setCliCelular(jtfCelular.getText());
        clienteModel.setCliUf(estado);
        clienteModel.setCliNumero(jtfNumero.getText());
        /**
         * chama o metodo de alterar no controller salva o produto altera o nome
         * do botao salvar recarrega os produtos exibidos na tabela limpa os
         * campos do formulario desabilita os campos do formluario se houver
         * algum erro exibe uma mensagem na tela
         */

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(null);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja alterar o cliente?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            desabilitarCampos(false);
            if (clienteService.atualizarClienteDAO(clienteModel)) {
                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
                alerta1.setTitle(null);
                alerta1.setHeaderText(null);
                alerta1.setContentText("Cliente alterado com sucesso!");
                alerta1.show();
                btnSalvar.setText("Salvar");
                carregarClientes();
                limparCampos();
                desabilitarCampos(true);
                this.btnSalvar.setText("Salvar");
            }
        } else {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle(null);
            alerta2.setHeaderText(null);
            alerta2.setContentText("Erro ao alterar o cliente!");
            alerta2.show();
        }
    }

    public void carregarClientes() {
        listaClienteModel = clienteService.getListaClienteDAO();

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("cliNome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("cliTelefone"));

        colunaTelefone.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());

        jtCliente.setItems(FXCollections.observableArrayList(listaClienteModel));
    }

    ObservableList<String> listaDeEstados = FXCollections.observableArrayList(
            "Acre",
            "Alagoas",
            "Amapá",
            "Amazonas",
            "Bahia",
            "Ceará",
            "Distrito Federal",
            "Espírito Santo",
            "Goiás",
            "Maranhão",
            "Mato Grosso",
            "Mato Grosso do Sul",
            "Minas Gerais",
            "Pará",
            "Paraíba",
            "Paraná",
            "Pernambuco",
            "Piauí",
            "Rio de Janeiro",
            "Rio Grande do Norte",
            "Rio Grande do Sul",
            "Rondônia",
            "Roraima",
            "Santa Catarina",
            "São Paulo  ",
            "Sergipe",
            "Tocantins"
    );
}
