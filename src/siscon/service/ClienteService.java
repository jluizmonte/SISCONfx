/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.service;

import java.util.ArrayList;
import siscon.model.ClienteModel;
import siscon.model.dao.ClienteDao;

/**
 *
 * @author jluiz
 */
public class ClienteService {

    ClienteDao clienteDao = new ClienteDao();

    public int salvarClienteDAO(ClienteModel pClienteModel) {
        return clienteDao.salvarClienteDAO(pClienteModel);
    }

    public ClienteModel getClienteDAO(int pIdCliente) {

        return clienteDao.getClienteDAO(pIdCliente);
    }

    public ClienteModel getClienteDAO(String pNomeCliente) {

        return clienteDao.getClienteDAO(pNomeCliente);
    }

    public ArrayList<ClienteModel> getListaClienteDAO() {

        return clienteDao.getListaClienteDAO();
    }

    public boolean atualizarClienteDAO(ClienteModel pClienteModel) {

        return clienteDao.atualizarClienteDAO(pClienteModel);
    }

    public boolean excluirClienteDAO(int pIdCliente) {

        return clienteDao.excluirClienteDAO(pIdCliente);
    }

}
