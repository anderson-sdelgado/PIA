package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

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
    private Long matricAuditor;
    @DatabaseField
    private String dthr;
    @DatabaseField
    private Long dthrLong;
    @DatabaseField
    private Long idOrgan;
    @DatabaseField
    private Long idCaracOrgan;
    @DatabaseField
    private Long idAmostraOrgan;
    @DatabaseField
    private Long ponto;
    @DatabaseField
    private Long statusPonto; // 1 - Apontando; 2 - Encerrado;
    @DatabaseField
    private Long statusCabec; // 1 - Aberto; 2 - Encerrado; 3 - Enviado;
    @DatabaseField
    private Long statusApont; // 0 - Stand-by; 1 - Apontando;
    private List<LocalAmostraBean> localAmostraList;
    private List<RespItemAmostraBean> respItemAmostraList;
    private List<RespItemCabecBean> respItemCabecList;

    public CabecAmostraBean() {
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public Long getMatricAuditor() {
        return matricAuditor;
    }

    public void setMatricAuditor(Long matricAuditor) {
        this.matricAuditor = matricAuditor;
    }

    public Long getIdOrgan() {
        return idOrgan;
    }

    public void setIdOrgan(Long idOrgan) {
        this.idOrgan = idOrgan;
    }

    public Long getIdCaracOrgan() {
        return idCaracOrgan;
    }

    public void setIdCaracOrgan(Long idCaracOrgan) {
        this.idCaracOrgan = idCaracOrgan;
    }

    public Long getStatusCabec() {
        return statusCabec;
    }

    public void setStatusCabec(Long statusCabec) {
        this.statusCabec = statusCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getStatusApont() {
        return statusApont;
    }

    public void setStatusApont(Long statusApont) {
        this.statusApont = statusApont;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }

    public Long getDthrLong() {
        return dthrLong;
    }

    public void setDthrLong(Long dthrLong) {
        this.dthrLong = dthrLong;
    }

    public List<LocalAmostraBean> getLocalAmostraList() {
        return localAmostraList;
    }

    public void setLocalAmostraList(List<LocalAmostraBean> localAmostraList) {
        this.localAmostraList = localAmostraList;
    }

    public List<RespItemAmostraBean> getRespItemAmostraList() {
        return respItemAmostraList;
    }

    public void setRespItemAmostraList(List<RespItemAmostraBean> respItemAmostraList) {
        this.respItemAmostraList = respItemAmostraList;
    }

    public Long getIdAmostraOrgan() {
        return idAmostraOrgan;
    }

    public void setIdAmostraOrgan(Long idAmostraOrgan) {
        this.idAmostraOrgan = idAmostraOrgan;
    }

    public Long getPonto() {
        return ponto;
    }

    public void setPonto(Long ponto) {
        this.ponto = ponto;
    }

    public Long getStatusPonto() {
        return statusPonto;
    }

    public void setStatusPonto(Long statusPonto) {
        this.statusPonto = statusPonto;
    }

    public List<RespItemCabecBean> getRespItemCabecList() {
        return respItemCabecList;
    }

    public void setRespItemCabecList(List<RespItemCabecBean> respItemCabecList) {
        this.respItemCabecList = respItemCabecList;
    }
}
