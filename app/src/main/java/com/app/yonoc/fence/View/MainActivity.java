package com.app.yonoc.fence.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yonoc.fence.R;
import com.app.yonoc.fence.View.Asalto.AsaltoActivity;
import com.app.yonoc.fence.View.Login.LoginActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkFirebaseUserStatus();
        ejecutarSilentLoginDeGoogle();
        fillCell();
    }


    /*****************************Chequea el estado de usuario de Firebase*************************/

    private void checkFirebaseUserStatus() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    /***************************Métodos del Silent Login de Google*********************************/

    private GoogleApiClient googleApiClient;

    private void ejecutarSilentLoginDeGoogle() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        if (AccessToken.getCurrentAccessToken() != null){
            Toast.makeText(this, "Loggeado con Facebook", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    /*********************************Botones de la pantalla principal*****************************/

    public void logout(View view) {

        if (AccessToken.getCurrentAccessToken() == null){

            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if (status.isSuccess()){
                        //goToLoginActivity();
                        Toast.makeText(MainActivity.this, "Desloggeado papá!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No se pudo salir de la cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {

            LoginManager.getInstance().logOut();

            if (AccessToken.getCurrentAccessToken() == null){
                Toast.makeText(this, "Desloggeado de Facebook", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se pudo cerrar sesión de facebook", Toast.LENGTH_SHORT).show();
            }
        }

        FirebaseAuth.getInstance().signOut();

    }

    public void irAsalto(View view) {
        Intent intent = new Intent(this, AsaltoActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**********************************Utils*******************************************************/

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void fillCell() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){

            TextView nombre = findViewById(R.id.nombreEnMainActivity);
            TextView mail = findViewById(R.id.maileEnMainActivity);
            TextView url = findViewById(R.id.urlEnMainActivity);

            nombre.setText(user.getDisplayName());
            mail.setText(user.getEmail());
            url.setText(user.getPhotoUrl().toString());
        }
    }
}