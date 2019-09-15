package siscon.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import siscon.connection.ConexaoMySql;
import siscon.model.FornecedorModel;

/**
 *
 * @author jluiz
 */
public class FornecedorDao extends ConexaoMySql implements IFornecedorDao {

    @Override
    public int salvarFornecedorDAO(FornecedorModel pFornecedorModel) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_fornecedor ("
                    + "nome_fornecedor,"
                    + "rua_fornecedor,"
                    + "numero_fornecedor,"
                    + "bairro_fornecedor,"
                    + "cidade_fornecedor,"
                    + "uf_fornecedor,"
                    + "cep_fornecedor,"
                    + "telefone_fornecedor,"
                    + "celular_fornecedor,"
                    + "email_fornecedor"
                    + ") VALUES ("
                    + "'" + pFornecedorModel.getNomeFornecedor() + "',"
                    + "'" + pFornecedorModel.getRuaFornecedor() + "',"
                    + "'" + pFornecedorModel.getNumeroFornecedor() + "',"
                    + "'" + pFornecedorModel.getBairroFornecedor() + "',"
                    + "'" + pFornecedorModel.getCidadeFornecedor() + "',"
                    + "'" + pFornecedorModel.getCepFornecedor() + "',"
                    + "'" + pFornecedorModel.getUfFornecedor() + "',"
                    + "'" + pFornecedorModel.getTelefoneFornecedor() + "',"
                    + "'" + pFornecedorModel.getCelularFornecedor() + "',"
                    + "'" + pFornecedorModel.getEmailFornecedor() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    @Override
    public FornecedorModel getFornecedorDAO(int pIdFornecedor) {
        FornecedorModel fornecedorModel = new FornecedorModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM tbl_fornecedor"
                    + " WHERE"
                    + " codigo_fornecedor = '" + pIdFornecedor + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                fornecedorModel.setCodigoFornecedor(this.getResultSet().getInt(1));
                fornecedorModel.setNomeFornecedor(this.getResultSet().getString(2));
                fornecedorModel.setRuaFornecedor(this.getResultSet().getString(3));
                fornecedorModel.setNumeroFornecedor(this.getResultSet().getString(4));
                fornecedorModel.setBairroFornecedor(this.getResultSet().getString(5));
                fornecedorModel.setCidadeFornecedor(this.getResultSet().getString(6));
                fornecedorModel.setUfFornecedor(this.getResultSet().getString(7));
                fornecedorModel.setCepFornecedor(this.getResultSet().getString(8));
                fornecedorModel.setTelefoneFornecedor(this.getResultSet().getString(9));
                fornecedorModel.setCelularFornecedor(this.getResultSet().getString(10));
                fornecedorModel.setEmailFornecedor(this.getResultSet().getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return fornecedorModel;
    }

    @Override
    public FornecedorModel getFornecedorDAO(String pNomeFornecedor) {
        FornecedorModel fornecedorModel = new FornecedorModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM tbl_fornecedor"
                    + " WHERE"
                    + " id_fornecedor = '" + pNomeFornecedor + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                fornecedorModel.setCodigoFornecedor(this.getResultSet().getInt(1));
                fornecedorModel.setNomeFornecedor(this.getResultSet().getString(2));
                fornecedorModel.setRuaFornecedor(this.getResultSet().getString(3));
                fornecedorModel.setNumeroFornecedor(this.getResultSet().getString(4));
                fornecedorModel.setBairroFornecedor(this.getResultSet().getString(5));
                fornecedorModel.setCidadeFornecedor(this.getResultSet().getString(6));
                fornecedorModel.setUfFornecedor(this.getResultSet().getString(7));
                fornecedorModel.setCepFornecedor(this.getResultSet().getString(8));
                fornecedorModel.setTelefoneFornecedor(this.getResultSet().getString(9));
                fornecedorModel.setCelularFornecedor(this.getResultSet().getString(10));
                fornecedorModel.setEmailFornecedor(this.getResultSet().getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return fornecedorModel;
    }

    @Override
    public ArrayList<FornecedorModel> getListaFornecedorDAO() {
        ArrayList<FornecedorModel> listamodelFornecedor = new ArrayList();
        FornecedorModel fornecedorModel = new FornecedorModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM tbl_fornecedor;"
            );

            while (this.getResultSet().next()) {
                fornecedorModel = new FornecedorModel();
                fornecedorModel.setCodigoFornecedor(this.getResultSet().getInt(1));
                fornecedorModel.setNomeFornecedor(this.getResultSet().getString(2));
                fornecedorModel.setRuaFornecedor(this.getResultSet().getString(3));
                fornecedorModel.setNumeroFornecedor(this.getResultSet().getString(4));
                fornecedorModel.setBairroFornecedor(this.getResultSet().getString(5));
                fornecedorModel.setCidadeFornecedor(this.getResultSet().getString(6));
                fornecedorModel.setUfFornecedor(this.getResultSet().getString(7));
                fornecedorModel.setCepFornecedor(this.getResultSet().getString(8));
                fornecedorModel.setTelefoneFornecedor(this.getResultSet().getString(9));
                fornecedorModel.setCelularFornecedor(this.getResultSet().getString(10));
                fornecedorModel.setEmailFornecedor(this.getResultSet().getString(11));

                listamodelFornecedor.add(fornecedorModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelFornecedor;
    }

    @Override
    public boolean atualizarFornecedorDAO(FornecedorModel pFornecedorModel) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_fornecedor SET "
                    + "codigo_fornecedor = '" + pFornecedorModel.getCodigoFornecedor() + "',"
                    + "nome_fornecedor = '" + pFornecedorModel.getNomeFornecedor() + "',"
                    + "rua_fornecedor = '" + pFornecedorModel.getRuaFornecedor() + "',"
                    + "numero_fornecedor = '" + pFornecedorModel.getNumeroFornecedor() + "',"
                    + "bairro_fornecedor = '" + pFornecedorModel.getBairroFornecedor() + "',"
                    + "cidade_fornecedor = '" + pFornecedorModel.getCidadeFornecedor() + "',"
                    + "uf_fornecedor = '" + pFornecedorModel.getUfFornecedor() + "',"
                    + "cep_fornecedor = '" + pFornecedorModel.getCepFornecedor() + "',"
                    + "telefone_fornecedor = '" + pFornecedorModel.getTelefoneFornecedor() + "',"
                    + "celular_fornecedor = '" + pFornecedorModel.getCelularFornecedor() + "',"
                    + "email_fornecedor = '" + pFornecedorModel.getEmailFornecedor() + "'"
                    + " WHERE "
                    + "codigo_formecedor = '" + pFornecedorModel.getCodigoFornecedor() + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    @Override
    public boolean excluirFornecedorDAO(int pIdFornecedor) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_fornecedor "
                    + " WHERE "
                    + "codigo_fornecedor = '" + pIdFornecedor + "'"
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
