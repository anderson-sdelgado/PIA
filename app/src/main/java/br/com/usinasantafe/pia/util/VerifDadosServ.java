package br.com.usinasantafe.pia.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pia.control.ConfigCTR;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.pia.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pia.model.pst.GenericRecordable;

public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dados;
    private String classe;
    private String senha;

    public VerifDadosServ() {
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.recToken(result.trim(), this.senha, this.telaAtual, this.telaProx, this.progressDialog);

    }

    public void salvarToken(String senha, String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dados = dados;
        this.senha = senha;
        this.classe = "Token";

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        String[] url = {urlsConexaoHttp.urlVerifica(classe), activity};
        Map<String, Object> parametrosPost = new HashMap<>();
        parametrosPost.put("dado", String.valueOf(dados));

        Log.i("PMM", "postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados, activity);
        PostVerGenerico postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

}
