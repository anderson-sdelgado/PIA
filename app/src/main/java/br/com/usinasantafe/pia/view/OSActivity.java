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

public class OSActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os_activity);

        piaContext = (PIAContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "progressBar = new ProgressDialog(OSActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                            progressBar.setProgress(0);\n" +
                            "                            progressBar.setMax(100);\n" +
                            "                            progressBar.show();", getLocalClassName());
                    progressBar = new ProgressDialog(OSActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(OSActivity.this, OSActivity.class, progressBar, \"OS\", 1, getLocalClassName());", getLocalClassName());
                    piaContext.getInfestacaoCTR().atualDados(OSActivity.this, OSActivity.class, progressBar, "OS", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(OSActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {
                    });
                    alerta1.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

        buttonOkOS.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkAuditor.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(!editTextPadrao.getText().toString().equals("")){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                        "                    Long nroOS = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                Long nroOS = Long.parseLong(editTextPadrao.getText().toString());
                if(piaContext.getInfestacaoCTR().verOSNro(nroOS)){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().verOSNro(nroOS)){\n" +
                            "                        piaContext.getConfigCTR().setOS(nroOS);\n" +
                            "                        Intent it = new Intent(OSActivity.this, SecaoActivity.class);", getLocalClassName());
                    piaContext.getConfigCTR().setOS(nroOS);
                    Intent it = new Intent(OSActivity.this, SecaoActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"O.S. INVÁLIDA. POR FAVOR, VERIFIQUE A NUMERAÇÃO DE O.S. DIGITADA.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O.S. INVÁLIDA. POR FAVOR, VERIFIQUE A NUMERAÇÃO DE O.S. DIGITADA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                    alerta.show();

                }

            }

        });

        buttonCancOS.setOnClickListener(v -> {
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
                "        Intent it = new Intent(OSActivity.this, AuditorActivity.class);", getLocalClassName());
        Intent it = new Intent(OSActivity.this, AuditorActivity.class);
        startActivity(it);
        finish();
    }

}