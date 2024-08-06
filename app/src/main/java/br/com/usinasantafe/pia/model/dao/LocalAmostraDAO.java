package br.com.usinasantafe.pia.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LocalAmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.util.Tempo;

public class LocalAmostraDAO {

    public LocalAmostraBean getLocalAmostraIdCabec(Long idCabec) {
        List<LocalAmostraBean> localAmostraList = localAmostraIdCabecList(idCabec);
        LocalAmostraBean localAmostraBean = localAmostraList.get(0);
        localAmostraList.clear();
        return localAmostraBean;
    }

    public LocalAmostraBean getLocalAmostraApontIdCabec(Long idCabec) {
        List<LocalAmostraBean> localAmostraList = localAmostraApontIdCabecList(idCabec);
        LocalAmostraBean localAmostraBean = localAmostraList.get(0);
        localAmostraList.clear();
        return localAmostraBean;
    }

    public List<LocalAmostraBean> localAmostraIdCabecList(Long idCabec) {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        return localAmostraBean.get(pesqArrayList);
    }

    public List<LocalAmostraBean> localAmostraApontIdCabecList(Long idCabec) {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqApont());
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        return localAmostraBean.get(pesqArrayList);
    }

    public void salvarLocal(Long idCabec, Long nroOS, Long idSecao, Long idTalhao, Double latitude, Double longitude, String obs, Long status) {
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        localAmostraBean.setIdCabec(idCabec);
        localAmostraBean.setDthr(Tempo.getInstance().dthrAtualString());
        localAmostraBean.setDthrLong(Tempo.getInstance().dthrAtualLong());
        localAmostraBean.setNroOS(nroOS);
        localAmostraBean.setIdSecao(idSecao);
        localAmostraBean.setIdTalhao(idTalhao);
        localAmostraBean.setLatitude(latitude);
        localAmostraBean.setLongitude(longitude);
        localAmostraBean.setStatusApont(status);
        localAmostraBean.setObs(obs);
        localAmostraBean.insert();
        localAmostraBean.commit();
    }

    public void updateLocalApont(LocalAmostraBean localAmostraBean){
        localAmostraBean.setStatusApont(1L);
        localAmostraBean.update();
        localAmostraBean.commit();
    }

    public void deleteLocalAmostraApont(Long idCabec) {

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqApont());

        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        List<LocalAmostraBean> locaAmostraList = localAmostraBean.get(pesqArrayList);

        for (LocalAmostraBean localAmostraBeanBD : locaAmostraList) {
            localAmostraBeanBD.delete();
            localAmostraBeanBD.commit();
        }

        locaAmostraList.clear();
    }

    public void deleteLocalAmostraIdCabec(Long idCabec) {

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

    public void deleteLocalAmostraId(Long idLocal) {

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdLocal(idLocal));

        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        List<LocalAmostraBean> locaAmostraList = localAmostraBean.get(pesqArrayList);

        for (LocalAmostraBean localAmostraBeanBD : locaAmostraList) {
            localAmostraBeanBD.delete();
            localAmostraBeanBD.commit();
        }

        locaAmostraList.clear();
    }

    public void deleteLocalAmostra(ArrayList<Long> idLocalAmostraArrayList) {

        List<LocalAmostraBean> localAmostraList = localAmostraIdCabecList(idLocalAmostraArrayList);

        for (LocalAmostraBean localAmostraBean : localAmostraList) {
            localAmostraBean.delete();
        }

        localAmostraList.clear();
        idLocalAmostraArrayList.clear();

    }


    public ArrayList<Long> idLocalAmostraArrayList(List<LocalAmostraBean> localAmostraList) {
        ArrayList<Long> idLocalAmostraList = new ArrayList<>();
        for (LocalAmostraBean respItemAmostra : localAmostraList) {
            idLocalAmostraList.add(respItemAmostra.getIdLocal());
        }
        return idLocalAmostraList;
    }

    public List<LocalAmostraBean> localAmostraIdCabecList(ArrayList<Long> idCabec) {
        LocalAmostraBean localAmostraBean = new LocalAmostraBean();
        ArrayList pesqArrayList = new ArrayList();
        return localAmostraBean.inAndGetAndOrderBy("idCabec", idCabec, pesqArrayList, "idLocal", true);
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec) {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdLocal(Long idLocal) {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idLocal");
        pesquisa.setValor(idLocal);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqApont() {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApont");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
