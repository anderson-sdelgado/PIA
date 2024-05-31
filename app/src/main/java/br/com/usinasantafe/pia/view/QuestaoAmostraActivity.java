package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class QuestaoAmostraActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private AmostraBean amostraBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_amostra);

        piaContext = (PIAContext) getApplication();

        TextView textViewPadrao = findViewById(R.id.textViewPadrao);
        Button buttonOkQA = findViewById(R.id.buttonOkPadrao);
        Button buttonCancQA = findViewById(R.id.buttonCancPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        if(piaContext.getVerTelaQuestao() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getVerTelaQuestao() == 1){\n" +
                    "            itemAmostraBean = piaContext.getInfestacaoCTR().getItemAmostra(piaContext.getPosQuestaoAmostra());\n" +
                    "            textViewPadrao.setText(itemAmostraBean.getDescrItem());\n" +
                    "            editText.setText(\"\");", getLocalClassName());
            amostraBean = piaContext.getInfestacaoCTR().getItemAmostra(piaContext.getPosQuestaoAmostra());
            textViewPadrao.setText(amostraBean.getDescrAmostra());
            editText.setText("");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            RespItemAmostraBean respItemAmostraBean = piaContext.getInfestacaoCTR().getRespItemAmostra(piaContext.getIdRespItem());\n" +
                    "            itemAmostraBean = piaContext.getInfestacaoCTR().getItemAmostraId(respItemAmostraBean.getIdAmostraRespItem());\n" +
                    "            textViewPadrao.setText(itemAmostraBean.getDescrItem());\n" +
                    "            editText.setText(String.valueOf(respItemAmostraBean.getValorRespItem()));", getLocalClassName());
            RespItemAmostraBean respItemAmostraBean = piaContext.getInfestacaoCTR().getRespItemAmostra(piaContext.getIdRespItem());
            amostraBean = piaContext.getInfestacaoCTR().getAmostraId(respItemAmostraBean.getIdAmostraRespItem());
            textViewPadrao.setText(amostraBean.getDescrAmostra());
            editText.setText(String.valueOf(respItemAmostraBean.getValorRespItem()));
        }

        buttonOkQA.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkQA.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Long valor = (editTextPadrao.getText().toString().equals(\"\")) ? 0L : Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
            Long valor = (editTextPadrao.getText().toString().equals("")) ? 0L : Long.parseLong(editTextPadrao.getText().toString());

            if(piaContext.getVerTelaQuestao() == 1) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getVerTelaQuestao() == 1) {\n" +
                        "                    piaContext.getInfestacaoCTR().inserirRespItemAmostra(itemAmostraBean.getIdAmostraItem(), valor);\n" +
                        "                    piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() + 1);", getLocalClassName());
                piaContext.getInfestacaoCTR().inserirRespItemAmostra(amostraBean.getIdAmostra(), valor);
                piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() + 1);

                if (piaContext.getPosQuestaoAmostra() < piaContext.getInfestacaoCTR().qtdeItemAmostra()) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getPosQuestaoAmostra() < piaContext.getInfestacaoCTR().qtdeItemAmostra()) {\n" +
                            "                        Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
                    Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);
                    startActivity(it);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        piaContext.getInfestacaoCTR().fecharPonto();\n" +
                            "                        Intent it = new Intent(QuestaoAmostraActivity.this, MsgPontoActivity.class);", getLocalClassName());
                    piaContext.getInfestacaoCTR().fecharPonto();
                    Intent it = new Intent(QuestaoAmostraActivity.this, MsgPontoActivity.class);
                    startActivity(it);

                }

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else if(piaContext.getVerTelaQuestao() == 2) {\n" +
                        "                    piaContext.getInfestacaoCTR().updateRespItemAmostra(piaContext.getIdRespItem(), valor);\n" +
                        "                    Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().updateRespItemAmostra(piaContext.getIdRespItem(), valor);
                Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);
                startActivity(it);

            }
            System.gc();
            finish();

        });

        buttonCancQA.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancQA.setOnClickListener(v -> {", getLocalClassName());
            if(editTextPadrao.getText().toString().length() > 0){
                LogProcessoDAO.getInstance().insertLogProcesso("if(editTextPadrao.getText().toString().length() > 0){\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {

        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {", getLocalClassName());
        if(piaContext.getVerTelaQuestao() == 1) {

            LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getVerTelaQuestao() == 1) {\n" +
                    "                        piaContext.getInfestacaoCTR().deleteRespItemAmostraAberto(itemAmostraBean.getIdAmostraItem());", getLocalClassName());
            piaContext.getInfestacaoCTR().deleteRespItemAmostra(amostraBean.getIdAmostra());
            if (piaContext.getPosQuestaoAmostra() == 0) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getPosQuestaoAmostra() == 0) {\n" +
                        "                            piaContext.getInfestacaoCTR().deleteRespItemAmostra();\n" +
                        "                            Intent it = new Intent(QuestaoAmostraActivity.this, ListaPontosActivity.class);", getLocalClassName());
                Intent it = new Intent(QuestaoAmostraActivity.this, ListaPontosActivity.class);
                startActivity(it);

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                            piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() - 1);\n" +
                        "                            Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
                piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() - 1);
                Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);
                startActivity(it);
            }

        } else {

            LogProcessoDAO.getInstance().insertLogProcesso("} else if(piaContext.getVerTelaQuestao() == 2) {\n" +
                    "                        Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);", getLocalClassName());
            Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);
            startActivity(it);

        }
        System.gc();
        finish();
    }

}
