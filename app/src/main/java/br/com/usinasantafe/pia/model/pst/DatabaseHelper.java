package br.com.usinasantafe.pia.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.usinasantafe.pia.model.bean.estaticas.*;
import br.com.usinasantafe.pia.model.bean.variaveis.*;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static DatabaseHelper instance;
	public static final String FORCA_DB_NAME = "pia_db";
	public static final int FORCA_BD_VERSION = 2;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, FORCA_DB_NAME,
				null, FORCA_BD_VERSION);
		instance = this;
	}

	@Override
	public void close() {
		super.close();
		instance = null;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			createTable(cs);

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
				dropTable(cs);
				createTable(cs);
			}

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

	public void dropTable(ConnectionSource cs) throws SQLException {
		TableUtils.dropTable(cs, AmostraBean.class, true);
		TableUtils.dropTable(cs, AuditorBean.class, true);
		TableUtils.dropTable(cs, CaracOrganBean.class, true);
		TableUtils.dropTable(cs, OrganBean.class, true);
		TableUtils.dropTable(cs, OSBean.class, true);
		TableUtils.dropTable(cs, RCaracAmostraBean.class, true);
		TableUtils.dropTable(cs, ROrganCaracBean.class, true);
		TableUtils.dropTable(cs, SecaoBean.class, true);
		TableUtils.dropTable(cs, TalhaoBean.class, true);

		TableUtils.dropTable(cs, CabecAmostraBean.class, true);
		TableUtils.dropTable(cs, ConfigBean.class, true);
		TableUtils.dropTable(cs, ItemAmostraBean.class, true);
		TableUtils.dropTable(cs, LogErroBean.class, true);
		TableUtils.dropTable(cs, LogProcessoBean.class, true);
		TableUtils.dropTable(cs, RespItemAmostraBean.class, true);
	}

	public void createTable(ConnectionSource cs) throws SQLException {
		TableUtils.createTable(cs, AmostraBean.class);
		TableUtils.createTable(cs, AuditorBean.class);
		TableUtils.createTable(cs, CaracOrganBean.class);
		TableUtils.createTable(cs, OrganBean.class);
		TableUtils.createTable(cs, OSBean.class);
		TableUtils.createTable(cs, RCaracAmostraBean.class);
		TableUtils.createTable(cs, ROrganCaracBean.class);
		TableUtils.createTable(cs, SecaoBean.class);
		TableUtils.createTable(cs, TalhaoBean.class);

		TableUtils.createTable(cs, CabecAmostraBean.class);
		TableUtils.createTable(cs, ConfigBean.class);
		TableUtils.createTable(cs, ItemAmostraBean.class);
		TableUtils.createTable(cs, LogErroBean.class);
		TableUtils.createTable(cs, LogProcessoBean.class);
		TableUtils.createTable(cs, RespItemAmostraBean.class);
	}

}
