package br.com.usinasantafe.pia.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

/**
 * Created by anderson on 30/03/2017.
 */

@DatabaseTable(tableName="tbitemvar")
public class ItemAmostraTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItem;
    @DatabaseField
    private Long idCabecItem;
    @DatabaseField
    private Long idAmostraItem;
    @DatabaseField
    private String descrItem;
    @DatabaseField
    private Long valorAmostraItem;
    @DatabaseField
    private Long tipoAmostraItem;

    public ItemAmostraTO() {
    }

    public Long getIdItem() {
        return idItem;
    }

    public Long getIdCabecItem() {
        return idCabecItem;
    }

    public void setIdCabecItem(Long idCabecItem) {
        this.idCabecItem = idCabecItem;
    }

    public Long getIdAmostraItem() {
        return idAmostraItem;
    }

    public void setIdAmostraItem(Long idAmostraItem) {
        this.idAmostraItem = idAmostraItem;
    }

    public String getDescrItem() {
        return descrItem;
    }

    public void setDescrItem(String descrItem) {
        this.descrItem = descrItem;
    }

    public Long getValorAmostraItem() {
        return valorAmostraItem;
    }

    public void setValorAmostraItem(Long valorAmostraItem) {
        this.valorAmostraItem = valorAmostraItem;
    }

    public Long getTipoAmostraItem() {
        return tipoAmostraItem;
    }

    public void setTipoAmostraItem(Long tipoAmostraItem) {
        this.tipoAmostraItem = tipoAmostraItem;
    }
}
