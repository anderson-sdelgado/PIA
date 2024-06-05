package br.com.usinasantafe.pia.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

@DatabaseTable(tableName="tbpergcabecest")
public class PergCabecBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idPergCabec;
    @DatabaseField
    private String descrPergCabec;

    public PergCabecBean() {
    }

    public Long getIdPergCabec() {
        return idPergCabec;
    }

    public void setIdPergCabec(Long idPergCabec) {
        this.idPergCabec = idPergCabec;
    }

    public String getDescrPergCabec() {
        return descrPergCabec;
    }

    public void setDescrPergCabec(String descrPergCabec) {
        this.descrPergCabec = descrPergCabec;
    }
}
