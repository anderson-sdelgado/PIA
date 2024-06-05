package br.com.usinasantafe.pia.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.bean.estaticas.AuditorBean;
import br.com.usinasantafe.pia.model.bean.estaticas.CaracOrganBean;
import br.com.usinasantafe.pia.model.bean.estaticas.OrganBean;
import br.com.usinasantafe.pia.model.bean.estaticas.PergCabecBean;
import br.com.usinasantafe.pia.model.bean.estaticas.ROrganCaracBean;
import br.com.usinasantafe.pia.model.bean.estaticas.SecaoBean;
import br.com.usinasantafe.pia.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LocalAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemCabecBean;
import br.com.usinasantafe.pia.model.dao.AmostraDAO;
import br.com.usinasantafe.pia.model.dao.AuditorDAO;
import br.com.usinasantafe.pia.model.dao.CabecAmostraDAO;
import br.com.usinasantafe.pia.model.dao.CaracOrganismoDAO;
import br.com.usinasantafe.pia.model.dao.LocalAmostraDAO;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.model.dao.OSDAO;
import br.com.usinasantafe.pia.model.dao.OrganDAO;
import br.com.usinasantafe.pia.model.dao.PergCabecDAO;
import br.com.usinasantafe.pia.model.dao.RCaracAmostraDAO;
import br.com.usinasantafe.pia.model.dao.ROrganCaracDAO;
import br.com.usinasantafe.pia.model.dao.RespItemAmostraDAO;
import br.com.usinasantafe.pia.model.dao.RespItemCabecDAO;
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
            Long idSecaoOS = osDAO.getOSNro(configCTR.getConfig().getNroOSConfig()).getIdProprAgr();
            return idSecao.equals(idSecaoOS);
        } else {
            return false;
        }
    }

    public boolean verSecaoArmadilhaCod(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.verSecaoCod(codSecao);
    }

    public boolean verTalhaCod(Long codTalhao){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return talhaoDAO.verTalhaCod(configCTR.getConfig().getIdSecaoConfig(), codTalhao);
    }

    public boolean verRespItemAmostraCabecAbertoApontList(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.verRespItemAmostraList(cabecAmostraDAO.getCabecAbertoApont().getIdCabec());
    }

    public boolean verAmostraCarac(Long idCaracOrganismo){
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        RCaracAmostraDAO rCaracAmostraDAO = new RCaracAmostraDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ROrganCaracBean rOrganCaracBean = rOrganCaracDAO.getROrganCarac(configCTR.getConfig().getIdOrganConfig(), idCaracOrganismo);
        if(rCaracAmostraDAO.verRCaracAmostraList(rOrganCaracBean.getIdROrganCarac())){
            return amostraDAO.verAmostraIdAmostraOrgan(rCaracAmostraDAO.getRCaracAmostra(rOrganCaracBean.getIdROrganCarac()).getIdAmostraOrgan());
        } else {
            return false;
        }
    }

    public int qtdeCabecFechado(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.qtdeCabecFechado();
    }

    public int qtdeItemAmostra(){
        AmostraDAO amostraDAO = new AmostraDAO();
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return amostraDAO.qtdeAmostraIdAmostraOrgan(cabecAmostraDAO.getCabecAbertoApont().getIdAmostraOrgan());
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
        ConfigCTR configCTR = new ConfigCTR();
        return talhaoDAO.getTalhaCod(configCTR.getConfig().getIdSecaoConfig(), codTalhao);
    }

    public AuditorBean getAuditor(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        AuditorDAO auditorDAO = new AuditorDAO();
        return auditorDAO.getAuditorId(cabecAmostraDAO.getCabecAbertoApont().getMatricAuditor());
    }

    public Long getIdAmostraOrgan(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.getCabecAbertoApont().getIdAmostraOrgan();
    }

    public SecaoBean getSecao(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        SecaoDAO secaoDAO = new SecaoDAO();
        LocalAmostraBean localAmostraBean = localAmostraDAO.getLocalAmostraIdCabec(cabecAmostraDAO.getCabecAbertoApont().getIdCabec());
        return secaoDAO.getSecaoId(localAmostraBean.getIdSecao());
    }

    public SecaoBean getSecaoId(Long idSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.getSecaoId(idSecao);
    }

    public TalhaoBean getTalhao(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        LocalAmostraBean localAmostraBean = localAmostraDAO.getLocalAmostraIdCabec(cabecAmostraDAO.getCabecAbertoApont().getIdCabec());
        return talhaoDAO.getTalhaId(localAmostraBean.getIdTalhao());
    }

    public TalhaoBean getTalhaoId(Long idTalhao){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhaId(idTalhao);
    }

    public List<RespItemAmostraBean> getRespItemAmostraCabecApontList(Long ponto){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.respItemAmostraList(cabecAmostraDAO.getCabecAbertoApont().getIdCabec(), ponto);
    }

    public AmostraBean getAmostraIdAmostra(Long idAmostra){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.getAmostraId(idAmostra);
    }

    public AmostraBean getItemAmostra(int posicao){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.getAmostraIdAmostraOrganSeq(cabecAmostraDAO.getCabecAbertoApont().getIdAmostraOrgan(), posicao);
    }

    public RespItemAmostraBean getRespItemAmostra(Long idRespItem){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        return respItemAmostraDAO.getRespItemAmostra(idRespItem);
    }

    public AmostraBean getAmostraId(Long idAmostraItem){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.getAmostraId(idAmostraItem);
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


    public List<CabecAmostraBean> dadosEnvioAmostra(){

        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        RespItemCabecDAO respItemCabecDAO = new RespItemCabecDAO();

        List<CabecAmostraBean> cabecAmostraList = cabecAmostraDAO.cabecFechadoList();
        for (int i = 0; i < cabecAmostraList.size(); i++) {
            List<LocalAmostraBean> localAmostraList = localAmostraDAO.localAmostraIdCabecList(cabecAmostraList.get(i).getIdCabec());
            cabecAmostraList.get(i).setLocalAmostraList(localAmostraList);
            List<RespItemAmostraBean> respItemAmostraList = respItemAmostraDAO.respItemAmostraList(cabecAmostraList.get(i).getIdCabec());
            cabecAmostraList.get(i).setRespItemAmostraList(respItemAmostraList);
            List<RespItemCabecBean> respItemCabecList = respItemCabecDAO.respItemCabecList(cabecAmostraList.get(i).getIdCabec());
            cabecAmostraList.get(i).setRespItemCabecList(respItemCabecList);
        }
        return cabecAmostraList;

    }

    public void salvarCabecAberto(Double latitude, Double longitude){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        RCaracAmostraDAO rCaracAmostraDAO = new RCaracAmostraDAO();
        ROrganCaracBean rOrganCaracBean = rOrganCaracDAO.getROrganCarac(configBean.getIdOrganConfig(), configBean.getIdCaracOrganConfig());
        Long idAmostraOrgan = rCaracAmostraDAO.getRCaracAmostra(rOrganCaracBean.getIdROrganCarac()).getIdAmostraOrgan();
        cabecAmostraDAO.salvarCabecAberto(configBean.getMatricAuditorConfig(), configBean.getIdOrganConfig(), configBean.getIdCaracOrganConfig(), idAmostraOrgan);
        localAmostraDAO.salvarLocal(cabecAmostraDAO.getCabecAbertoApont().getIdCabec(), configBean.getNroOSConfig(), configBean.getIdSecaoConfig(), configBean.getIdTalhaoConfig(), latitude, longitude, null, 2L);
    }

    public void salvarCabecAbertoArmadilha(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        ROrganCaracDAO rOrganCaracDAO = new ROrganCaracDAO();
        RCaracAmostraDAO rCaracAmostraDAO = new RCaracAmostraDAO();
        ROrganCaracBean rOrganCaracBean = rOrganCaracDAO.getROrganCarac(configBean.getIdOrganConfig(), configBean.getIdCaracOrganConfig());
        Long idAmostraOrgan = rCaracAmostraDAO.getRCaracAmostra(rOrganCaracBean.getIdROrganCarac()).getIdAmostraOrgan();
        cabecAmostraDAO.salvarCabecAberto(configBean.getMatricAuditorConfig(), configBean.getIdOrganConfig(), configBean.getIdCaracOrganConfig(), idAmostraOrgan);
    }

    public void salvarLocalAbertoArmadilha(String obs, Double latitude, Double longitude){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        localAmostraDAO.salvarLocal(cabecAmostraDAO.getCabecAbertoApont().getIdCabec(), configBean.getNroOSConfig(), configBean.getIdSecaoConfig(), configBean.getIdTalhaoConfig(), latitude, longitude, obs, 1L);
    }

    public void inserirRespItemAmostra(Long idAmostra, Long valor){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        Long idLocal = localAmostraDAO.getLocalAmostraIdCabec(cabecAmostraBean.getIdCabec()).getIdLocal();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.inserirRespItemAmostra(cabecAmostraBean.getIdCabec(), idLocal, idAmostra, valor, cabecAmostraBean.getPonto(), null);
    }

    public void inserirRespItemAmostra(Long idAmostra, String obs){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        Long idLocal = localAmostraDAO.getLocalAmostraApontIdCabec(cabecAmostraBean.getIdCabec()).getIdLocal();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.inserirRespItemAmostra(cabecAmostraBean.getIdCabec(), idLocal, idAmostra, configCTR.getConfig().getValorRespConfig(), cabecAmostraBean.getPonto(), obs);
        localAmostraDAO.fecharLocal(cabecAmostraBean.getIdCabec());
    }

    public void updateRespItemAmostra(Long idRespItem, Long valor){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.updateRespItemAmostra(idRespItem, valor, null);
    }

    public void updateRespItemAmostra(Long idRespItem, String obs){
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        respItemAmostraDAO.updateRespItemAmostra(idRespItem, configCTR.getConfig().getValorRespConfig(), obs);
    }

    public void fecharCabec(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        cabecAmostraDAO.fecharCabecAbertoApont();
    }

    public void fecharPonto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        cabecAmostraDAO.fecharPonto();
    }

    public void deleteCabecAbertoApontAll(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        RespItemCabecDAO respItemCabecDAO = new RespItemCabecDAO();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        localAmostraDAO.deleteLocalAmostraIdCabec(cabecAmostraBean.getIdCabec());
        respItemCabecDAO.deleteRespItemCabec(cabecAmostraBean.getIdCabec());
        respItemAmostraDAO.deleteRespItemAmostra(cabecAmostraBean.getIdCabec());
        cabecAmostraDAO.deleteCabecAberto();
    }

    public void deleteCabecAberto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        cabecAmostraDAO.deleteCabecAberto();
    }

    public void deleteRespItemAmostraCabecApont(Long idAmostra){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        respItemAmostraDAO.deleteRespItemAmostra(cabecAmostraBean.getIdCabec(), idAmostra, cabecAmostraBean.getPonto());
    }

    public void deleteRespItemAmostraPonto(Long ponto){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getIdAmostra() == 51L){
            LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
            Long idLocal = respItemAmostraDAO.getRespItemAmostraIdCabecPonto(cabecAmostraBean.getIdCabec(), ponto).getIdLocal();
            localAmostraDAO.deleteLocalAmostraId(idLocal);
        }
        respItemAmostraDAO.deleteRespItemAmostra(cabecAmostraBean.getIdCabec(), ponto);
        respItemAmostraDAO.updateRespItemPonto(cabecAmostraBean.getIdCabec(), ponto);
        cabecAmostraDAO.updateDeletePonto(cabecAmostraBean.getIdCabec());
    }

    public Long ponto(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        return cabecAmostraBean.getPonto();
    }

    public void updateAmostraEnviada(List<CabecAmostraBean> cabecAmostraList, String activity) {

        try {

            CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
            cabecAmostraDAO.updateCabecEnviado(cabecAmostraList);

        } catch (Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void deleteCabecEnviado(){

        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        ArrayList<CabecAmostraBean> cabecAmostraArrayList = cabecAmostraDAO.cabecAmostraExcluirArrayList();

        for (CabecAmostraBean cabecAmostraBean : cabecAmostraArrayList) {

            LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
            RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
            ArrayList<Long> idLocalAmostraArrayList = localAmostraDAO.idLocalAmostraArrayList(localAmostraDAO.localAmostraIdCabecList(cabecAmostraBean.getIdCabec()));
            localAmostraDAO.deleteLocalAmostra(idLocalAmostraArrayList);
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
            case "Secao":
                classeArrayList.add("SecaoBean");
                classeArrayList.add("TalhaoBean");
                break;
        }
        return classeArrayList;
    }

    public void clearPontoIncompleto() {
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        if(cabecAmostraBean.getStatusPonto() == 1L){
            LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
            localAmostraDAO.deleteLocalAmostraApont(cabecAmostraBean.getIdCabec());
            RespItemAmostraDAO respItemAmostraDAO = new RespItemAmostraDAO();
            respItemAmostraDAO.deleteRespItemAmostra(cabecAmostraBean.getIdCabec(), cabecAmostraBean.getPonto());
            cabecAmostraDAO.updatePonto(cabecAmostraBean.getIdCabec(), cabecAmostraBean.getPonto() - 1);
            cabecAmostraDAO.fecharPonto();
        }
    }

    public void addPonto() {
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        CabecAmostraBean cabecAmostraBean = cabecAmostraDAO.getCabecAbertoApont();
        cabecAmostraDAO.updatePonto(cabecAmostraBean.getIdCabec(), cabecAmostraBean.getPonto() + 1);
    }

    public boolean verifCabecAbertoCarac(Long idCaracOrgan) {
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        return cabecAmostraDAO.verCabecAbertoIdCaracOrgan(idCaracOrgan);
    }

    public void updateCabecAbertoCarac(Long idCaracOrgan) {
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        cabecAmostraDAO.updateCabecApont(cabecAmostraDAO.getCabecAbertoIdCaracOrgan(idCaracOrgan));
    }

    public void updateCabecAbertoNApont(){
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        cabecAmostraDAO.updateCabecAbertoNApont();
    }

    public List<PergCabecBean> getPergCabecList() {
        PergCabecDAO pergCabecDAO = new PergCabecDAO();
        return pergCabecDAO.pergCabecList();
    }

    public void salvarRespItemCabec(ArrayList<RespItemCabecBean> respItemCabecSelectedList) {
        CabecAmostraDAO cabecAmostraDAO = new CabecAmostraDAO();
        RespItemCabecDAO respItemCabecDAO = new RespItemCabecDAO();
        respItemCabecDAO.salvarRespItemCabec(respItemCabecSelectedList, cabecAmostraDAO.getCabecAbertoApont().getIdCabec());
    }

    public LocalAmostraBean getLocalAmostraId(Long idLocal) {
        LocalAmostraDAO localAmostraDAO = new LocalAmostraDAO();
        return localAmostraDAO.getLocalAmostraIdLocal(idLocal);
    }
}
