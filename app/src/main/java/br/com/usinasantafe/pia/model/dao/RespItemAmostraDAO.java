package br.com.usinasantafe.pia.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;

public class RespItemAmostraDAO {

    public RespItemAmostraDAO() {
    }

    public boolean verRespItemAmostraList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);
        boolean retorno = respItemList.size() > 0;
        respItemList.clear();
        return retorno;
    }

    public boolean verRespItemAmostraFechado(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqStatusFechado());

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);
        boolean retorno = respItemList.size() > 0;
        respItemList.clear();
        return retorno;
    }

    public void inserirRespItemAmostra(Long idCabec, Long idAmostra, Long valor, Long qtde){
//        Random rand = new Random();
//        int randomNum = rand.nextInt((50 - 1) + 1) + 1;
        for(int i = 0; i < 60; i++){
            RespItemAmostraBean respItemAmostraBean = new RespItemAmostraBean();
            respItemAmostraBean.setIdCabec(idCabec);
            respItemAmostraBean.setIdAmostraRespItem(idAmostra);
            respItemAmostraBean.setPontoRespItem(qtde + i);
            respItemAmostraBean.setValorRespItem(valor);
            respItemAmostraBean.setStatusRespItem(1L);
            respItemAmostraBean.insert();
            respItemAmostraBean.commit();
        }
    }

    public void updateRespItemAmostra(Long idRespItem, Long valor){
        RespItemAmostraBean respItemAmostraBean = getRespItemAmostra(idRespItem);
        respItemAmostraBean.setValorRespItem(valor);
        respItemAmostraBean.insert();
        respItemAmostraBean.commit();
    }

    public void updateRespItemAmostraFechado(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqStatusAberto());

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            respItemAmostraBean.setStatusRespItem(2L);
            respItemAmostraBean.update();
            respItemAmostraBean.commit();
        }

        respItemList.clear();
    }


    public Long updateRespItemAmostraEnviado(Long idRespItem){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqId(idRespItem));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);
        RespItemAmostraBean respItemAmostraBean = respItemList.get(0);
        respItemAmostraBean.setStatusRespItem(3L);
        respItemAmostraBean.update();
        respItemAmostraBean.commit();
        respItemList.clear();

        return respItemAmostraBean.getIdCabec();

    }

    public void deleteRespItemAmostraAberto(Long idCabec, Long idAmostra){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqStatusAberto());
        pesqArrayList.add(getPesqIdAmostraItem(idAmostra));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            respItemAmostraBean.delete();
            respItemAmostraBean.commit();
        }

        respItemList.clear();
    }

    public void deleteRespItemAmostraAberto(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqStatusAberto());

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            respItemAmostraBean.delete();
            respItemAmostraBean.commit();
        }

        respItemList.clear();
    }

    public void deleteRespItemAmostra(Long idCabec, Long ultPonto){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqUltPonto(ultPonto));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            respItemAmostraBean.delete();
            respItemAmostraBean.commit();
        }

        respItemList.clear();
    }

    public void deleteRespItemAmostra(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            respItemAmostraBean.delete();
            respItemAmostraBean.commit();
        }

        respItemList.clear();
    }

    public void deleteRespItemAmostra(ArrayList<Long> idRespItemAmostraArrayList){

        List<RespItemAmostraBean> respItemAmostraList = respItemAmostraList(idRespItemAmostraArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemAmostraList) {
            respItemAmostraBean.delete();
        }

        respItemAmostraList.clear();
        idRespItemAmostraArrayList.clear();

    }

    public RespItemAmostraBean getUltRespItemAmostra(Long idCabec){
        List<RespItemAmostraBean> respItemList = respItemAmostraFechadoList(idCabec);
        RespItemAmostraBean respItemAmostra = respItemList.get(0);
        respItemList.clear();
        return respItemAmostra;
    }

    public List<RespItemAmostraBean> respItemAmostraFechadoList(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqStatusFechado());

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        return respItemAmostra.getAndOrderBy(pesqArrayList, "idRespItem", false);
    }

    public List<RespItemAmostraBean> respItemAmostraList(Long idCabec){
        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        return respItemAmostra.getAndOrderBy("idCabec", idCabec, "idRespItem", false);
    }

    public List<RespItemAmostraBean> respItemAmostraList(ArrayList<Long> idRespItemAmostraArrayList){
        RespItemAmostraBean respItemAmostraBean = new RespItemAmostraBean();
        return respItemAmostraBean.in("idRespItem", idRespItemAmostraArrayList);
    }

    public List<RespItemAmostraBean> respRespItemAmostraList(ArrayList<Long> idCabec){
        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechado());
        return respItemAmostra.inAndGetAndOrderBy("idCabec", idCabec, pesqArrayList, "idRespItem", true);
    }

    public String dadosEnvioRespItemAmostra(List<RespItemAmostraBean> respItemList){

        JsonArray jsonArrayApont = new JsonArray();

        int i = 0;
        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            Gson gsonItemImp = new Gson();
            jsonArrayApont.add(gsonItemImp.toJsonTree(respItemAmostraBean, respItemAmostraBean.getClass()));
            i++;
            if(i==50){
                break;
            }
        }

        respItemList.clear();

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("resp", jsonArrayApont);

        return jsonApont.toString();

    }

    public List<RespItemAmostraBean> respItemAmostraList(Long idCabec, Long ponto){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqUltPonto(ponto));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        return respItemAmostra.get(pesqArrayList);

    }

    public RespItemAmostraBean getRespItemAmostra(Long idRespItem){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqId(idRespItem));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);
        RespItemAmostraBean respItemAmostraBean = respItemList.get(0);
        respItemList.clear();
        return respItemAmostraBean;

    }

    public ArrayList<Long> idRespItemAmostraArrayList(List<RespItemAmostraBean> respItemAmostraList){
        ArrayList<Long> idRespItemAmostraList = new ArrayList<Long>();
        for (RespItemAmostraBean respItemAmostra : respItemAmostraList) {
            idRespItemAmostraList.add(respItemAmostra.getIdRespItem());
        }
        return idRespItemAmostraList;
    }

    public ArrayList<String> respAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("RESP");
        RespItemAmostraBean respItemAmostraBean = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemAmostraList = respItemAmostraBean.orderBy("idRespItem", true);
        for (RespItemAmostraBean respItemAmostraBeanBD : respItemAmostraList) {
            dadosArrayList.add(dadosResp(respItemAmostraBeanBD));
        }
        respItemAmostraList.clear();
        return dadosArrayList;
    }

    private String dadosResp(RespItemAmostraBean respItemAmostraBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(respItemAmostraBean, respItemAmostraBean.getClass()).toString();
    }

    private EspecificaPesquisa getPesqId(Long idRespItem){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idRespItem");
        pesquisa.setValor(idRespItem);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusRespItem");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusRespItem");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqUltPonto(Long ultPonto){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("pontoRespItem");
        pesquisa.setValor(ultPonto + 1);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdAmostraItem(Long idAmostraItem){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idAmostraRespItem");
        pesquisa.setValor(idAmostraItem);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
