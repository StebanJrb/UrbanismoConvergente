package co.edu.unipiloto.app_urbanismo_tactico.Activities.Admin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.unipiloto.app_urbanismo_tactico.R;

public class TerminadasActivityAdmin extends AppCompatActivity {

    private Button btnTraerReportes;
    private ListView listviewReportesParaBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminadas_admin);

        listviewReportesParaBorrar = findViewById(R.id.listReportesDoneAdmin);

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
        listviewReportesParaBorrar.setAdapter(adapter);

        btnTraerReportes = findViewById(R.id.buttonDeleteReport);
        btnTraerReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llama al método para copiar y eliminar el primer registro
                boolean result = copyAndDeleteFirstReport();

                if (result) {
                    Toast.makeText(TerminadasActivityAdmin.this, "Reporte copiado y eliminado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TerminadasActivityAdmin.this, "No se pudo realizar la acción", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("Range")
    private boolean copyAndDeleteFirstReport() {
        // Obtén una instancia de la base de datos
        SQLiteDatabase sql = openOrCreateDatabase("BDUsuariosUT", MODE_PRIVATE, null);

        // Realiza una consulta para obtener el primer reporte de la tabla Reportes
        Cursor cursor = sql.rawQuery("SELECT * FROM Reportes LIMIT 1", null);

        // Verifica si hay al menos un reporte
        if (cursor.moveToFirst()) {
            // Obtiene los valores del primer reporte
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") byte[] photo = cursor.getBlob(cursor.getColumnIndex("photo"));

            // Inserta el reporte en la tabla Reportes_solved
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("description", description);
            cv.put("photo", photo);

            long result = sql.insert("Reportes_solved", null, cv);

            // Verifica si se insertó correctamente en Reportes_solved
            if (result != -1) {
                // Elimina el primer reporte de la tabla Reportes
                sql.execSQL("DELETE FROM Reportes WHERE id = ?", new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("id")))});
                cursor.close();
                sql.close();
                return true;
            }
        }

        cursor.close();
        sql.close();
        return false;

    }
}