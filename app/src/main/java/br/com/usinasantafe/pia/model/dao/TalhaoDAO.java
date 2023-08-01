package br.com.usinasantafe.pia.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;

public class TalhaoDAO {

    public TalhaoDAO() {
    }

    public boolean verTalhaCod(Long idSecao, Long codTalhao){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdSecao(idSecao));
        pesqArrayList.add(getPesqCod(codTalhao));

        TalhaoBean talhaoBean = new TalhaoBean();
        List<TalhaoBean> talhaoList = talhaoBean.get(pesqArrayList);
        boolean retorno = (talhaoList.size() > 0);
        talhaoList.clear();
        return retorno;

    }

    public TalhaoBean getTalhaCod(Long idSecao, Long codTalhao){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdSecao(idSecao));
        pesqArrayList.add(getPesqCod(codTalhao));

        TalhaoBean talhaoBean = new TalhaoBean();
        List<TalhaoBean> talhaoList = talhaoBean.get(pesqArrayList);
        talhaoBean = talhaoList.get(0);
        talhaoList.clear();
        return talhaoBean;

    }

    public TalhaoBean getTalhaId(Long idTalhao){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqId(idTalhao));

        TalhaoBean talhaoBean = new TalhaoBean();
        List<TalhaoBean> talhaoList = talhaoBean.get(pesqArrayList);
        talhaoBean = talhaoList.get(0);
        talhaoList.clear();
        return talhaoBean;

    }


    private EspecificaPesquisa getPesqIdSecao(Long idSecao){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idSecao");
        pesquisa.setValor(idSecao);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCod(Long codTalhao){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codTalhao");
        pesquisa.setValor(codTalhao);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqId(Long idTalhao){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idTalhao");
        pesquisa.setValor(idTalhao);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
