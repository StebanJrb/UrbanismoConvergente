package co.edu.unipiloto.app_urbanismo_tactico.Activities.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import co.edu.unipiloto.app_urbanismo_tactico.Activities.MainActivity;
import co.edu.unipiloto.app_urbanismo_tactico.Activities.Admin.TerminadasActivityAdmin;
import co.edu.unipiloto.app_urbanismo_tactico.R;

public class InicioActivityUsuarios extends AppCompatActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient locationProviderClient;
    private TextView txtLatitud, txtLongitud, txtPais, txtLocalidad, txtDireccion;
    GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuarios);
        //uso de la Ubicacion
        Button btnUbicacion = findViewById(R.id.buttonUbicacion);
        txtLatitud = findViewById(R.id.editTextLatitud);
        txtLongitud = findViewById(R.id.editTextLongitud);
        txtPais = findViewById(R.id.editTextPais);
        txtLocalidad = findViewById(R.id.editTextLocalidad);
        txtDireccion = findViewById(R.id.editTextDireccion);

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        // Toolbar y resto de configuracion de botones
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btnReportar = findViewById(R.id.buttonReportIncident);
        Button btnList = findViewById(R.id.buttonListIncident);

        //Google maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad ReportarActivity
                Intent intenti3 = new Intent(InicioActivityUsuarios.this, ReportarActivityUsuarios.class);
                startActivity(intenti3);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad ListaReportesActivity
                Intent intenti4 = new Intent(InicioActivityUsuarios.this, ListaReportesActivityUsuarios.class);
                startActivity(intenti4);
            }
        });

    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //Obtener la Ubicacion
                    Location location = task.getResult();
                    if (location != null) {
                        //Objeto geocoder y Obtener informacion de ubicacion
                        Geocoder geocoder = new Geocoder(InicioActivityUsuarios.this, Locale.getDefault());
                        List<Address> addressList = null;
                        try {
                            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            txtLatitud.setText(String.valueOf(addressList.get(0).getLatitude()));
                            txtLongitud.setText(String.valueOf(addressList.get(0).getLongitude()));
                            txtPais.setText(addressList.get(0).getCountryName());
                            txtLocalidad.setText(addressList.get(0).getLocality());
                            txtDireccion.setText(addressList.get(0).getAddressLine(0));

                            showLocationOnMap(location.getLatitude(),location.getLongitude());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }else{
            ActivityCompat.requestPermissions( InicioActivityUsuarios.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},44);
            }
        }
        private void showLocationOnMap(double latitude, double longitude) {
            if (mMap != null) {
            LatLng ubicacion = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(ubicacion).title("Ubicacion"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
            }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 44) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El usuario otorgó el permiso, puedes llamar a getLocation() de nuevo.
                    getLocation();
                } else {
                    // El usuario negó el permiso, puedes mostrar un mensaje o tomar otra acción.
                }
            }}

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        /*String TextoLatitud = txtLatitud.getText().toString();
        String TextoLongitud = txtLongitud.getText().toString();
        try {
            double valorlatitud = Double.parseDouble(TextoLatitud);
            double valorLogitud = Double.parseDouble(TextoLongitud);
            LatLng ultimaUbicacion = new LatLng(valorlatitud,valorLogitud);
            mMap.addMarker(new MarkerOptions().position(ultimaUbicacion).title("Ubicacion"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ultimaUbicacion));

        }catch (NumberFormatException e){

        }*/


    }
}