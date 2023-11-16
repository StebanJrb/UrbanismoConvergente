package co.edu.unipiloto.app_urbanismo_tactico.Activities.Admin;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.app_urbanismo_tactico.R;
public class InicioActivityAdmin extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btnList = findViewById(R.id.buttonListIncidet);
        Button btnSolucionados = findViewById(R.id.buttonSolved);

       btnSolucionados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenti2 = new Intent(InicioActivityAdmin.this,TerminadasActivityAdmin.class);
                startActivity(intenti2);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad ListaReportesActivity
                Intent intenti4 = new Intent(InicioActivityAdmin.this, ListaReportesActivityAdmin.class);
                startActivity(intenti4);
            }
        });

    }

}
