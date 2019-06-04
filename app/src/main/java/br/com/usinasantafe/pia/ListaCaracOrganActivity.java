package br.com.usinasantafe.pia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.bo.ManipDadosEnvio;
import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.tb.estaticas.AmostraTO;
import br.com.usinasantafe.pia.tb.estaticas.CaracOrganTO;
import br.com.usinasantafe.pia.tb.estaticas.RCaracAmostraTO;
import br.com.usinasantafe.pia.tb.estaticas.ROrganCaracTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;

public class ListaCaracOrganActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
    private List caracOrganList;
    private List rOrganCaracList;
    private CabecAmostraTO cabecAmostraTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carac_organ);

        piaContext = (PIAContext) getApplication();

        Button buttonRetCaracOrgan = (Button) findViewById(R.id.buttonRetCaracOrgan);

        cabecAmostraTO = new CabecAmostraTO();
        List listCabecAmostra = cabecAmostraTO.get("statusAmostra", 1L);
        cabecAmostraTO = (CabecAmostraTO) listCabecAmostra.get(0);

        ArrayList<String> itens = new ArrayList<String>();

        Log.i("PBM", "cabecAmostraTO.getIdOrgCabec() = " + cabecAmostraTO.getIdOrgCabec());

        ROrganCaracTO rOrganCaracTO = new ROrganCaracTO();
        rOrganCaracList = rOrganCaracTO.get("idOrgan", cabecAmostraTO.getIdOrgCabec());

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < rOrganCaracList.size(); i++) {
            rOrganCaracTO = (ROrganCaracTO) rOrganCaracList.get(i);
            Log.i("PBM", "rOrganCaracTO.getIdCaracOrgan() = " + rOrganCaracTO.getIdCaracOrgan());
            rLista.add(rOrganCaracTO.getIdCaracOrgan());
        }

        CaracOrganTO caracOrganTO = new CaracOrganTO();
        caracOrganList = caracOrganTO.in("idCaracOrgan", rLista);

        for (int i = 0; i < caracOrganList.size(); i++) {
            caracOrganTO = (CaracOrganTO) caracOrganList.get(i);
            Log.i("PBM", "caracOrganTO.getDescrCaracOrgan() = " + caracOrganTO.getDescrCaracOrgan());
            itens.add(caracOrganTO.getDescrCaracOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listCaracOrgan);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                CaracOrganTO caracOrganTO = (CaracOrganTO) caracOrganList.get(position);

                CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                List listCabecAmostra = cabecAmostraTO.get("statusAmostra", 1L);
                cabecAmostraTO = (CabecAmostraTO) listCabecAmostra.get(0);
                cabecAmostraTO.setIdCaracOrgCabec(caracOrganTO.getIdCaracOrgan());
                cabecAmostraTO.setUltPonto(0L);
                cabecAmostraTO.setIdExtBoletim(0L);
                cabecAmostraTO.update();
                cabecAmostraTO.commit();

                ArrayList listaPesq = new ArrayList();

                EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                pesquisa.setCampo("idOrgan");
                pesquisa.setValor(cabecAmostraTO.getIdOrgCabec());
                listaPesq.add(pesquisa);

                EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                pesquisa2.setCampo("idCaracOrgan");
                pesquisa2.setValor(caracOrganTO.getIdCaracOrgan());
                listaPesq.add(pesquisa2);

                ROrganCaracTO rOrganCaracTO = new ROrganCaracTO();
                List lOrgCarac = rOrganCaracTO.getAndOrderBy(listaPesq, "idROrganCarac", true);
                rOrganCaracTO = (ROrganCaracTO) lOrgCarac.get(0);

                RCaracAmostraTO rCaracAmostraTO = new RCaracAmostraTO();
                List lCaracAmostra = rCaracAmostraTO.get("idRCaracAmostra", rOrganCaracTO.getIdROrganCarac());
                rCaracAmostraTO = (RCaracAmostraTO) lCaracAmostra.get(0);

                AmostraTO amostraTO = new AmostraTO();
                List lAmostra = amostraTO.getAndOrderBy("idAmostraOrgan", rCaracAmostraTO.getIdAmostraOrgan(), "seqAmostra", true);

                ItemAmostraTO itemAmostraTODel = new ItemAmostraTO();
                itemAmostraTODel.deleteAll();

                boolean verItemCabec = false;

                for (int i = 0; i < lAmostra.size(); i++) {

                    amostraTO = (AmostraTO) lAmostra.get(i);

                    ItemAmostraTO itemAmostraTO = new ItemAmostraTO();
                    itemAmostraTO.setIdCabecItem(cabecAmostraTO.getIdCabec());
                    itemAmostraTO.setDescrItem(amostraTO.getDescrAmostra());
                    itemAmostraTO.setIdAmostraItem(amostraTO.getIdAmostra());
                    itemAmostraTO.setTipoAmostraItem(amostraTO.getTipoAmostra());
                    itemAmostraTO.setValorAmostraItem(0L);
                    itemAmostraTO.insert();
                    itemAmostraTO.commit();

                    if(amostraTO.getTipoAmostra() == 2){
                        verItemCabec = true;
                    }

                }

                if(lAmostra.size() > 0){

                    piaContext.setVerTelaQuestao(1);

                    if(verItemCabec){

                        Intent it = new Intent(ListaCaracOrganActivity.this, QuestaoCabecActivity.class);
                        startActivity(it);
                        finish();

                        caracOrganList.clear();
                        rOrganCaracList.clear();

                    }
                    else{


                        ManipDadosEnvio.getInstance().envioDados( ListaCaracOrganActivity.this);
                        Intent it = new Intent(ListaCaracOrganActivity.this, ListaPontosActivity.class);
                        startActivity(it);
                        finish();

                        caracOrganList.clear();
                        rOrganCaracList.clear();

                    }

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCaracOrganActivity.this);
                    alerta.setTitle("ATENCAO");
                    alerta.setMessage("ESSA CARACTERÍSTICA NÃO CONTÉM ITEM. POR FAVOR, VERIFIQUE SE CARACTERÍSTICA QUE DESEJA APONTAR.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }

            }

        });

        buttonRetCaracOrgan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaCaracOrganActivity.this, ListaOrganActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
