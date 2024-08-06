package br.com.usinasantafe.pia.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pia.PIAContext;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.bean.estaticas.PergCabecBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemCabecBean;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;

public class QuestaoCabecPergActivity extends ActivityGeneric  {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView pergListView;
    private List<PergCabecBean>  pergCabecList;
    private PIAContext piaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_cabec_perg);

        Button buttonPergCabecCanc = findViewById(R.id.buttonPergCabecCanc);
        Button buttoPergCabecOk = findViewById(R.id.buttoPergCabecOk);

        piaContext = (PIAContext) getApplication();
        itens = new ArrayList<>();

        pergCabecList =  piaContext.getInfestacaoCTR().getPergCabecList();

        for(PergCabecBean pergCabecBean : pergCabecList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(pergCabecBean.getDescrPergCabec());
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(QuestaoCabecPergActivity.this, itens);
        pergListView = (ListView) findViewById(R.id.listViewPergCabec);
        pergListView.setAdapter(adapterListChoice);

        buttoPergCabecOk.setOnClickListener(v -> {

            ArrayList<RespItemCabecBean> respItemCabecSelectedList = new ArrayList();

            for (int i = 0; i < itens.size(); i++) {
                ViewHolderChoice viewHolderChoice = itens.get(i);
                PergCabecBean pergCabecBean = (PergCabecBean) pergCabecList.get(i);
                RespItemCabecBean respItemCabecBean = new RespItemCabecBean();
                respItemCabecBean.setIdItemCabec(pergCabecBean.getIdPergCabec());
                if(viewHolderChoice.isSelected()){
                    respItemCabecBean.setFlag(1L);
                } else {
                    respItemCabecBean.setFlag(0L);
                }
                respItemCabecSelectedList.add(respItemCabecBean);
            }

            piaContext.getInfestacaoCTR().salvarRespItemCabec(respItemCabecSelectedList);
            Intent it = new Intent(QuestaoCabecPergActivity.this, ListaPontosActivity.class);
            startActivity(it);
            finish();

        });

        buttonPergCabecCanc.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonPergCabecCanc.setOnClickListener(v -> {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(QuestaoCabecPergActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJAR REALMENTE CANCELAR? ISSO APAGAR TODOS OS DADOS DO CABEÇALHO DA ANALISE.\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(QuestaoCabecPergActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJAR REALMENTE CANCELAR? ISSO APAGAR TODOS OS DADOS DO CABEÇALHO DA ANALISE.");
            alerta.setPositiveButton("SIM", (dialog, which) -> {
                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        piaContext.getInfestacaoCTR().deleteCabecAbertoAll();\n" +
                        "                        Intent it = new Intent(ListaPontosActivity.this, MenuInicialActivity.class);", getLocalClassName());
                piaContext.getInfestacaoCTR().deleteCabecAbertoApontAll();
                Intent it = new Intent(QuestaoCabecPergActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();

            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

    }
}