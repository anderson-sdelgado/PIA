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
            LogProcessoDAO.getInstance().insertLogProcesso("RespItemAmostraBean respItemAmostraBean = piaContext.getInfestacaoCTR().getRespItemAmostra(piaContext.getIdRespItem());\n" +
                    "            amostraBean = piaContext.getInfestacaoCTR().getAmostraId(respItemAmostraBean.getIdAmostra());\n" +
                    "            textViewPadrao.setText(amostraBean.getDescrAmostra());\n" +
                    "            String valor = String.valueOf(respItemAmostraBean.getValor());\n" +
                    "            if(piaContext.getConfigCTR().getIdAmostra() == 51L){\n" +
                    "                valor = (respItemAmostraBean.getValor() == 0) ? \"\" : String.valueOf(respItemAmostraBean.getValor());\n" +
                    "            }\n" +
                    "            editText.setText(valor);", getLocalClassName());
            RespItemAmostraBean respItemAmostraBean = piaContext.getInfestacaoCTR().getRespItemAmostra(piaContext.getIdRespItem());
            amostraBean = piaContext.getInfestacaoCTR().getAmostraId(respItemAmostraBean.getIdAmostra());
            textViewPadrao.setText(amostraBean.getDescrAmostra());
            String valor = String.valueOf(respItemAmostraBean.getValor());
            if(piaContext.getConfigCTR().getIdAmostra() == 51L){
                valor = (respItemAmostraBean.getValor() == null) ? "" : String.valueOf(respItemAmostraBean.getValor());
            }
            editText.setText(valor);
        }

        buttonOkQA.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkQA.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Long valor = (editTextPadrao.getText().toString().equals(\"\")) ? 0L : Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());


            Intent it;
            if(piaContext.getConfigCTR().getIdAmostra() == 51L) {

                Long valor = (editTextPadrao.getText().toString().equals("")) ? null : Long.parseLong(editTextPadrao.getText().toString());

                LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getConfigCTR().getIdAmostra() == 51L) {\n" +
                        "                piaContext.setTipoFluxo(2);\n" +
                        "                piaContext.getConfigCTR().setValorResp(valor);\n" +
                        "                it = new Intent(QuestaoAmostraActivity.this, ObservActivity.class);", getLocalClassName());
                piaContext.setTipoFluxo(2);
                piaContext.getConfigCTR().setValorResp(valor);
                it = new Intent(QuestaoAmostraActivity.this, ObservActivity.class);

            } else {

                Long valor = (editTextPadrao.getText().toString().equals("")) ? 0 : Long.parseLong(editTextPadrao.getText().toString());

                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if(piaContext.getVerTelaQuestao() == 1) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getVerTelaQuestao() == 1) {\n" +
                            "                    piaContext.getInfestacaoCTR().inserirRespItemAmostra(itemAmostraBean.getIdAmostraItem(), valor);\n" +
                            "                    piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() + 1);", getLocalClassName());
                    piaContext.getInfestacaoCTR().inserirRespItemAmostra(amostraBean.getIdAmostra(), valor);
                    piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() + 1);
                    if (piaContext.getPosQuestaoAmostra() < piaContext.getInfestacaoCTR().qtdeItemAmostra()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getPosQuestaoAmostra() < piaContext.getInfestacaoCTR().qtdeItemAmostra()) {\n" +
                                "                        Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
                        it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        piaContext.getInfestacaoCTR().fecharPonto();\n" +
                                "                        Intent it = new Intent(QuestaoAmostraActivity.this, MsgPontoActivity.class);", getLocalClassName());
                        piaContext.getInfestacaoCTR().fecharPonto();
                        it = new Intent(QuestaoAmostraActivity.this, MsgPontoArmadilhaActivity.class);
                    }

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    piaContext.getInfestacaoCTR().updateRespItemAmostra(piaContext.getIdRespItem(), valor);\n" +
                            "                    Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);", getLocalClassName());
                    piaContext.getInfestacaoCTR().updateRespItemAmostra(piaContext.getIdRespItem(), valor);
                    it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);

                }
            }

            System.gc();
            startActivity(it);
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
        Intent it;
        if(piaContext.getVerTelaQuestao() == 1) {

            LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getVerTelaQuestao() == 1) {\n" +
                    "                        piaContext.getInfestacaoCTR().deleteRespItemAmostraAberto(itemAmostraBean.getIdAmostraItem());", getLocalClassName());
            piaContext.getInfestacaoCTR().deleteRespItemAmostraCabecApont(amostraBean.getIdAmostra());
            if (piaContext.getPosQuestaoAmostra() == 0) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getPosQuestaoAmostra() == 0) {", getLocalClassName());
                if(piaContext.getConfigCTR().getIdAmostra() == 51L){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getConfigCTR().getIdAmostra() == 51L){\n" +
                            "                    it = new Intent(QuestaoAmostraActivity.this, ListaArmadilhaActivity.class);", getLocalClassName());
                    it = new Intent(QuestaoAmostraActivity.this, ListaArmadilhaActivity.class);
                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    it = new Intent(QuestaoAmostraActivity.this, ListaPontosActivity.class);", getLocalClassName());
                    it = new Intent(QuestaoAmostraActivity.this, ListaPontosActivity.class);

                }

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                            piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() - 1);\n" +
                        "                            Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
                piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() - 1);
                it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);
            }

        } else {

            LogProcessoDAO.getInstance().insertLogProcesso("} else if(piaContext.getVerTelaQuestao() == 2) {\n" +
                    "                        Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);", getLocalClassName());
            it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);

        }

        System.gc();
        startActivity(it);
        finish();

    }

}
