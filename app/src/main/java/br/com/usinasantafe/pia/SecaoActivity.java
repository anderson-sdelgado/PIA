package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pia.tb.estaticas.SecaoTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;

public class SecaoActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao);

        piaContext = (PIAContext) getApplication();

        Button buttonOkTalhao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    SecaoTO secaoTO = new SecaoTO();
                    List listSecao = secaoTO.get("codSecao", Long.parseLong(editTextPadrao.getText().toString()));

                    if(listSecao.size() > 0) {

                        secaoTO = (SecaoTO) listSecao.get(0);

                        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                        List listCabecAmostra = cabecAmostraTO.get("statusAmostra", 1L);
                        cabecAmostraTO = (CabecAmostraTO) listCabecAmostra.get(0);
                        cabecAmostraTO.setSecaoCabec(secaoTO.getIdSecao());
                        cabecAmostraTO.update();
                        cabecAmostraTO.commit();

                        Intent it = new Intent(SecaoActivity.this, TalhaoActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        buttonCancTalhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else {
                    Intent it = new Intent(SecaoActivity.this, AuditorActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed()  {
    }

}
