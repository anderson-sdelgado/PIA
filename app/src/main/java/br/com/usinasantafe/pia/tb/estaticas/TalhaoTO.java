package br.com.usinasantafe.pia.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

/**
 * Created by anderson on 04/07/2017.
 */
@DatabaseTable(tableName="tbtalhaoest")
public class TalhaoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTalhao;
    @DatabaseField
    private Long idSecao;
    @DatabaseField
    private Long codTalhao;

    public TalhaoTO() {
    }

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
    }

    public Long getIdSecao() {
        return idSecao;
    }

    public void setIdSecao(Long idSecao) {
        this.idSecao = idSecao;
    }

    public Long getCodTalhao() {
        return codTalhao;
    }

    public void setCodTalhao(Long codTalhao) {
        this.codTalhao = codTalhao;
    }
}



