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

public class AuditorActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor);

        piaContext = (PIAContext) getApplication();

        Button buttonOkAuditor = findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(AuditorActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(AuditorActivity.this);
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
                        progressBar = new ProgressDialog(AuditorActivity.this);
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(AuditorActivity.this, AuditorActivity.class, progressBar, \"Auditor\", 1, getLocalClassName());", getLocalClassName());
                        piaContext.getInfestacaoCTR().atualDados(AuditorActivity.this, AuditorActivity.class, progressBar, "Auditor", 1, getLocalClassName());

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(AuditorActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                                @Override\n" +
                                "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                }\n" +
                                "                            });\n" +
                                "                            alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(AuditorActivity.this);
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

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

        buttonOkAuditor.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkAuditor.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(!editTextPadrao.getText().toString().equals("")){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                        "                    Long matricAuditor = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                Long matricAuditor = Long.parseLong(editTextPadrao.getText().toString());
                if(piaContext.getInfestacaoCTR().verAuditorCod(matricAuditor)){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().verAuditorCod(matricAuditor)){\n" +
                            "                        piaContext.getConfigCTR().setAuditor(matricAuditor);\n" +
                            "                        Intent it = new Intent(AuditorActivity.this, SecaoActivity.class);", getLocalClassName());
                    piaContext.getConfigCTR().setAuditor(piaContext.getInfestacaoCTR().getAuditorMatric(matricAuditor).getIdAuditor());
                    Intent it = new Intent(AuditorActivity.this, ListaOrganActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonCancAuditor.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancAuditor.setOnClickListener(new View.OnClickListener() {\n" +
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
                "        Intent it = new Intent(AuditorActivity.this, MenuInicialActivity.class);", getLocalClassName());
        Intent it = new Intent(AuditorActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}
