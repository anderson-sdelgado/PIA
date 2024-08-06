package br.com.usinasantafe.pia.view;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class ObservActivity extends ActivityGeneric {

    private PIAContext piaContext;
    private EditText editTextObserv;
    private FusedLocationProviderClient fusedLocationClient;
    private Double latitude = 0D;
    private Double longitude = 0D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observ);

        piaContext = (PIAContext) getApplication();

        editTextObserv = findViewById(R.id.editTextObserv);
        Button buttonOkObserv =  findViewById(R.id.buttonOkObserv);
        Button buttonCancObserv = findViewById(R.id.buttonCancObserv);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        boolean result = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;

        if (!result) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLatitude();
                        }
                    });
        }

        buttonOkObserv.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkObserv.setOnClickListener(v -> {", getLocalClassName());
            String obs = "";
            if(!editTextObserv.getText().toString().isEmpty()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextObservacao.getText().toString().equals(\"\")) {\n" +
                        "                    obs = editTextObservacao.getText().toString();", getLocalClassName());
                obs = editTextObserv.getText().toString();
            }

            Intent it;
            if(piaContext.getTipoFluxo() == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getTipoFluxo() == 1){\n" +
                        "                piaContext.getInfestacaoCTR().salvarLocalAbertoArmadilha(obs, latitude, longitude);\n" +
                        "                it = new Intent(ObservActivity.this, QuestaoAmostraActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().salvarLocalArmadilha(obs, latitude, longitude);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                AmostraBean amostraBean;
                if(piaContext.getVerTelaQuestao() == 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("amostraBean = piaContext.getInfestacaoCTR().getItemAmostra(piaContext.getPosQuestaoAmostra());\n" +
                            "                    piaContext.getInfestacaoCTR().inserirRespItemAmostra(amostraBean.getIdAmostra(), obs);\n" +
                            "                    piaContext.getInfestacaoCTR().fecharPonto();", getLocalClassName());
                    amostraBean = piaContext.getInfestacaoCTR().getItemAmostra(piaContext.getPosQuestaoAmostra());
                    piaContext.getInfestacaoCTR().inserirRespItemAmostra(amostraBean.getIdAmostra(), (long) piaContext.getPosPonto(), obs);
                    piaContext.getInfestacaoCTR().fecharPonto();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    piaContext.getInfestacaoCTR().updateRespItemAmostra(piaContext.getIdRespItem(), obs);", getLocalClassName());
                    piaContext.getInfestacaoCTR().updateRespItemAmostra(piaContext.getIdRespItem(), obs);
                }
                LogProcessoDAO.getInstance().insertLogProcesso("it = new Intent(ObservActivity.this, ListaArmadilhaActivity.class);", getLocalClassName());
            }
            it = new Intent(ObservActivity.this, ListaArmadilhaActivity.class);
            startActivity(it);
            finish();

        });

        buttonCancObserv.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancObserv.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            Intent it;
            if(piaContext.getTipoFluxo() == 1){
                it = new Intent(ObservActivity.this, TalhaoActivity.class);
            } else {
                it = new Intent(ObservActivity.this, QuestaoAmostraActivity.class);
            }
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }
}