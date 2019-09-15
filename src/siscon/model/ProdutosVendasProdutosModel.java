package siscon.model;

import java.util.ArrayList;

/**
 *
 * @author Jose Luiz
 */
public class ProdutosVendasProdutosModel {

    private ProdutoModel modelProdutos;
    private VendasProdutosModel modelVendasProdutos;
    private ArrayList<ProdutosVendasProdutosModel> listaModelProdutosVendasProdutoses = new ArrayList<>();

    /**
     * @return the modelProdutos
     */
    public ProdutoModel getModelProdutos() {
        return modelProdutos;
    }

    /**
     * @param modelProdutos the modelProdutos to set
     */
    public void setModelProdutos(ProdutoModel modelProdutos) {
        this.modelProdutos = modelProdutos;
    }

    /**
     * @return the modelVendasProdutos
     */
    public VendasProdutosModel getModelVendasProdutos() {
        return modelVendasProdutos;
    }

    /**
     * @param modelVendasProdutos the modelVendasProdutos to set
     */
    public void setModelVendasProdutos(VendasProdutosModel modelVendasProdutos) {
        this.modelVendasProdutos = modelVendasProdutos;
    }

    /**
     * @return the listaModelProdutosVendasProdutoses
     */
    public ArrayList<ProdutosVendasProdutosModel> getListaModelProdutosVendasProdutoses() {
        return listaModelProdutosVendasProdutoses;
    }

    /**
     * @param listaModelProdutosVendasProdutoses the
     * listaModelProdutosVendasProdutoses to set
     */
    public void setListaModelProdutosVendasProdutoses(ArrayList<ProdutosVendasProdutosModel> listaModelProdutosVendasProdutoses) {
        this.listaModelProdutosVendasProdutoses = listaModelProdutosVendasProdutoses;
    }
}
