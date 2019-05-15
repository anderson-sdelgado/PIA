package br.com.usinasantafe.pia.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabamostravar")
public class CabecAmostraTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabec;
    @DatabaseField
    private Long idExtBoletim;
    @DatabaseField
    private Long auditorCabec;
    @DatabaseField
    private String dtCabec;
    @DatabaseField
    private Long secaoCabec;
    @DatabaseField
    private Long talhaoCabec;
    @DatabaseField
    private Long idOrgCabec;
    @DatabaseField
    private Long idCaracOrgCabec;
    @DatabaseField
    private Long ultPonto;
    @DatabaseField
    private Long statusAmostra; // 1 - ABERTA; 2 - FECHADA;

    public CabecAmostraTO() {
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public Long getAuditorCabec() {
        return auditorCabec;
    }

    public void setAuditorCabec(Long auditorCabec) {
        this.auditorCabec = auditorCabec;
    }

    public String getDtCabec() {
        return dtCabec;
    }

    public void setDtCabec(String dtCabec) {
        this.dtCabec = dtCabec;
    }

    public Long getSecaoCabec() {
        return secaoCabec;
    }

    public void setSecaoCabec(Long secaoCabec) {
        this.secaoCabec = secaoCabec;
    }

    public Long getTalhaoCabec() {
        return talhaoCabec;
    }

    public void setTalhaoCabec(Long talhaoCabec) {
        this.talhaoCabec = talhaoCabec;
    }

    public Long getIdOrgCabec() {
        return idOrgCabec;
    }

    public void setIdOrgCabec(Long idOrgCabec) {
        this.idOrgCabec = idOrgCabec;
    }

    public Long getIdCaracOrgCabec() {
        return idCaracOrgCabec;
    }

    public void setIdCaracOrgCabec(Long idCaracOrgCabec) {
        this.idCaracOrgCabec = idCaracOrgCabec;
    }

    public Long getUltPonto() {
        return ultPonto;
    }

    public void setUltPonto(Long ultPonto) {
        this.ultPonto = ultPonto;
    }

    public Long getStatusAmostra() {
        return statusAmostra;
    }

    public void setStatusAmostra(Long statusAmostra) {
        this.statusAmostra = statusAmostra;
    }

    public Long getIdExtBoletim() {
        return idExtBoletim;
    }

    public void setIdExtBoletim(Long idExtBoletim) {
        this.idExtBoletim = idExtBoletim;
    }
}
