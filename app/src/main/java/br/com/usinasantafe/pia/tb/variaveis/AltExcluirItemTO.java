package br.com.usinasantafe.pia.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

@DatabaseTable(tableName="tbedititemvar")
public class AltExcluirItemTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idAltExcluirItem;
    @DatabaseField
    private Long idRespItem;
    @DatabaseField
    private Long idCabecRespItem;
    @DatabaseField
    private Long idAmostraRespItem;
    @DatabaseField
    private Long pontoRespItem;
    @DatabaseField
    private Long valorRespItem;
    @DatabaseField
    private Long tipoAltExc;

    public AltExcluirItemTO() {
    }

    public Long getIdRespItem() {
        return idRespItem;
    }

    public void setIdRespItem(Long idRespItem) {
        this.idRespItem = idRespItem;
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

    public Long getTipoAltExc() {
        return tipoAltExc;
    }

    public void setTipoAltExc(Long tipoAltExc) {
        this.tipoAltExc = tipoAltExc;
    }
}
