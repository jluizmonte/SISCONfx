package siscon.model.dao;

import java.util.ArrayList;
import siscon.connection.ConexaoMySql;
import siscon.model.VendasProdutosModel;

/**
 *
 * @author Jose Luiz
 */
public class VendasProdutosDao extends ConexaoMySql implements IVendasProdutos {

    /**
     * grava VendasProdutos
     *
     * @param pModelVendasProdutos return int
     * @return
     */
    @Override
    public int salvarVendasProdutosDAO(VendasProdutosModel pModelVendasProdutos) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_vendas_produtos ("
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "vendas_pro_valor,"
                    + "ven_pro_quantidade"
                    + ") VALUES ("
                    + "'" + pModelVendasProdutos.getProduto() + "',"
                    + "'" + pModelVendasProdutos.getVendas() + "',"
                    + "'" + pModelVendasProdutos.getVendasProValor() + "',"
                    + "'" + pModelVendasProdutos.getVenProQuantidade() + "'"
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
     * recupera VendasProdutos
     *
     * @param pIdVendaProduto return VendasProdutosModel
     * @return
     */
    @Override
    public VendasProdutosModel getVendasProdutosDAO(int pIdVendaProduto) {
        VendasProdutosModel modelVendasProdutos = new VendasProdutosModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_venda_produto,"
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "vendas_pro_valor,"
                    + "ven_pro_quantidade"
                    + " FROM"
                    + " tbl_vendas_produtos"
                    + " WHERE"
                    + " pk_id_venda_produto = '" + pIdVendaProduto + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendasProdutos.setIdVendaProduto(this.getResultSet().getInt(1));
                modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
                modelVendasProdutos.setVendas(this.getResultSet().getInt(3));
                modelVendasProdutos.setVendasProValor(this.getResultSet().getDouble(4));
                modelVendasProdutos.setVenProQuantidade(this.getResultSet().getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelVendasProdutos;
    }

    /**
     * recupera uma lista de VendasProdutos return ArrayList
     *
     * @return
     */
    @Override
    public ArrayList<VendasProdutosModel> getListaVendasProdutosDAO() {
        ArrayList<VendasProdutosModel> listamodelVendasProdutos = new ArrayList();
        VendasProdutosModel modelVendasProdutos = new VendasProdutosModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_venda_produto,"
                    + "fk_produto,"
                    + "fk_vendas,"
                    + "vendas_pro_valor,"
                    + "ven_pro_quantidade"
                    + " FROM"
                    + " tbl_vendas_produtos"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelVendasProdutos = new VendasProdutosModel();
                modelVendasProdutos.setIdVendaProduto(this.getResultSet().getInt(1));
                modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
                modelVendasProdutos.setVendas(this.getResultSet().getInt(3));
                modelVendasProdutos.setVendasProValor(this.getResultSet().getDouble(4));
                modelVendasProdutos.setVenProQuantidade(this.getResultSet().getInt(5));
                listamodelVendasProdutos.add(modelVendasProdutos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelVendasProdutos;
    }

    /**
     * atualiza VendasProdutos
     *
     * @param pModelVendasProdutos return boolean
     * @return
     */
    @Override
    public boolean atualizarVendasProdutosDAO(VendasProdutosModel pModelVendasProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_vendas_produtos SET "
                    + "pk_id_venda_produto = '" + pModelVendasProdutos.getIdVendaProduto() + "',"
                    + "fk_produto = '" + pModelVendasProdutos.getProduto() + "',"
                    + "fk_vendas = '" + pModelVendasProdutos.getVendas() + "',"
                    + "vendas_pro_valor = '" + pModelVendasProdutos.getVendasProValor() + "',"
                    + "ven_pro_quantidade = '" + pModelVendasProdutos.getVenProQuantidade() + "'"
                    + " WHERE "
                    + "pk_id_venda_produto = '" + pModelVendasProdutos.getIdVendaProduto() + "'"
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
     * exclui VendasProdutos
     *
     * @param pIdVendaProduto return boolean
     * @return
     */
    @Override
    public boolean excluirVendasProdutosDAO(int pIdVendaProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_vendas_produtos "
                    + " WHERE "
                    + "fk_vendas = '" + pIdVendaProduto + "'"
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
     * Salva uma lista de produtos da venda
     *
     * @param plistaModelVendasProdutos
     * @return
     */
    @Override
    public boolean salvarVendasProdutosDAO(ArrayList<VendasProdutosModel> plistaModelVendasProdutos) {
        try {
            this.conectar();
            int cont = plistaModelVendasProdutos.size();
            for (int i = 0; i < cont; i++) {
                this.insertSQL(
                        "INSERT INTO tbl_vendas_produtos ("
                        + "fk_vendas,"
                        + "fk_produto,"
                        + "ven_pro_valor,"
                        + "ven_pro_quantidade"
                        + ") VALUES ("
                        + "'" + plistaModelVendasProdutos.get(i).getVendas() + "',"
                        + "'" + plistaModelVendasProdutos.get(i).getProduto() + "',"
                        + "'" + plistaModelVendasProdutos.get(i).getVendasProValor() + "',"
                        + "'" + plistaModelVendasProdutos.get(i).getVenProQuantidade() + "'"
                        + ");"
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}
