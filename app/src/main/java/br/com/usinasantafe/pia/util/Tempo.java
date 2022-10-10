package br.com.usinasantafe.pia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.usinasantafe.pia.model.dao.LogErroDAO;

public class Tempo {

    private static Tempo instance = null;
	
	public Tempo() {
	}

    public static Tempo getInstance() {
        if (instance == null)
            instance = new Tempo();
        return instance;
    }

    private String dthrAtualBaseString(){
        Date dataHora = new Date();
        return dthrLongToString(dataHora.getTime());
    }

    //////////////////////////////////// DATA/HORA ATUAL ///////////////////////////////////////////

    public String dthrAtualString(){
        return dthrLongToString(dthrAtualLong());
    }

    public String dtAtualString(){
        return dtLongToString(dthrAtualLong());
    }

    public Long dthrAtualLong(){
        return dthrStringToLong(dthrAtualBaseString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// STRING TO LONG ///////////////////////////////////////////

    public Long dthrStringToLong(String dthrString){
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            date = format.parse(dthrString + ":00");
        } catch (ParseException e) {
            LogErroDAO.getInstance().insertLogErro(e);
        }
        return date.getTime();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// LONG TO STRING ///////////////////////////////////////////

    public String dthrLongToString(Long dthrLong){
        Date dt = new Date (dthrLong);
        SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
        return df.format(dt);
    }

    public String dtLongToString(Long dthrLong){
        Date dt = new Date (dthrLong);
        SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        return df.format(dt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Long dthrLongDiaMenos(int dia){
        return dthrStringToLong(dthrAtualString()) - (dia*24*60*60*1000);
    }

}
