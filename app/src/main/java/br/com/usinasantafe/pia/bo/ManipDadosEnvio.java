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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pia.EnvioDadosActivity;
import br.com.usinasantafe.pia.PrincipalActivity;
import br.com.usinasantafe.pia.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pia.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pia.pst.EspecificaPesquisa;
import br.com.usinasantafe.pia.pst.GenericRecordable;
import br.com.usinasantafe.pia.tb.estaticas.DataTO;
import br.com.usinasantafe.pia.tb.variaveis.AltExcluirItemTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemSalvoTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;


public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;
    private Context context;
    private ProgressDialog progressDialog;
    private int tipoEnvio;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

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

    public List verifDadosAnalise() {
        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        return cabecAmostraTO.get("statusAmostra", 2L);
    }


    public void enviarAnalise() {

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List lCabec = verifDadosAnalise();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayRespItem = new JsonArray();

        for (int i = 0; i < lCabec.size(); i++) {

            cabecAmostraTO = (CabecAmostraTO) lCabec.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecAmostraTO, cabecAmostraTO.getClass()));

            RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
            List lRespItem = respItemAmostraTO.get("idCabecRespItem", cabecAmostraTO.getIdCabec());

            for (int j = 0; j < lRespItem.size(); j++) {

                respItemAmostraTO = (RespItemAmostraTO) lRespItem.get(j);
                Gson gsonItem = new Gson();
                jsonArrayRespItem.add(gsonItem.toJsonTree(respItemAmostraTO, respItemAmostraTO.getClass()));

            }

        }

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);

        JsonObject jsonRespItem = new JsonObject();
        jsonRespItem.add("item", jsonArrayRespItem);

        String dados = jsonCabec.toString() + "_" + jsonRespItem.toString();

        Log.i("PIA", "DADOS = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsApontaAnalise()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void deleteAnalise(){

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List lCabec = verifDadosAnalise();

        for (int i = 0; i < lCabec.size(); i++) {

            cabecAmostraTO = (CabecAmostraTO) lCabec.get(i);
            RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
            List lRespItem = respItemAmostraTO.get("idCabecRespItem", cabecAmostraTO.getIdCabec());

            for (int j = 0; j < lRespItem.size(); j++) {

                respItemAmostraTO = (RespItemAmostraTO) lRespItem.get(j);
                respItemAmostraTO.delete();

            }

            cabecAmostraTO.delete();

        }

    }

    public void respostaEnvio(boolean verif){

        String msg = "";

        if(verif){
            msg = "ENVIADO COM SUCESSO.";
        }
        else{
            msg = "HOUVE FALHA NO ENVIO. REENVIE OS DADOS NOVAMENTE!";
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this.context);
        alerta.setTitle("ATENCAO");
        alerta.setMessage(msg);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(tipoEnvio == 1){
                    Intent it = new Intent(context, PrincipalActivity.class);
                    context.startActivity(it);
                }
                else if(tipoEnvio == 2){
                    Intent it = new Intent(context, EnvioDadosActivity.class);
                    context.startActivity(it);
                }
            }
        });

        alerta.show();

    }


    public List verifData(){
        DataTO dataTO = new DataTO();
        return dataTO.all();
    }

    public void setContext(Context context, ProgressDialog progressDialog, int tipoEnvio){
        this.context = context;
        this.progressDialog = progressDialog;
        this.tipoEnvio = tipoEnvio;
    }

    public List boletinsAbertoSemEnvio() {

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        ArrayList listaPesq = new ArrayList();


        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusAmostra");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBoletim");
        pesquisa2.setValor(0);
        listaPesq.add(pesquisa2);

        return cabecAmostraTO.get(listaPesq);

    }

    public List boletinsFechado() {
        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        return cabecAmostraTO.get("statusAmostra", 2L);
    }

    public List editAponta() {
        AltExcluirItemTO altExcluirItemTO = new AltExcluirItemTO();
        return altExcluirItemTO.all();
    }

    public List aponta(){

        ItemSalvoTO itemSalvoTO = new ItemSalvoTO();
        List itemSalvoList = itemSalvoTO.all();

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < itemSalvoList.size(); i++) {
            itemSalvoTO = (ItemSalvoTO) itemSalvoList.get(i);
            rLista.add(itemSalvoTO.getIdRespItem());
        }

        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
        return respItemAmostraTO.notIn("idRespItem", rLista);

    }

    public void enviarBolAberto() {

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = boletinsAbertoSemEnvio();

        JsonArray jsonArrayBoletim = new JsonArray();

        for (int i = 0; i < listBoletim.size(); i++) {

            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(cabecAmostraTO, cabecAmostraTO.getClass()));

        }

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);


        String dados = jsonBoletim.toString();
        //String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAberto()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioAponta() {

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = cabecAmostraTO.get("statusAmostra", 1L);

        for (int i = 0; i < listBoletim.size(); i++) {

            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(cabecAmostraTO, cabecAmostraTO.getClass()));

        }

        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
        List apontaList = aponta();

        for (int i = 0; i < apontaList.size(); i++) {

            respItemAmostraTO = (RespItemAmostraTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(respItemAmostraTO, respItemAmostraTO.getClass()));

        }

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);


        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertAponta()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }


    public void envioAltExcAponta() {

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = cabecAmostraTO.get("statusAmostra", 1L);

        for (int i = 0; i < listBoletim.size(); i++) {

            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(cabecAmostraTO, cabecAmostraTO.getClass()));

        }

        AltExcluirItemTO altExcluirItemTO = new AltExcluirItemTO();
        List apontaList = editAponta();

        for (int i = 0; i < apontaList.size(); i++) {

            altExcluirItemTO = (AltExcluirItemTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(altExcluirItemTO, altExcluirItemTO.getClass()));

        }

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString();

        Log.i("PMM", "EDITAR = " + dados);

        String[] url = {urlsConexaoHttp.getsEditAponta()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void enviarBolFechados() {

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = boletinsFechado();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();

        for (int i = 0; i < listBoletim.size(); i++) {

            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(cabecAmostraTO, cabecAmostraTO.getClass()));

            ItemSalvoTO itemSalvoTO = new ItemSalvoTO();
            List itemSalvoList = itemSalvoTO.all();

            ArrayList<Long> rLista = new ArrayList<Long>();

            for (int z = 0; z < itemSalvoList.size(); z++) {
                itemSalvoTO = (ItemSalvoTO) itemSalvoList.get(i);
                rLista.add(itemSalvoTO.getIdRespItem());
            }

            RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
            List apontaList = respItemAmostraTO.notIn("idRespItem", rLista);

            for (int j = 0; j < apontaList.size(); j++) {

                respItemAmostraTO = (RespItemAmostraTO) apontaList.get(j);
                if(respItemAmostraTO.getIdCabecRespItem() == cabecAmostraTO.getIdCabec()){
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
        //String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonRend.toString();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechado()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }


    public void atualDelBoletim(String retorno){

        try{

            int pos1 = retorno.indexOf("=") + 1;
            int pos2 = retorno.indexOf("_") + 1;
            String id = retorno.substring(pos1, (pos2 - 1));

            CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
            List listBoletim = cabecAmostraTO.get("statusAmostra", 1L);
            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(0);
            cabecAmostraTO.setIdExtBoletim(Long.parseLong(id.trim()));
            cabecAmostraTO.update();

            RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
            List apontaList = respItemAmostraTO.get("idCabecRespItem", respItemAmostraTO.getIdCabecRespItem());

            for (int j = 0; j < apontaList.size(); j++) {

                respItemAmostraTO = (RespItemAmostraTO) apontaList.get(j);
                respItemAmostraTO.update();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void atualAponta(String retorno) {

        try{

            int pos1 = retorno.indexOf("=") + 1;
            int pos2 = retorno.indexOf("_") + 1;
            String dados = retorno.substring(pos1, (pos2 - 1));

            JSONObject jObj = new JSONObject(dados);
            JSONArray jsonArray = jObj.getJSONArray("dados");
            Class classe = Class.forName(urlsConexaoHttp.localPSTVariavel + "ItemSalvoTO");

            if (jsonArray.length() > 0) {

                GenericRecordable genericRecordable = new GenericRecordable();
//                genericRecordable.deleteAll(classe);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                }

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delBolFechado() {

        CabecAmostraTO cabecAmostraTO = new CabecAmostraTO();
        List listBoletim = cabecAmostraTO.get("statusAmostra", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < listBoletim.size(); i++) {
            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);
            rLista.add(cabecAmostraTO.getIdCabec());
        }

        RespItemAmostraTO respItemAmostraTO = new RespItemAmostraTO();
        List apontaList = respItemAmostraTO.in("idCabecRespItem", rLista);

        for (int j = 0; j < apontaList.size(); j++) {

            respItemAmostraTO = (RespItemAmostraTO) apontaList.get(j);
            respItemAmostraTO.delete();

        }

        ItemSalvoTO itemSalvoTO = new ItemSalvoTO();
        List itemSalvoList = itemSalvoTO.in("idCabecRespItem", rLista);
        for (int i = 0; i < itemSalvoList.size(); i++) {
            itemSalvoTO = (ItemSalvoTO) itemSalvoList.get(i);
            itemSalvoTO.delete();
        }

        AltExcluirItemTO altExcluirItemTO = new AltExcluirItemTO();
        List altExcluirItemList = altExcluirItemTO.all();
        for (int i = 0; i < altExcluirItemList.size(); i++) {
            altExcluirItemTO = (AltExcluirItemTO) altExcluirItemList.get(i);
            altExcluirItemTO.delete();
        }

        for (int i = 0; i < listBoletim.size(); i++) {
            cabecAmostraTO = (CabecAmostraTO) listBoletim.get(i);
            cabecAmostraTO.delete();
        }

    }

    public void delAtualExcApont() {

        AltExcluirItemTO altExcluirItemTO = new AltExcluirItemTO();
        List altExcluirItemList = altExcluirItemTO.all();
        for (int i = 0; i < altExcluirItemList.size(); i++) {
            altExcluirItemTO = (AltExcluirItemTO) altExcluirItemList.get(i);
            altExcluirItemTO.delete();
        }

    }


    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public Boolean verifBolAbertoSemEnvio() {
        return boletinsAbertoSemEnvio().size() > 0;
    }

    public Boolean verifBolFechado() {
        return boletinsFechado().size() > 0;
    }

    public Boolean verifAponta() {
            return aponta().size() > 0;
    }

    public Boolean altExcAponta(){ return editAponta().size() > 0; }

    public void envioDadosPrinc() {
        if (verifBolFechado()) {
            enviarBolFechados();
        } else {
            if (verifBolAbertoSemEnvio()) {
                enviarBolAberto();
            } else {
                if(altExcAponta()){
                    envioAltExcAponta();
                }else{
                    if (verifAponta()) {
                        envioAponta();
                    }
                }
            }
        }
    }


    public boolean verifDadosEnvio() {
        if ((!verifBolFechado())
                && (!verifBolAbertoSemEnvio())
                && (!verifAponta())
                && (!altExcAponta())){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if(statusEnvio != 4) {
            if (enviando) {
                statusEnvio = 1;
            } else {
                if (!verifDadosEnvio()) {
                    statusEnvio = 3;
                } else {
                    statusEnvio = 2;
                }
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

}
