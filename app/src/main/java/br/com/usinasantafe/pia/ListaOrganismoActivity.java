package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.tb.estaticas.OrganTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;

public class ListaOrganismoActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
    private List lOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_organismo);

        piaContext = (PIAContext) getApplication();

        Button buttonRetOrganismo = (Button) findViewById(R.id.buttonRetOrganismo);

        ArrayList<String> itens = new ArrayList<String>();

        OrganTO organTO = new OrganTO();
        lOrg = organTO.all();

        for (int i = 0; i < lOrg.size(); i++) {
            organTO = (OrganTO) lOrg.get(i);
            itens.add(organTO.getDescrOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listOrganismo);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                OrganTO organTO = (OrganTO) lOrg.get(position);

                CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                List listCabecAmostra = cabecAmostraTO.get("statusAmostra", 1L);
                cabecAmostraTO = (CabecAmostraTO) listCabecAmostra.get(0);
                cabecAmostraTO.setIdOrgCabec(organTO.getIdOrgan());
                cabecAmostraTO.update();
                cabecAmostraTO.commit();

                Intent it = new Intent(ListaOrganismoActivity.this, ListaCaracOrganismoActivity.class);
                startActivity(it);
                finish();

                lOrg.clear();

            }

        });

        buttonRetOrganismo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaOrganismoActivity.this, TalhaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
