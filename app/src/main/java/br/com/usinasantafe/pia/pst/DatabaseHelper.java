package br.com.usinasantafe.pia.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pia.tb.estaticas.CaracOrganTO;
import br.com.usinasantafe.pia.tb.estaticas.DataTO;
import br.com.usinasantafe.pia.tb.estaticas.OrganTO;
import br.com.usinasantafe.pia.tb.estaticas.AuditorTO;
import br.com.usinasantafe.pia.tb.estaticas.AmostraTO;
import br.com.usinasantafe.pia.tb.estaticas.RCaracAmostraTO;
import br.com.usinasantafe.pia.tb.estaticas.ROrganCaracTO;
import br.com.usinasantafe.pia.tb.estaticas.SecaoTO;
import br.com.usinasantafe.pia.tb.estaticas.TalhaoTO;
import br.com.usinasantafe.pia.tb.variaveis.AltExcluirItemTO;
import br.com.usinasantafe.pia.tb.variaveis.CabecAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemAmostraTO;
import br.com.usinasantafe.pia.tb.variaveis.ItemSalvoTO;
import br.com.usinasantafe.pia.tb.variaveis.RespItemAmostraTO;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static DatabaseHelper instance;
	public static final String FORCA_DB_NAME = "pia_db";
	public static final int FORCA_BD_VERSION = 1;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, FORCA_DB_NAME,
				null, FORCA_BD_VERSION);
		
		// TODO Auto-generated constructor stub
		instance = this;
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// TODO Auto-generated method stub
		
		try{

			TableUtils.createTable(cs, OrganTO.class);
			TableUtils.createTable(cs, AmostraTO.class);
			TableUtils.createTable(cs, RCaracAmostraTO.class);
			TableUtils.createTable(cs, AuditorTO.class);
			TableUtils.createTable(cs, ROrganCaracTO.class);
			TableUtils.createTable(cs, CaracOrganTO.class);
			TableUtils.createTable(cs, SecaoTO.class);
			TableUtils.createTable(cs, TalhaoTO.class);
			TableUtils.createTable(cs, DataTO.class);

			TableUtils.createTable(cs, CabecAmostraTO.class);
			TableUtils.createTable(cs, ItemAmostraTO.class);
			TableUtils.createTable(cs, RespItemAmostraTO.class);
			TableUtils.createTable(cs, ItemSalvoTO.class);
			TableUtils.createTable(cs, AltExcluirItemTO.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...", e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				oldVersion = 2;
			}

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
