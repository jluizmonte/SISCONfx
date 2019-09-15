package siscon.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import siscon.connection.ConexaoMySql;
import siscon.model.VendasModel;

/**
 *
 * @author joseluiz
 */
public class VendasDao extends ConexaoMySql implements IVendasDao {

    /**
     * grava Vendas
     *
     * @param pModelVendas return int
     * @return
     */
    @Override
    public int salvarVendasDAO(VendasModel pModelVendas) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_vendas ("
                    + "fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto,"
                    + "ven_forma_pagamento,"
                    + "ven_parcelas"
                    + ") VALUES ("
                    + "'" + pModelVendas.getCliente() + "',"
                    + "'" + pModelVendas.getVenDataVenda() + "',"
                    + "'" + pModelVendas.getVenValorLiquido() + "',"
                    + "'" + pModelVendas.getVenValorBruto() + "',"
                    + "'" + pModelVendas.getVenDesconto() + "',"
                    + "'" + pModelVendas.getVenFormaPagamento() + "',"
                    + "'" + pModelVendas.getVenParcelas() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Vendas
     *
     * @param pIdVenda return ModelVendas
     * @return
     */
    @Override
    public VendasModel getVendasDAO(int pIdVenda) {
        VendasModel modelVendas = new VendasModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_vendas,"
                    + "fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto,"
                    + "ven_forma_pagamento,"
                    + "ven_parcelas"
                    + " FROM"
                    + " tbl_vendas"
                    + " WHERE"
                    + " pk_id_vendas = '" + pIdVenda + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getString(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                modelVendas.setVenFormaPagamento(this.getResultSet().getString(7));
                modelVendas.setVenParcelas(this.getResultSet().getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelVendas;
    }

    /**
     * recupera uma lista de Vendas return ArrayList
     *
     * @return
     */
    @Override
    public ArrayList<VendasModel> getListaVendasDAO() {
        ArrayList<VendasModel> listamodelVendas = new ArrayList();
        VendasModel modelVendas = new VendasModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_vendas,"
                    + "fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto,"
                    + "ven_forma_pagamento,"
                    + "ven_parcelas"
                    + " FROM"
                    + " tbl_vendas"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendas = new VendasModel();
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getString(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                modelVendas.setVenFormaPagamento(this.getResultSet().getString(7));
                modelVendas.setVenParcelas(this.getResultSet().getString(8));
                listamodelVendas.add(modelVendas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelVendas;
    }

    /**
     * atualiza Vendas
     *
     * @param pModelVendas return boolean
     * @return
     */
    @Override
    public boolean atualizarVendasDAO(VendasModel pModelVendas) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_vendas SET "
                    + "pk_id_vendas = '" + pModelVendas.getIdVenda() + "',"
                    + "fk_cliente = '" + pModelVendas.getCliente() + "',"
                    + "ven_data_venda = '" + pModelVendas.getVenDataVenda() + "',"
                    + "ven_valor_liquido = '" + pModelVendas.getVenValorLiquido() + "',"
                    + "ven_valor_bruto = '" + pModelVendas.getVenValorBruto() + "',"
                    + "ven_desconto = '" + pModelVendas.getVenDesconto() + "',"
                    + "ven_forma_pagamento = '" + pModelVendas.getVenFormaPagamento() + "',"
                    + "ven_parcelas = '" + pModelVendas.getVenParcelas() + "'"
                    + " WHERE "
                    + "pk_id_vendas = '" + pModelVendas.getIdVenda() + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui Vendas
     *
     * @param pIdVenda return boolean
     * @return
     */
    @Override
    public boolean excluirVendasDAO(int pIdVenda) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_vendas "
                    + " WHERE "
                    + "pk_id_vendas = '" + pIdVenda + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}
