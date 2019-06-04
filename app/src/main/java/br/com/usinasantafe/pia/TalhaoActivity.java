package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.tb.estaticas.TalhaoTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;

public class TalhaoActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        piaContext = (PIAContext) getApplication();

        Button buttonOkTalhao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
                    List listCabecAmostra = cabecAmostraTO.get("statusAmostra", 1L);
                    cabecAmostraTO = (CabecAmostraTO) listCabecAmostra.get(0);

                    ArrayList listaPesq = new ArrayList();

                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("idSecao");
                    pesquisa.setValor(cabecAmostraTO.getSecaoCabec());
                    listaPesq.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("codTalhao");
                    pesquisa2.setValor(Long.parseLong(editTextPadrao.getText().toString()));
                    listaPesq.add(pesquisa2);

                    TalhaoTO talhaoTO = new TalhaoTO();
                    List listTalhao = talhaoTO.get(listaPesq);

                    if(listTalhao.size() > 0) {

                        talhaoTO = (TalhaoTO) listTalhao.get(0);

                        cabecAmostraTO.setTalhaoCabec(talhaoTO.getIdTalhao());
                        cabecAmostraTO.update();
                        cabecAmostraTO.commit();

                        Intent it = new Intent(TalhaoActivity.this, ListaOrganActivity.class);
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
                    Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed()  {
    }

}
