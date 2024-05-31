package br.com.usinasantafe.pia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        editTextSenha = findViewById(R.id.editTextSenha);
        Button buttonOkSenha = findViewById(R.id.buttonOkSenha);
        Button buttonCancSenha = findViewById(R.id.buttonCancSenha);

        piaContext = (PIAContext) getApplication();

        buttonOkSenha.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkSenha.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"unchecked\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!piaContext.getConfigCTR().hasElements()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!piaContext.getConfigCTR().hasElements()) {\n" +
                        "                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                startActivity(it);
                finish();
            } else {
                if (piaContext.getVertelaConfigORLogs() == 1) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getConfigCTR().getConfig().getPosicaoTela() == 11L){", getLocalClassName());
                    if (piaContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (piaContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                        startActivity(it);
                        finish();

                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (editTextSenha.getText().toString().equals("fgbny946")) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (editTextSenha.getText().toString().equals(\"fgbny946\")) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }


        });

        buttonCancSenha.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancSenha.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}