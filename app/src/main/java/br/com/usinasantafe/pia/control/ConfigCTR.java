package br.com.usinasantafe.pia.control;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pia.model.dao.CabecAmostraDAO;
import br.com.usinasantafe.pia.model.dao.ConfigDAO;
import br.com.usinasantafe.pia.model.dao.ItemAmostraDAO;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.model.dao.RespItemAmostraDAO;

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

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        return logProcessoDAO.logProcessoList();
    }

    public ArrayList<String> logBaseDadoList(){
        ArrayList<String> dadosArrayList = new ArrayList<>();
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        dadosArrayList = cabecAmostraDAO.cabecAmostraAllArrayList(dadosArrayList);
        dadosArrayList = respItemAmostraDAO.respAllArrayList(dadosArrayList);
        return dadosArrayList;
    }

    public List<LogErroBean> logErroList(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.logErroBeanList();
    }

    public void deleteLogs(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        LogErroDAO logErroDAO = new LogErroDAO();
        logProcessoDAO.deleteLogProcesso();
        logErroDAO.deleteLogErro();
    }

}
