package br.com.usinasantafe.pia.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.AtualAplicBean;
import br.com.usinasantafe.pia.model.bean.estaticas.ROrganCaracBean;
import br.com.usinasantafe.pia.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pia.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pia.model.dao.CabecAmostraDAO;
import br.com.usinasantafe.pia.model.dao.ConfigDAO;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.model.dao.RCaracAmostraDAO;
import br.com.usinasantafe.pia.model.dao.ROrganCaracDAO;
import br.com.usinasantafe.pia.model.dao.RespItemAmostraDAO;
import br.com.usinasantafe.pia.util.AtualDadosServ;
import br.com.usinasantafe.pia.util.VerifDadosServ;

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

    public Long getIdAmostra(){
        ConfigBean configBean = getConfig();
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        RCaracAmostraDAO rCaracAmostraDAO = new RCaracAmostraDAO();
        ROrganCaracBean rOrganCaracBean = rOrganCaracDAO.getROrganCarac(configBean.getIdOrganConfig(), configBean.getIdCaracOrganConfig());
        return rCaracAmostraDAO.getRCaracAmostra(rOrganCaracBean.getIdROrganCarac()).getIdAmostraOrgan();
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho, senha);
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

    public void setValorResp(Long valorResp){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setValorResp(valorResp);
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

    public void salvarToken(String senha, String versao, Long nroAparelho, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("equipDAO.verEquip(equipDAO.dadosVerEquip(Long.parseLong(nroEquip), versao), telaAtual, telaProx, progressDialog, activity, tipo);", activity);
        VerifDadosServ.getInstance().salvarToken(senha, atualAplicDAO.dadosAplic(nroAparelho, versao), telaAtual, telaProx, progressDialog, activity);
    }

    public void recToken(String result, String senha, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            progressDialog.dismiss();

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
                atualAplicBean = atualAplicDAO.recAparelho(jsonArray);
            }

            salvarConfig(atualAplicBean.getNroAparelho(), senha);

            progressDialog = new ProgressDialog(telaAtual);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("ATUALIZANDO ...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();

            AtualDadosServ.getInstance().atualTodasTabBD(telaAtual, telaProx, progressDialog, "ConfigActivity");

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
        }
    }

}
