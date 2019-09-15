package siscon.model;

/**
 *
 * @author jluiz
 */
public class ProdutoModel {

    private String proCodigoProduto;
    private String proDescricao;
    private Double proValorTotal;
    private int proQuantidadeEstoque;
    private Double proValorUnitario;
    private String proObservacao;
    private String proCodigoBarras;
    private String proDataEntrada;

    public String getProDescricao() {
        return proDescricao;
    }

    public void setProDescricao(String proDescricao) {
        this.proDescricao = proDescricao;
    }

    public Double getProValorTotal() {
        return proValorTotal;
    }

    public void setProValorTotal(Double proValorTotal) {
        this.proValorTotal = proValorTotal;
    }

    public int getProQuantidadeEstoque() {
        return proQuantidadeEstoque;
    }

    public void setProQuantidadeEstoque(int proQuantidadeEstoque) {
        this.proQuantidadeEstoque = proQuantidadeEstoque;
    }

    public Double getProValorUnitario() {
        return proValorUnitario;
    }

    public void setProValorUnitario(Double proValorUnitario) {
        this.proValorUnitario = proValorUnitario;
    }

    public String getProObservacao() {
        return proObservacao;
    }

    public void setProObservacao(String proObservacao) {
        this.proObservacao = proObservacao;
    }

    public String getProCodigoProduto() {
        return proCodigoProduto;
    }

    public void setProCodigoProduto(String proCodigoProduto) {
        this.proCodigoProduto = proCodigoProduto;
    }

    public String getProCodigoBarras() {
        return proCodigoBarras;
    }

    public void setProCodigoBarras(String proCodigoBarras) {
        this.proCodigoBarras = proCodigoBarras;
    }

    public String getProDataEntrada() {
        return proDataEntrada;
    }

    public void setProDataEntrada(String proDataEntrada) {
        this.proDataEntrada = proDataEntrada;
    }

    @Override
    public String toString() {
        return "ProdutoModel{" + "proDescricao=" + proDescricao + ", proValorTotal=" + proValorTotal + ", proQuantidadeEstoque=" + proQuantidadeEstoque + ", proValorUnitario=" + proValorUnitario + ", proObservacao=" + proObservacao + ", proCodigoProduto=" + proCodigoProduto + ", proCodigoBarras=" + proCodigoBarras + ", proDataEntrada=" + proDataEntrada + '}';
    }

}
