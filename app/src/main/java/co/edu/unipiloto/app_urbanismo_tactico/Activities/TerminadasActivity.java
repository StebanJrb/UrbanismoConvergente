package co.edu.unipiloto.app_urbanismo_tactico.Activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




import co.edu.unipiloto.app_urbanismo_tactico.R;

public class TerminadasActivity extends AppCompatActivity {

    private Button btnTraerReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminadas);

        btnTraerReportes = findViewById(R.id.buttonBringReports);

        btnTraerReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llama al método para copiar y eliminar el primer registro
                boolean result = copyAndDeleteFirstReport();

                if (result) {
                    Toast.makeText(TerminadasActivity.this, "Reporte copiado y eliminado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TerminadasActivity.this, "No se pudo realizar la acción", Toast.LENGTH_SHORT).show();
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