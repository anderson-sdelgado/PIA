package br.com.usinasantafe.pia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.bean.variaveis.RespItemAmostraBean;
import br.com.usinasantafe.pia.util.ConnectNetwork;
import br.com.usinasantafe.pia.util.Tempo;
import br.com.usinasantafe.pia.model.pst.DatabaseHelper;
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
		if(ConnectNetwork.isConnected(context)){
			ActivityGeneric.connectNetwork = true;
		}
		else{
			ActivityGeneric.connectNetwork = false;
		}

		cabec();
		resp();

	}

	public void cabec(){
		CabecAmostraBean cabecAmostraBean = new CabecAmostraBean();
		List<CabecAmostraBean> cabecAmostraBeanList = cabecAmostraBean.all();
		Log.i("PIA", "CABEC");
		for(CabecAmostraBean cabecAmostraBeanBD : cabecAmostraBeanList){
			Log.i("PIA", "idCabec = " + cabecAmostraBeanBD.getIdCabec());
			Log.i("PIA", "auditorCabec = " + cabecAmostraBeanBD.getAuditorCabec());
			Log.i("PIA", "dthrCabec = " + cabecAmostraBeanBD.getDthrCabec());
			Log.i("PIA", "dthrLongCabec = " + cabecAmostraBeanBD.getDthrLongCabec());
			Log.i("PIA", "osCabec = " + cabecAmostraBeanBD.getOsCabec());
			Log.i("PIA", "secaoCabec = " + cabecAmostraBeanBD.getSecaoCabec());
			Log.i("PIA", "talhaoCabec = " + cabecAmostraBeanBD.getTalhaoCabec());
			Log.i("PIA", "idOrganCabec = " + cabecAmostraBeanBD.getIdOrganCabec());
			Log.i("PIA", "idCaracOrganCabec = " + cabecAmostraBeanBD.getIdCaracOrganCabec());
			Log.i("PIA", "statusAmostra = " + cabecAmostraBeanBD.getStatusAmostra());
		}
		cabecAmostraBeanList.clear();
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
			Log.i("PIA", "statusRespItem = " + respItemAmostraBeanBD.getStatusRespItem());
		}
		respItemAmostraBeanList.clear();
	}

}