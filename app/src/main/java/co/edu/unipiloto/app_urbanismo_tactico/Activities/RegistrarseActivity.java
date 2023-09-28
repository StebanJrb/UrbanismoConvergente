package co.edu.unipiloto.app_urbanismo_tactico.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unipiloto.app_urbanismo_tactico.DAO.daoUsuario;
import co.edu.unipiloto.app_urbanismo_tactico.R;
import co.edu.unipiloto.app_urbanismo_tactico.modelo.Usuario;

public class RegistrarseActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nombrec, us, cedula,correo, pass,confirmarpass;
    Button btnreg,btncan;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        nombrec=(EditText)findViewById(R.id.firstNameEditText);
        us=(EditText)findViewById(R.id.UsuarioNameEditText);
        cedula=(EditText)findViewById(R.id.cedulaEditText);
        correo =(EditText)findViewById(R.id.EmailEditText);
        pass=(EditText)findViewById(R.id.passwordEditText);
        confirmarpass=(EditText)findViewById(R.id.confirmPasswordEditText);
        btnreg = (Button) findViewById(R.id.registerButton);
        btncan = (Button) findViewById(R.id.cancelarButton);
        btnreg.setOnClickListener(this);
        btncan.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()){
            case R.id.registerButton:
                Usuario u =new Usuario();
                u.setNombre(nombrec.getText().toString());
                u.setUsuario(us.getText().toString());
                u.setCedula(Integer.parseInt(cedula.getText().toString()));
                u.setNombre(correo.getText().toString());
                u.setNombre(pass.getText().toString());
                u.setNombre(confirmarpass.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"error: campos vacios",Toast.LENGTH_LONG).show();

                }else if(dao.insertUsuario(u)) {
                    Toast.makeText(this,"registro exitoso",Toast.LENGTH_LONG).show();
                    Intent i2= new Intent(RegistrarseActivity.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this,"usuario ya registrado",Toast.LENGTH_LONG).show();
                }
        }*/
        if (v.getId() == R.id.registerButton){
            Usuario u =new Usuario();
            u.setNombre(nombrec.getText().toString());
            u.setUsuario(us.getText().toString());
            u.setCedula(cedula.getText().toString());
            u.setEmail(correo.getText().toString());
            u.setPassword(pass.getText().toString());
            u.setConfirmpassword(confirmarpass.getText().toString());

            if (!u.isNull()){
                Toast.makeText(this,"error: campos vacios",Toast.LENGTH_LONG).show();

            }else if(dao.insertUsuario(u)) {
                Toast.makeText(this,"registro exitoso",Toast.LENGTH_LONG).show();
                Intent i2= new Intent(RegistrarseActivity.this, MainActivity.class);
                startActivity(i2);
                finish();
            }else {
                Toast.makeText(this,"usuario ya registrado",Toast.LENGTH_LONG).show();
            }
        }else if (v.getId() == R.id.cancelarButton){
            Intent i = new Intent(RegistrarseActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}