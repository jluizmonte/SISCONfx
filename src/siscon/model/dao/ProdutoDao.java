package siscon.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import siscon.connection.ConexaoMySql;
import siscon.model.ProdutoModel;

/**
 *
 * @author jluiz
 */
public class ProdutoDao extends ConexaoMySql implements IProdutoDao {

    /**
     * grava Produto
     *
     * @param pProdutoModel return int
     * @return
     */
    @Override
    public int salvarProdutoDAO(ProdutoModel pProdutoModel) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_produto ("
                    //                    + "pro_codigo_produto,"
                    + "pro_descricao,"
                    + "pro_observacao,"
                    + "pro_quantidade_estoque,"
                    + "pro_valor_total,"
                    + "pro_valor_unitario,"
                    + "pro_codigo_barra,"
                    + "pro_data_entrada"
                    + ") VALUES ("
                    //                    + "'" + pProdutoModel.getProCodigoProduto() + "',"
                    + "'" + pProdutoModel.getProDescricao() + "',"
                    + "'" + pProdutoModel.getProObservacao() + "',"
                    + "'" + pProdutoModel.getProQuantidadeEstoque() + "',"
                    + "'" + pProdutoModel.getProValorTotal() + "',"
                    + "'" + pProdutoModel.getProValorUnitario() + "',"
                    + "'" + pProdutoModel.getProCodigoBarras() + "',"
                    + "'" + pProdutoModel.getProDataEntrada() + "'"
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
     * recupera Produto
     *
     * @param pIdProduto return ModelProduto
     * @return
     */
    @Override
    public ProdutoModel getProdutoDAO(int pIdProduto) {
        ProdutoModel produtoModel = new ProdutoModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM tbl_produto"
                    + " WHERE"
                    + " pro_codigo_produto = '" + pIdProduto + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                produtoModel.setProCodigoProduto(this.getResultSet().getString(1));
                produtoModel.setProDescricao(this.getResultSet().getString(2));
                produtoModel.setProObservacao(this.getResultSet().getString(3));
                produtoModel.setProQuantidadeEstoque(this.getResultSet().getInt(4));
                produtoModel.setProValorTotal(this.getResultSet().getDouble(5));
                produtoModel.setProValorUnitario(this.getResultSet().getDouble(6));
                produtoModel.setProCodigoBarras(this.getResultSet().getString(7));
                produtoModel.setProDataEntrada(this.getResultSet().getString(8));
//                produtoModel.setProDataEntrada(this.getResultSet().getDate(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return produtoModel;
    }

    /**
     * recupera uma lista de Produto return ArrayList
     *
     * @return
     */
    @Override
    public ArrayList<ProdutoModel> getListaProdutoDAO() {
        ArrayList<ProdutoModel> listamodelProduto = new ArrayList();
        ProdutoModel produtoModel = new ProdutoModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM tbl_produto;"
            );

            while (this.getResultSet().next()) {
                produtoModel = new ProdutoModel();
                produtoModel.setProCodigoProduto(this.getResultSet().getString(1));
                produtoModel.setProDescricao(this.getResultSet().getString(2));
                produtoModel.setProObservacao(this.getResultSet().getString(3));
                produtoModel.setProQuantidadeEstoque(this.getResultSet().getInt(4));
                produtoModel.setProValorTotal(this.getResultSet().getDouble(5));
                produtoModel.setProValorUnitario(this.getResultSet().getDouble(6));
                produtoModel.setProCodigoBarras(this.getResultSet().getString(7));
                produtoModel.setProDataEntrada(this.getResultSet().getString(8));
//                produtoModel.setProDataEntrada(this.getResultSet().getDate(9));
                listamodelProduto.add(produtoModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelProduto;
    }

    /**
     * atualiza Produto
     *
     * @param pProdutoModel return boolean
     * @return
     */
    @Override
    public boolean atualizarProdutoDAO(ProdutoModel pProdutoModel) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_produto SET "
                    + "pro_codigo_produto = '" + pProdutoModel.getProCodigoProduto() + "',"
                    + "pro_descricao = '" + pProdutoModel.getProDescricao() + "',"
                    + "pro_observacao = '" + pProdutoModel.getProObservacao() + "',"
                    + "pro_quantidade_estoque = '" + pProdutoModel.getProQuantidadeEstoque() + "',"
                    + "pro_valor_total = '" + pProdutoModel.getProValorTotal() + "',"
                    + "pro_valor_unitario = '" + pProdutoModel.getProValorUnitario() + "',"
                    + "pro_codigo_barra = '" + pProdutoModel.getProCodigoBarras() + "',"
                    + "pro_data_entrada = '" + pProdutoModel.getProDataEntrada() + "'"
                    + " WHERE "
                    + "pro_codigo_produto = '" + pProdutoModel.getProCodigoProduto() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui Produto
     *
     * @param pIdProduto return boolean
     * @return
     */
    @Override
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_produto "
                    + " WHERE "
                    + "pro_codigo_produto = '" + pIdProduto + "'"
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
     * Alterar estoque de produtos no banco
     *
     * @param pListaModelProdutos
     * @return
     */
    @Override
    public boolean alterarEstoqueProdutosDAO(ArrayList<ProdutoModel> pListaModelProdutos) {
        try {
            this.conectar();
            for (int i = 0; i < pListaModelProdutos.size(); i++) {
                this.executarUpdateDeleteSQL(
                        "UPDATE tbl_produto SET "
                        + "pro_quantidade_estoque = '" + pListaModelProdutos.get(i).getProQuantidadeEstoque() + "'"
                        + " WHERE pro_codigo_produto = '" + pListaModelProdutos.get(i).getProCodigoProduto() + "'"
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

    /**
     * retornar um produto pelo nome
     *
     * @param pNomeProduto
     * @return modelproduto
     */
    @Override
    public ProdutoModel retornarProdutoDAO(String pNomeProduto) {
        ProdutoModel produtoModel = new ProdutoModel();
        try {
            this.conectar();
            this.executarSQL("SELECT * FROM tbl_produto"
                    + " WHERE"
                    + " pro_nome = '" + pNomeProduto + "'"
                    + ";");
            while (this.getResultSet().next()) {
                produtoModel = new ProdutoModel();
                produtoModel.setProCodigoProduto(this.getResultSet().getString(1));
                produtoModel.setProDescricao(this.getResultSet().getString(2));
                produtoModel.setProObservacao(this.getResultSet().getString(3));
                produtoModel.setProQuantidadeEstoque(this.getResultSet().getInt(4));
                produtoModel.setProValorTotal(this.getResultSet().getDouble(5));
                produtoModel.setProValorUnitario(this.getResultSet().getDouble(6));
                produtoModel.setProCodigoBarras(this.getResultSet().getString(7));
                produtoModel.setProDataEntrada(this.getResultSet().getString(8));
//                produtoModel.setProDataEntrada(this.getResultSet().getDate(9));
                //  listamodelProdutos.add(modelProduto);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return produtoModel;
    }

    /**
     * Filtra produtos pela quantidade em estoque
     *
     * @param pEstoqueProduto
     * @return
     */
    @Override
    public ProdutoModel filtrarEstoqueProdutosDAO(String pEstoqueProduto) {
        ProdutoModel produtoModel = new ProdutoModel();
        try {
            this.conectar();
            this.executarSQL("SELECT * FROM tbl_produto" + " WHERE pro_quantidade_estoque = '" + pEstoqueProduto + "';");
            while (this.getResultSet().next()) {
                produtoModel = new ProdutoModel();
                produtoModel.setProCodigoProduto(this.getResultSet().getString(1));
                produtoModel.setProDescricao(this.getResultSet().getString(2));
                produtoModel.setProObservacao(this.getResultSet().getString(3));
                produtoModel.setProQuantidadeEstoque(this.getResultSet().getInt(4));
                produtoModel.setProValorTotal(this.getResultSet().getDouble(5));
                produtoModel.setProValorUnitario(this.getResultSet().getDouble(6));
                produtoModel.setProCodigoBarras(this.getResultSet().getString(7));
                produtoModel.setProDataEntrada(this.getResultSet().getString(8));
//                produtoModel.setProDataEntrada(this.getResultSet().getDate(9));
                //  listamodelProdutos.add(modelProduto);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return produtoModel;
    }
}
