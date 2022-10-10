package br.com.usinasantafe.pia.control;

import br.com.usinasantafe.pia.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pia.model.dao.ConfigDAO;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
    }

    public void salvarConfig(String numLinha, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(numLinha, senha);
    }

    public void setOS(Long os){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOS(os);
    }

    public void setAuditor(Long auditor){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setAuditor(auditor);
    }

    public void setSecao(Long secao){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setSecao(secao);
    }

    public void setTalhao(Long talhao){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setTalhao(talhao);
    }

    public void setIdOrg(Long idOrg){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdOrg(idOrg);
    }

    public void setIdCaracOrg(Long idCaracOrg){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdCaracOrg(idCaracOrg);
    }

}
