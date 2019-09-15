package siscon.model.dao;

import java.util.ArrayList;
import siscon.model.VendasModel;

/**
 *
 * @author joseluiz
 */
public interface IVendasDao {

    public int salvarVendasDAO(VendasModel pModelVendas);

    public VendasModel getVendasDAO(int pIdVenda);

    public ArrayList<VendasModel> getListaVendasDAO();

    public boolean atualizarVendasDAO(VendasModel pModelVendas);

    public boolean excluirVendasDAO(int pIdVenda);

}
