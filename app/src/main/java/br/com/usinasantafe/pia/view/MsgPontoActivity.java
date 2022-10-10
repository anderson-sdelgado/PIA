package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class MsgPontoActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_ponto);

        piaContext = (PIAContext) getApplication();

        TextView textViewPonto = findViewById(R.id.textViewMsgPonto);
        Button buttonSimPonto = findViewById(R.id.buttonSimPonto);
        Button buttonNaoPonto = findViewById(R.id.buttonNaoPonto);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewPonto.setText(\"DESEJA INSERIR PONTO \" + piaContext.getConfigCTR().getConfig().getUltPonto() + \"?\");", getLocalClassName());
        textViewPonto.setText("DESEJA INSERIR PONTO " + (piaContext.getInfestacaoCTR().ultPonto() + 1) + "?");

        buttonSimPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonSimPonto.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                piaContext.setPosQuestaoAmostra(0);\n" +
                        "                Intent it = new Intent(MsgPontoActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
                piaContext.setPosQuestaoAmostra(0);
                Intent it = new Intent(MsgPontoActivity.this, QuestaoAmostraActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonNaoPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoPonto.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgPontoActivity.this, ListaPontosActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgPontoActivity.this, ListaPontosActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }


}
