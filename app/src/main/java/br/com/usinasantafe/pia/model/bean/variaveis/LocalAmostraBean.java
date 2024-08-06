package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import br.com.usinasantafe.pia.model.pst.Entidade;

@DatabaseTable(tableName="tblocalamostravar")
public class LocalAmostraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idLocal;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private String dthr;
    @DatabaseField
    private Long dthrLong;
    @DatabaseField
    private Long nroOS;
    @DatabaseField
    private Long idSecao;
    @DatabaseField
    private Long idTalhao;
    @DatabaseField
    private Double latitude;
    @DatabaseField
    private Double longitude;
    @DatabaseField
    private String obs;
    @DatabaseField
    private Long statusApont; // 0 - Stand-by; 1 - Apontando;
    private List<RespItemAmostraBean> respItemAmostraList;

    public LocalAmostraBean() {
    }

    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
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

    public Long getNroOS() {
        return nroOS;
    }

    public void setNroOS(Long nroOS) {
        this.nroOS = nroOS;
    }

    public Long getIdSecao() {
        return idSecao;
    }

    public void setIdSecao(Long idSecao) {
        this.idSecao = idSecao;
    }

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
    }

    public Long getStatusApont() {
        return statusApont;
    }

    public void setStatusApont(Long statusApont) {
        this.statusApont = statusApont;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<RespItemAmostraBean> getRespItemAmostraList() {
        return respItemAmostraList;
    }

    public void setRespItemAmostraList(List<RespItemAmostraBean> respItemAmostraList) {
        this.respItemAmostraList = respItemAmostraList;
    }
}
