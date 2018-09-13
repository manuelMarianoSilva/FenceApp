package com.app.yonoc.fence.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejecutarSilentLoginDeGoogle();

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
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> pendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if (pendingResult.isDone()){
            GoogleSignInResult result = pendingResult.get();
            handleSignInResult(result);

        } else {
            pendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            Toast.makeText(this, account.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
            //goToLoginActivity();
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
}