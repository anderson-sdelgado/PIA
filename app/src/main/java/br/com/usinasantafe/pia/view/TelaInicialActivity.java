package br.com.usinasantafe.pia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pia.BuildConfig;
import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.EnvioDadosServ;

public class TelaInicialActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        piaContext = (PIAContext) getApplication();
        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(excluirBDThread, 0);", getLocalClassName());
        customHandler.postDelayed(excluirBDThread, 0);
    }

    private Runnable excluirBDThread = new Runnable() {

        public void run() {
            LogProcessoDAO.getInstance().insertLogProcesso("private Runnable excluirBDThread = new Runnable() {\n" +
                    "        public void run() {\n" +
                    "            clearBD();\n" +
                    "            goMenuInicial();", getLocalClassName());
            clearBD();
            goMenuInicial();
        }

    };

    public void clearBD() {
        LogProcessoDAO.getInstance().insertLogProcesso("piaContext.getInfestacaoCTR().deleteCabecEnviado();\n" +
                "        piaContext.getConfigCTR().deleteLogs();", getLocalClassName());
        piaContext.getInfestacaoCTR().deleteCabecEnviado();
        piaContext.getConfigCTR().deleteLogs();
    }

    public void goMenuInicial(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void goMenuInicial(){", getLocalClassName());
        Intent it;
        if (!piaContext.getInfestacaoCTR().verCabecAberto()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (!piaContext.getInfestacaoCTR().verCabecAberto()) {\n" +
                    "            it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
            it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            if (piaContext.getInfestacaoCTR().hasElemItemAmostra()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getInfestacaoCTR().hasElemItemAmostra()) {\n" +
                        "                piaContext.getInfestacaoCTR().deleteRespItemAmostraAberto();\n" +
                        "                it = new Intent(TelaInicialActivity.this, ListaPontosActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().deleteRespItemAmostraAberto();
                it = new Intent(TelaInicialActivity.this, ListaPontosActivity.class);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                piaContext.getInfestacaoCTR().deleteCabecAberto();\n" +
                        "                it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().deleteCabecAberto();
                it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
            }
        }
        startActivity(it);
        finish();

    }

    public void onBackPressed()  {
    }

}