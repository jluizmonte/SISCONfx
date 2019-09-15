/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.service;

import java.util.ArrayList;
import siscon.model.ProdutosVendasProdutosModel;
import siscon.model.dao.ProdutosVendasProdutosDao;

/**
 *
 * @author joseluiz
 */
public class ProdutosVendasProdutosService {

    ProdutosVendasProdutosDao produtosVendasProdutosDao = new ProdutosVendasProdutosDao();

    public ArrayList<ProdutosVendasProdutosModel> getListaProdutosVendasProdutosDAO(int pCodigoVenda) {
        return produtosVendasProdutosDao.getListaProdutosVendasProdutosDAO(pCodigoVenda);
    }

}
