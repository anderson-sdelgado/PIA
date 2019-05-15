package br.com.usinasantafe.pia.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */
@DatabaseTable(tableName="tbauditorest")
public class AuditorTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idAuditor;
    @DatabaseField
    private Long codAuditor;
    @DatabaseField
    private String nomeAuditor;

    public AuditorTO() {
    }

    public Long getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(Long idAuditor) {
        this.idAuditor = idAuditor;
    }

    public Long getCodAuditor() {
        return codAuditor;
    }

    public void setCodAuditor(Long codAuditor) {
        this.codAuditor = codAuditor;
    }

    public String getNomeAuditor() {
        return nomeAuditor;
    }

    public void setNomeAuditor(String nomeAuditor) {
        this.nomeAuditor = nomeAuditor;
    }
}
