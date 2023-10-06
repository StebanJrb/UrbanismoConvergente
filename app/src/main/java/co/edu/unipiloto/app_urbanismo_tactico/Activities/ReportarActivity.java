package co.edu.unipiloto.app_urbanismo_tactico.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import co.edu.unipiloto.app_urbanismo_tactico.DAO.daoUsuario;
import co.edu.unipiloto.app_urbanismo_tactico.R;
import co.edu.unipiloto.app_urbanismo_tactico.modelo.Usuario;

public class ReportarActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextDescription, tittleDescription;
    private ImageView imageView;
    private Bitmap photoBitmap;

    private Button btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tittleDescription = findViewById(R.id.tittleReportDescription);
        editTextDescription = findViewById(R.id.TextDescription);

        btnCamera = findViewById(R.id.buttonTakePhoto);
        imageView = findViewById(R.id.imageView);
        Button buttonSend = findViewById(R.id.buttonSend);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReportToDatabase();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        //}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
                photoBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(photoBitmap);
        }
    }

    private void saveReportToDatabase() {
        String title = tittleDescription.getText().toString();
        String description = editTextDescription.getText().toString();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (photoBitmap == null) {
            Toast.makeText(this, "Por favor, tome una foto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guarda los datos en la base de datos SQLite usando daoUsuario
        daoUsuario usuarioDAO = new daoUsuario(this);
        Usuario usuario = usuarioDAO.getUsuarioById(1); // Cambia el ID de usuario según tu lógica
        int userId = usuario.getId();
        if (usuario != null) {
            // Guarda los datos en la base de datos
            boolean resultado = daoUsuario.insertReporte(title, description, photoBitmap,userId);
            if (resultado) {
                Toast.makeText(this, "Reporte guardado con éxito", Toast.LENGTH_SHORT).show();
                finish(); // Vuelve a la actividad anterior
            } else {
                Toast.makeText(this, "Error al guardar el reporte", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se encontró un usuario válido", Toast.LENGTH_SHORT).show();
        }
    }
}
