package br.com.usinasantafe.pia.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pia.control.InfestacaoCTR;
import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.model.dao.RespItemAmostraDAO;
import br.com.usinasantafe.pia.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pia.util.conHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private int qtdeEnvios;
    private int qtdeEnviada;

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

    public void envioBoletim(Context telaAtual, ProgressDialog progressDialog, Class telaProx, String activity){

        LogProcessoDAO.getInstance().insertLogProcesso("this.telaAtual = telaAtual;\n" +
                "        this.progressDialog = progressDialog;\n" +
                "        this.telaProx = telaProx;\n" +
                "        qtdeEnvios = 0;\n" +
                "        qtdeEnviada = 0;\n" +
                "        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();\n" +
                "        List<CabecAmostraBean> cabecAmostraList = infestacaoCTR.cabecFechadoList();", activity);
        this.telaAtual = telaAtual;
        this.progressDialog = progressDialog;
        this.telaProx = telaProx;

        qtdeEnvios = 0;
        qtdeEnviada = 0;

        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
        List<CabecAmostraBean> cabecAmostraList = infestacaoCTR.cabecFechadoList();

        LogProcessoDAO.getInstance().insertLogProcesso("for (CabecAmostraBean cabecAmostraBean : cabecAmostraList) {\n" +
                "            List<RespItemAmostraBean> respItemAmostraList = infestacaoCTR.respItemAmostraFechadoList(cabecAmostraBean.getIdCabec());\n" +
                "            qtdeEnvios = (respItemAmostraList.size() / 50) + qtdeEnvios;\n" +
                "            if((qtdeEnvios%50) > 0){\n" +
                "                qtdeEnvios = qtdeEnvios + 1;\n" +
                "            }\n" +
                "            respItemAmostraList.clear();\n" +
                "        }\n" +
                "        enviarDados(activity);", activity);
        for (CabecAmostraBean cabecAmostraBean : cabecAmostraList) {
            List<RespItemAmostraBean> respItemAmostraList = infestacaoCTR.respItemAmostraFechadoList(cabecAmostraBean.getIdCabec());
            qtdeEnvios = (respItemAmostraList.size() / 50) + qtdeEnvios;
            if((qtdeEnvios%50) > 0){
                qtdeEnvios = qtdeEnvios + 1;
            }
            respItemAmostraList.clear();
        }

        enviarDados(activity);

    }

    public void enviarDados(String activity) {

        LogProcessoDAO.getInstance().insertLogProcesso("InfestacaoCTR infestacaoCTR = new InfestacaoCTR();\n" +
                "        String dados = infestacaoCTR.dadosEnvioBolFechadoMMFert();", activity);
        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
        String dados = infestacaoCTR.dadosEnvioBolFechadoMMFert();

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBoletim(), activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("PMM", "URL = " + url + " - Dados de Envio = " + dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void updateDadosEnviado(String result, String activity) {

        LogProcessoDAO.getInstance().insertLogProcesso("public void updateDadosEnviado(String " + result + ", String activity){", activity);
        try {

            LogProcessoDAO.getInstance().insertLogProcesso("try {\n" +
                    "            String[] retorno = result.split(\"_\");\n" +
                    "            InfestacaoCTR infestacaoCTR = new InfestacaoCTR();\n" +
                    "            Long idCabec = infestacaoCTR.updateRespEnviado(retorno[2]);", activity);
            String[] retorno = result.split("_");

            InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
            Long idCabec = infestacaoCTR.updateRespEnviado(retorno[2]);

            if(!infestacaoCTR.verRespItemAmostraFechado(idCabec)){
                LogProcessoDAO.getInstance().insertLogProcesso("if(!infestacaoCTR.verRespItemAmostraFechado(idCabec)){\n" +
                        "                infestacaoCTR.updateCabecEnviado(retorno[1]);", activity);
                infestacaoCTR.updateCabecEnviado(retorno[1]);
            }

            qtdeEnviada = qtdeEnviada + 1;
            LogProcessoDAO.getInstance().insertLogProcesso("qtdeEnviada = qtdeEnviada + 1;", activity);
            if(qtdeEnviada < qtdeEnvios){
                LogProcessoDAO.getInstance().insertLogProcesso("if(qtdeEnviada < qtdeEnvios){\n" +
                        "                this.progressDialog.setProgress((qtdeEnviada * 100) / qtdeEnvios);\n" +
                        "                enviarDados(activity);", activity);
                this.progressDialog.setProgress((qtdeEnviada * 100) / qtdeEnvios);
                enviarDados(activity);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                this.progressDialog.dismiss();\n" +
                        "                respostaEnvio(true, activity);", activity);
                this.progressDialog.dismiss();
                respostaEnvio(true, activity);
            }

        }
        catch (Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

}
