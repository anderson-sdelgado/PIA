package br.com.usinasantafe.pia.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

@DatabaseTable(tableName="tbitemsalvvar")
public class ItemSalvoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idRespItem;
    @DatabaseField
    private Long idExtRespItem;
    @DatabaseField
    private Long idCabecRespItem;

    public ItemSalvoTO() {
    }

    public Long getIdRespItem() {
        return idRespItem;
    }

    public void setIdRespItem(Long idRespItem) {
        this.idRespItem = idRespItem;
    }

    public Long getIdExtRespItem() {
        return idExtRespItem;
    }

    public void setIdExtRespItem(Long idExtRespItem) {
        this.idExtRespItem = idExtRespItem;
    }

    public Long getIdCabecRespItem() {
        return idCabecRespItem;
    }

    public void setIdCabecRespItem(Long idCabecRespItem) {
        this.idCabecRespItem = idCabecRespItem;
    }
}
