package siscon.model.dao;

import java.util.ArrayList;
import siscon.model.ProdutoModel;

/**
 *
 * @author jluiz
 */
public interface IProdutoDao {

    public int salvarProdutoDAO(ProdutoModel pProdutoModel);

    public ProdutoModel getProdutoDAO(int pIdProduto);

    public ArrayList<ProdutoModel> getListaProdutoDAO();

    public boolean atualizarProdutoDAO(ProdutoModel pProdutoModel);

    public boolean excluirProdutoDAO(int pIdProduto);

    public boolean alterarEstoqueProdutosDAO(ArrayList<ProdutoModel> pListaProdutoModel);

    public ProdutoModel retornarProdutoDAO(String pProdutoNome);

    public ProdutoModel filtrarEstoqueProdutosDAO(String pEstoqueProduto);

}
