package co.edu.unipiloto.app_urbanismo_tactico.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unipiloto.app_urbanismo_tactico.DAO.daoUsuario;
import co.edu.unipiloto.app_urbanismo_tactico.R;
import co.edu.unipiloto.app_urbanismo_tactico.modelo.Usuario;

public class SugerenciasActivity extends AppCompatActivity {

    //CODIGO DA LA FUNCIONALIDAD COMENTARIOS Y SUGERENCIAS DE USUSARIOS

    EditText editTextSugerencias;
    Button btnSugerencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencias);


        editTextSugerencias=(EditText)findViewById(R.id.editTextCommentarios);
        btnSugerencias= (Button) findViewById(R.id.buttonEnviarSug);
        //btnSugerencias.setOnClickListener(this);
        btnSugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveComentToDatabase();
            }
        });
        //SE MUESTRA EL TOOBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //SE MUESTRA EL TOOBAR
    }
    private void saveComentToDatabase() {

        String comentarios = editTextSugerencias.getText().toString();

        if (comentarios.isEmpty()) {
            Toast.makeText(this, "complete el campo de texto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guarda los datos de los comentarios de los usuarios en la base de datos
        daoUsuario daou = new daoUsuario(this);
        Usuario usuario = daou.getUsuarioById(1);
        int userId = usuario.getId();
        if (usuario != null) {
            boolean datosinsertados = daou.insertSugerencia(comentarios, userId );
            if (datosinsertados) {
                Toast.makeText(this, " Gracias por tu opinion.. enviado con exito", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al enviar su comentario intente nuevamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se encontró un usuario válido", Toast.LENGTH_SHORT).show();
        }
    }

}
