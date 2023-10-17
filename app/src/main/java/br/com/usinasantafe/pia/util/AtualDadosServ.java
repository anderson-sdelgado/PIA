package br.com.usinasantafe.pia.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pia.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.conHttp.PostBDGenerico;
import br.com.usinasantafe.pia.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pia.model.pst.GenericRecordable;

public class AtualDadosServ {

	private ArrayList tabAtualArrayList;
	private static AtualDadosServ instance = null;
	private int contAtualBD = 0;
	private String classe = "";
	private ProgressDialog progressDialog;
	private int qtdeBD = 0;
	private GenericRecordable genericRecordable;
	private Context telaAtual;
	private Class telaProx;
	private int tipoReceb;
	private UrlsConexaoHttp urlsConexaoHttp;
	
	public AtualDadosServ() {
		genericRecordable = new GenericRecordable();
	}
	
    public static AtualDadosServ getInstance() {
        if (instance == null)
        instance = new AtualDadosServ();
        return instance;
    }
	
	@SuppressWarnings("unchecked")
	public void manipularDadosHttp(String tipo, String result, String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("if(!result.equals(\"\")){", activity);
		if(!result.equals("")){

			try{

				Log.i("PIA", "TIPO -> " + tipo);
				Log.i("PIA", "RESULT -> " + result);

				JSONObject jObj = new JSONObject(result);
				JSONArray jsonArray = jObj.getJSONArray("dados");
				Class classe = Class.forName(manipLocalClasse(tipo));

				LogProcessoDAO.getInstance().insertLogProcesso("genericRecordable.deleteAll('" + classe + "');", activity);
				genericRecordable.deleteAll(classe);

				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject objeto = jsonArray.getJSONObject(i);
					Gson gson = new Gson();
					Log.i("PIA", "OBJETO -> " + objeto.toString());
					genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);
				}

				if(contAtualBD > 0){
					LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD > 0){\n" +
							"atualizandoBD(activity);", activity);
					atualizandoBD(activity);
				}

			}
			catch (Exception e) {
				LogErroDAO.getInstance().insertLogErro(e);
			}

		} else {
			LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
					"encerrar(activity);", activity);
			encerrar(activity);
		}

	}

	public void atualGenericoBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, ArrayList classeArrayList, int tipoReceb, String activity){

		this.tipoReceb = tipoReceb;
		this.telaAtual = telaAtual;
		this.telaProx = telaProx;
		this.progressDialog = progressDialog;

		selecionarClasses(classeArrayList);
		startAtualizacao(activity);

	}

	public void selecionarClasses(ArrayList classeArrayList){

		try {

			tabAtualArrayList = new ArrayList();

			Class<?> retClasse = Class.forName(UrlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				for (int i = 0; i < classeArrayList.size(); i++) {
					String classe = (String) classeArrayList.get(i);
					if(campo.equals(classe)){
						tabAtualArrayList.add(campo);
					}
				}
			}

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}

	}

	public void startAtualizacao(String activity){

		classe = (String) tabAtualArrayList.get(contAtualBD);
		String[] url = {classe, activity};
		contAtualBD++;

		AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
		Map<String, Object> parametrosPost = new HashMap<>();
		parametrosPost.put("dado", atualAplicDAO.getAtualBDToken());

		LogProcessoDAO.getInstance().insertLogProcesso("postBDGenerico.execute('" + classe + "');", activity);
		PostBDGenerico postBDGenerico = new PostBDGenerico();
		postBDGenerico.setParametrosPost(parametrosPost);
		postBDGenerico.execute(url);

	}

	public void atualTodasTabBD(Context telaAtual, ProgressDialog progressDialog, String activity){

		this.tipoReceb = 1;
		this.telaAtual = telaAtual;
		this.progressDialog = progressDialog;

		allClasses();
		startAtualizacao(activity);

	}

	public void atualTodasTabBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){

		this.tipoReceb = 2;
		this.telaAtual = telaAtual;
		this.telaProx = telaProx;
		this.progressDialog = progressDialog;

		allClasses();
		startAtualizacao(activity);

	}

	public void allClasses(){

		try {

			tabAtualArrayList = new ArrayList();

			Class<?> retClasse = Class.forName(UrlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				if(campo.contains("Bean")){
					tabAtualArrayList.add(campo);
				}
			}

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}

	}
	
	public void atualizandoBD(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("public void atualizandoBD(String activity){", activity);
		if(this.tipoReceb == 1){

			LogProcessoDAO.getInstance().insertLogProcesso("if(this.tipoReceb == 1){\n" +
					"qtdeBD = tabAtualArrayList.size();", activity);
			qtdeBD = tabAtualArrayList.size();
			if(contAtualBD < tabAtualArrayList.size()){

				LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){\n" +
						"this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);\n" +
						"startAtualizacao(activity);", activity);
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
				startAtualizacao(activity);
		        
			} else {

				LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
						"this.progressDialog.dismiss();\n" +
						"contAtualBD = 0;\n" +
						"AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"alerta.setTitle(\"ATENCAO\");\n" +
						"alerta.setMessage(\"FOI ATUALIZADO COM SUCESSO OS DADOS.\");", activity);
				this.progressDialog.dismiss();
				contAtualBD = 0;
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("FOI ATUALIZADO COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
						"@Override\n" +
						"public void onClick(DialogInterface dialog, int which) {", activity));
				alerta.show();
			}
		
		} else if(this.tipoReceb == 2) {

			LogProcessoDAO.getInstance().insertLogProcesso("} else if(this.tipoReceb == 2){\n" +
					"qtdeBD = tabAtualArrayList.size();", activity);
			qtdeBD = tabAtualArrayList.size();
			if(contAtualBD < tabAtualArrayList.size()){

				LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){\n" +
						"this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);\n" +
						"startAtualizacao(activity);", activity);
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
				startAtualizacao(activity);
		        
			} else {
				LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
						"this.progressDialog.dismiss();\n" +
						"contAtualBD = 0;\n" +
						"AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"alerta.setTitle(\"ATENCAO\");\n" +
						"alerta.setMessage(\"FOI ATUALIZADO COM SUCESSO OS DADOS.\");", activity);
				this.progressDialog.dismiss();
				contAtualBD = 0;
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("FOI ATUALIZADO COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", (dialog, which) -> {
					LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
							"@Override\n" +
							"public void onClick(DialogInterface dialog, int which) {\n" +
							"Intent it = new Intent(telaAtual, telaProx);\n" +
							"telaAtual.startActivity(it);", activity);
					Intent it = new Intent(telaAtual, telaProx);
					telaAtual.startActivity(it);
				});

				alerta.show();
			}
			
		}

	}
	
	
	public void encerrar(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("public void encerrar(){\n" +
				"this.progressDialog.dismiss();\n" +
				"AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
				"alerta.setTitle(\"ATENCAO\");\n" +
				"alerta.setMessage(\"FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", activity);
		this.progressDialog.dismiss();
		AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
		alerta.setTitle("ATENCAO");
		alerta.setMessage("FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
		alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
				"@Override\n" +
				"public void onClick(DialogInterface dialog, int which) {", activity));
		alerta.show();

	}

	public String manipLocalClasse(String classe){
	    if(classe.contains("Bean")){
	    	classe = urlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}

}
