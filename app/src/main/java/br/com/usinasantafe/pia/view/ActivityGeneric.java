package br.com.usinasantafe.pia.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.util.ArrayList;

import br.com.usinasantafe.pia.NetworkChangeListerner;
import br.com.usinasantafe.pia.R;
import br.com.usinasantafe.pia.model.pst.DatabaseHelper;

public class ActivityGeneric extends OrmLiteBaseActivity<DatabaseHelper> {

    public EditText editTextPadrao;
    public static boolean connectNetwork;

    NetworkChangeListerner networkChangeListerner = new NetworkChangeListerner();
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private ArrayList<String> permissionsToRequest;
    private static final int ALL_PERMISSIONS_RESULT = 1011;

    @Override
    protected void onStart() {

        super.onStart();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListerner, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHelper();

        if (!this.getLocalClassName().contains("TelaInicialActivity")){

            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissions.add(Manifest.permission.CAMERA);

            permissionsToRequest = permissionsToRequest(permissions);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(
                            new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
                }
            }

        }
    }

    @Override
    protected void onStop() {

        unregisterReceiver(networkChangeListerner);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (findViewById(R.id.editTextPadrao) != null) {
            editTextPadrao = findViewById(R.id.editTextPadrao);
            if(!this.getLocalClassName().equals("view.QuestaoAmostraActivity")) {
                editTextPadrao.setText("");
            }
        }

        if(findViewById(R.id.buttonNum0) != null){
            Button buttonNum0 = findViewById(R.id.buttonNum0);
            buttonNum0.setOnClickListener(new EventoBotao("0"));
        }

        if(findViewById(R.id.buttonNum1) != null){
            Button buttonNum1 = findViewById(R.id.buttonNum1);
            buttonNum1.setOnClickListener(new EventoBotao("1"));
        }

        if(findViewById(R.id.buttonNum2) != null){
            Button buttonNum2 = findViewById(R.id.buttonNum2);
            buttonNum2.setOnClickListener(new EventoBotao("2"));
        }

        if(findViewById(R.id.buttonNum3) != null){
            Button buttonNum3 = findViewById(R.id.buttonNum3);
            buttonNum3.setOnClickListener(new EventoBotao("3"));
        }

        if(findViewById(R.id.buttonNum4) != null){
            Button buttonNum4 = findViewById(R.id.buttonNum4);
            buttonNum4.setOnClickListener(new EventoBotao("4"));
        }

        if(findViewById(R.id.buttonNum5) != null){
            Button buttonNum5 = findViewById(R.id.buttonNum5);
            buttonNum5.setOnClickListener(new EventoBotao("5"));
        }

        if(findViewById(R.id.buttonNum6) != null){
            Button buttonNum6 = findViewById(R.id.buttonNum6);
            buttonNum6.setOnClickListener(new EventoBotao("6"));
        }

        if(findViewById(R.id.buttonNum7) != null){
            Button buttonNum7 = findViewById(R.id.buttonNum7);
            buttonNum7.setOnClickListener(new EventoBotao("7"));
        }

        if(findViewById(R.id.buttonNum8) != null){
            Button buttonNum8 = findViewById(R.id.buttonNum8);
            buttonNum8.setOnClickListener(new EventoBotao("8"));
        }

        if(findViewById(R.id.buttonNum9) != null){
            Button buttonNum9 = findViewById(R.id.buttonNum9);
            buttonNum9.setOnClickListener(new EventoBotao("9"));
        }

        if(findViewById(R.id.buttonNum00) != null){
            Button buttonNum00 = findViewById(R.id.buttonNum00);
            buttonNum00.setOnClickListener(new EventoBotao("00"));
        }

        if(findViewById(R.id.buttonVirg) != null){
            Button buttonVirg = findViewById(R.id.buttonVirg);
            buttonVirg.setOnClickListener(new EventoBotao(","));
        }
    }

    private class EventoBotao implements View.OnClickListener{

        private String numBotao;

        public EventoBotao(String numBotao) {
            this.numBotao = numBotao;
        }

        @Override
        public void onClick(View v) {

            String texto = editTextPadrao.getText().toString();
            if(numBotao.equals(",")){
                if(!texto.contains(",")){
                    editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
                }
            }
            else{
                editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
            }

        }

    }

    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    public void onBackPressed()  {
    }

}
