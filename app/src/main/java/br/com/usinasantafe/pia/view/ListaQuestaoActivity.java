package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class ListaQuestaoActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private ListView lista;
    private List<RespItemAmostraBean> respItemAmostraList;

    private boolean tipoAmostra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questao);

        piaContext = (PIAContext) getApplication();

        TextView textViewTituloPonto = findViewById(R.id.textViewTituloPonto);
        Button buttonListaQuestaoRetornar =  findViewById(R.id.buttonListaQuestaoRetornar);
        Button buttonListaQuestaoExcluir =  findViewById(R.id.buttonListaQuestaoExcluir);

        LogProcessoDAO.getInstance().insertLogProcesso("int pos = piaContext.getPosPonto() + 1;\n" +
                "        textViewTituloPonto.setText(\"PONTO \" + pos);\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        respItemAmostraList = piaContext.getInfestacaoCTR().getRespItemAmostraList((long) piaContext.getPosPonto());\n" +
                "        for (RespItemAmostraBean respItemAmostraBean : respItemAmostraList) {\n" +
                "            AmostraBean amostraBean = piaContext.getInfestacaoCTR().getAmostra(respItemAmostraBean.getIdAmostraRespItem());\n" +
                "            itens.add(amostraBean.getDescrAmostra() + \"\\nVALOR: \" + respItemAmostraBean.getValorRespItem());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        lista = (ListView) findViewById(R.id.listViewQuestao);\n" +
                "        lista.setAdapter(adapterList);", getLocalClassName());
        int pos = piaContext.getPosPonto();
        String tipo;
        tipoAmostra = piaContext.getConfigCTR().getIdAmostra() == 51L;

        if(tipoAmostra) {
            tipo = "AMOSTRA";
        } else {
            tipo = "PONTO";
        }
        textViewTituloPonto.setText(tipo + " " + pos);

        ArrayList<String> itens = new ArrayList<>();
        respItemAmostraList = piaContext.getInfestacaoCTR().getRespItemAmostraLocalApontList((long) piaContext.getPosPonto());

        for (RespItemAmostraBean respItemAmostraBean : respItemAmostraList) {
            AmostraBean amostraBean = piaContext.getInfestacaoCTR().getAmostraIdAmostra(respItemAmostraBean.getIdAmostra());
            String obs = "";
            String valor = String.valueOf(respItemAmostraBean.getValor());
            if(tipoAmostra){
                obs = "\nOBSERV: " + ((respItemAmostraBean.getObs() == null) ? "" : respItemAmostraBean.getObs());
                valor = (respItemAmostraBean.getValor() == null) ? "" : String.valueOf(respItemAmostraBean.getValor());
            }
            itens.add(amostraBean.getDescrAmostra() + "\nVALOR: " + valor + obs);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listViewQuestao);
        lista.setAdapter(adapterList);
        lista.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                RespItemAmostraBean respItemAmostraTO = (RespItemAmostraBean) respItemAmostraList.get(position);\n" +
                    "                piaContext.setIdRespItem(respItemAmostraTO.getIdRespItem());\n" +
                    "                piaContext.setVerTelaQuestao(2);\n" +
                    "                respItemAmostraList.clear();\n" +
                    "                Intent it = new Intent(ListaQuestaoActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
            RespItemAmostraBean respItemAmostraTO = respItemAmostraList.get(position);
            piaContext.setIdRespItem(respItemAmostraTO.getIdRespItemAmostra());
            piaContext.setVerTelaQuestao(2);
            respItemAmostraList.clear();

            Intent it = new Intent(ListaQuestaoActivity.this, QuestaoAmostraActivity.class);
            startActivity(it);
            finish();

        });

        buttonListaQuestaoRetornar.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonListaQuestaoRetornar.setOnClickListener(v -> {", getLocalClassName());
            Intent it;
            if(tipoAmostra){
                LogProcessoDAO.getInstance().insertLogProcesso("if(tipoAmostra){\n" +
                        "                it = new Intent(ListaQuestaoActivity.this, ListaArmadilhaActivity.class);", getLocalClassName());
                it = new Intent(ListaQuestaoActivity.this, ListaArmadilhaActivity.class);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                it = new Intent(ListaQuestaoActivity.this, ListaPontosActivity.class);", getLocalClassName());
                it = new Intent(ListaQuestaoActivity.this, ListaPontosActivity.class);
            }

            startActivity(it);
            finish();

        });

        buttonListaQuestaoExcluir.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonListaQuestaoExcluir.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJAR REALMENTE EXCLUIR ESSE PONTO?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE PONTO?");
            alerta.setPositiveButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        piaContext.getInfestacaoCTR().deleteRespItemAmostra((long) piaContext.getPosPonto());\n" +
                        "                        Intent it = new Intent(ListaQuestaoActivity.this, ListaPontosActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().deleteRespItemAmostraPonto((long) piaContext.getPosPonto());
                Intent it = new Intent(ListaQuestaoActivity.this, ListaPontosActivity.class);
                startActivity(it);
                finish();

            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

    }

    public void onBackPressed()  {
    }

}
