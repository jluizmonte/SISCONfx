/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import siscon.connection.ConexaoMySql;
import siscon.model.ClienteModel;
import siscon.model.VendasClientesModel;
import siscon.model.VendasModel;

/**
 *
 * @author Jose Luiz
 */
public class VendasClientesDao extends ConexaoMySql implements IVendasClientes{

    /**
     * recupera uma lista de Vendas return ArrayList
     *
     * @return
     */
    @Override
    public ArrayList<VendasClientesModel> getListaVendasClienteDAO() {
        ArrayList<VendasClientesModel> listaModelVendasCliente = new ArrayList();
        VendasModel modelVendas = new VendasModel();
        ClienteModel clienteModel = new ClienteModel();
        VendasClientesModel modelVendasCliente = new VendasClientesModel();

        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "tbl_vendas.pk_id_vendas,"
                    + "tbl_vendas.fk_cliente,"
                    + "tbl_vendas.ven_data_venda,"
                    + "tbl_vendas.ven_valor_liquido,"
                    + "tbl_vendas.ven_valor_bruto,"
                    + "tbl_vendas.ven_desconto,"
                    + "tbl_cliente.pk_id_cliente,"
                    + "tbl_cliente.cli_nome,"
                    + "tbl_cliente.cli_endereco,"
                    + "tbl_cliente.cli_bairro,"
                    + "tbl_cliente.cli_cidade,"
                    + "tbl_cliente.cli_uf,"
                    + "tbl_cliente.cli_cep,"
                    + "tbl_cliente.cli_telefone"
                    + " FROM "
                    + " tbl_vendas INNER JOIN tbl_cliente "
                    + "ON tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente;"
            //  + ";"
            );
            while (this.getResultSet().next()) {
                modelVendas = new VendasModel();
                clienteModel = new ClienteModel();
                modelVendasCliente = new VendasClientesModel();

                //vendas
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getString(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));

                //cliente
                clienteModel = new ClienteModel();
                clienteModel.setIdCliente(this.getResultSet().getInt(1));
                clienteModel.setCliNome(this.getResultSet().getString(2));
                clienteModel.setCliRua(this.getResultSet().getString(3));
                clienteModel.setCliNumero(this.getResultSet().getString(4));
                clienteModel.setCliBairro(this.getResultSet().getString(5));
                clienteModel.setCliCidade(this.getResultSet().getString(6));
                clienteModel.setCliUf(this.getResultSet().getString(7));
                clienteModel.setCliCep(this.getResultSet().getString(8));
                clienteModel.setCliTelefone(this.getResultSet().getString(9));
                clienteModel.setCliCelular(this.getResultSet().getString(10));
                clienteModel.setCliEmail(this.getResultSet().getString(11));

                modelVendasCliente.setModelVendas(modelVendas);
                modelVendasCliente.setModelCliente(clienteModel);
                listaModelVendasCliente.add(modelVendasCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelVendasCliente;
    }
}
