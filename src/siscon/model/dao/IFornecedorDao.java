/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.model.dao;

import java.util.ArrayList;
import siscon.model.FornecedorModel;

/**
 *
 * @author jluiz
 */
public interface IFornecedorDao {

    public int salvarFornecedorDAO(FornecedorModel pFornecedorModel);

    public FornecedorModel getFornecedorDAO(int pIdFornecedor);

    public FornecedorModel getFornecedorDAO(String pNomeFornecedor);

    public ArrayList<FornecedorModel> getListaFornecedorDAO();

    public boolean atualizarFornecedorDAO(FornecedorModel pFornecedorModel);

    public boolean excluirFornecedorDAO(int pIdFornecedor);
}
