package br.com.usinasantafe.pia;

import android.app.AlertDialog;
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

import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;

public class ListaQuestaoActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private ListView lista;
    private List lRespItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questao);

        piaContext = (PIAContext) getApplication();

        TextView textViewTituloPonto = (TextView) findViewById(R.id.textViewTituloPonto);
        Button buttonListaQuestaoRetornar =  (Button) findViewById(R.id.buttonListaQuestaoRetornar);
        Button buttonListaQuestaoExcluir =  (Button) findViewById(R.id.buttonListaQuestaoExcluir);

        int pos = piaContext.getPosPonto() + 1;
        textViewTituloPonto.setText("PONTO " + pos);

        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
        List lItem = itemAmostraTO.all();
        itemAmostraTO = (ItemAmostraTO) lItem.get(0);

        RespItemAmostraTO respItemAmostraTOPesq = new RespItemAmostraTO();
        List lVPonto = respItemAmostraTOPesq.get("idAmostraRespItem", itemAmostraTO.getIdAmostraItem());
        respItemAmostraTOPesq = (RespItemAmostraTO) lVPonto.get(piaContext.getPosPonto());

        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
        lRespItem = respItemAmostraTO.get("pontoRespItem", respItemAmostraTOPesq.getPontoRespItem());

        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < lRespItem.size(); i++) {
            respItemAmostraTO = (RespItemAmostraTO) lRespItem.get(i);
            ItemAmostraTO item = new ItemAmostraTO();
            List li = item.get("idAmostraItem", respItemAmostraTO.getIdAmostraRespItem());
            item = (ItemAmostraTO) li.get(0);
            itens.add(item.getDescrItem() + "\nVALOR: " + respItemAmostraTO.getValorRespItem());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewQuestao);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                RespItemAmostraTO respItemAmostraTO = (RespItemAmostraTO) lRespItem.get(position);
                piaContext.setIdRespItem(respItemAmostraTO.getIdRespItem());
                piaContext.setVerTelaQuestao(2);
                lRespItem.clear();

                Intent it = new Intent(ListaQuestaoActivity.this, QuestaoAmostraActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonListaQuestaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ListaQuestaoActivity.this, ListaPontosActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonListaQuestaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE PONTO?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < lRespItem.size(); i++) {

//                            AltExcluirItemTO altExcluirItemTO = new AltExcluirItemTO();
//                            altExcluirItemTO.setIdRespItem(respItemAmostraTO.getIdRespItem());
//                            altExcluirItemTO.setIdCabecRespItem(respItemAmostraTO.getIdCabecRespItem());
//                            altExcluirItemTO.setIdAmostraRespItem(respItemAmostraTO.getIdAmostraRespItem());
//                            altExcluirItemTO.setPontoRespItem(respItemAmostraTO.getPontoRespItem());
//                            altExcluirItemTO.setValorRespItem(respItemAmostraTO.getValorRespItem());
//                            altExcluirItemTO.setTipoAltExc(2L);
//                            altExcluirItemTO.insert();
//
//                            ItemSalvoTO itemSalvoTO = new ItemSalvoTO();
//                            List itemSalvoList = itemSalvoTO.get("idRespItem", respItemAmostraTO.getIdRespItem());
//                            itemSalvoTO = (ItemSalvoTO) itemSalvoList.get(0);
//                            itemSalvoTO.delete();

                            RespItemAmostraTO respItemAmostraTO = (RespItemAmostraTO) lRespItem.get(i);
                            respItemAmostraTO.delete();
                            respItemAmostraTO.commit();

                        }

//                        ManipDadosEnvio.getInstance().envioDados(ListaQuestaoActivity.this);

                        Intent it = new Intent(ListaQuestaoActivity.this, ListaPontosActivity.class);
                        startActivity(it);
                        finish();

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

    }

    public void onBackPressed()  {
    }

}
