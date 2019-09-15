/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.service;

import java.util.ArrayList;
import siscon.model.ProdutoModel;
import siscon.model.dao.ProdutoDao;

/**
 *
 * @author jluiz
 */
public class ProdutoService {

    ProdutoDao produtoDao = new ProdutoDao();

    public int salvarProdutoDAO(ProdutoModel pProdutoModel) {
        return produtoDao.salvarProdutoDAO(pProdutoModel);
    }

    public ProdutoModel getProdutoDAO(int pIdProduto) {
        return produtoDao.getProdutoDAO(pIdProduto);
    }

    public ArrayList<ProdutoModel> getListaProdutoDAO() {
        return produtoDao.getListaProdutoDAO();
    }

    public boolean atualizarProdutoDAO(ProdutoModel pProdutoModel) {
        return produtoDao.atualizarProdutoDAO(pProdutoModel);
    }

    public boolean excluirProdutoDAO(int pIdProduto) {
        return produtoDao.excluirProdutoDAO(pIdProduto);
    }

    public boolean alterarEstoqueProdutosDAO(ArrayList<ProdutoModel> pListaProdutoModel) {
        return produtoDao.alterarEstoqueProdutosDAO(pListaProdutoModel);
    }

    public ProdutoModel retornarProdutoDAO(String pProdutoNome) {
        return produtoDao.retornarProdutoDAO(pProdutoNome);
    }

    public ProdutoModel filtrarEstoqueProdutosDAO(String pEstoqueProduto) {
        return produtoDao.filtrarEstoqueProdutosDAO(pEstoqueProduto);
    }
}
