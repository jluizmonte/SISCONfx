/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.service;

import java.util.ArrayList;
import siscon.model.VendasModel;
import siscon.model.dao.VendasDao;

/**
 *
 * @author joseluiz
 */
public class VendasService {

    VendasDao vendasDao = new VendasDao();

    public int salvarVendasDAO(VendasModel pModelVendas) {
        return vendasDao.salvarVendasDAO(pModelVendas);
    }

    public VendasModel getVendasDAO(int pIdVenda) {
        return vendasDao.getVendasDAO(pIdVenda);
    }

    public ArrayList<VendasModel> getListaVendasDAO() {
        return vendasDao.getListaVendasDAO();
    }

    public boolean atualizarVendasDAO(VendasModel pModelVendas) {
        return vendasDao.atualizarVendasDAO(pModelVendas);
    }

    public boolean excluirVendasDAO(int pIdVenda) {
        return vendasDao.excluirVendasDAO(pIdVenda);
    }
}
