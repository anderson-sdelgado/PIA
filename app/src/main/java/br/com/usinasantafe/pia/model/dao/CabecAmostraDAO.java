package br.com.usinasantafe.pia.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.util.Tempo;

public class CabecAmostraDAO {

    public CabecAmostraDAO() {
    }

    public boolean verCabecAberto(){
        List<CabecAmostraBean> cabecList = cabecAbertoList();
        boolean retorno = (cabecList.size() > 0);
        cabecList.clear();
        return retorno;
    }

    public boolean verCabecFechado(){
        List<CabecAmostraBean> cabecList = cabecFechadoList();
        boolean retorno = (cabecList.size() > 0);
        cabecList.clear();
        return retorno;
    }

    public int qtdeCabecFechado(){
        List<CabecAmostraBean> cabecList = cabecFechadoList();
        int retorno = cabecList.size();
        cabecList.clear();
        return retorno;
    }

    public CabecAmostraBean getCabecAberto(){
        List<CabecAmostraBean> cabecList = cabecAbertoList();
        CabecAmostraBean cabecAmostraBean = cabecList.get(0);
        cabecList.clear();
        return cabecAmostraBean;
    }

    public List<CabecAmostraBean> cabecAbertoList(){
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.get("statusAmostra", 1L);
    }

    public List<CabecAmostraBean> cabecFechadoList(){
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.getAndOrderBy("statusAmostra", 2L, "idCabec", true);
    }

    public String dadosEnvioCabecAmostraFechado(){
        return dadosCabecAmostra(cabecFechadoList());
    }

    private String dadosCabecAmostra(List<CabecAmostraBean> cabecAmostraList){

        JsonArray cabecAmostraJsonArray = new JsonArray();

        for (CabecAmostraBean cabecAmostraBean : cabecAmostraList) {
            Gson gsonCabec = new Gson();
            cabecAmostraJsonArray.add(gsonCabec.toJsonTree(cabecAmostraBean, cabecAmostraBean.getClass()));
        }

        cabecAmostraList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("cabec", cabecAmostraJsonArray);

        return jsonBoletim.toString();
    }

    public ArrayList<Long> idCabecAmostraArrayList(List<CabecAmostraBean> cabecAmostraList){
        ArrayList<Long> idCabecAmostraList = new ArrayList<Long>();
        for (CabecAmostraBean cabecAmostraBean : cabecAmostraList) {
            idCabecAmostraList.add(cabecAmostraBean.getIdCabec());
        }
        cabecAmostraList.clear();
        return idCabecAmostraList;
    }

    public void salvarCabecAberto(Long idAuditor, Long nroOS, Long idSecao, Long idTalhao, Long idOrg, Long idCaracOrg){
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        cabecAmostraBean.setDthrCabec(Tempo.getInstance().dthrAtualString());
        cabecAmostraBean.setDthrLongCabec(Tempo.getInstance().dthrAtualLong());
        cabecAmostraBean.setAuditorCabec(idAuditor);
        cabecAmostraBean.setOsCabec(nroOS);
        cabecAmostraBean.setSecaoCabec(idSecao);
        cabecAmostraBean.setTalhaoCabec(idTalhao);
        cabecAmostraBean.setIdOrganCabec(idOrg);
        cabecAmostraBean.setIdCaracOrganCabec(idCaracOrg);
        cabecAmostraBean.setStatusAmostra(1L);
        cabecAmostraBean.insert();
        cabecAmostraBean.commit();
    }

    public void fecharCabec(){
        CabecAmostraBean cabecAmostraBean = getCabecAberto();
        cabecAmostraBean.setStatusAmostra(2L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
    }

    public void updateCabecEnviado(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        List<CabecAmostraBean> cabecAmostraList = cabecAmostraBean.get(pesqArrayList);
        cabecAmostraBean = cabecAmostraList.get(0);
        cabecAmostraBean.setStatusAmostra(3L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
        cabecAmostraList.clear();

    }

    public void deleteCabecAmostra(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        List<CabecAmostraBean> cabecAmostraList = cabecAmostraBean.get(pesqArrayList);
        cabecAmostraBean = cabecAmostraList.get(0);
        cabecAmostraBean.delete();
        cabecAmostraList.clear();

    }

    public ArrayList<CabecAmostraBean> cabecAmostraExcluirArrayList(){

        List<CabecAmostraBean> cabecAmostraList = cabecFechadoList();
        ArrayList<CabecAmostraBean> cabecAmostraArrayList = new ArrayList<>();
        for (CabecAmostraBean cabecAmostraBeanBD : cabecAmostraList) {
            if(cabecAmostraBeanBD.getDthrLongCabec() < Tempo.getInstance().dthrLongDiaMenos(15)) {
                cabecAmostraArrayList.add(cabecAmostraBeanBD);
            }
        }
        cabecAmostraList.clear();
        return cabecAmostraArrayList;

    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }


    public void deleteCabecAberto(){
        List<CabecAmostraBean> cabecList = cabecAbertoList();
        for(CabecAmostraBean cabecAmostraBean : cabecList){
            cabecAmostraBean.delete();
        }
        cabecList.clear();
    }

    public void deleteCabecFechado(CabecAmostraBean cabecAmostraBean){
        cabecAmostraBean.delete();
    }

}
