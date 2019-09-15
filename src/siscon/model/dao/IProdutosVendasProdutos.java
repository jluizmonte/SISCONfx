package siscon.model.dao;

import java.util.ArrayList;
import siscon.model.ProdutosVendasProdutosModel;

/**
 *
 * @author joseluiz
 */
public interface IProdutosVendasProdutos {

    public ArrayList<ProdutosVendasProdutosModel> getListaProdutosVendasProdutosDAO(int pCodigoVenda);
}
