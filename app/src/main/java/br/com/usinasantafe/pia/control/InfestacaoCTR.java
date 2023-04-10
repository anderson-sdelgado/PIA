package br.com.usinasantafe.pia.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.bean.estaticas.AuditorBean;
import br.com.usinasantafe.pia.model.bean.estaticas.CaracOrganBean;
import br.com.usinasantafe.pia.model.bean.estaticas.OrganBean;
import br.com.usinasantafe.pia.model.bean.estaticas.ROrganCaracBean;
import br.com.usinasantafe.pia.model.bean.estaticas.SecaoBean;
import br.com.usinasantafe.pia.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pia.model.bean.variaveis.ItemAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.dao.AmostraDAO;
import br.com.usinasantafe.pia.model.dao.AuditorDAO;
import br.com.usinasantafe.pia.model.dao.CabecAmostraDAO;
import br.com.usinasantafe.pia.model.dao.CaracOrganismoDAO;
import br.com.usinasantafe.pia.model.dao.ItemAmostraDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.model.dao.OSDAO;
import br.com.usinasantafe.pia.model.dao.OrganDAO;
import br.com.usinasantafe.pia.model.dao.RCaracAmostraDAO;
import br.com.usinasantafe.pia.model.dao.ROrganCaracDAO;
import br.com.usinasantafe.pia.model.dao.RespItemAmostraDAO;
import br.com.usinasantafe.pia.model.dao.SecaoDAO;
import br.com.usinasantafe.pia.model.dao.TalhaoDAO;
import br.com.usinasantafe.pia.util.AtualDadosServ;

public class InfestacaoCTR {

    public InfestacaoCTR() {
    }

    public boolean hasElemAuditor(){
        AuditorDAO auditorDAO = new AuditorDAO();
        return auditorDAO.hasElements();
    }

    public boolean hasElemItemAmostra(){
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        return itemAmostraDAO.hasElements();
    }

    public boolean verCabecAberto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.verCabecAberto();
    }

    public boolean verCabecFechado(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.verCabecFechado();
    }

    public boolean verAuditorCod(Long matricAuditor){
        AuditorDAO auditorDAO = new AuditorDAO();
        return auditorDAO.verAuditorMatric(matricAuditor);
    }

    public boolean verOSNro(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.verOSNro(nroOS);
    }

    public boolean verSecaoCod(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        OSDAO osDAO = new OSDAO();
        ConfigCTR configCTR = new ConfigCTR();
        if(secaoDAO.verSecaoCod(codSecao)){
            Long idSecao = secaoDAO.getSecaoCod(codSecao).getIdSecao();
            Long idSecaoOS = osDAO.getOSNro(configCTR.getConfig().getOSConfig()).getIdProprAgr();
            if(idSecao.equals(idSecaoOS)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean verTalhaCod(Long codTalhao){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return talhaoDAO.verTalhaCod(configCTR.getConfig().getSecaoConfig(), codTalhao);
    }

    public boolean verRespItemAmostraList(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.verRespItemAmostraList(cabecAmostraDAO.getCabecAberto().getIdCabec());
    }

    public boolean verRespItemAmostraFechado(Long idCabec){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.verRespItemAmostraFechado(idCabec);
    }

    public boolean verAmostraCarac(Long idCaracOrganismo){
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        RCaracAmostraDAO rCaracAmostraDAO = new RCaracAmostraDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ROrganCaracBean rOrganCaracBean = rOrganCaracDAO.getROrganCarac(configCTR.getConfig().getIdOrganConfig(), idCaracOrganismo);
        if(rCaracAmostraDAO.verRCaracAmostraList(rOrganCaracBean.getIdROrganCarac())){
            return amostraDAO.verAmostra(rCaracAmostraDAO.getRCaracAmostra(rOrganCaracBean.getIdROrganCarac()).getIdAmostraOrgan());
        } else {
            return false;
        }
    }

    public int qtdeCabecFechado(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.qtdeCabecFechado();
    }

    public AuditorBean getAuditorMatric(Long matricAuditor){
        AuditorDAO auditorDAO = new AuditorDAO();
        return auditorDAO.getAuditorMatric(matricAuditor);
    }

    public SecaoBean getSecaoCod(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.getSecaoCod(codSecao);
    }

    public TalhaoBean getTalhaCod(Long codTalhao){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhaCod(codTalhao);
    }

    public ItemAmostraBean getItemAmostraCabec(){
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        return itemAmostraDAO.getItemAmostraCabec();
    }

    public AuditorBean getAuditor(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        AuditorDAO auditorDAO = new AuditorDAO();
        return auditorDAO.getAuditorId(cabecAmostraDAO.getCabecAberto().getAuditorCabec());
    }

    public SecaoBean getSecao(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.getSecaoId(cabecAmostraDAO.getCabecAberto().getSecaoCabec());
    }

    public TalhaoBean getTalhao(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhaId(cabecAmostraDAO.getCabecAberto().getTalhaoCabec());
    }

    public List<RespItemAmostraBean> getRespItemAmostraList(Long ponto){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.respItemAmostraList(cabecAmostraDAO.getCabecAberto().getIdCabec(), ponto);
    }

    public AmostraBean getAmostraIdAmostra(Long idAmostra){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.getAmostraIdAmostra(idAmostra);
    }

    public ItemAmostraBean getItemAmostra(int posicao){
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        return itemAmostraDAO.getItemAmostra(posicao);
    }

    public List<CabecAmostraBean> cabecFechadoList(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.cabecFechadoList();
    }

    public List<RespItemAmostraBean> respItemAmostraFechadoList(Long idCabec){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.respItemAmostraFechadoList(idCabec);
    }

    public Long ultPonto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        if(respItemAmostraDAO.verRespItemAmostraFechado(cabecAmostraDAO.getCabecAberto().getIdCabec())){
            return respItemAmostraDAO.getUltRespItemAmostra(cabecAmostraDAO.getCabecAberto().getIdCabec()).getPontoRespItem();
        } else {
            return 0L;
        }
    }

    public RespItemAmostraBean getRespItemAmostra(Long idRespItem){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.getRespItemAmostra(idRespItem);
    }

    public ItemAmostraBean getItemAmostraId(Long idAmostraItem){
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        return itemAmostraDAO.getItemAmostraId(idAmostraItem);
    }

    public List<OrganBean> organismoList(){
        OrganDAO organDAO = new OrganDAO();
        return organDAO.organismoList();
    }

    public List<CaracOrganBean> caracOrganismoList(){
        ConfigCTR configCTR = new ConfigCTR();
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        CaracOrganismoDAO caracOrganismoDAO = new CaracOrganismoDAO();
        return caracOrganismoDAO.caracOrganismoList(rOrganCaracDAO.idCaracOrganArrayList(configCTR.getConfig().getIdOrganConfig()));
    }

    public List<AmostraBean> amostraList(Long idCaracOrganismo){
        ConfigCTR configCTR = new ConfigCTR();
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        RCaracAmostraDAO rCaracAmostraDAO = new RCaracAmostraDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        ROrganCaracBean rOrganCaracBean = rOrganCaracDAO.getROrganCarac(configCTR.getConfig().getIdOrganConfig(), idCaracOrganismo);
        return  amostraDAO.amostraIdAmostraOrganList(rCaracAmostraDAO.getRCaracAmostra(rOrganCaracBean.getIdROrganCarac()).getIdAmostraOrgan());
    }

    public int qtdeItemAmostra(){
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        return itemAmostraDAO.qtdeItemAmostra();
    }

    public String dadosEnvioBolFechadoMMFert(){

        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        String dadosEnvioBoletim = cabecAmostraDAO.dadosEnvioCabecAmostraFechado();

        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        String dadosEnvioApont = respItemAmostraDAO.dadosEnvioRespItemAmostra(respItemAmostraDAO.respRespItemAmostraList(cabecAmostraDAO.idCabecAmostraArrayList(cabecAmostraDAO.cabecFechadoList())));

        return dadosEnvioBoletim + "_" + dadosEnvioApont;
    }

    public void salvarCabecAberto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        cabecAmostraDAO.salvarCabecAberto(configBean.getAuditorConfig(), configBean.getOSConfig(), configBean.getSecaoConfig()
                , configBean.getTalhaoConfig(), configBean.getIdOrganConfig(), configBean.getIdCaracOrganConfig());
    }

    public boolean inserirItemAmostra(List<AmostraBean> amostraList) {
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        return itemAmostraDAO.inserirItemAmostra(cabecAmostraDAO.getCabecAberto().getIdCabec(), amostraList);
    }

    public void inserirRespItemAmostra(Long idAmostra, Long valor){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.inserirRespItemAmostra(cabecAmostraDAO.getCabecAberto().getIdCabec(), idAmostra, valor, (ultPonto() + 1));
    }

    public void updateRespItemAmostra(Long idRespItem, Long valor){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.updateRespItemAmostra(idRespItem, valor);
    }

    public void updateRespItemAmostraFechado(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAberto();;
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.updateRespItemAmostraFechado(cabecAmostraBean.getIdCabec());
    }

    public void updateItemAmostraCabec(Long valor){
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        itemAmostraDAO.updateItemAmostraCabec(valor);
    }

    public void fecharCabec(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        itemAmostraDAO.deleteItemAmostra();
        cabecAmostraDAO.fecharCabec();
    }

    public void deleteCabecAbertoAll(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        ItemAmostraDAO itemAmostraDAO = new ItemAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAberto();
        respItemAmostraDAO.deleteRespItemAmostra(cabecAmostraBean.getIdCabec());
        itemAmostraDAO.deleteItemAmostra();
        cabecAmostraDAO.deleteCabecAberto();
    }

    public void deleteCabecAberto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        cabecAmostraDAO.deleteCabecAberto();
    }

    public void deleteRespItemAmostraAberto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAberto();;
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.deleteRespItemAmostraAberto(cabecAmostraBean.getIdCabec());
    }

    public void deleteRespItemAmostraAberto(Long idAmostra){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAberto();;
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.deleteRespItemAmostraAberto(cabecAmostraBean.getIdCabec(), idAmostra);
    }

    public void deleteRespItemAmostra(Long ponto){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAberto();;
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.deleteRespItemAmostra(cabecAmostraBean.getIdCabec(), ponto);
    }

    public void updateCabecEnviado(String objeto) throws Exception {

        JSONObject jObjCabec = new JSONObject(objeto);
        JSONArray jsonArrayCabec = jObjCabec.getJSONArray("cabec");

        for (int i = 0; i < jsonArrayCabec.length(); i++) {

            JSONObject objBol = jsonArrayCabec.getJSONObject(i);
            Gson gsonBol = new Gson();
            CabecAmostraBean cabecAmostraBean = gsonBol.fromJson(objBol.toString(), CabecAmostraBean.class);

            CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
            cabecAmostraDAO.updateCabecEnviado(cabecAmostraBean.getIdCabec());

        }

    }

    public Long updateRespEnviado(String objeto) throws Exception {

        JSONObject jObjResp = new JSONObject(objeto);
        JSONArray jsonArrayResp = jObjResp.getJSONArray("resp");

        Long idCabec = 0L;

        for (int i = 0; i < jsonArrayResp.length(); i++) {

            JSONObject objBol = jsonArrayResp.getJSONObject(i);
            Gson gsonBol = new Gson();
            RespItemAmostraBean respItemAmostraBean = gsonBol.fromJson(objBol.toString(), RespItemAmostraBean.class);

            RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
            idCabec = respItemAmostraDAO.updateRespItemAmostraEnviado(respItemAmostraBean.getIdRespItem());

        }

        return idCabec;

    }

    public void deleteCabecEnviado(){

        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        ArrayList<CabecAmostraBean> cabecAmostraArrayList = cabecAmostraDAO.cabecAmostraExcluirArrayList();

        for (CabecAmostraBean cabecAmostraBean : cabecAmostraArrayList) {

            RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
            ArrayList<Long> idRespItemAmostraArrayList = respItemAmostraDAO.idRespItemAmostraArrayList(respItemAmostraDAO.respItemAmostraList(cabecAmostraBean.getIdCabec()));
            respItemAmostraDAO.deleteRespItemAmostra(idRespItemAmostraArrayList);

            cabecAmostraDAO.deleteCabecAmostra(cabecAmostraBean.getIdCabec());

        }

        cabecAmostraArrayList.clear();

    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeAtual(tipoAtual, activity);\n" +
                "        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        ArrayList classeArrayList = classeAtual(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }

    public ArrayList<String> classeAtual(String tipoAtual, String activity){
        ArrayList<String> classeArrayList = new ArrayList();
        switch (tipoAtual) {
            case "Auditor":
                classeArrayList.add("AuditorBean");
                break;
            case "OS":
                classeArrayList.add("OSBean");
                break;
        }
        return classeArrayList;
    }

}
