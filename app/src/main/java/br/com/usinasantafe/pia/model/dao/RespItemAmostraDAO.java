package br.com.usinasantafe.pia.model.dao;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.util.Tempo;

public class RespItemAmostraDAO {

    public RespItemAmostraDAO() {
    }

    public boolean verRespItemAmostraList(Long idLocal){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));
        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);
        boolean retorno = respItemList.size() == 0;
        respItemList.clear();
        return retorno;
    }

    public void inserirRespItemAmostra(Long idLocal, Long idAmostra, Long valor, Long qtde, String obs){
        RespItemAmostraBean respItemAmostraBean = new RespItemAmostraBean();
        respItemAmostraBean.setDthr(Tempo.getInstance().dthrAtualString());
        respItemAmostraBean.setDthrLong(Tempo.getInstance().dthrAtualLong());
        respItemAmostraBean.setIdLocal(idLocal);
        respItemAmostraBean.setIdAmostra(idAmostra);
        respItemAmostraBean.setPonto(qtde);
        respItemAmostraBean.setValor(valor);
        respItemAmostraBean.setObs(obs);
        respItemAmostraBean.insert();
        respItemAmostraBean.commit();
    }

    public void updateRespItemAmostra(Long idRespItem, Long valor, String obs){
        RespItemAmostraBean respItemAmostraBean = getRespItemAmostra(idRespItem);
        respItemAmostraBean.setValor(valor);
        respItemAmostraBean.setDthr(Tempo.getInstance().dthrAtualString());
        respItemAmostraBean.setDthrLong(Tempo.getInstance().dthrAtualLong());
        respItemAmostraBean.setObs(obs);
        respItemAmostraBean.update();
        respItemAmostraBean.commit();
    }

    public void updateRespItemPonto(Long idLocal, Long ponto){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            Long pontoBD = respItemAmostraBean.getPonto();
            if(pontoBD > ponto) {
                respItemAmostraBean.setPonto(pontoBD - 1L);
                respItemAmostraBean.update();
                respItemAmostraBean.commit();
            }
        }

        respItemList.clear();
    }


    public void deleteRespItemAmostra(Long idLocal, Long idAmostra, Long ponto){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));
        pesqArrayList.add(getPesqIdAmostra(idAmostra));
        pesqArrayList.add(getPesqPonto(ponto));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemAmostraList = respItemAmostra.get(pesqArrayList);
        respItemAmostra = respItemAmostraList.get(0);
        respItemAmostra.delete();
        respItemAmostra.commit();

        respItemAmostraList.clear();
    }

    public void deleteRespItemAmostra(Long idLocal, Long ponto){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));
        pesqArrayList.add(getPesqPonto(ponto));

        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        List<RespItemAmostraBean> respItemList = respItemAmostra.get(pesqArrayList);

        for (RespItemAmostraBean respItemAmostraBean : respItemList) {
            respItemAmostraBean.delete();
            respItemAmostraBean.commit();
        }

        respItemList.clear();
    }

    public void deleteRespItemAmostra(Long idLocal){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));

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

    public List<RespItemAmostraBean> respItemAmostraList(Long idLocal){
        RespItemAmostraBean respItemAmostra = new RespItemAmostraBean();
        return respItemAmostra.getAndOrderBy("idLocal", idLocal, "idRespItemAmostra", true);
    }

    public List<RespItemAmostraBean> respItemAmostraList(ArrayList<Long> idRespItemAmostraArrayList){
        RespItemAmostraBean respItemAmostraBean = new RespItemAmostraBean();
        return respItemAmostraBean.in("idRespItem", idRespItemAmostraArrayList);
    }

    public List<RespItemAmostraBean> respItemAmostraList(Long idLocal, Long ponto){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));
        pesqArrayList.add(getPesqPonto(ponto));

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
            idRespItemAmostraList.add(respItemAmostra.getIdRespItemAmostra());
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
        pesquisa.setCampo("idRespItemAmostra");
        pesquisa.setValor(idRespItem);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdLocal(Long idLocal){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idLocal");
        pesquisa.setValor(idLocal);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqPonto(Long ponto){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("ponto");
        pesquisa.setValor(ponto);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdAmostra(Long idAmostra){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idAmostra");
        pesquisa.setValor(idAmostra);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
