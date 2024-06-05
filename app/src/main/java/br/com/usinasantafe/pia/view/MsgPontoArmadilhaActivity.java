package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class MsgPontoArmadilhaActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_ponto_armadilha);

        piaContext = (PIAContext) getApplication();

        TextView textViewPontoArmadilha = findViewById(R.id.textViewMsgPontoArmadilha);
        Button buttonSimPontoArmadilha = findViewById(R.id.buttonSimPontoArmadilha);
        Button buttonNaoPontoArmadilha = findViewById(R.id.buttonNaoPontoArmadilha);

        String tipo;
        if(piaContext.getConfigCTR().getIdAmostra() == 51L) {
            tipo = "ARMADILHA";
        } else {
            tipo = "PONTO";
        }

        LogProcessoDAO.getInstance().insertLogProcesso("textViewPontoArmadilha.setText(\"DESEJA INSERIR \"  + tipo + \" \" + (piaContext.getInfestacaoCTR().ponto() + 1) + \"?\");", getLocalClassName());
        textViewPontoArmadilha.setText("DESEJA INSERIR "  + tipo + " " + (piaContext.getInfestacaoCTR().ponto() + 1) + "?");

        buttonSimPontoArmadilha.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonSimPonto.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                piaContext.setPosQuestaoAmostra(0);\n" +
                    "                Intent it = new Intent(MsgPontoActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
            piaContext.getInfestacaoCTR().addPonto();
            piaContext.setPosQuestaoAmostra(0);
            if(piaContext.getConfigCTR().getIdAmostra() == 51L) {
                piaContext.getConfigCTR().setOS(0L);
                Intent it = new Intent(MsgPontoArmadilhaActivity.this, SecaoActivity.class);
                startActivity(it);
                finish();
            } else {
                Intent it = new Intent(MsgPontoArmadilhaActivity.this, QuestaoAmostraActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonNaoPontoArmadilha.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoPonto.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(MsgPontoActivity.this, ListaPontosActivity.class);", getLocalClassName());
            Intent it = new Intent(MsgPontoArmadilhaActivity.this, ListaPontosActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed()  {
    }

}
