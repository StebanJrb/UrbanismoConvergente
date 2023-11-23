package co.edu.unipiloto.app_urbanismo_tactico.Activities.Usuario;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.Random;

public class OdometerService extends Service {

    private final IBinder binder = new OdometerBinder();
    private final Random random=new Random();
    private static double distanciaEnMetros;
    //declarar objetos para hacer uso de los servicios de localizacion
    // Variable para escuchar cambios en servicios de localizacion
    private LocationListener listener;
    // Guardar ubicción anterior para calcular distancia
    private static Location ultimaLocalizacion;
    //Instanciar objeto de un administrador de localizacion
    private static LocationManager locManager;

    public class OdometerBinder extends Binder {
        OdometerService getOdometer(){
            return OdometerService.this;
        }
    }

    public OdometerService() {
    }
    @Override
    public void onCreate(){
        super.onCreate();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
               if(ultimaLocalizacion==null){
                   ultimaLocalizacion=location;
               }
               distanciaEnMetros+=location.distanceTo(ultimaLocalizacion);
               ultimaLocalizacion=location;
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(null,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        locManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String proveedor = locManager.getBestProvider(new Criteria(), true);
        if(proveedor !=null){
            locManager.requestLocationUpdates(proveedor, 2000, 1,listener);
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double obtenerDistancia() {
      //return random.nextDouble();
        return this.distanciaEnMetros;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (locManager!=null&& listener!=null){
            locManager.removeUpdates(listener);
        }
        locManager=null;
        listener=null;
    }
}