package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.EnvioDadosServ;

public class EnvioDadosActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_dados);

        piaContext = (PIAContext) getApplication();

        TextView textViewEnvioDados = findViewById(R.id.textViewEnvioDados);
        Button buttonSimEnvioDados = findViewById(R.id.buttonSimEnvioDados);
        Button buttonNaoEnvioDados = findViewById(R.id.buttonNaoEnvioDados);

        if(!piaContext.getInfestacaoCTR().verCabecFechado()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(!piaContext.getInfestacaoCTR().verCabecFechado()){\n" +
                    "            textViewEnvioDados.setText(\"NÃO CONTÉM ANALISE(S) PARA SEREM(S) REENVIADA(S).\");", getLocalClassName());
            textViewEnvioDados.setText("NÃO CONTÉM ANALISE(S) PARA SEREM(S) REENVIADA(S).");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            textViewEnvioDados.setText(\"CONTÉM \" + piaContext.getInfestacaoCTR().qtdeCabecFechado() + \" ANALISE(S) PARA SEREM(S) REENVIADA(S).\");", getLocalClassName());
            textViewEnvioDados.setText("CONTÉM " + piaContext.getInfestacaoCTR().qtdeCabecFechado() + " ANALISE(S) PARA SEREM(S) REENVIADA(S).");
        }

        buttonSimEnvioDados.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonSimEnvioDados.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (connectNetwork) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                        "                    EnvioDadosServ.getInstance().enviarAmostra(getLocalClassName());\n" +
                        "                    Intent it = new Intent( ListaPontosActivity.this, MenuInicialActivity.class);", getLocalClassName());
                EnvioDadosServ.getInstance().enviarAmostra(getLocalClassName());
                Intent it = new Intent( EnvioDadosActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(EnvioDadosActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(EnvioDadosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                alerta.show();
            }


        });


        buttonNaoEnvioDados.setOnClickListener(v -> {
            Intent it = new Intent(EnvioDadosActivity.this, MenuInicialActivity.class);
            LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoEnvioDados.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(EnvioDadosActivity.this, MenuInicialActivity.class);", getLocalClassName());
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
