package br.com.usinasantafe.pia.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */

@DatabaseTable(tableName="tbrespitemvar")
public class RespItemAmostraTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRespItem;
    @DatabaseField
    private Long idCabecRespItem;
    @DatabaseField
    private Long idAmostraRespItem;
    @DatabaseField
    private Long pontoRespItem;
    @DatabaseField
    private Long valorRespItem;

    public RespItemAmostraTO() {
    }

    public Long getIdRespItem() {
        return idRespItem;
    }

    public Long getIdCabecRespItem() {
        return idCabecRespItem;
    }

    public void setIdCabecRespItem(Long idCabecRespItem) {
        this.idCabecRespItem = idCabecRespItem;
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

}
