package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.AtualDadosServ;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextNroLinhaConfig;
    private EditText editTextSenhaConfig;
    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        Button buttonSalvarConfig =  findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        Button buttonAtualizarDados = findViewById(R.id.buttonAtualizarDados);
        editTextNroLinhaConfig = findViewById(R.id.editTextNroLinhaConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);

        piaContext = (PIAContext) getApplication();

        if (piaContext.getConfigCTR().hasElements()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getConfigCTR().hasElements()) {\n" +
                    "            editTextNroLinhaConfig.setText(String.valueOf(piaContext.getConfigCTR().getConfig().getNumLinha()));\n" +
                    "            editTextSenhaConfig.setText(piaContext.getConfigCTR().getConfig().getSenha());", getLocalClassName());
            editTextNroLinhaConfig.setText(String.valueOf(piaContext.getConfigCTR().getConfig().getNumLinhaConfig()));
            editTextSenhaConfig.setText(piaContext.getConfigCTR().getConfig().getSenhaConfig());
        }

        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(!editTextNroLinhaConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextNroLinhaConfig.getText().toString().equals(\"\") &&\n" +
                            "                        !editTextSenhaConfig.getText().toString().equals(\"\")){\n" +
                            "                    piaContext.getConfigCTR().salvarConfig(editTextNroLinhaConfig.getText().toString(), editTextSenhaConfig.getText().toString());\n" +
                            "                    Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    piaContext.getConfigCTR().salvarConfig(editTextNroLinhaConfig.getText().toString(), editTextSenhaConfig.getText().toString());
                    Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAtualizarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("btAtualBDConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();\n" +
                            "                    AtualDadosServ.getInstance().atualizarBD(progressBar);\n" +
                            "                    AtualDadosServ.getInstance().atualTodasTabBD( ConfigActivity.this, progressBar, getLocalClassName());", getLocalClassName());
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    AtualDadosServ.getInstance().atualTodasTabBD( ConfigActivity.this, progressBar, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
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
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ConfiguracaoActivity.this, MenuInicialActivity.class);", getLocalClassName());
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
