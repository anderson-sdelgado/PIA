package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class QuestaoCabecActivity extends ActivityGeneric {

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

        LogProcessoDAO.getInstance().insertLogProcesso("textViewPadrao.setText(piaContext.getInfestacaoCTR().getItemAmostraCabec().getDescrItem());\n" +
                "        editText.setText(\"\");", getLocalClassName());
        textViewPadrao.setText(piaContext.getInfestacaoCTR().getItemAmostraCabec().getDescrItem());
        editText.setText("");

        buttonOkQC.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkQC.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    piaContext.setVerTelaQuestao(1);\n" +
                        "                    piaContext.getInfestacaoCTR().updateItemAmostraCabec(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                        "                    Intent it = new Intent(QuestaoCabecActivity.this, MsgPontoActivity.class);", getLocalClassName());
                piaContext.setVerTelaQuestao(1);
                piaContext.getInfestacaoCTR().updateItemAmostraCabec(Long.parseLong(editTextPadrao.getText().toString()));

                Intent it = new Intent(QuestaoCabecActivity.this, MsgPontoActivity.class);
                startActivity(it);
                finish();

            }


        });

        buttonCancQC.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancQC.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(editTextPadrao.getText().toString().length() > 0){
                LogProcessoDAO.getInstance().insertLogProcesso("if(editTextPadrao.getText().toString().length() > 0){\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(QuestaoCabecActivity.this, ListaCaracOrganActivity.class);", getLocalClassName());
        Intent it = new Intent(QuestaoCabecActivity.this, ListaCaracOrganActivity.class);
        startActivity(it);
        finish();
    }

}
