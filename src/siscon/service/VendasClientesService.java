/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.service;

import java.util.ArrayList;
import siscon.model.VendasClientesModel;
import siscon.model.dao.VendasClientesDao;

/**
 *
 * @author joseluiz
 */
public class VendasClientesService {

    VendasClientesDao vendasClientesDao = new VendasClientesDao();

    public ArrayList<VendasClientesModel> getListaVendasClienteDAO() {
        return vendasClientesDao.getListaVendasClienteDAO();
    }

}
