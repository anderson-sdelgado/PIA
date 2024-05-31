package br.com.usinasantafe.pia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.LocalAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.util.ConnectNetwork;
import br.com.usinasantafe.pia.view.ActivityGeneric;


/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 * @author ricardo
 * 
 */
public class NetworkChangeListerner extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ActivityGeneric.connectNetwork = ConnectNetwork.isConnected(context);
		cabec();
		local();
		resp();
	}

	public void cabec(){
		CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
		List<CabecAmostraBean> cabecAmostraBeanList = cabecAmostraBean.all();
		Log.i("PIA", "CABEC");
		for(CabecAmostraBean cabecAmostraBeanBD : cabecAmostraBeanList){
			Log.i("PIA", "idCabec = " + cabecAmostraBeanBD.getIdCabec());
			Log.i("PIA", "matricAuditor = " + cabecAmostraBeanBD.getMatricAuditor());
			Log.i("PIA", "dthr = " + cabecAmostraBeanBD.getDthr());
			Log.i("PIA", "dthrLong = " + cabecAmostraBeanBD.getDthrLong());
			Log.i("PIA", "idOrgan = " + cabecAmostraBeanBD.getIdOrgan());
			Log.i("PIA", "idCaracOrgan = " + cabecAmostraBeanBD.getIdCaracOrgan());
			Log.i("PIA", "statusCabec = " + cabecAmostraBeanBD.getStatusCabec());
			Log.i("PIA", "statusApont = " + cabecAmostraBeanBD.getStatusApont());
		}
		cabecAmostraBeanList.clear();
	}

	public void local(){
		LocalAmostraBean localAmostraBean = new LocalAmostraBean();
		List<LocalAmostraBean> localAmostraBeaList = localAmostraBean.all();
		Log.i("PIA", "LOCAL");
		for(LocalAmostraBean localAmostraBeanBD : localAmostraBeaList){
			Log.i("PIA", "idLocal = " + localAmostraBeanBD.getIdLocal());
			Log.i("PIA", "idCabec = " + localAmostraBeanBD.getIdCabec());
			Log.i("PIA", "dthr = " + localAmostraBeanBD.getDthr());
			Log.i("PIA", "dthrLong = " + localAmostraBeanBD.getDthrLong());
			Log.i("PIA", "nroOS = " + localAmostraBeanBD.getNroOS());
			Log.i("PIA", "secao = " + localAmostraBeanBD.getIdSecao());
			Log.i("PIA", "talhao = " + localAmostraBeanBD.getIdLocal());
			Log.i("PIA", "statusLocal = " + localAmostraBeanBD.getStatusLocal());
		}
		localAmostraBeaList.clear();
	}

	public void resp(){
		RespItemAmostraBean respItemAmostraBean = new RespItemAmostraBean();
		List<RespItemAmostraBean> respItemAmostraBeanList = respItemAmostraBean.all();
		Log.i("PIA", "RESP");
		for(RespItemAmostraBean respItemAmostraBeanBD : respItemAmostraBeanList){
			Log.i("PIA", "idRespItem = " + respItemAmostraBeanBD.getIdRespItem());
			Log.i("PIA", "idCabec = " + respItemAmostraBeanBD.getIdCabec());
			Log.i("PIA", "idAmostraRespItem = " + respItemAmostraBeanBD.getIdAmostraRespItem());
			Log.i("PIA", "pontoRespItem = " + respItemAmostraBeanBD.getPontoRespItem());
			Log.i("PIA", "valorRespItem = " + respItemAmostraBeanBD.getValorRespItem());
		}
		respItemAmostraBeanList.clear();
	}

}