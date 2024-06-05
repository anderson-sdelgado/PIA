package br.com.usinasantafe.pia.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.RespItemCabecBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;

public class RespItemCabecDAO {

    public RespItemCabecDAO() {
    }

    public void salvarRespItemCabec(ArrayList<RespItemCabecBean> respItemCabecSelectedList, Long idCabec) {
        for(RespItemCabecBean respItemCabecBean : respItemCabecSelectedList){
            respItemCabecBean.setIdCabec(idCabec);
            respItemCabecBean.insert();
            respItemCabecBean.commit();
        }
    }

    public void deleteRespItemCabec(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        RespItemCabecBean respItemCabec = new RespItemCabecBean();
        List<RespItemCabecBean> respItemList = respItemCabec.get(pesqArrayList);

        for (RespItemCabecBean respItemCabecBeanBD : respItemList) {
            respItemCabecBeanBD.delete();
            respItemCabecBeanBD.commit();
        }

        respItemList.clear();
    }

    public List<RespItemCabecBean> respItemCabecList(Long idCabec){
        RespItemCabecBean respItemCabec = new RespItemCabecBean();
        return respItemCabec.getAndOrderBy("idCabec", idCabec, "idRespItemCabec", true);
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
