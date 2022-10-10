package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class TalhaoActivity extends ActivityGeneric {

    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        piaContext = (PIAContext) getApplication();

        Button buttonOkTalhao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = findViewById(R.id.buttonCancPadrao);

        buttonOkTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(!editTextPadrao.getText().toString().equals("")){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                            "                    Long codTalhao = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                    Long codTalhao = Long.parseLong(editTextPadrao.getText().toString());
                    if(piaContext.getInfestacaoCTR().verTalhaCod(codTalhao)) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(piaContext.getInfestacaoCTR().verTalhaCod(codTalhao)) {\n" +
                                "                        piaContext.getConfigCTR().setTalhao(piaContext.getInfestacaoCTR().getTalhaCod(codTalhao).getIdTalhao());\n" +
                                "                        Intent it = new Intent(TalhaoActivity.this, ListaOrganActivity.class);", getLocalClassName());
                        piaContext.getConfigCTR().setTalhao(piaContext.getInfestacaoCTR().getTalhaCod(codTalhao).getIdTalhao());
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
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(editTextPadrao.getText().toString().length() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(editTextPadrao.getText().toString().length() > 0){\n" +
                            "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);", getLocalClassName());
        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
        startActivity(it);
        finish();
    }

}
