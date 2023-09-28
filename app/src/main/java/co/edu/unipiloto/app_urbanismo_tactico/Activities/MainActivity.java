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

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText user, pass;
    private Button btniniciar, btnregistrar;
    private daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.UserText);
        pass = (EditText) findViewById(R.id.passwordText);
        btniniciar = (Button) findViewById(R.id.loginButton);
        btnregistrar = (Button) findViewById(R.id.registroButton);
        btniniciar.setOnClickListener(this);
        btnregistrar.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
       /* switch (v.getId()){
            case R.id.loginButton:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"error: campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this,"datos correctos",Toast.LENGTH_LONG).show();
                    Intent i2= new Intent(MainActivity.this,InicioActivity.class);
                    i2.putExtra("id",ux.getId());
                    startActivity(i2);
                    finish();
                }
                break;
            case R.id.registroButton:
                Intent i= new Intent(MainActivity.this,RegistrarseActivity.class);
                startActivity(i);
                break;
        }*/
        if (v.getId() == R.id.loginButton) {
            String u = user.getText().toString();
            String p = pass.getText().toString();
            if (u.equals("") && p.equals("")) {
                Toast.makeText(this, "error: campos vacios", Toast.LENGTH_LONG).show();
            } else if (dao.login(u, p) == 1) {
                Usuario ux = dao.getUsuario(u, p);
                Toast.makeText(this, "datos correctos", Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(MainActivity.this, InicioActivity.class);
                i2.putExtra("id", ux.getId());
                startActivity(i2);
                finish();
            }else{
                Toast.makeText(this, "usuario y contraseña incorrectos", Toast.LENGTH_LONG).show();
            }

        }else if (v.getId() == R.id.registroButton) {
            Intent i = new Intent(MainActivity.this, RegistrarseActivity.class);
            startActivity(i);
        }
    }
}