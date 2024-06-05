package br.com.usinasantafe.pia.view;

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

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.bean.estaticas.CaracOrganBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class ListaCaracOrganActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
    private List<CaracOrganBean> caracOrganList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carac_organ);

        piaContext = (PIAContext) getApplication();

        Button buttonRetCaracOrgan = findViewById(R.id.buttonRetCaracOrgan);

        LogProcessoDAO.getInstance().insertLogProcesso("caracOrganList = piaContext.getInfestacaoCTR().caracOrganismoList();\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for (CaracOrganBean caracOrganBean : caracOrganList) {\n" +
                "            itens.add(caracOrganBean.getDescrCaracOrganismo());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        lista = findViewById(R.id.listCaracOrgan);\n" +
                "        lista.setAdapter(adapterList);", getLocalClassName());

        caracOrganList = piaContext.getInfestacaoCTR().caracOrganismoList();

        ArrayList<String> itens = new ArrayList<>();

        for (CaracOrganBean caracOrganBean : caracOrganList) {
            itens.add(caracOrganBean.getDescrCaracOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listCaracOrgan);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                CaracOrganBean caracOrganBean = caracOrganList.get(position);", getLocalClassName());
            CaracOrganBean caracOrganBean = caracOrganList.get(position);

            if(piaContext.getInfestacaoCTR().verAmostraCarac(caracOrganBean.getIdCaracOrgan())){

                LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().verAmostraCarac(caracOrganBean.getIdCaracOrgan())){\n" +
                        "                piaContext.getConfigCTR().setIdCaracOrg(caracOrganBean.getIdCaracOrgan());\n" +
                        "                caracOrganList.clear();\n" +
                        "                Intent it = new Intent(ListaCaracOrganActivity.this, OSActivity.class);\n" +
                        "                startActivity(it);\n" +
                        "                finish();", getLocalClassName());
                piaContext.getConfigCTR().setIdCaracOrg(caracOrganBean.getIdCaracOrgan());
                caracOrganList.clear();

                Intent it;
                if(!piaContext.getInfestacaoCTR().verifCabecAbertoCarac(caracOrganBean.getIdCaracOrgan())){
                    it = new Intent(ListaCaracOrganActivity.this, MatricAuditorActivity.class);
                } else {
                    piaContext.getInfestacaoCTR().updateCabecAbertoCarac(caracOrganBean.getIdCaracOrgan());
                    it = new Intent(ListaCaracOrganActivity.this, ListaPontosActivity.class);
                }
                startActivity(it);
                finish();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCaracOrganActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENCAO\");\n" +
                        "                    alerta.setMessage(\"ESSA CARACTERÍSTICA NÃO CONTÉM ITEM. POR FAVOR, VERIFIQUE SE CARACTERÍSTICA QUE DESEJA APONTAR.\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCaracOrganActivity.this);
                alerta.setTitle("ATENCAO");
                alerta.setMessage("ESSA CARACTERÍSTICA NÃO CONTÉM ITEM. POR FAVOR, VERIFIQUE SE CARACTERÍSTICA QUE DESEJA APONTAR.");
                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                alerta.show();

            }

        });

        buttonRetCaracOrgan.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetCaracOrgan.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaCaracOrganActivity.this, ListaOrganActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaCaracOrganActivity.this, ListaOrganActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
