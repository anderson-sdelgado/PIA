package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long nroAparelhoConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private Long nroOSConfig;
    @DatabaseField
    private Long matricAuditorConfig;
    @DatabaseField
    private Long idSecaoConfig;
    @DatabaseField
    private Long idTalhaoConfig;
    @DatabaseField
    private Long idOrganConfig;
    @DatabaseField
    private Long idCaracOrganConfig;
    @DatabaseField
    private Long valorRespConfig;

    public ConfigBean() {
    }

    public Long getNroAparelhoConfig() {
        return nroAparelhoConfig;
    }

    public void setNroAparelhoConfig(Long nroAparelhoConfig) {
        this.nroAparelhoConfig = nroAparelhoConfig;
    }

    public String getSenhaConfig() {
        return senhaConfig;
    }

    public void setSenhaConfig(String senhaConfig) {
        this.senhaConfig = senhaConfig;
    }

    public Long getNroOSConfig() {
        return nroOSConfig;
    }

    public void setNroOSConfig(Long osConfig) {
        this.nroOSConfig = osConfig;
    }

    public Long getMatricAuditorConfig() {
        return matricAuditorConfig;
    }

    public void setMatricAuditorConfig(Long matricAuditorConfig) {
        this.matricAuditorConfig = matricAuditorConfig;
    }

    public Long getIdSecaoConfig() {
        return idSecaoConfig;
    }

    public void setIdSecaoConfig(Long idSecaoConfig) {
        this.idSecaoConfig = idSecaoConfig;
    }

    public Long getIdTalhaoConfig() {
        return idTalhaoConfig;
    }

    public void setIdTalhaoConfig(Long idTalhaoConfig) {
        this.idTalhaoConfig = idTalhaoConfig;
    }

    public Long getIdOrganConfig() {
        return idOrganConfig;
    }

    public void setIdOrganConfig(Long idOrganConfig) {
        this.idOrganConfig = idOrganConfig;
    }

    public Long getIdCaracOrganConfig() {
        return idCaracOrganConfig;
    }

    public void setIdCaracOrganConfig(Long idCaracOrganConfig) {
        this.idCaracOrganConfig = idCaracOrganConfig;
    }

    public Long getValorRespConfig() {
        return valorRespConfig;
    }

    public void setValorRespConfig(Long valorRespConfig) {
        this.valorRespConfig = valorRespConfig;
    }
}
