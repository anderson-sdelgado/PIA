package br.com.usinasantafe.pia.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView lista;
    private PIAContext piaContext;
    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        piaContext = (PIAContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("textViewProcesso = findViewById(R.id.textViewProcesso);\n" +
                "        customHandler.postDelayed(updateTimerThread, 0);\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        itens.add(\"APONTAMENTO\");\n" +
                "        itens.add(\"ENVIO DE DADOS\");\n" +
                "        itens.add(\"CONFIGURAÇÃO\");\n" +
                "        itens.add(\"SAIR\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        lista = findViewById(R.id.listaMenuInicial);\n" +
                "        lista.setAdapter(adapterList);", getLocalClassName());

        textViewProcesso = findViewById(R.id.textViewProcesso);
        customHandler.postDelayed(updateTimerThread, 0);

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("ENVIO DE DADOS");
        itens.add("CONFIGURAÇÃO");
        itens.add("LOG");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"APONTAMENTO\")) {", getLocalClassName());
                    if(piaContext.getInfestacaoCTR().hasElemAuditor()
                        && piaContext.getConfigCTR().hasElements()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().hasElemAuditor() \n" +
                                "                        && piaContext.getConfigCTR().hasElements()) {", getLocalClassName());
                        if (!piaContext.getInfestacaoCTR().verCabecAberto()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (!piaContext.getInfestacaoCTR().verCabecAberto()) {\n" +
                                    "                            Intent it = new Intent(MenuInicialActivity.this, AuditorActivity.class);", getLocalClassName());
                            Intent it = new Intent(MenuInicialActivity.this, AuditorActivity.class);
                            startActivity(it);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                            if (piaContext.getInfestacaoCTR().hasElemItemAmostra()) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getInfestacaoCTR().hasElemItemAmostra()) {\n" +
                                        "                                piaContext.getInfestacaoCTR().deleteRespItemAmostra();\n" +
                                        "                                Intent it = new Intent(MenuInicialActivity.this, ListaPontosActivity.class);", getLocalClassName());
                                piaContext.getInfestacaoCTR().deleteRespItemAmostraAberto();
                                Intent it = new Intent(MenuInicialActivity.this, ListaPontosActivity.class);
                                startActivity(it);
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                piaContext.getInfestacaoCTR().deleteCabecAberto();\n" +
                                        "                                Intent it = new Intent(MenuInicialActivity.this, AuditorActivity.class);", getLocalClassName());
                                piaContext.getInfestacaoCTR().deleteCabecAberto();
                                Intent it = new Intent(MenuInicialActivity.this, AuditorActivity.class);
                                startActivity(it);
                            }
                        }
                        finish();

                    }

                } else if (text.equals("ENVIO DE DADOS")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"ENVIO DE DADOS\")) {\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, EnvioDadosActivity.class);", getLocalClassName());
                    Intent it = new Intent(MenuInicialActivity.this, EnvioDadosActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("CONFIGURAÇÃO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"CONFIGURAÇÃO\")) {\n" +
                            "                    piaContext.setTela(1);\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    piaContext.setTela(1);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("LOG")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"LOG\")) {\n" +
                            "                    piaContext.setTela(2);\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    piaContext.setTela(2);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("SAIR")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"SAIR\")) {\n" +
                            "                    Intent intent = new Intent(Intent.ACTION_MAIN);\n" +
                            "                    intent.addCategory(Intent.CATEGORY_HOME);\n" +
                            "                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);\n" +
                            "                    startActivity(intent);", getLocalClassName());
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

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(piaContext.getInfestacaoCTR().qtdeCabecFechado() == 0){
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
            else{
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
