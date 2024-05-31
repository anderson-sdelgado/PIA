package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbrespitemcabecvar")
public class RespItemCabecBean {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRespItemCabec;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idItemCabec;
    @DatabaseField
    private Long idRespItem;

    public Long getIdRespItemCabec() {
        return idRespItemCabec;
    }

    public void setIdRespItemCabec(Long idRespItemCabec) {
        this.idRespItemCabec = idRespItemCabec;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdItemCabec() {
        return idItemCabec;
    }

    public void setIdItemCabec(Long idItemCabec) {
        this.idItemCabec = idItemCabec;
    }

    public Long getIdRespItem() {
        return idRespItem;
    }

    public void setIdRespItem(Long idRespItem) {
        this.idRespItem = idRespItem;
    }
}
