package br.com.usinasantafe.pia.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */
@DatabaseTable(tableName="tbauditorest")
public class AuditorBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idAuditor;
    @DatabaseField
    private Long matricAuditor;
    @DatabaseField
    private String nomeAuditor;

    public AuditorBean() {
    }

    public Long getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(Long idAuditor) {
        this.idAuditor = idAuditor;
    }

    public Long getMatricAuditor() {
        return matricAuditor;
    }

    public void setMatricAuditor(Long matricAuditor) {
        this.matricAuditor = matricAuditor;
    }

    public String getNomeAuditor() {
        return nomeAuditor;
    }

    public void setNomeAuditor(String nomeAuditor) {
        this.nomeAuditor = nomeAuditor;
    }
}
