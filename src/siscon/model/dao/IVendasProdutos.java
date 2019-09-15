package siscon.model.dao;

import java.util.ArrayList;
import siscon.model.VendasProdutosModel;

/**
 *
 * @author joseluiz
 */
public interface IVendasProdutos {

    public boolean salvarVendasProdutosDAO(ArrayList<VendasProdutosModel> plistaModelVendasProdutos);

    public boolean excluirVendasProdutosDAO(int pIdVendaProduto);

    public boolean atualizarVendasProdutosDAO(VendasProdutosModel pModelVendasProdutos);

    public ArrayList<VendasProdutosModel> getListaVendasProdutosDAO();

    public VendasProdutosModel getVendasProdutosDAO(int pIdVendaProduto);

    public int salvarVendasProdutosDAO(VendasProdutosModel pModelVendasProdutos);

}
