package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AuditorBean;
import br.com.usinasantafe.pia.model.bean.estaticas.SecaoBean;
import br.com.usinasantafe.pia.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.ConnectNetwork;
import br.com.usinasantafe.pia.util.EnvioDadosServ;

public class ListaPontosActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pontos);

        piaContext = (PIAContext) getApplication();

        TextView textViewAuditor = findViewById(R.id.textViewAuditor);
        TextView textViewSecao = findViewById(R.id.textViewSecao);
        TextView textViewTalhao = findViewById(R.id.textViewTalhao);
        Button buttonInserirPonto = findViewById(R.id.buttonInserirPonto);
        Button buttonExcluirAnalise = findViewById(R.id.buttonExcluirAnalise);
        Button buttonEnviarAnalise = findViewById(R.id.buttonEnviarAnalise);

        LogProcessoDAO.getInstance().insertLogProcesso("AuditorBean auditorBean = piaContext.getInfestacaoCTR().getAuditor();\n" +
                "        textViewAuditor.setText(auditorBean.getMatricAuditor() + \" - \" + auditorBean.getNomeAuditor());\n" +
                "        SecaoBean secaoBean = piaContext.getInfestacaoCTR().getSecao();\n" +
                "        textViewSecao.setText(\"SECAO: \" + secaoBean.getCodSecao() + \" - \" + secaoBean.getDescrSecao());\n" +
                "        TalhaoBean talhaoBean = piaContext.getInfestacaoCTR().getTalhao();\n" +
                "        textViewTalhao.setText(\"TALHAO: \" + talhaoBean.getCodTalhao());\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        List<RespItemAmostraBean> respItemAmostraPontoList = piaContext.getInfestacaoCTR().respItemAmostraPontoList();\n" +
                "        for (int i = 0; i < respItemAmostraPontoList.size(); i++) {\n" +
                "            int pos = i + 1;\n" +
                "            itens.add(\"PONTO \" + pos);\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        lista = (ListView) findViewById(R.id.listViewPontos);\n" +
                "        lista.setAdapter(adapterList);", getLocalClassName());

        AuditorBean auditorBean = piaContext.getInfestacaoCTR().getAuditor();
        textViewAuditor.setText(auditorBean.getMatricAuditor() + " - " + auditorBean.getNomeAuditor());

        SecaoBean secaoBean = piaContext.getInfestacaoCTR().getSecao();
        textViewSecao.setText("SECAO: " + secaoBean.getCodSecao() + " - " + secaoBean.getDescrSecao());

        TalhaoBean talhaoBean = piaContext.getInfestacaoCTR().getTalhao();
        textViewTalhao.setText("TALHAO: " + talhaoBean.getCodTalhao());

        ArrayList<String> itens = new ArrayList<>();

        for (int i = 0; i < piaContext.getInfestacaoCTR().ultPonto(); i++) {
            int pos = i + 1;
            itens.add("PONTO " + pos);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listViewPontos);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                piaContext.setPosPonto(position);\n" +
                    "                Intent it = new Intent(ListaPontosActivity.this, ListaQuestaoActivity.class);", getLocalClassName());
            piaContext.setPosPonto(position);
            Intent it = new Intent(ListaPontosActivity.this, ListaQuestaoActivity.class);
            startActivity(it);
            finish();

        });

        buttonInserirPonto.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonInserirPonto.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                piaContext.setVerTelaQuestao(1);\n" +
                    "                Intent it = new Intent(ListaPontosActivity.this, MsgPontoActivity.class);", getLocalClassName());
            piaContext.setVerTelaQuestao(1);
            Intent it = new Intent(ListaPontosActivity.this, MsgPontoActivity.class);
            startActivity(it);
            finish();

        });

        buttonExcluirAnalise.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonExcluirAnalise.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJAR REALMENTE EXCLUIR ESSE ANALISE?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE ANALISE?");
            alerta.setPositiveButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        piaContext.getInfestacaoCTR().deleteCabecAbertoAll();\n" +
                        "                        Intent it = new Intent(ListaPontosActivity.this, MenuInicialActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().deleteCabecAbertoAll();
                Intent it = new Intent(ListaPontosActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

        buttonEnviarAnalise.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonEnviarAnalise.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(!piaContext.getInfestacaoCTR().verRespItemAmostraList()){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!piaContext.getInfestacaoCTR().verRespItemAmostraList()){\n" +
                        "                    String mensagem = \"POR FAVOR, INSIRA PONTOS ANTES DE ENVIAR OS DADOS.\";\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPontosActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(mensagem);", getLocalClassName());
                String mensagem = "POR FAVOR, INSIRA PONTOS ANTES DE ENVIAR OS DADOS.";

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPontosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage(mensagem);
                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                alerta.show();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    piaContext.getInfestacaoCTR().fecharCabec();", getLocalClassName());
                piaContext.getInfestacaoCTR().fecharCabec();

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                        progressBar = new ProgressDialog(ListaPontosActivity.this);\n" +
                            "                        progressBar.setCancelable(true);\n" +
                            "                        progressBar.setMessage(\"ENVIANDO DADOS...\");\n" +
                            "                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                        progressBar.setProgress(0);\n" +
                            "                        progressBar.setMax(100);\n" +
                            "                        progressBar.show();\n" +
                            "                        EnvioDadosServ.getInstance().envioBoletim(ListaPontosActivity.this, progressBar, MenuInicialActivity.class, getLocalClassName());", getLocalClassName());
                    progressBar = new ProgressDialog(ListaPontosActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ENVIANDO DADOS...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    EnvioDadosServ.getInstance().envioBoletim(ListaPontosActivity.this, progressBar, MenuInicialActivity.class, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                Intent it = new Intent( ListaPontosActivity.this, MenuInicialActivity.class);", getLocalClassName());
                        Intent it = new Intent( ListaPontosActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();
                    });

                    alerta.show();
                }


            }

        });

    }

    public void onBackPressed()  {
    }
}
