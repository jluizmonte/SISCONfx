package siscon.model;
/**
*
* @author Jose Luiz
*/
public class VendasProdutosModel {

    private int idVendaProduto;
    private int produto;
    private int vendas;
    private double vendasProValor;
    private int venProQuantidade;

    /**
    * Construtor
    */
    public VendasProdutosModel(){}

    /**
    * seta o valor de idVendaProduto
    * @param pIdVendaProduto
    */
    public void setIdVendaProduto(int pIdVendaProduto){
        this.idVendaProduto = pIdVendaProduto;
    }
    /**
    * return idVendaProduto
    */
    public int getIdVendaProduto(){
        return this.idVendaProduto;
    }

    /**
    * seta o valor de produto
    * @param pProduto
    */
    public void setProduto(int pProduto){
        this.produto = pProduto;
    }
    /**
    * return produto
    */
    public int getProduto(){
        return this.produto;
    }

    /**
    * seta o valor de vendas
    * @param pVendas
    */
    public void setVendas(int pVendas){
        this.vendas = pVendas;
    }
    /**
    * return vendas
    */
    public int getVendas(){
        return this.vendas;
    }

    /**
    * seta o valor de vendasProValor
    * @param pVendasProValor
    */
    public void setVendasProValor(double pVendasProValor){
        this.vendasProValor = pVendasProValor;
    }
    /**
    * return vendasProValor
    */
    public double getVendasProValor(){
        return this.vendasProValor;
    }

    /**
    * seta o valor de venProQuantidade
    * @param pVenProQuantidade
    */
    public void setVenProQuantidade(int pVenProQuantidade){
        this.venProQuantidade = pVenProQuantidade;
    }
    /**
    * return venProQuantidade
    */
    public int getVenProQuantidade(){
        return this.venProQuantidade;
    }

    @Override
    public String toString(){
        return "ModelVendasProdutos {" + "::idVendaProduto = " + this.idVendaProduto + "::produto = " + this.produto + "::vendas = " + this.vendas + "::vendasProValor = " + this.vendasProValor + "::venProQuantidade = " + this.venProQuantidade +  "}";
    }
}