package br.com.usinasantafe.pia.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */

@DatabaseTable(tableName="tbrespitemvar")
public class RespItemAmostraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRespItemAmostra;
    @DatabaseField
    private Long idLocal;
    @DatabaseField
    private String dthr;
    @DatabaseField
    private Long dthrLong;
    @DatabaseField
    private Long idAmostra;
    @DatabaseField
    private Long ponto;
    @DatabaseField
    private Long valor;
    @DatabaseField
    private String obs;

    public RespItemAmostraBean() {
    }

    public Long getIdRespItemAmostra() {
        return idRespItemAmostra;
    }

    public void setIdRespItemAmostra(Long idRespItemAmostra) {
        this.idRespItemAmostra = idRespItemAmostra;
    }

    public Long getIdAmostra() {
        return idAmostra;
    }

    public void setIdAmostra(Long idAmostra) {
        this.idAmostra = idAmostra;
    }

    public Long getPonto() {
        return ponto;
    }

    public void setPonto(Long ponto) {
        this.ponto = ponto;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
