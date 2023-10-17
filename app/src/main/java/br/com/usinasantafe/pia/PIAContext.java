package br.com.usinasantafe.pia;

import android.app.Application;
import android.util.Log;

import java.util.Calendar;

import br.com.usinasantafe.pia.control.ConfigCTR;
import br.com.usinasantafe.pia.control.InfestacaoCTR;

public class PIAContext extends Application {

    private ConfigCTR configCTR;
    private InfestacaoCTR infestacaoCTR;
    public static String versaoWS = "2.01";
    private int posQuestaoAmostra;
    private int posPonto;
    private Long idRespItem;
    private int verTelaQuestao; // 1 - MSG PONTO E QUESTAO AMOSTRA; 2 - ALTERAR AMOSTRA;
    private int tela;

    public ConfigCTR getConfigCTR() {
        if(configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public InfestacaoCTR getInfestacaoCTR() {
        if(infestacaoCTR == null)
            infestacaoCTR = new InfestacaoCTR();
        return infestacaoCTR;
    }

    public int getPosQuestaoAmostra() {
        return posQuestaoAmostra;
    }

    public void setPosQuestaoAmostra(int posQuestaoAmostra) {
        this.posQuestaoAmostra = posQuestaoAmostra;
    }

    public int getPosPonto() {
        return posPonto;
    }

    public void setPosPonto(int posPonto) {
        this.posPonto = posPonto;
    }

    public int getVerTelaQuestao() {
        return verTelaQuestao;
    }

    public void setVerTelaQuestao(int verTelaQuestao) {
        this.verTelaQuestao = verTelaQuestao;
    }

    public Long getIdRespItem() {
        return idRespItem;
    }

    public void setIdRespItem(Long idRespItem) {
        this.idRespItem = idRespItem;
    }

    public int getTela() {
        return tela;
    }

    public void setTela(int tela) {
        this.tela = tela;
    }
}
