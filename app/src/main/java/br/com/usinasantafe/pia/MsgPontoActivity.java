package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;

public class MsgPontoActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_ponto);

        piaContext = (PIAContext) getApplication();

        TextView textViewPonto = (TextView) findViewById(R.id.textViewMsgPonto);
        Button buttonSimPonto = (Button) findViewById(R.id.buttonSimPonto);
        Button buttonNaoPonto = (Button) findViewById(R.id.buttonNaoPonto);

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listCabec = cabecAmostraTO.get("statusAmostra", 1L);
        cabecAmostraTO = (CabecAmostraTO) listCabec.get(0);

        ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
        List lItem = itemAmostraTO.get("tipoAmostraItem", 3L);
        itemAmostraTO = (ItemAmostraTO) lItem.get(0);

        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecRespItem");
        pesquisa.setValor(cabecAmostraTO.getIdCabec());
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idAmostraRespItem");
        pesquisa2.setValor(itemAmostraTO.getIdAmostraItem());
        listaPesq.add(pesquisa2);

        RespItemAmostraTO respItemAmostraTOPesq = new RespItemAmostraTO();
        List lVPonto = respItemAmostraTOPesq.get(listaPesq);

        int qtde = lVPonto.size() + 1;
        textViewPonto.setText("DESEJA INSERIR PONTO " + qtde + "?");

        buttonSimPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                piaContext.setPosQuestaoAmostra(0);
                Intent it = new Intent(MsgPontoActivity.this, QuestaoAmostraActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonNaoPonto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(MsgPontoActivity.this, ListaPontosActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }


}
