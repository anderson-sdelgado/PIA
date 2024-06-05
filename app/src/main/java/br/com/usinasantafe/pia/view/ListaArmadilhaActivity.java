package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AuditorBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LocalAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.EnvioDadosServ;

public class ListaArmadilhaActivity extends ActivityGeneric {

    private ListView listView;
    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_armadilha);

        piaContext = (PIAContext) getApplication();

        TextView textViewAuditor = findViewById(R.id.textViewAuditor);
        Button buttonInserirArmadilha = findViewById(R.id.buttonInserirArmadilha);
        Button buttonExcluirAnalise = findViewById(R.id.buttonExcluirAnalise);
        Button buttonEnviarAnalise = findViewById(R.id.buttonEnviarAnalise);

        LogProcessoDAO.getInstance().insertLogProcesso("AuditorBean auditorBean = piaContext.getInfestacaoCTR().getAuditor();\n" +
                "        textViewAuditor.setText(auditorBean.getMatricAuditor() + \" - \" + auditorBean.getNomeAuditor());\n" +
                "        SecaoBean secaoBean = piaContext.getInfestacaoCTR().getSecao();", getLocalClassName());

        AuditorBean auditorBean = piaContext.getInfestacaoCTR().getAuditor();
        textViewAuditor.setText(auditorBean.getMatricAuditor() + " - " + auditorBean.getNomeAuditor());

        ArrayList<String> itens = new ArrayList<>();

        piaContext.getInfestacaoCTR().clearPontoIncompleto();

        for (int i = 0; i < piaContext.getInfestacaoCTR().ponto(); i++) {
            Long ponto = i + 1L;
            RespItemAmostraBean respItemAmostraBean =  piaContext.getInfestacaoCTR().getRespItemAmostraCabecApontList(ponto).get(0);
            LocalAmostraBean localAmostraBean = piaContext.getInfestacaoCTR().getLocalAmostraId(respItemAmostraBean.getIdLocal());
            String obs = localAmostraBean.getObs() == null ? "" : localAmostraBean.getObs();
            itens.add("ARMADILHA " + ponto + "\n" +
                    "DATA/HORA: " + localAmostraBean.getDthr() + "\n" +
                    "SECAO: " + piaContext.getInfestacaoCTR().getSecaoId(localAmostraBean.getIdSecao()).getCodSecao() + "\n" +
                    "TALHÃO: " + piaContext.getInfestacaoCTR().getTalhaoId(localAmostraBean.getIdTalhao()).getCodTalhao() + "\n" +
                    "OBS.:  " + obs);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        listView = findViewById(R.id.listViewArmadilha);
        listView.setAdapter(adapterList);

        listView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                piaContext.setPosPonto(position);\n" +
                    "                Intent it = new Intent(ListaArmadilhaActivity.this, ListaQuestaoActivity.class);", getLocalClassName());
            piaContext.setPosPonto(position + 1);
            Intent it = new Intent(ListaArmadilhaActivity.this, ListaQuestaoActivity.class);
            startActivity(it);
            finish();

        });

        buttonInserirArmadilha.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonInserirPonto.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                piaContext.setVerTelaQuestao(1);\n" +
                    "                Intent it = new Intent(ListaArmadilhaActivity.this, MsgPontoActivity.class);", getLocalClassName());
            piaContext.setVerTelaQuestao(1);
            Intent it = new Intent(ListaArmadilhaActivity.this, MsgPontoArmadilhaActivity.class);
            startActivity(it);
            finish();

        });

        buttonExcluirAnalise.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonExcluirAnalise.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaArmadilhaActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJAR REALMENTE EXCLUIR ESSE ANALISE?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaArmadilhaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE ANALISE?");
            alerta.setPositiveButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        piaContext.getInfestacaoCTR().deleteCabecAbertoAll();\n" +
                        "                        Intent it = new Intent(ListaArmadilhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().deleteCabecAbertoApontAll();
                Intent it = new Intent(ListaArmadilhaActivity.this, TelaInicialActivity.class);
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
            if(!piaContext.getInfestacaoCTR().verRespItemAmostraCabecAbertoApontList()){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!piaContext.getInfestacaoCTR().verRespItemAmostraList()){\n" +
                        "                    String mensagem = \"POR FAVOR, INSIRA PONTOS ANTES DE ENVIAR OS DADOS.\";\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaArmadilhaActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(mensagem);", getLocalClassName());
                String mensagem = "POR FAVOR, INSIRA PONTOS ANTES DE ENVIAR OS DADOS.";
                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaArmadilhaActivity.this);
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
                            "                    EnvioDadosServ.getInstance().enviarAmostra(getLocalClassName());\n" +
                            "                    Intent it = new Intent( ListaArmadilhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    EnvioDadosServ.getInstance().enviarAmostra(getLocalClassName());
                    Intent it = new Intent( ListaArmadilhaActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaArmadilhaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                Intent it = new Intent( ListaArmadilhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                        Intent it = new Intent( ListaArmadilhaActivity.this, TelaInicialActivity.class);
                        startActivity(it);
                        finish();
                    });

                    alerta.show();
                }


            }

        });
    }
}