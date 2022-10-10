package br.com.usinasantafe.pia.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.ROrganCaracBean;
import br.com.usinasantafe.pia.model.pst.EspecificaPesquisa;

public class ROrganCaracDAO {

    public ROrganCaracDAO() {
    }

    public ArrayList<Long> idCaracOrganArrayList(Long idOrgCabec){

        ROrganCaracBean rOrganCaracBean = new ROrganCaracBean();
        List<ROrganCaracBean> rOrganCaracList = rOrganCaracBean.get("idOrgan", idOrgCabec);

        ArrayList<Long> idCaracOrganArrayList = new ArrayList<Long>();

        for (ROrganCaracBean rOrganCaracBeanBD : rOrganCaracList) {
            idCaracOrganArrayList.add(rOrganCaracBeanBD.getIdCaracOrgan());
        }

        rOrganCaracList.clear();
        return idCaracOrganArrayList;

    }

    public ROrganCaracBean getROrganCarac(Long idOrgan, Long idCaracOrgan){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdOrgCabec(idOrgan));
        pesqArrayList.add(getPesqIdCaracOrganismo(idCaracOrgan));

        ROrganCaracBean rOrganCaracBean = new ROrganCaracBean();
        List<ROrganCaracBean> rOrganismoCaracList = rOrganCaracBean.get(pesqArrayList);
        rOrganCaracBean = rOrganismoCaracList.get(0);
        rOrganismoCaracList.clear();
        return rOrganCaracBean;
    }


    private EspecificaPesquisa getPesqIdOrgCabec(Long idOrgan){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idOrgan");
        pesquisa.setValor(idOrgan);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCaracOrganismo(Long idCaracOrgan){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCaracOrgan");
        pesquisa.setValor(idCaracOrgan);
        pesquisa.setTipo(1);
        return pesquisa;
    }



}
