package br.com.usinasantafe.pia.model.dao;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.ItemAmostraBean;

public class ItemAmostraDAO {

    public ItemAmostraDAO() {
    }

    public boolean hasElements(){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        return itemAmostraBean.hasElements();
    }

    public int qtdeItemAmostra(){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        List<ItemAmostraBean>  itemAmostraList = itemAmostraBean.all();
        int retorno = itemAmostraList.size();
        itemAmostraList.clear();
        return retorno;
    }

    public List<ItemAmostraBean> itemAmostraIdList(Long idAmostraItem){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        return itemAmostraBean.get("idAmostraItem", idAmostraItem);
    }

    public ItemAmostraBean getItemAmostraId(Long idAmostraItem){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        List<ItemAmostraBean>  itemAmostraList = itemAmostraBean.get("idAmostraItem", idAmostraItem);
        itemAmostraBean = itemAmostraList.get(0);
        itemAmostraList.clear();
        return itemAmostraBean;
    }

    public ItemAmostraBean getItemAmostra(){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        List<ItemAmostraBean>  itemAmostraList = itemAmostraBean.get("tipoAmostraItem", 3L);
        itemAmostraBean = itemAmostraList.get(0);
        itemAmostraList.clear();
        return itemAmostraBean;
    }

    public ItemAmostraBean getItemAmostra(int posicao){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        List<ItemAmostraBean>  itemAmostraList = itemAmostraBean.get("tipoAmostraItem", 3L);
        itemAmostraBean = itemAmostraList.get(posicao);
        itemAmostraList.clear();
        return itemAmostraBean;
    }

    public ItemAmostraBean getItemAmostraCabec(){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        List<ItemAmostraBean>  itemAmostraList = itemAmostraBean.get("tipoAmostraItem", 2L);
        itemAmostraBean = itemAmostraList.get(0);
        itemAmostraList.clear();
        return itemAmostraBean;
    }

    public void updateItemAmostraCabec(Long valor){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        List<ItemAmostraBean>  itemAmostraList = itemAmostraBean.get("tipoAmostraItem", 2L);
        itemAmostraBean = itemAmostraList.get(0);
        itemAmostraList.clear();
        itemAmostraBean.setValorAmostraItem(valor);
        itemAmostraBean.update();
        itemAmostraBean.commit();
    }

    public boolean inserirItemAmostra(Long idCabec, List<AmostraBean> amostraList){

        ItemAmostraBean itemAmostraDelBean = new ItemAmostraBean();
        itemAmostraDelBean.deleteAll();

        boolean verItemCabec = false;

        for (AmostraBean amostraBean : amostraList) {

            ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
            itemAmostraBean.setIdCabec(idCabec);
            itemAmostraBean.setDescrItem(amostraBean.getDescrAmostra());
            itemAmostraBean.setIdAmostraItem(amostraBean.getIdAmostra());
            itemAmostraBean.setTipoAmostraItem(amostraBean.getTipoAmostra());
            itemAmostraBean.setValorAmostraItem(0L);
            itemAmostraBean.insert();
            itemAmostraBean.commit();

            if(amostraBean.getTipoAmostra() == 2){
                verItemCabec = true;
            }

        }

        return verItemCabec;

    }

    public void deleteItemAmostra(){
        ItemAmostraBean itemAmostraBean = new ItemAmostraBean();
        itemAmostraBean.deleteAll();
        itemAmostraBean.commit();
    }

}
