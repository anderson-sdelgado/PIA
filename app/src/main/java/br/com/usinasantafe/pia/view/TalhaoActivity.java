package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class TalhaoActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        piaContext = (PIAContext) getApplication();

        Button buttonOkTalhao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = findViewById(R.id.buttonCancPadrao);

        buttonOkTalhao.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(!editTextPadrao.getText().toString().equals("")){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                        "                    Long codTalhao = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                Long codTalhao = Long.parseLong(editTextPadrao.getText().toString());
                if(piaContext.getInfestacaoCTR().verTalhaCod(codTalhao)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().verTalhaCod(codTalhao)) {\n" +
                            "                    piaContext.getConfigCTR().setTalhao(piaContext.getInfestacaoCTR().getTalhaCod(codTalhao).getIdTalhao());\n" +
                            "                    piaContext.setVerTelaQuestao(1);\n" +
                            "                    boolean retorno = piaContext.getInfestacaoCTR().salvarCabecAberto();", getLocalClassName());
                    piaContext.getConfigCTR().setTalhao(piaContext.getInfestacaoCTR().getTalhaCod(codTalhao).getIdTalhao());
                    piaContext.setVerTelaQuestao(1);
                    piaContext.getInfestacaoCTR().salvarCabecAberto();

                    Intent it = new Intent(TalhaoActivity.this, ListaPontosActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"O.S. INVÁLIDA. POR FAVOR, VERIFIQUE A NUMERAÇÃO DE O.S. DIGITADA.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("TALHÃO INVÁLIDO. POR FAVOR, VERIFIQUE A NUMERAÇÃO DE TALHÃO DIGITADO É VALIDO E BATE COM A SEÇÃO.");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                    alerta.show();

                }

            }

        });

        buttonCancTalhao.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(editTextPadrao.getText().toString().length() > 0){
                LogProcessoDAO.getInstance().insertLogProcesso("if(editTextPadrao.getText().toString().length() > 0){\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);", getLocalClassName());
        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
        startActivity(it);
        finish();
    }

}
