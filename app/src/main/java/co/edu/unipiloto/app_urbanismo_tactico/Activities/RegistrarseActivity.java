package co.edu.unipiloto.app_urbanismo_tactico.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import co.edu.unipiloto.app_urbanismo_tactico.DAO.daoUsuario;
import co.edu.unipiloto.app_urbanismo_tactico.R;
import co.edu.unipiloto.app_urbanismo_tactico.modelo.Usuario;

public class RegistrarseActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombrec, us, cedula, correo, pass,confirmarpass;
    private Button btnreg,btncan;
    private RadioGroup radioGroupAdmin ;
    private daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        nombrec = (EditText)findViewById(R.id.firstNameEditText);
        us = (EditText)findViewById(R.id.UsuarioNameEditText);
        cedula = (EditText)findViewById(R.id.cedulaEditText);
        correo = (EditText)findViewById(R.id.EmailEditText);
        pass = (EditText)findViewById(R.id.passwordEditText);
        confirmarpass = (EditText)findViewById(R.id.confirmPasswordEditText);
        btnreg = (Button) findViewById(R.id.registerButton);
        btncan = (Button) findViewById(R.id.cancelarButton);
        radioGroupAdmin = (RadioGroup) findViewById(R.id.radioGroupAdmin);
        btnreg.setOnClickListener(this);
        btncan.setOnClickListener(this);
        dao = new daoUsuario(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerButton){
            Usuario u =new Usuario();
            u.setNombre(nombrec.getText().toString());
            u.setUsuario(us.getText().toString());
            u.setCedula(cedula.getText().toString());
            u.setEmail(correo.getText().toString());
            u.setPassword(pass.getText().toString());
            u.setConfirmpassword(confirmarpass.getText().toString());
            int selectedRadioButtonId = radioGroupAdmin.getCheckedRadioButtonId();
                if(selectedRadioButtonId == R.id.radioButtonAdmin) {
                    u.setIsAdmin(1);
                }else {
                    u.setIsAdmin(0);
                }

            if (!u.isNull()){
                Toast.makeText(this,"Error: campos vacios",Toast.LENGTH_LONG).show();

            }else if(dao.insertUsuario(u)) {
                Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                Intent i2= new Intent(RegistrarseActivity.this, MainActivity.class);
                startActivity(i2);
                finish();
            }else {
                Toast.makeText(this,"Usuario ya existe",Toast.LENGTH_LONG).show();
            }
        }else if (v.getId() == R.id.cancelarButton){
            Intent i = new Intent(RegistrarseActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}