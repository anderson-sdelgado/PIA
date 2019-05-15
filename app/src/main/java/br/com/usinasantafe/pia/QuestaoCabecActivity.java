package br.com.usinasantafe.pia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;

public class QuestaoCabecActivity extends ActivityGeneric {

    private ItemAmostraTO itemAmostraTO;
    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_cabec);

        piaContext = (PIAContext) getApplication();

        TextView textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQC = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQC = (Button) findViewById(R.id.buttonCancPadrao);
        EditText editText = (EditText) findViewById(R.id.editTextPadrao);

        itemAmostraTO = new ItemAmostraTO();
        List lItem = itemAmostraTO.get("tipoAmostraItem", 2L);
        itemAmostraTO = (ItemAmostraTO) lItem.get(0);

        textViewPadrao.setText(itemAmostraTO.getDescrItem());
        editText.setText("");

        buttonOkQC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    piaContext.setVerTelaQuestao(1);

                    itemAmostraTO.setValorAmostraItem(Long.parseLong(editTextPadrao.getText().toString()));
                    itemAmostraTO.update();
                    itemAmostraTO.commit();

                    Intent it = new Intent(QuestaoCabecActivity.this, MsgPontoActivity.class);
                    startActivity(it);
                    finish();

                }


            }
        });

        buttonCancQC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else {
                    Intent it = new Intent(QuestaoCabecActivity.this, ListaCaracOrganismoActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed()  {
    }

}
