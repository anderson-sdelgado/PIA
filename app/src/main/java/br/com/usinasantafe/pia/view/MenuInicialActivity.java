package br.com.usinasantafe.pia.view;

import static com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import br.com.usinasantafe.pia.BuildConfig;
import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView listaMenuInicial;
    private PIAContext piaContext;
    private TextView textViewProcesso;
    private Handler customHandler = new Handler();
    private FusedLocationProviderClient fusedLocationClient;
    private Double latitude = 0D;
    private Double longitude = 0D;

    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        boolean result = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;

        if (!result) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    });
        }

        TextView textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

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

        ArrayList<String> itens = new ArrayList<>();

        itens.add("APONTAMENTO");
        itens.add("ENVIO DE DADOS");
        itens.add("CONFIGURAÇÃO");
        itens.add("LOG");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        listaMenuInicial = findViewById(R.id.listaMenuInicial);
        listaMenuInicial.setAdapter(adapterList);

        listaMenuInicial.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                    "                String text = textView.getText().toString();", getLocalClassName());
            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            switch (text) {
                case "APONTAMENTO": {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"APONTAMENTO\")) {", getLocalClassName());
                    if (piaContext.getInfestacaoCTR().hasElemAuditor()
                            && piaContext.getConfigCTR().hasElements()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().hasElemAuditor()\n" +
                                "                        && piaContext.getConfigCTR().hasElements()) {\n" +
                                "                        Intent it = new Intent(MenuInicialActivity.this, ListaOrganActivity.class);", getLocalClassName());
                        Intent it = new Intent(MenuInicialActivity.this, ListaOrganActivity.class);
                        startActivity(it);
                        finish();
                    }
                    break;
                }
                case "ENVIO DE DADOS": {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"ENVIO DE DADOS\")) {\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, EnvioDadosActivity.class);", getLocalClassName());
                    Intent it = new Intent(MenuInicialActivity.this, EnvioDadosActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case "CONFIGURAÇÃO": {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"CONFIGURAÇÃO\")) {\n" +
                            "                    piaContext.setTela(1);\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    piaContext.setVertelaConfigORLogs(1);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case "LOG": {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"LOG\")) {\n" +
                            "                    piaContext.setTela(2);\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    piaContext.setVertelaConfigORLogs(2);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case "SAIR": {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"SAIR\")) {\n" +
                            "                    Intent intent = new Intent(Intent.ACTION_MAIN);\n" +
                            "                    intent.addCategory(Intent.CATEGORY_HOME);\n" +
                            "                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);\n" +
                            "                    startActivity(intent);", getLocalClassName());
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
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
