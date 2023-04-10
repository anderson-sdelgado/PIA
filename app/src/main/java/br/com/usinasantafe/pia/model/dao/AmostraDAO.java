package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;

public class AmostraDAO {

    public AmostraDAO() {
    }

    public boolean verAmostra(Long idAmostra){
        List<AmostraBean> amostraList = amostraIdAmostraOrganList(idAmostra);
        boolean retorno = amostraList.size() > 0;
        amostraList.clear();
        return retorno;
    }

    public AmostraBean getAmostraIdAmostra(Long idAmostra){
        List<AmostraBean> amostraList = amostraIdAmostraList(idAmostra);
        AmostraBean amostraFitoBean = amostraList.get(0);
        amostraList.clear();
        return amostraFitoBean;
    }

    public List<AmostraBean> amostraIdAmostraOrganList(Long idAmostraOrgan){
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.getAndOrderBy("idAmostraOrgan", idAmostraOrgan, "seqAmostra", true);
    }

    public List<AmostraBean> amostraIdAmostraList(Long idAmostra){
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.getAndOrderBy("idAmostra", idAmostra, "seqAmostra", true);
    }

}
