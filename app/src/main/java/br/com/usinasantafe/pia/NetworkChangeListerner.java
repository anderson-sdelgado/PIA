package br.com.usinasantafe.pia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

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
	}

}