package br.com.usinasantafe.pia.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.LocalAmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.util.Tempo;

public class LocalAmostraDAO {

    public LocalAmostraBean getLocalAmostra(Long idCabec){
        List<LocalAmostraBean> localAmostraList = localAmostraList(idCabec);
        LocalAmostraBean localAmostraBean = localAmostraList.get(0);
        localAmostraList.clear();
        return localAmostraBean;
    }

    public int qtdeLocalCabec(Long idCabec){
        List<LocalAmostraBean> localAmostraList = localAmostraList(idCabec);
        int retorno = localAmostraList.size();
        localAmostraList.clear();
        return retorno;
    }

    public List<LocalAmostraBean> localAmostraList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        return localAmostraBean.get(pesqArrayList);
    }

    public void salvarLocal(Long idCabec, Long nroOS, Long idSecao, Long idTalhao){
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        localAmostraBean.setIdCabec(idCabec);
        localAmostraBean.setDthr(Tempo.getInstance().dthrAtualString());
        localAmostraBean.setDthrLong(Tempo.getInstance().dthrAtualLong());
        localAmostraBean.setNroOS(nroOS);
        localAmostraBean.setIdSecao(idSecao);
        localAmostraBean.setIdTalhao(idTalhao);
        localAmostraBean.insert();
        localAmostraBean.commit();
    }

    public void updateLocalEnviado(Long idLocal){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));

        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        List<LocalAmostraBean> localAmostraList = localAmostraBean.get(pesqArrayList);
        localAmostraBean = localAmostraList.get(0);
        localAmostraBean.setStatusLocal(3L);
        localAmostraBean.update();
        localAmostraBean.commit();
        localAmostraList.clear();

    }

    public void deleteLocalAmostra(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        List<LocalAmostraBean> locaAmostraList = localAmostraBean.get(pesqArrayList);

        for (LocalAmostraBean localAmostraBeanBD : locaAmostraList) {
            localAmostraBeanBD.delete();
            localAmostraBeanBD.commit();
        }

        locaAmostraList.clear();
    }

    public void deleteLocalAmostra(ArrayList<Long> idLocalAmostraArrayList){

        List<LocalAmostraBean> localAmostraList = localAmostraList(idLocalAmostraArrayList);

        for (LocalAmostraBean localAmostraBean : localAmostraList) {
            localAmostraBean.delete();
        }

        localAmostraList.clear();
        idLocalAmostraArrayList.clear();

    }

    public void fecharLocal(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        List<LocalAmostraBean> locaAmostraList = localAmostraBean.get(pesqArrayList);

        for (LocalAmostraBean localAmostraBeanBD : locaAmostraList) {
            localAmostraBeanBD.setStatusLocal(2L);
            localAmostraBeanBD.update();
        }

        locaAmostraList.clear();
    }

    public ArrayList<Long> idLocalAmostraArrayList(List<LocalAmostraBean> localAmostraList){
        ArrayList<Long> idLocalAmostraList = new ArrayList<>();
        for (LocalAmostraBean respItemAmostra : localAmostraList) {
            idLocalAmostraList.add(respItemAmostra.getIdLocal());
        }
        return idLocalAmostraList;
    }

    public List<LocalAmostraBean> localAmostraList(ArrayList<Long> idCabec){
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        ArrayList pesqArrayList = new ArrayList();
        return localAmostraBean.inAndGetAndOrderBy("idCabec", idCabec, pesqArrayList, "idLocal", true);
    }

    public String dadosEnvioLocalAmostra(List<LocalAmostraBean> localAmostraList){

        JsonArray localAmostraJsonArray = new JsonArray();

        for (LocalAmostraBean localAmostraBean : localAmostraList) {
            Gson gsonCabec = new Gson();
            localAmostraJsonArray.add(gsonCabec.toJsonTree(localAmostraBean, localAmostraBean.getClass()));
        }

        localAmostraList.clear();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("local", localAmostraJsonArray);

        return jsonObject.toString();
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
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

}
