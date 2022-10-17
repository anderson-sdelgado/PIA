package br.com.usinasantafe.pia.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pia.util.conHttp.GetBDGenerico;
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
					genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);
				}

				if(contAtualBD > 0){
					LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD > 0){\n" +
							"\t\t\t\t\tatualizandoBD(activity);", activity);
					atualizandoBD(activity);
				}

			}
			catch (Exception e) {
				LogErroDAO.getInstance().insertLogErro(e);
			}

		} else {
			LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
					"\t\t\tencerrar(activity);", activity);
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

		LogProcessoDAO.getInstance().insertLogProcesso("getBDGenerico.execute('" + classe + "');", activity);
		GetBDGenerico getBDGenerico = new GetBDGenerico();
		getBDGenerico.execute(url);

	}

	public void atualTodasTabBD(Context telaAtual, ProgressDialog progressDialog, String activity){

		this.tipoReceb = 1;
		this.telaAtual = telaAtual;
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
					"\t\t\tqtdeBD = tabAtualArrayList.size();", activity);
			qtdeBD = tabAtualArrayList.size();
			if(contAtualBD < tabAtualArrayList.size()){

				LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){\n" +
						"\t\t\t\tthis.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);\n" +
						"\t\t        classe = (String) tabAtualArrayList.get(contAtualBD);\n" +
						"\t\t\t\tString[] url = {classe, activity};\n" +
						"\t\t\t\tcontAtualBD++;\n" +
						"\t\t\t\tGetBDGenerico getBDGenerico = new GetBDGenerico();\n" +
						"\t\t        getBDGenerico.execute(url);", activity);
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe, activity};
				contAtualBD++;

				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			} else {

				LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
						"\t\t\t\tthis.progressDialog.dismiss();\n" +
						"\t\t\t\tcontAtualBD = 0;\n" +
						"\t\t\t\tAlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"\t\t\t\talerta.setTitle(\"ATENCAO\");\n" +
						"\t\t\t\talerta.setMessage(\"FOI ATUALIZADO COM SUCESSO OS DADOS.\");", activity);
				this.progressDialog.dismiss();
				contAtualBD = 0;
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("FOI ATUALIZADO COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
								"\t\t\t\t\t@Override\n" +
								"\t\t\t\t\tpublic void onClick(DialogInterface dialog, int which) {", activity);
					}
				});
				
				alerta.show();
			}
		
		} else if(this.tipoReceb == 2){

			LogProcessoDAO.getInstance().insertLogProcesso("} else if(this.tipoReceb == 2){\n" +
					"\t\t\tqtdeBD = tabAtualArrayList.size();", activity);
			qtdeBD = tabAtualArrayList.size();
			if(contAtualBD < tabAtualArrayList.size()){

				LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){\n" +
						"\t\t        classe = (String) tabAtualArrayList.get(contAtualBD);\n" +
						"\t\t\t\tString[] url = {classe, activity};\n" +
						"\t\t\t\tcontAtualBD++;\n" +
						"\t\t\t\tGetBDGenerico getBDGenerico = new GetBDGenerico();\n" +
						"\t\t        getBDGenerico.execute(url);\n", activity);
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe, activity};
				contAtualBD++;

				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			} else {
				LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
						"\t\t\t\tcontAtualBD = 0;", activity);
				contAtualBD = 0;
			}
			
		}

	}
	
	
	public void encerrar(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("public void encerrar(){", activity);
		if(this.tipoReceb == 1){

			LogProcessoDAO.getInstance().insertLogProcesso("if(this.tipoReceb == 1){\n" +
					"\t\t\tthis.progressDialog.dismiss();\n" +
					"\t\t\tAlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
					"\t\t\talerta.setTitle(\"ATENCAO\");\n" +
					"\t\t\talerta.setMessage(\"FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", activity);
			this.progressDialog.dismiss();
			AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
			alerta.setTitle("ATENCAO");
			alerta.setMessage("FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
							"\t\t\t\t@Override\n" +
							"\t\t\t\tpublic void onClick(DialogInterface dialog, int which) {", activity);
				}
			});
			
			alerta.show();
			
		}
	}

	public String manipLocalClasse(String classe){
	    if(classe.contains("Bean")){
	    	classe = urlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}

}
