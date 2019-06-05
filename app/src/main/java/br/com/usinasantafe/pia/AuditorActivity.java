package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pia.bo.Tempo;
import br.com.usinasantafe.pia.tb.estaticas.AuditorTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;

public class AuditorActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor);

        piaContext = (PIAContext) getApplication();

        Button buttonOkAuditor = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    AuditorTO auditorTO = new AuditorTO();
                    List listAuditor = auditorTO.get("codAuditor", Long.parseLong(editTextPadrao.getText().toString()));

                    if(listAuditor.size() > 0){

                        auditorTO = (AuditorTO) listAuditor.get(0);

                        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                        cabecAmostraTO.setDtCabec(Tempo.getInstance().data());
                        cabecAmostraTO.setAuditorCabec(auditorTO.getIdAuditor());
                        cabecAmostraTO.setStatusAmostra(1L);
                        cabecAmostraTO.insert();
                        cabecAmostraTO.commit();

                        Intent it = new Intent(AuditorActivity.this, SecaoActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        buttonCancAuditor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }

        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(AuditorActivity.this, PrincipalActivity.class);
        startActivity(it);
        finish();
    }


}
