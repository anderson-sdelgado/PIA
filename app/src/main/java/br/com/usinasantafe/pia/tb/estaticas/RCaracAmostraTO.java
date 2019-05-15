package br.com.usinasantafe.pia.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

/**
 * Created by anderson on 06/06/2017.
 */
@DatabaseTable(tableName="tbrcaracamostest")
public class RCaracAmostraTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idRCaracAmostra;
    @DatabaseField
    private Long idAmostraOrgan;
    @DatabaseField
    private Long idCaracOrgan;

    public RCaracAmostraTO() {
    }

    public Long getIdRCaracAmostra() {
        return idRCaracAmostra;
    }

    public void setIdRCaracAmostra(Long idRCaracAmostra) {
        this.idRCaracAmostra = idRCaracAmostra;
    }

    public Long getIdAmostraOrgan() {
        return idAmostraOrgan;
    }

    public void setIdAmostraOrgan(Long idAmostraOrgan) {
        this.idAmostraOrgan = idAmostraOrgan;
    }

    public Long getIdCaracOrgan() {
        return idCaracOrgan;
    }

    public void setIdCaracOrgan(Long idCaracOrgan) {
        this.idCaracOrgan = idCaracOrgan;
    }

}
