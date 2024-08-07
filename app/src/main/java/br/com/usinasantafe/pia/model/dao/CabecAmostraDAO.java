package br.com.usinasantafe.pia.model.dao;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.util.Tempo;

public class CabecAmostraDAO {

    public CabecAmostraDAO() {
    }

    public boolean verCabecAberto(){
        List<CabecAmostraBean> cabecList = cabecAbertoApontList();
        boolean retorno = (cabecList.size() > 0);
        cabecList.clear();
        return retorno;
    }

    public boolean verCabecFechado(){
        List<CabecAmostraBean> cabecList = cabecFechadoList();
        boolean retorno = (!cabecList.isEmpty());
        cabecList.clear();
        return retorno;
    }

    public boolean verCabecAbertoIdAmostraOrgan(Long idAmostraOrgan){
        List<CabecAmostraBean> cabecList = cabecAbertoIdAmostraOrgan(idAmostraOrgan);
        boolean retorno = (!cabecList.isEmpty());
        cabecList.clear();
        return retorno;
    }

    public int qtdeCabecFechado(){
        List<CabecAmostraBean> cabecList = cabecFechadoList();
        int retorno = cabecList.size();
        cabecList.clear();
        return retorno;
    }

    public CabecAmostraBean getCabecId(Long idCabec){
        List<CabecAmostraBean> cabecList = cabecListId(idCabec);
        CabecAmostraBean cabecAmostraBean = cabecList.get(0);
        cabecList.clear();
        return cabecAmostraBean;
    }

    public CabecAmostraBean getCabecAbertoApont(){
        List<CabecAmostraBean> cabecList = cabecAbertoApontList();
        CabecAmostraBean cabecAmostraBean = cabecList.get(0);
        cabecList.clear();
        return cabecAmostraBean;
    }

    public CabecAmostraBean getCabecAbertoIdAmostraOrgan(Long idAmostraOrgan){
        List<CabecAmostraBean> cabecList = cabecAbertoIdAmostraOrgan(idAmostraOrgan);
        CabecAmostraBean cabecAmostraBean = cabecList.get(0);
        cabecList.clear();
        return cabecAmostraBean;
    }

    public List<CabecAmostraBean> cabecListId(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.get(pesqArrayList);
    }

    public List<CabecAmostraBean> cabecAbertoApontList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        pesqArrayList.add(getPesqCabecApont());
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.get(pesqArrayList);
    }

    public List<CabecAmostraBean> cabecAbertoIdAmostraOrgan(Long idAmostraOrgan){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdAmostraOrgan(idAmostraOrgan));
        pesqArrayList.add(getPesqCabecAberto());
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.get(pesqArrayList);
    }

    public List<CabecAmostraBean> cabecFechadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecFechado());
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.get(pesqArrayList);
    }

    public List<CabecAmostraBean> cabecEnviadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecEnviado());
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        return cabecAmostraBean.get(pesqArrayList);
    }

    public void salvarCabecAberto(Long idFunc, Long idOrg, Long idCaracOrg, Long idAmostraOrgan){
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        cabecAmostraBean.setIdFunc(idFunc);
        cabecAmostraBean.setDthr(Tempo.getInstance().dthrAtualString());
        cabecAmostraBean.setDthrLong(Tempo.getInstance().dthrAtualLong());
        cabecAmostraBean.setIdOrgan(idOrg);
        cabecAmostraBean.setIdCaracOrgan(idCaracOrg);
        cabecAmostraBean.setIdAmostraOrgan(idAmostraOrgan);
        cabecAmostraBean.setPonto(0L);
        cabecAmostraBean.setStatusPonto(2L);
        cabecAmostraBean.setStatusCabec(1L);
        cabecAmostraBean.setStatusApont(1L);
        cabecAmostraBean.insert();
        cabecAmostraBean.commit();
    }

    public void fecharCabecAbertoApont(){
        CabecAmostraBean cabecAmostraBean = getCabecAbertoApont();
        cabecAmostraBean.setStatusCabec(2L);
        cabecAmostraBean.setStatusApont(0L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
    }

    public void fecharPonto(){
        CabecAmostraBean cabecAmostraBean = getCabecAbertoApont();
        cabecAmostraBean.setStatusPonto(2L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
    }

    public void updateCabecApont(CabecAmostraBean cabecAmostraBean){
        cabecAmostraBean.setStatusApont(1L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
    }

    public void updateCabecAbertoNApont(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        List<CabecAmostraBean> cabecAmostraBeanList = cabecAmostraBean.get(pesqArrayList);
        for(CabecAmostraBean cabecAmostraBeanBD : cabecAmostraBeanList){
            cabecAmostraBeanBD.setStatusApont(0L);
            cabecAmostraBeanBD.update();
            cabecAmostraBeanBD.commit();
        }
        cabecAmostraBeanList.clear();
    }

    public void updateDeletePonto(Long idCabec){
        CabecAmostraBean cabecAmostraBean = getCabecId(idCabec);
        cabecAmostraBean.setPonto(cabecAmostraBean.getPonto() - 1L);
        cabecAmostraBean.setStatusPonto(2L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
    }

    public void updatePonto(Long idCabec, Long ponto){
        CabecAmostraBean cabecAmostraBean = getCabecId(idCabec);
        cabecAmostraBean.setPonto(ponto);
        cabecAmostraBean.setStatusPonto(1L);
        cabecAmostraBean.update();
        cabecAmostraBean.commit();
    }

    public void updateCabecEnviado(List<CabecAmostraBean> cabecAmostraList){
        for(CabecAmostraBean cabecAmostraBean : cabecAmostraList){
            CabecAmostraBean cabecAmostraBeanBD = getCabecId(cabecAmostraBean.getIdCabec());
            cabecAmostraBeanBD.setStatusCabec(3L);
            cabecAmostraBeanBD.update();
            cabecAmostraBeanBD.commit();
        }
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

        List<CabecAmostraBean> cabecAmostraList = cabecEnviadoList();
        ArrayList<CabecAmostraBean> cabecAmostraArrayList = new ArrayList<>();
        for (CabecAmostraBean cabecAmostraBeanBD : cabecAmostraList) {
            if(cabecAmostraBeanBD.getDthrLong() < Tempo.getInstance().dthrLongDiaMenos(15)) {
                cabecAmostraArrayList.add(cabecAmostraBeanBD);
            }
        }
        cabecAmostraList.clear();
        return cabecAmostraArrayList;

    }

    public void deleteCabecAberto(){
        List<CabecAmostraBean> cabecList = cabecAbertoApontList();
        for(CabecAmostraBean cabecAmostraBean : cabecList){
            cabecAmostraBean.delete();
        }
        cabecList.clear();
    }

    public ArrayList<String> cabecAmostraAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABEC");
        CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
        List<CabecAmostraBean> cabecAmostraList = cabecAmostraBean.orderBy("idCabec", true);
        for (CabecAmostraBean cabecAmostraBeanBD : cabecAmostraList) {
            dadosArrayList.add(dadosCabecAmostra(cabecAmostraBeanBD));
        }
        cabecAmostraList.clear();
        return dadosArrayList;
    }

    private String dadosCabecAmostra(CabecAmostraBean cabecAmostraBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecAmostraBean, cabecAmostraBean.getClass()).toString();
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdAmostraOrgan(Long idAmostraOrgan){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idAmostraOrgan");
        pesquisa.setValor(idAmostraOrgan);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecApont(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApont");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
