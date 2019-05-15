package br.com.usinasantafe.pia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.bo.ManipDadosEnvio;
import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.tb.estaticas.AuditorTO;
import br.com.usinasantafe.pia.tb.estaticas.SecaoTO;
import br.com.usinasantafe.pia.tb.estaticas.TalhaoTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;

public class ListaPontosActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
//    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pontos);

        piaContext = (PIAContext) getApplication();

        TextView textViewAuditor = (TextView) findViewById(R.id.textViewAuditor);
        TextView textViewSecao = (TextView) findViewById(R.id.textViewSecao);
        TextView textViewTalhao = (TextView) findViewById(R.id.textViewTalhao);
        Button buttonInserirPonto = (Button) findViewById(R.id.buttonInserirPonto);
        Button buttonExcluirAnalise = (Button) findViewById(R.id.buttonExcluirAnalise);
        Button buttonEnviarAnalise = (Button)  findViewById(R.id.buttonEnviarAnalise);

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List lCabec = cabecAmostraTO.get("statusAmostra", 1L);
        cabecAmostraTO = (CabecAmostraTO) lCabec.get(0);

        AuditorTO auditorTO = new AuditorTO();
        List lAuditor = auditorTO.get("idAuditor", cabecAmostraTO.getAuditorCabec());
        auditorTO = (AuditorTO) lAuditor.get(0);

        textViewAuditor.setText(auditorTO.getCodAuditor() + " - " + auditorTO.getNomeAuditor());

        SecaoTO secaoTO = new SecaoTO();
        List lSecao = secaoTO.get("idSecao", cabecAmostraTO.getSecaoCabec());
        secaoTO = (SecaoTO) lSecao.get(0);

        textViewSecao.setText("SECAO: " + secaoTO.getCodSecao() + " - " + secaoTO.getDescrSecao());

        TalhaoTO talhaoTO = new TalhaoTO();
        List lTalhao = talhaoTO.get("idTalhao", cabecAmostraTO.getTalhaoCabec());
        talhaoTO = (TalhaoTO) lTalhao.get(0);

        textViewTalhao.setText("TALHAO: " + talhaoTO.getCodTalhao());

        ArrayList<String> itens = new ArrayList<String>();

        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
        List lItem = itemAmostraTO.get("tipoAmostraItem", 3L);
        itemAmostraTO = (ItemAmostraTO) lItem.get(0);

        ArrayList listaPesq = new ArrayList();
        RespItemAmostraTO respItemAmostraTOPesq = new RespItemAmostraTO();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idAmostraRespItem");
        pesquisa.setValor(itemAmostraTO.getIdAmostraItem());
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idCabecRespItem");
        pesquisa2.setValor(cabecAmostraTO.getIdCabec());
        listaPesq.add(pesquisa2);

        List lVPonto = respItemAmostraTOPesq.get(listaPesq);

        for (int i = 0; i < lVPonto.size(); i++) {
            int pos = i + 1;
            itens.add("PONTO " + pos);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewPontos);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                piaContext.setPosPonto(position);
                Intent it = new Intent(ListaPontosActivity.this, ListaQuestaoActivity.class);
                startActivity(it);

            }

        });

        buttonInserirPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                piaContext.setVerTelaQuestao(1);
                Intent it = new Intent(ListaPontosActivity.this, MsgPontoActivity.class);
                startActivity(it);

            }
        });

        buttonExcluirAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE ANALISE?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                        List lCabec = cabecAmostraTO.get("statusAmostra", 1L);
                        cabecAmostraTO = (CabecAmostraTO) lCabec.get(0);

                        RespItemAmostraTO respItemAmostraTOPesq = new RespItemAmostraTO();
                        List lResp = respItemAmostraTOPesq.get("idCabecRespItem", cabecAmostraTO.getIdCabec());

                        for (int i = 0; i < lResp.size(); i++) {

                            RespItemAmostraTO respItemAmostraTO = (RespItemAmostraTO) lResp.get(i) ;
                            respItemAmostraTO.delete();
                            respItemAmostraTO.commit();

                        }

                        cabecAmostraTO.delete();
                        cabecAmostraTO.commit();

                        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
                        itemAmostraTO.deleteAll();
                        itemAmostraTO.commit();

                        Intent it = new Intent(ListaPontosActivity.this, PrincipalActivity.class);
                        startActivity(it);

                    }

                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });

                alerta.show();

            }
        });

        buttonEnviarAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                List lCabec = cabecAmostraTO.get("statusAmostra", 1L);
                cabecAmostraTO = (CabecAmostraTO) lCabec.get(0);

                RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
                List lResp = respItemAmostraTO.get("idCabecRespItem", cabecAmostraTO.getIdCabec());

                if(lResp.size() == 0){

                    String mensagem = "POR FAVOR, INSIRA PONTOS ANTES DE ENVIAR OS DADOS.";

                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPontosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage(mensagem);

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }
                else{

                    cabecAmostraTO.setStatusAmostra(2L);
                    cabecAmostraTO.update();
                    cabecAmostraTO.commit();

                    ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
                    itemAmostraTO.deleteAll();
                    itemAmostraTO.commit();

                    ManipDadosEnvio.getInstance().enviarBolFechados();
                    Intent it = new Intent( ListaPontosActivity.this, PrincipalActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

    }

    public void onBackPressed()  {
    }
}
