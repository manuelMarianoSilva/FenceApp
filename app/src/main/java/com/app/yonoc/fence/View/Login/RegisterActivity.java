package com.app.yonoc.fence.View.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.yonoc.fence.R;
import com.app.yonoc.fence.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText userName, password1, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.mailUsuario);
        password1 = findViewById(R.id.contraseñaUsuario);
        password2 = findViewById(R.id.reingresarContraseñaUsuario);

        Button botonRegistrar = findViewById(R.id.buttonRegistrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarDatosParaCrearNuevaCuenta();
            }
        });
    }

    private void verificarDatosParaCrearNuevaCuenta() {
        String nombreDeUsuario = userName.getText().toString();
        String strPassword = password1.getText().toString();
        String passwordVerification = password2.getText().toString();

        if (nombreDeUsuario.isEmpty() || !isEmailValid(nombreDeUsuario)){
            notificarRegistroInvalido();
            return;
        }

        if (strPassword.isEmpty() || passwordVerification.isEmpty() || !strPassword.equals(passwordVerification)){
            notificarRegistroInvalido();
            return;
        }

        crearNuevaCuenta(nombreDeUsuario, strPassword);
    }

    private void crearNuevaCuenta(String nombreDeUsuario, String pass1) {

        mAuth.createUserWithEmailAndPassword(nombreDeUsuario, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //FirebaseUser user = mAuth.getCurrentUser();
                            goToMain();
                        } else {
                            Toast.makeText(RegisterActivity.this, "El registro falló.\nPor favor inténtelo nuevamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void notificarRegistroInvalido() {
        Toast.makeText(this, "Registro inválido.\nPor favor inténtelo nuevamente", Toast.LENGTH_SHORT).show();
    }

    private boolean isEmailValid (CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        //updateUI(user);
    }
}
