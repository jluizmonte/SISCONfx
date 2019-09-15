package siscon.model;

/**
 *
 * @author Jose Luiz
 */
public class UsuarioModel {

    private int idUsuario;
    private String usuNome;
    private String usuLogin;
    private String usuSenha;
    private int usuQuantidade;

    /**
     * seta o valor de idUsuario
     *
     * @param pIdUsuario
     */
    public void setIdUsuario(int pIdUsuario) {
        this.idUsuario = pIdUsuario;
    }

    /**
     * return pk_idUsuario
     *
     * @return
     */
    public int getIdUsuario() {
        return this.idUsuario;
    }

    /**
     * seta o valor de usuNome
     *
     * @param pUsuNome
     */
    public void setUsuNome(String pUsuNome) {
        this.usuNome = pUsuNome;
    }

    /**
     * return usuNome
     *
     * @return
     */
    public String getUsuNome() {
        return this.usuNome;
    }

    /**
     * seta o valor de usuLogin
     *
     * @param pUsuLogin
     */
    public void setUsuLogin(String pUsuLogin) {
        this.usuLogin = pUsuLogin;
    }

    /**
     * return usuLogin
     *
     * @return
     */
    public String getUsuLogin() {
        return this.usuLogin;
    }

    /**
     * seta o valor de usuSenha
     *
     * @param pUsuSenha
     */
    public void setUsuSenha(String pUsuSenha) {
        this.usuSenha = pUsuSenha;
    }

    /**
     * return usuSenha
     *
     * @return
     */
    public String getUsuSenha() {
        return this.usuSenha;
    }

    public int getUsuQuantidade() {
        return usuQuantidade;
    }

    public void setUsuQuantidade(int usuQuantidade) {
        this.usuQuantidade = usuQuantidade;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" + "idUsuario=" + idUsuario + ", usuNome=" + usuNome + ", usuLogin=" + usuLogin + ", usuSenha=" + usuSenha + ", usuQuantidade=" + usuQuantidade + '}';
    }

}
