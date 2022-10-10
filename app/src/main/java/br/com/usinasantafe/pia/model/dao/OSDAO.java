package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pia.model.bean.estaticas.SecaoBean;

public class OSDAO {

    public OSDAO() {
    }

    public boolean verOSNro(Long nroOS){
        List<OSBean> osList = osListNro(nroOS);
        boolean retorno = (osList.size() > 0);
        osList.clear();
        return retorno;
    }

    public OSBean getOSNro(Long nroOS){
        List<OSBean> osList = osListNro(nroOS);
        OSBean osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public List<OSBean> osListNro(Long nroOS){
        OSBean osBean = new OSBean();
        return osBean.get("nroOS", nroOS);
    }

}
