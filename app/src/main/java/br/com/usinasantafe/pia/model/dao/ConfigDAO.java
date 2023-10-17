package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List listConfigTO = configBean.all();
        configBean = (ConfigBean) listConfigTO.get(0);
        listConfigTO.clear();
        return configBean;
    }

    public boolean verSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setSenhaConfig(senha);
        configBean.insert();
        configBean.commit();
    }

    public void setOS(Long os){
        ConfigBean configBean = getConfig();
        configBean.setOSConfig(os);
        configBean.update();
        configBean.commit();
    }

    public void setAuditor(Long auditor){
        ConfigBean configBean = getConfig();
        configBean.setAuditorConfig(auditor);
        configBean.update();
        configBean.commit();
    }

    public void setSecao(Long secao){
        ConfigBean configBean = getConfig();
        configBean.setSecaoConfig(secao);
        configBean.update();
        configBean.commit();
    }

    public void setTalhao(Long talhao){
        ConfigBean configBean = getConfig();
        configBean.setTalhaoConfig(talhao);
        configBean.update();
        configBean.commit();
    }

    public void setIdOrg(Long idOrg){
        ConfigBean configBean = getConfig();
        configBean.setIdOrganConfig(idOrg);
        configBean.update();
        configBean.commit();
    }

    public void setIdCaracOrg(Long idCaracOrg){
        ConfigBean configBean = getConfig();
        configBean.setIdCaracOrganConfig(idCaracOrg);
        configBean.update();
        configBean.commit();
    }

}
