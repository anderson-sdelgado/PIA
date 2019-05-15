package br.com.usinasantafe.pia;

import android.app.Application;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by anderson on 05/06/2017.
 */

public class PIAContext extends Application {

    private int posQuestaoAmostra;
    private int posPonto;
    private Long idRespItem;
    private int verTelaQuestao; // 1 - MSG PONTO E QUESTAO AMOSTRA; 2 - ALTERAR AMOSTRA;

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


}
