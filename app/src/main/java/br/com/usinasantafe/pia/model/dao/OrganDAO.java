package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.OrganBean;

public class OrganDAO {

    public OrganDAO() {
    }

    public List<OrganBean> organismoList(){
        OrganBean organBean = new OrganBean();
        return organBean.all();
    }

}
