package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.PergCabecBean;

public class PergCabecDAO {

    public PergCabecDAO() {
    }

    public List<PergCabecBean> pergCabecList() {
        PergCabecBean pergCabecBean = new PergCabecBean();
        return pergCabecBean.all();
    }



}
