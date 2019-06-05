package br.com.usinasantafe.pia.bo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pia.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pia.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;


public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private int qtdeEnvios;
    private int qtdeEnviada;

    public ManipDadosEnvio() {
        // TODO Auto-generated constructor stub
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
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

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = boletins();

        qtdeEnvios = 0;
        qtdeEnviada = 0;

        for (int i = 0; i < listBoletim.size(); i++) {
            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);

            RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
            List apontaList = respItemAmostraTO.get("idCabecRespItem", cabecAmostraTO.getIdCabec());

            qtdeEnvios = (apontaList.size() / 50) + qtdeEnvios;
            if((qtdeEnvios%50) > 0){
                qtdeEnvios = qtdeEnvios + 1;
            }

        }

        enviarDados();

    }

    public List boletins() {
        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        return cabecAmostraTO.getAndOrderBy("statusAmostra", 2L, "idCabec", true);
    }

    public void enviarDados() {

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = boletins();
        cabecAmostraTO = (CabecAmostraTO) listBoletim.get(0);
        listBoletim.clear();

        Gson gsonCabec = new Gson();
        jsonArrayBoletim.add(gsonCabec.toJsonTree(cabecAmostraTO, cabecAmostraTO.getClass()));

        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
        List apontaList = respItemAmostraTO.getAndOrderBy("idCabecRespItem", cabecAmostraTO.getIdCabec(), "idRespItem", true);

        for (int j = 0; j < apontaList.size(); j++) {
            if(j < 50) {
                respItemAmostraTO = (RespItemAmostraTO) apontaList.get(j);
                if (respItemAmostraTO.getIdCabecRespItem() == cabecAmostraTO.getIdCabec()) {
                    Gson gson = new Gson();
                    jsonArrayAponta.add(gson.toJsonTree(respItemAmostraTO, respItemAmostraTO.getClass()));
                }
            }
        }

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString();

        Log.i("PMM", "DADOS = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBoletim()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void deletarDados() {

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = cabecAmostraTO.getAndOrderBy("statusAmostra", 2L, "idCabec", true);
        cabecAmostraTO = (CabecAmostraTO) listBoletim.get(0);
        listBoletim.clear();

        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
        List apontaList = respItemAmostraTO.getAndOrderBy("idCabecRespItem", cabecAmostraTO.getIdCabec(), "idRespItem", true);

        for (int j = 0; j < apontaList.size(); j++) {
            if(j < 50) {
                respItemAmostraTO = (RespItemAmostraTO) apontaList.get(j);
                respItemAmostraTO.delete();
            }
        }

        if(apontaList.size() < 50) {
            cabecAmostraTO.delete();
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

}
