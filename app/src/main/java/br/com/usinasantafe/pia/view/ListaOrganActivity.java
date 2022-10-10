package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.OrganBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class ListaOrganActivity extends ActivityGeneric {

    private ListView organListView;
    private PIAContext piaContext;
    private List<OrganBean> organList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_organ);

        piaContext = (PIAContext) getApplication();

        Button buttonRetOrgan = findViewById(R.id.buttonRetOrgan);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        organList = piaContext.getInfestacaoCTR().organismoList();\n" +
                "        for (OrganismoBean organismoBean : organList) {\n" +
                "            itens.add(organismoBean.getDescrOrganismo());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        organListView = findViewById(R.id.listOrgan);\n" +
                "        organListView.setAdapter(adapterList);", getLocalClassName());

        ArrayList<String> itens = new ArrayList<String>();

        organList = piaContext.getInfestacaoCTR().organismoList();

        for (OrganBean organBean : organList) {
            itens.add(organBean.getDescrOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        organListView = findViewById(R.id.listOrgan);
        organListView.setAdapter(adapterList);

        organListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("organListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                OrganismoBean organismoBean = organList.get(position);\n" +
                        "                piaContext.getConfigCTR().setIdOrg(organismoBean.getIdOrganismo());\n" +
                        "                organList.clear();\n" +
                        "                Intent it = new Intent(ListaOrganActivity.this, ListaCaracOrganActivity.class);", getLocalClassName());
                Log.i("PIA", "Posicao Organ = " + position);
                OrganBean organBean = organList.get(position);
                piaContext.getConfigCTR().setIdOrg(organBean.getIdOrgan());
                organList.clear();
                Intent it = new Intent(ListaOrganActivity.this, ListaCaracOrganActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetOrgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetOrgan.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaOrganActivity.this, TalhaoActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaOrganActivity.this, TalhaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
