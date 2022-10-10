package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabamostravar")
public class CabecAmostraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabec;
    @DatabaseField
    private Long auditorCabec;
    @DatabaseField
    private String dthrCabec;
    @DatabaseField
    private Long dthrLongCabec;
    @DatabaseField
    private Long osCabec;
    @DatabaseField
    private Long secaoCabec;
    @DatabaseField
    private Long talhaoCabec;
    @DatabaseField
    private Long idOrganCabec;
    @DatabaseField
    private Long idCaracOrganCabec;
    @DatabaseField
    private Long statusAmostra; // 1 - Aberto; 2 - Encerrado; 3 - Enviado;

    public CabecAmostraBean() {
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

    public String getDthrCabec() {
        return dthrCabec;
    }

    public void setDthrCabec(String dthrCabec) {
        this.dthrCabec = dthrCabec;
    }

    public Long getDthrLongCabec() {
        return dthrLongCabec;
    }

    public void setDthrLongCabec(Long dthrLongCabec) {
        this.dthrLongCabec = dthrLongCabec;
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

    public Long getIdOrganCabec() {
        return idOrganCabec;
    }

    public void setIdOrganCabec(Long idOrganCabec) {
        this.idOrganCabec = idOrganCabec;
    }

    public Long getIdCaracOrganCabec() {
        return idCaracOrganCabec;
    }

    public void setIdCaracOrganCabec(Long idCaracOrganCabec) {
        this.idCaracOrganCabec = idCaracOrganCabec;
    }

    public Long getStatusAmostra() {
        return statusAmostra;
    }

    public void setStatusAmostra(Long statusAmostra) {
        this.statusAmostra = statusAmostra;
    }

    public Long getOsCabec() {
        return osCabec;
    }

    public void setOsCabec(Long osCabec) {
        this.osCabec = osCabec;
    }
}
