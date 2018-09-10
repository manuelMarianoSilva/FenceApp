package com.app.yonoc.fence.View.Asalto;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.yonoc.fence.R;

public class AsaltoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asalto);

        mostrarFragmentDeAsalto();
    }

    private void mostrarFragmentDeAsalto() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedorDeFragmentAsalto, new AsaltoFragment()).commitAllowingStateLoss();
    }
}
