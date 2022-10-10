package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.SecaoBean;

public class SecaoDAO {

    public SecaoDAO() {
    }

    public boolean verSecaoCod(Long codSecao){
        List<SecaoBean> secaoList = secaoListCod(codSecao);
        boolean retorno = (secaoList.size() > 0);
        secaoList.clear();
        return retorno;
    }

    public SecaoBean getSecaoCod(Long codSecao){
        List<SecaoBean> secaoList = secaoListCod(codSecao);
        SecaoBean secaoBean = secaoList.get(0);
        secaoList.clear();
        return secaoBean;
    }

    public SecaoBean getSecaoId(Long idSecao){
        List<SecaoBean> secaoList = secaoListId(idSecao);
        SecaoBean secaoBean = secaoList.get(0);
        secaoList.clear();
        return secaoBean;
    }

    public List<SecaoBean> secaoListCod(Long codSecao){
        SecaoBean secaoBean = new SecaoBean();
        return secaoBean.get("codSecao", codSecao);
    }

    public List<SecaoBean> secaoListId(Long idSecao){
        SecaoBean secaoBean = new SecaoBean();
        return secaoBean.get("idSecao", idSecao);
    }

}
