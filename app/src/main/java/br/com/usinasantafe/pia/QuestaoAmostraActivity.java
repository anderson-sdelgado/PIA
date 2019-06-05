package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;

public class QuestaoAmostraActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private List listAmostra;
    private ItemAmostraTO itemAmostraTO;
    private RespItemAmostraTO respItemAmostraTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_amostra);

        piaContext = (PIAContext) getApplication();

        TextView textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQA = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQA = (Button) findViewById(R.id.buttonCancPadrao);
        EditText editText = (EditText) findViewById(R.id.editTextPadrao);

        itemAmostraTO = new ItemAmostraTO();

        if(piaContext.getVerTelaQuestao() == 1){

            listAmostra = itemAmostraTO.get("tipoAmostraItem", 3L);
            itemAmostraTO = (ItemAmostraTO) listAmostra.get(piaContext.getPosQuestaoAmostra());
            textViewPadrao.setText(itemAmostraTO.getDescrItem());
            editText.setText("");

        }
        else if(piaContext.getVerTelaQuestao() == 2){

            respItemAmostraTO = new RespItemAmostraTO();
            List listResp = respItemAmostraTO.get("idRespItem", piaContext.getIdRespItem());
            respItemAmostraTO = (RespItemAmostraTO) listResp.get(0);
            listAmostra = itemAmostraTO.get("idAmostraItem", respItemAmostraTO.getIdAmostraRespItem());
            itemAmostraTO = (ItemAmostraTO) listAmostra.get(0);
            textViewPadrao.setText(itemAmostraTO.getDescrItem());
            editText.setText(String.valueOf(respItemAmostraTO.getValorRespItem()));

        }

        buttonOkQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                List lCabec = cabecAmostraTO.get("statusAmostra", 1L);
                cabecAmostraTO = (CabecAmostraTO) lCabec.get(0);

                if(piaContext.getVerTelaQuestao() == 1) {

                    Long qtde = cabecAmostraTO.getUltPonto() + 1;

                    respItemAmostraTO = new RespItemAmostraTO();
                    respItemAmostraTO.setIdCabecRespItem(cabecAmostraTO.getIdCabec());
                    respItemAmostraTO.setIdAmostraRespItem(itemAmostraTO.getIdAmostraItem());
                    respItemAmostraTO.setPontoRespItem(qtde);

                    if (!editTextPadrao.getText().toString().equals("")) {
                        respItemAmostraTO.setValorRespItem(Long.parseLong(editTextPadrao.getText().toString()));
                    } else {
                        respItemAmostraTO.setValorRespItem(0L);
                    }
                    respItemAmostraTO.insert();
                    respItemAmostraTO.commit();

                    piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() + 1);

                    if (piaContext.getPosQuestaoAmostra() < listAmostra.size()) {

                        Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        cabecAmostraTO.setUltPonto(cabecAmostraTO.getUltPonto() + 1);
                        cabecAmostraTO.update();

                        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
                        List lItem = itemAmostraTO.dif("valorAmostraItem", 0L);

                        if(lItem.size() > 0){

                            itemAmostraTO = (ItemAmostraTO) lItem.get(0);

                            respItemAmostraTO = new RespItemAmostraTO();
                            respItemAmostraTO.setIdCabecRespItem(cabecAmostraTO.getIdCabec());
                            respItemAmostraTO.setIdAmostraRespItem(itemAmostraTO.getIdAmostraItem());
                            respItemAmostraTO.setPontoRespItem(qtde);
                            respItemAmostraTO.setValorRespItem(itemAmostraTO.getValorAmostraItem());
                            respItemAmostraTO.insert();

                        }

                        Intent it = new Intent(QuestaoAmostraActivity.this, MsgPontoActivity.class);
                        startActivity(it);
                        finish();

                    }

                }
                else if(piaContext.getVerTelaQuestao() == 2) {

                    if (!editTextPadrao.getText().toString().equals("")) {
                        respItemAmostraTO.setValorRespItem(Long.parseLong(editTextPadrao.getText().toString()));
                    } else {
                        respItemAmostraTO.setValorRespItem(0L);
                    }

//                    AltExcluirItemTO altExcluirItemTO = new AltExcluirItemTO();
//                    altExcluirItemTO.setIdRespItem(respItemAmostraTO.getIdRespItem());
//                    altExcluirItemTO.setIdCabecRespItem(respItemAmostraTO.getIdCabecRespItem());
//                    altExcluirItemTO.setIdAmostraRespItem(respItemAmostraTO.getIdAmostraRespItem());
//                    altExcluirItemTO.setPontoRespItem(respItemAmostraTO.getPontoRespItem());
//                    altExcluirItemTO.setValorRespItem(respItemAmostraTO.getValorRespItem());
//                    altExcluirItemTO.setTipoAltExc(1L);
//                    altExcluirItemTO.insert();

//                    ItemSalvoTO itemSalvoTO = new ItemSalvoTO();
//                    List itemSalvoList = itemSalvoTO.get("idRespItem", respItemAmostraTO.getIdRespItem());
//                    if(itemSalvoList.size() > 0){
//                        itemSalvoTO = (ItemSalvoTO) itemSalvoList.get(0);
//                        itemSalvoTO.delete();
//                    }

                    respItemAmostraTO.update();
                    respItemAmostraTO.commit();

                    Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);
                    startActivity(it);
                    finish();

                }

                listAmostra.clear();
                System.gc();

            }
        });

        buttonCancQA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else {

                    if(piaContext.getVerTelaQuestao() == 1) {

                        if (piaContext.getPosQuestaoAmostra() == 0) {

                            CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                            List lCabec = cabecAmostraTO.get("statusAmostra", 1L);
                            cabecAmostraTO = (CabecAmostraTO) lCabec.get(0);

                            ArrayList listaPesq = new ArrayList();

                            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                            pesquisa.setCampo("idCabecRespItem");
                            pesquisa.setValor(cabecAmostraTO.getIdCabec());
                            listaPesq.add(pesquisa);

                            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                            pesquisa2.setCampo("pontoRespItem");
                            pesquisa2.setValor(cabecAmostraTO.getUltPonto() + 1);
                            listaPesq.add(pesquisa2);

                            RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
                            List respItemList = respItemAmostraTO.get(listaPesq);

                            for (int i = 0; i < respItemList.size(); i++) {
                                respItemAmostraTO = (RespItemAmostraTO) respItemList.get(i);
                                respItemAmostraTO.delete();
                                respItemAmostraTO.commit();
                            }

                            Intent it = new Intent(QuestaoAmostraActivity.this, ListaPontosActivity.class);
                            startActivity(it);
                            listAmostra.clear();
                            System.gc();
                            finish();

                        } else {
                            piaContext.setPosQuestaoAmostra(piaContext.getPosQuestaoAmostra() - 1);
                            Intent it = new Intent(QuestaoAmostraActivity.this, QuestaoAmostraActivity.class);
                            startActivity(it);
                            listAmostra.clear();
                            System.gc();
                            finish();
                        }

                    }
                    else if(piaContext.getVerTelaQuestao() == 2) {

                        Intent it = new Intent(QuestaoAmostraActivity.this, ListaQuestaoActivity.class);
                        startActivity(it);
                        listAmostra.clear();
                        System.gc();
                        finish();

                    }

                }



            }
        });

    }

    public void onBackPressed()  {
    }

}
