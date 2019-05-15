package br.com.usinasantafe.pia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pia.bo.ManipDadosEnvio;
import br.com.usinasantafe.pia.bo.ManipDadosReceb;
import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.tb.estaticas.OrganTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        piaContext = (PIAContext) getApplication();

        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);
        customHandler.postDelayed(updateTimerThread, 0);

//        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
//        List cabecList = cabecAmostraTO.all();
//
//        Log.i("PIA", "QTDE CABEC = " + cabecList.size());
//
//        for (int i = 0; i < cabecList.size(); i++) {
//
//            cabecAmostraTO = (CabecAmostraTO) cabecList.get(i);
//
//            Log.i("PIA", "idCabec = " + cabecAmostraTO.getIdCabec());
//            Log.i("PIA", "auditorCabec = " + cabecAmostraTO.getAuditorCabec());
//            Log.i("PIA", "dtCabec = " + cabecAmostraTO.getDtCabec());
//            Log.i("PIA", "secaoCabec = " + cabecAmostraTO.getSecaoCabec());
//            Log.i("PIA", "talhaoCabec = " + cabecAmostraTO.getTalhaoCabec());
//            Log.i("PIA", "idOrgCabec = " + cabecAmostraTO.getIdOrgCabec());
//            Log.i("PIA", "idCaracOrgCabec = " + cabecAmostraTO.getIdCaracOrgCabec());
//            Log.i("PIA", "ultPonto = " + cabecAmostraTO.getUltPonto());
//            Log.i("PIA", "statusAmostra = " + cabecAmostraTO.getStatusAmostra());
//
//        }
//
//        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
//        List itemList = itemAmostraTO.all();
//
//        Log.i("PIA", "QTDE ITEM = " + itemList.size());
//
//        for (int i = 0; i < itemList.size(); i++) {
//
//            itemAmostraTO = (ItemAmostraTO) itemList.get(i);
//
//            Log.i("PIA", "idItem = " + itemAmostraTO.getIdItem());
//            Log.i("PIA", "idCabecItem = " + itemAmostraTO.getIdCabecItem());
//            Log.i("PIA", "idAmostraItem = " + itemAmostraTO.getIdAmostraItem());
//            Log.i("PIA", "descrItem = " + itemAmostraTO.getDescrItem());
//            Log.i("PIA", "valorAmostraItem = " + itemAmostraTO.getValorAmostraItem());
//            Log.i("PIA", "tipoAmostraItem = " + itemAmostraTO.getTipoAmostraItem());
//
//        }
//
//        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
//        List respList = respItemAmostraTO.all();
//
//        Log.i("PIA", "QTDE RESP = " + respList.size());
//
//        for (int i = 0; i < respList.size(); i++) {
//
//            respItemAmostraTO = (RespItemAmostraTO) respList.get(i);
//
//            Log.i("PIA", "idRespItem = " + respItemAmostraTO.getIdRespItem());
//            Log.i("PIA", "idCabecRespItem = " + respItemAmostraTO.getIdCabecRespItem());
//            Log.i("PIA", "idAmostraRespItem = " + respItemAmostraTO.getIdAmostraRespItem());
//            Log.i("PIA", "pontoRespItem = " + respItemAmostraTO.getPontoRespItem());
//            Log.i("PIA", "valorRespItem = " + respItemAmostraTO.getValorRespItem());
//
//        }

        timer();

        OrganTO organTO = new OrganTO();
        organTO.hasElements();

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                if (position == 0) {

                    CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                    List lCabec = cabecAmostraTO.get("statusAmostra", 1L);

                    if(lCabec.size() == 0){

                        Intent it = new Intent(PrincipalActivity.this, AuditorActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
                        List itemList = itemAmostraTO.all();

                        if(itemList.size() > 0) {

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

                            Intent it = new Intent(PrincipalActivity.this, ListaPontosActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            for(int i = 0; i < lCabec.size(); i++) {
                                cabecAmostraTO = (CabecAmostraTO) lCabec.get(i);
                                cabecAmostraTO.delete();
                                cabecAmostraTO.commit();
                            }

                            Intent it = new Intent(PrincipalActivity.this, AuditorActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                } else if (position == 1) {

                    Intent it = new Intent(PrincipalActivity.this, ConfiguracaoActivity.class);
                    startActivity(it);
                    finish();

                } else if (position == 2) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }

        });

    }

    public void onBackPressed()  {
    }

    public void timer() {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarmeAtivo){

            ManipDadosReceb.getInstance().tempo();
            Log.i("PIA", "NOVO ALARME");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PIA", "Alarme já ativo");
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(ManipDadosEnvio.getInstance().getStatusEnvio() == 1){
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            }
            else if(ManipDadosEnvio.getInstance().getStatusEnvio() == 2){
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            }
            else if(ManipDadosEnvio.getInstance().getStatusEnvio() == 3){
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

}
