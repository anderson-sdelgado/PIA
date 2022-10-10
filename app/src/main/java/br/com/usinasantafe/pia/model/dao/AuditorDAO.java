package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.AuditorBean;

public class AuditorDAO {

    public AuditorDAO() {
    }

    public boolean hasElements(){
        AuditorBean auditorBean = new AuditorBean();
        return auditorBean.hasElements();
    }

    public boolean verAuditorMatric(Long matricAuditor){
        List<AuditorBean> auditorList = auditorListMatric(matricAuditor);
        boolean retorno = (auditorList.size() > 0);
        auditorList.clear();
        return retorno;
    }

    public AuditorBean getAuditorMatric(Long matricAuditor){
        List<AuditorBean> auditorList = auditorListMatric(matricAuditor);
        AuditorBean auditorBean = auditorList.get(0);
        auditorList.clear();
        return auditorBean;
    }

    public AuditorBean getAuditorId(Long idAuditor){
        List<AuditorBean> auditorList = auditorListId(idAuditor);
        AuditorBean auditorBean = auditorList.get(0);
        auditorList.clear();
        return auditorBean;
    }

    public List<AuditorBean> auditorListMatric(Long matricAuditor){
        AuditorBean auditorTO = new AuditorBean();
        return auditorTO.get("matricAuditor", matricAuditor);
    }

    public List<AuditorBean> auditorListId(Long idAuditor){
        AuditorBean auditorTO = new AuditorBean();
        return auditorTO.get("idAuditor", idAuditor);
    }

}
