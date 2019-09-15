/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.model.dao;

import java.util.ArrayList;
import siscon.model.UsuarioModel;

/**
 *
 * @author jluiz
 */
public interface IUsuarioDao {

    public int salvarUsuarioDAO(UsuarioModel pUsuarioModel);

    public UsuarioModel getUsuarioDAO(String pLogin);

    public ArrayList<UsuarioModel> getListaUsuarioDAO();

    public boolean atualizarUsuarioDAO(UsuarioModel pUsuarioModel);

    public boolean excluirUsuarioDAO(int pIdUsuario);

    public boolean getValidarUsuarioDAO(UsuarioModel pUsuarioModel);
}
