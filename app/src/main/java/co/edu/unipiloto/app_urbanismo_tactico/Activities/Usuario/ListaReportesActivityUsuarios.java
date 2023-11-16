package co.edu.unipiloto.app_urbanismo_tactico.Activities.Usuario;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.appcompat.widget.Toolbar;
=======
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.unipiloto.app_urbanismo_tactico.R;

public class ListaReportesActivityUsuarios extends AppCompatActivity {

    private ListView listViewReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reportes_usuarios);

        listViewReportes = findViewById(R.id.listViewReportes);
<<<<<<< HEAD
        //SE MUESTRA EL TOOBAR

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //SE MUESTRA EL TOOBAR
=======
>>>>>>> 95eb378cd965e594ef317a7141d759ea83af7195

        // Obtén una instancia de la base de datos
        SQLiteDatabase sql = openOrCreateDatabase("BDUsuariosUT", MODE_PRIVATE, null);

        // Realiza una consulta para obtener los reportes
        Cursor cursor = sql.rawQuery("SELECT * FROM Reportes", null);

        // Crea una lista de mapas para almacenar los datos de los reportes
        List<Map<String, String>> reportData = new ArrayList<>();

        // Recorre el cursor y agrega los datos de los reportes a la lista
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                // Crea un mapa para almacenar los datos del reporte
                Map<String, String> reportItem = new HashMap<>();
                reportItem.put("Item1", title);
                reportItem.put("SubItem1", description);

                reportData.add(reportItem);
            } while (cursor.moveToNext());
        }

        // Cierra el cursor y la conexión a la base de datos
        cursor.close();
        sql.close();

        // Crea un adaptador simple para mostrar los títulos y descripciones en el ListView
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                reportData,
                android.R.layout.simple_list_item_2,
                new String[]{"Item1", "SubItem1"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        // Establece el adaptador en el ListView
        listViewReportes.setAdapter(adapter);
    }
}

