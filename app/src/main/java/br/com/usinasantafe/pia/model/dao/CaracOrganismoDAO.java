package br.com.usinasantafe.pia.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.CaracOrganBean;

public class CaracOrganismoDAO {

    public CaracOrganismoDAO() {
    }

    public List<CaracOrganBean> caracOrganismoList(ArrayList<Long> idCaracOrganArrayList){
        CaracOrganBean caracOrganBean = new CaracOrganBean();
        return caracOrganBean.in("idCaracOrgan", idCaracOrganArrayList);
    }



}
