package co.edu.unipiloto.app_urbanismo_tactico.Activities;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import co.edu.unipiloto.app_urbanismo_tactico.R;

public class InicioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btnReportar = findViewById(R.id.buttonReportIncident);
        Button btnList = findViewById(R.id.buttonListIncidet);
        Button btnSolucionados = findViewById(R.id.buttonSolved);

        btnSolucionados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenti2 = new Intent(InicioActivity.this,TerminadasActivity.class);
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

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.item1){
            Toast.makeText(this,"oprimiste el boton reportar situacion",Toast.LENGTH_SHORT).show();
            Intent intenti3 = new Intent(InicioActivity.this, ReportarActivity.class);
            startActivity(intenti3);

        } else if (id==R.id.item2) {
            Toast.makeText(this,"oprimiste el boton ver estadisticas",Toast.LENGTH_SHORT).show();
        }else if (id==R.id.item3) {
            Toast.makeText(this,"oprimiste el boton historial de reportes",Toast.LENGTH_SHORT).show();
            Intent intenti4 = new Intent(InicioActivity.this, ListaReportesActivity.class);
            startActivity(intenti4);

        }else if (id==R.id.item4) {
            Toast.makeText(this,"oprimiste el boton salir",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(InicioActivity.this, MainActivity.class);
            startActivity(i);
            finish();
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
                Intent intenti2 = new Intent(InicioActivity.this,TerminadasActivity.class);
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
}