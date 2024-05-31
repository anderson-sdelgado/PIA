package br.com.usinasantafe.pia.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import br.com.usinasantafe.pia.control.InfestacaoCTR;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pia.util.retrofit.AmostraEnvio;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    public void respostaEnvio(boolean verif, String activity){

        String msg = "";

        if(verif){
            LogProcessoDAO.getInstance().insertLogProcesso("if(verif){\n" +
                    "            msg = \"ENVIADO COM SUCESSO.\";", activity);
            msg = "ENVIADO COM SUCESSO.";
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            msg = \"HOUVE FALHA NO ENVIO. REENVIE OS DADOS NOVAMENTE!\";", activity);
            msg = "HOUVE FALHA NO ENVIO. REENVIE OS DADOS NOVAMENTE!";
        }

        LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
                "        alerta.setTitle(\"ATENCAO\");\n" +
                "        alerta.setMessage(msg);", activity);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
        alerta.setTitle("ATENCAO");
        alerta.setMessage(msg);
        alerta.setPositiveButton("OK", (dialog, which) -> {
            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(DialogInterface dialog, int which) {\n" +
                    "                Intent it = new Intent(telaAtual, telaProx);\n" +
                    "                telaAtual.startActivity(it);", activity);
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        });

        alerta.show();

    }

    public void enviarAmostra(String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("public void enviarAmostra(String activity) {\n" +
                "        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();\n" +
                "        AmostraEnvio amostraEnvio = new AmostraEnvio();\n" +
                "        amostraEnvio.envioDadosAmostra(infestacaoCTR.dadosEnvioAmostra(), activity);", activity);
        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
        AmostraEnvio amostraEnvio = new AmostraEnvio();
        amostraEnvio.envioDadosAmostra(infestacaoCTR.dadosEnvioAmostra(), activity);
    }

}
