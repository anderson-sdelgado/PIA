package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class SecaoActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao);

        piaContext = (PIAContext) getApplication();

        Button buttonOkTalhao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(SecaoActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(SecaoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(AuditorActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());
                            progressBar = new ProgressDialog( SecaoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("piaContext.getInfestacaoCTR().atualDados( SecaoActivity.this,  SecaoActivity.class, progressBar, \"Secao\", 1, getLocalClassName());", getLocalClassName());
                            piaContext.getInfestacaoCTR().atualDados( SecaoActivity.this,  SecaoActivity.class, progressBar, "Secao", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(SecaoActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(SecaoActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });
                alerta.show();

            }

        });

        buttonOkTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(!editTextPadrao.getText().toString().equals("")){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                            "                    Long codSecao = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                    Long codSecao = Long.parseLong(editTextPadrao.getText().toString());
                    if(piaContext.getInfestacaoCTR().verSecaoCod(codSecao)) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().verSecaoCod(codSecao)) {\n" +
                                "                        piaContext.getConfigCTR().setSecao(piaContext.getInfestacaoCTR().getSecaoCod(codSecao).getIdSecao());\n" +
                                "                        Intent it = new Intent(SecaoActivity.this, TalhaoActivity.class);", getLocalClassName());
                        piaContext.getConfigCTR().setSecao(piaContext.getInfestacaoCTR().getSecaoCod(codSecao).getIdSecao());
                        Intent it = new Intent(SecaoActivity.this, TalhaoActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                    AlertDialog.Builder alerta = new AlertDialog.Builder(SecaoActivity.this);\n" +
                                "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                    alerta.setMessage(\"O.S. INVÁLIDA. POR FAVOR, VERIFIQUE A NUMERAÇÃO DE O.S. DIGITADA.\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(SecaoActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("SEÇÃO INVÁLIDA. POR FAVOR, VERIFIQUE A NUMERAÇÃO DE SEÇÃO DIGITADA É VALIDA E BATE COM A O.S.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                        @Override\n" +
                                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            }
                        });

                        alerta.show();

                    }

                }

            }
        });

        buttonCancTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(editTextPadrao.getText().toString().length() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(editTextPadrao.getText().toString().length() > 0){\n" +
                            "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(SecaoActivity.this, AuditorActivity.class);", getLocalClassName());
        Intent it = new Intent(SecaoActivity.this, AuditorActivity.class);
        startActivity(it);
        finish();
    }

}
