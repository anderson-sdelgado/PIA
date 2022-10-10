package br.com.usinasantafe.pia.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;

public class AmostraDAO {

    public AmostraDAO() {
    }

    public boolean verAmostra(Long idAmostra){
        List<AmostraBean> amostraList = amostraList(idAmostra);
        boolean retorno = amostraList.size() > 0;
        amostraList.clear();
        return retorno;
    }

    public AmostraBean getAmostra(Long idAmostra){
        List<AmostraBean> amostraList = amostraList(idAmostra);
        AmostraBean amostraFitoBean = amostraList.get(0);
        amostraList.clear();
        return amostraFitoBean;
    }

    public List<AmostraBean> amostraList(Long idAmostra){
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.getAndOrderBy("idAmostraOrgan", idAmostra, "seqAmostra", true);
    }

}
