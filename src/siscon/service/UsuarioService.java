/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.service;

import java.util.ArrayList;
import siscon.model.UsuarioModel;
import siscon.model.dao.UsuarioDao;

/**
 *
 * @author jluiz
 */
public class UsuarioService {

    UsuarioDao usuarioDao = new UsuarioDao();

    public int salvarUsuarioDAO(UsuarioModel pModelUsuario) {
        return usuarioDao.salvarUsuarioDAO(pModelUsuario);
    }

    public UsuarioModel getUsuarioDAO(String pLogin) {
        return usuarioDao.getUsuarioDAO(pLogin);
    }

    public ArrayList<UsuarioModel> getListaUsuarioDAO() {
        return usuarioDao.getListaUsuarioDAO();
    }

    public boolean atualizarUsuarioDAO(UsuarioModel pModelUsuario) {
        return usuarioDao.atualizarUsuarioDAO(pModelUsuario);
    }

    public boolean excluirUsuarioDAO(int pIdUsuario) {
        return usuarioDao.excluirUsuarioDAO(pIdUsuario);
    }

    public boolean getValidarUsuarioDAO(UsuarioModel pModelUsuario) {
        return usuarioDao.getValidarUsuarioDAO(pModelUsuario);
    }

    public UsuarioModel getUltimoCodigo() {
        return usuarioDao.getUltimoCodigo();
    }
}
