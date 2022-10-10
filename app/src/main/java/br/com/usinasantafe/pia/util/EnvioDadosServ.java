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

    public void respostaEnvio(boolean verif){

        String msg = "";

        if(verif){
            msg = "ENVIADO COM SUCESSO.";
        }
        else{
            msg = "HOUVE FALHA NO ENVIO. REENVIE OS DADOS NOVAMENTE!";
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
        alerta.setTitle("ATENCAO");
        alerta.setMessage(msg);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent it = new Intent(telaAtual, telaProx);
                telaAtual.startActivity(it);
            }
        });

        alerta.show();

    }



    public void envioBoletim(Context telaAtual, ProgressDialog progressDialog, Class telaProx){

        this.telaAtual = telaAtual;
        this.progressDialog = progressDialog;
        this.telaProx = telaProx;

        qtdeEnvios = 0;
        qtdeEnviada = 0;

        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
        List<CabecAmostraBean> cabecAmostraList = infestacaoCTR.cabecFechadoList();

        for (CabecAmostraBean cabecAmostraBean : cabecAmostraList) {

            List<RespItemAmostraBean> respItemAmostraList = infestacaoCTR.respItemAmostraFechadoList(cabecAmostraBean.getIdCabec());
            qtdeEnvios = (respItemAmostraList.size() / 50) + qtdeEnvios;
            if((qtdeEnvios%50) > 0){
                qtdeEnvios = qtdeEnvios + 1;
            }
            respItemAmostraList.clear();

        }

        enviarDados();

    }

    public void enviarDados() {

        InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
        String dados = infestacaoCTR.dadosEnvioBolFechadoMMFert();

        Log.i("PMM", "DADOS = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBoletim()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void updateDadosEnviado(String result) {

        try {

            String[] retorno = result.split("_");

            InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
            Long idCabec = infestacaoCTR.updateRespEnviado(retorno[2]);

            if(!infestacaoCTR.verRespItemAmostraFechado(idCabec)){
                infestacaoCTR.updateCabecEnviado(retorno[1]);
            }

            qtdeEnviada = qtdeEnviada + 1;
            if(qtdeEnviada < qtdeEnvios){
                this.progressDialog.setProgress((qtdeEnviada * 100) / qtdeEnvios);
                enviarDados();
            }
            else{
                this.progressDialog.dismiss();
                respostaEnvio(true);
            }

        }
        catch (Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

}
