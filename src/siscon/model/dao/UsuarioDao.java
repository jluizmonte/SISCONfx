package siscon.model.dao;

import java.sql.SQLException;
import siscon.connection.ConexaoMySql;
import java.util.ArrayList;
import siscon.model.UsuarioModel;

/**
 *
 * @author Jose Luiz
 */
public class UsuarioDao extends ConexaoMySql implements IUsuarioDao {

    /**
     * grava Usuario
     *
     * @param pUsuarioModel
     * @return
     */
    @Override
    public int salvarUsuarioDAO(UsuarioModel pUsuarioModel) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_usuario ("
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha "
                    + ") VALUES ("
                    + "'" + pUsuarioModel.getUsuNome() + "',"
                    + "'" + pUsuarioModel.getUsuLogin() + "',"
                    + "'" + pUsuarioModel.getUsuSenha() + "'"
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
     * recupera Usuario
     *
     * @param pLogin return ModelUsuario
     * @return
     */
    @Override
    public UsuarioModel getUsuarioDAO(String pLogin) {
        UsuarioModel usuarioModel = new UsuarioModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " usu_login = '" + pLogin + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                usuarioModel.setIdUsuario(this.getResultSet().getInt(1));
                usuarioModel.setUsuNome(this.getResultSet().getString(2));
                usuarioModel.setUsuLogin(this.getResultSet().getString(3));
                usuarioModel.setUsuSenha(this.getResultSet().getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return usuarioModel;
    }

    /**
     * recupera uma lista de Usuario return ArrayList
     *
     * @return
     */
    @Override
    public ArrayList<UsuarioModel> getListaUsuarioDAO() {
        ArrayList<UsuarioModel> listaUsuarioModel = new ArrayList();
        UsuarioModel usuarioModel = new UsuarioModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT * FROM tbl_usuario;"
            );

            while (this.getResultSet().next()) {
                usuarioModel = new UsuarioModel();
                usuarioModel.setIdUsuario(this.getResultSet().getInt(1));
                usuarioModel.setUsuNome(this.getResultSet().getString(2));
                usuarioModel.setUsuLogin(this.getResultSet().getString(3));
                usuarioModel.setUsuSenha(this.getResultSet().getString(4));
                listaUsuarioModel.add(usuarioModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaUsuarioModel;
    }

    /**
     * atualiza Usuario
     *
     * @param pUsuarioModel return boolean
     * @return
     */
    @Override
    public boolean atualizarUsuarioDAO(UsuarioModel pUsuarioModel) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_usuario SET "
                    + "pk_id_usuario = '" + pUsuarioModel.getIdUsuario() + "',"
                    + "usu_nome = '" + pUsuarioModel.getUsuNome() + "',"
                    + "usu_login = '" + pUsuarioModel.getUsuLogin() + "',"
                    + "usu_senha = '" + pUsuarioModel.getUsuSenha() + "'"
                    + " WHERE "
                    + "pk_id_usuario = '" + pUsuarioModel.getIdUsuario() + "'"
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
     * exclui Usuario
     *
     * @param pIdUsuario return boolean
     * @return
     */
    @Override
    public boolean excluirUsuarioDAO(int pIdUsuario) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_usuario "
                    + " WHERE "
                    + "pk_id_usuario = '" + pIdUsuario + "'"
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
     * Validar login e senha do usuário
     *
     * @param pUsuarioModel
     * @return
     */
    @Override
    public boolean getValidarUsuarioDAO(UsuarioModel pUsuarioModel) {
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT *"
                    + " FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " usu_login = '" + pUsuarioModel.getUsuLogin()
                    + "' AND usu_senha = '" + pUsuarioModel.getUsuSenha() + "'"
                    + ";"
            );

            if (getResultSet().next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    public UsuarioModel getUltimoCodigo() {
        UsuarioModel usuarioModel = new UsuarioModel();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT COUNT(*) FROM tbl_usuario;"
            );

            while (this.getResultSet().next()) {
                usuarioModel.setUsuQuantidade(this.getResultSet().getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return usuarioModel;
    }
}
