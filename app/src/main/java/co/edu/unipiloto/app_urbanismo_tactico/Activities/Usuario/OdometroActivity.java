package co.edu.unipiloto.app_urbanismo_tactico.Activities.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

import java.util.Locale;

import co.edu.unipiloto.app_urbanismo_tactico.R;

public class OdometroActivity extends AppCompatActivity {

    private OdometerService odometer;
    // Variable para controlar el estado de la conexion al servicio
    private boolean enlazado=false;

    private ServiceConnection connection= new ServiceConnection (){

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            OdometerService.OdometerBinder odometerbinder= (OdometerService.OdometerBinder) iBinder ;
            odometer=odometerbinder.getOdometer();
            enlazado=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            enlazado=false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometro);
        mostrarDistancia();
    }
    @Override
    protected void onStart(){
        super.onStart();
        Intent intent=new Intent(this, OdometerService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(enlazado){
            unbindService(connection);
            enlazado=false;
        }
    }
    private void mostrarDistancia(){
        final TextView distanceView =findViewById(R.id.distancia);
        final Handler handler= new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distancia=0.0;
                if(enlazado && odometer !=null){
                    distancia=odometer.obtenerDistancia();
                }
                String distanceSTR=String.format(Locale.getDefault(),"%1$,.2f kilometros", distancia);
                distanceView.setText(distanceSTR);
                handler.postDelayed(this,1000);
            }
        });
    }

}