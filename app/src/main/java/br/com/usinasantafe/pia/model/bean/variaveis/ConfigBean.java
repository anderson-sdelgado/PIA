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
    private Long osConfig;
    @DatabaseField
    private Long auditorConfig;
    @DatabaseField
    private Long secaoConfig;
    @DatabaseField
    private Long talhaoConfig;
    @DatabaseField
    private Long idOrganConfig;
    @DatabaseField
    private Long idCaracOrganConfig;

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

    public Long getOSConfig() {
        return osConfig;
    }

    public void setOSConfig(Long osConfig) {
        this.osConfig = osConfig;
    }

    public Long getAuditorConfig() {
        return auditorConfig;
    }

    public void setAuditorConfig(Long auditorConfig) {
        this.auditorConfig = auditorConfig;
    }

    public Long getSecaoConfig() {
        return secaoConfig;
    }

    public void setSecaoConfig(Long secaoConfig) {
        this.secaoConfig = secaoConfig;
    }

    public Long getTalhaoConfig() {
        return talhaoConfig;
    }

    public void setTalhaoConfig(Long talhaoConfig) {
        this.talhaoConfig = talhaoConfig;
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
//
//    public Long getUltPonto() {
//        return ultPonto;
//    }
//
//    public void setUltPonto(Long ultPonto) {
//        this.ultPonto = ultPonto;
//    }
}
