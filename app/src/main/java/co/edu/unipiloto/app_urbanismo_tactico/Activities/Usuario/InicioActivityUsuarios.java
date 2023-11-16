package co.edu.unipiloto.app_urbanismo_tactico.Activities.Usuario;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

=======
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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
import co.edu.unipiloto.app_urbanismo_tactico.Activities.SugerenciasActivity;
import co.edu.unipiloto.app_urbanismo_tactico.R;

public class InicioActivityUsuarios extends AppCompatActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient locationProviderClient;
    private TextView txtLatitud, txtLongitud, txtPais, txtLocalidad, txtDireccion;
    GoogleMap mMap;


=======
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.app_urbanismo_tactico.Activities.MainActivity;
import co.edu.unipiloto.app_urbanismo_tactico.Activities.Admin.TerminadasActivityAdmin;
import co.edu.unipiloto.app_urbanismo_tactico.R;

public class InicioActivityUsuarios extends AppCompatActivity {
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuarios);
<<<<<<< HEAD
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
=======

>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btnReportar = findViewById(R.id.buttonReportIncident);
<<<<<<< HEAD
        Button btnList = findViewById(R.id.buttonListIncident);

        //Google maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


=======
        Button btnList = findViewById(R.id.buttonListIncidet);
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195

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
<<<<<<< HEAD
    public boolean onCreateOptionsMenu(Menu menu){
=======

    /*public boolean onCreateOptionsMenu(Menu menu){
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.item1){
            Toast.makeText(this,"oprimiste el boton reportar situacion",Toast.LENGTH_SHORT).show();
            Intent intenti3 = new Intent(InicioActivityUsuarios.this, ReportarActivityUsuarios.class);
            startActivity(intenti3);

        } else if (id==R.id.item2) {
            Toast.makeText(this,"oprimiste el boton ver estadisticas",Toast.LENGTH_SHORT).show();
        }else if (id==R.id.item3) {
            Toast.makeText(this,"oprimiste el boton historial de reportes",Toast.LENGTH_SHORT).show();
            Intent intenti4 = new Intent(InicioActivityUsuarios.this, ListaReportesActivityUsuarios.class);
            startActivity(intenti4);

        }else if (id==R.id.item4) {
            Toast.makeText(this,"oprimiste el boton salir",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(InicioActivityUsuarios.this, MainActivity.class);
            startActivity(i);
            finish();
<<<<<<< HEAD
        }else if (id==R.id.item5) {
            Toast.makeText(this,"oprimiste el boton Comentarios",Toast.LENGTH_SHORT).show();
            Intent intenti5 = new Intent(InicioActivityUsuarios.this, SugerenciasActivity.class);
            startActivity(intenti5);
        }
        return super.onOptionsItemSelected(item);
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
    }
=======
        }
        return super.onOptionsItemSelected(item);
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button btnReportar = findViewById(R.id.buttonReportIncident);
        Button btnList = findViewById(R.id.buttonListIncidet);
        Button btnSolucionados = findViewById(R.id.buttonSolved);

        btnSolucionados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenti2 = new Intent(InicioActivity.this,TerminadasActivityAdmin.class);
                startActivity(intenti2);
            }
        });

        btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad ReportarActivity
                Intent intenti3 = new Intent(InicioActivity.this, ReportarActivity.class);
                startActivity(intenti3);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad ListaReportesActivity
                Intent intenti4 = new Intent(InicioActivity.this, ListaReportesActivity.class);
                startActivity(intenti4);
            }
        });
    }*/
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195
}