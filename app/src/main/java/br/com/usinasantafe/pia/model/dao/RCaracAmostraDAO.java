package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.RCaracAmostraBean;

public class RCaracAmostraDAO {

    public RCaracAmostraDAO() {
    }

    public boolean verRCaracAmostraList(Long idROrganismoCarac){
        List<RCaracAmostraBean> rCaracAmostraList = rCaracAmostraList(idROrganismoCarac);
        boolean retorno = rCaracAmostraList.size() > 0;
        rCaracAmostraList.clear();
        return retorno;
    }

    public RCaracAmostraBean getRCaracAmostra(Long idROrgCarac){
        List<RCaracAmostraBean> rCaracAmostraList = rCaracAmostraList(idROrgCarac);
        RCaracAmostraBean rCaracAmostraBean = rCaracAmostraList.get(0);
        rCaracAmostraList.clear();
        return rCaracAmostraBean;
    }

    public List<RCaracAmostraBean> rCaracAmostraList(Long idROrganismoCarac){
        RCaracAmostraBean rCaracAmostraBean = new RCaracAmostraBean();
        return rCaracAmostraBean.get("idRCaracAmostra", idROrganismoCarac);
    }

}
