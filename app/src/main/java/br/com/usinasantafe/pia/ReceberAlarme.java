package br.com.usinasantafe.pia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import br.com.usinasantafe.pia.bo.ManipDadosEnvio;
import br.com.usinasantafe.pia.bo.Tempo;
import br.com.usinasantafe.pia.pst.DatabaseHelper;


/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 * @author ricardo
 * 
 */
public class ReceberAlarme extends BroadcastReceiver {

	private DatabaseHelper databaseHelper = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
		}

		Log.i("PIA", "DATA HORA = " + Tempo.getInstance().data());
		System.gc();
		if(ManipDadosEnvio.getInstance().verifDadosEnvio()){
			Log.i("PIA", "ENVIANDO");
			ManipDadosEnvio.getInstance().envioDados(context);
		}

	}

}