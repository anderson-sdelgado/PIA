package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */

@DatabaseTable(tableName="tbrespitemvar")
public class RespItemAmostraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRespItem;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idAmostraRespItem;
    @DatabaseField
    private Long pontoRespItem;
    @DatabaseField
    private Long valorRespItem;
    @DatabaseField
    private Long statusRespItem; // 1 - Aberto; 2 - Fechado; 3 - Enviado

    public RespItemAmostraBean() {
    }

    public Long getIdRespItem() {
        return idRespItem;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdAmostraRespItem() {
        return idAmostraRespItem;
    }

    public void setIdAmostraRespItem(Long idAmostraRespItem) {
        this.idAmostraRespItem = idAmostraRespItem;
    }

    public Long getPontoRespItem() {
        return pontoRespItem;
    }

    public void setPontoRespItem(Long pontoRespItem) {
        this.pontoRespItem = pontoRespItem;
    }

    public Long getValorRespItem() {
        return valorRespItem;
    }

    public void setValorRespItem(Long valorRespItem) {
        this.valorRespItem = valorRespItem;
    }

    public Long getStatusRespItem() {
        return statusRespItem;
    }

    public void setStatusRespItem(Long statusRespItem) {
        this.statusRespItem = statusRespItem;
    }
}
