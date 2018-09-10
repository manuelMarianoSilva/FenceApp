package com.app.yonoc.fence.View.Asalto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.yonoc.fence.Utils.ConstantContainer;
import com.app.yonoc.fence.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsaltoFragment extends Fragment implements RecyclerPedanaAdapter.GuardadorDeGolpe{

    private ImageView verde01, verde02, verde03, verde04, verde05, rojo01, rojo02, rojo03, rojo04, rojo05, amaVerde06, amaVerde07, amaVerde08, amaRojo06, amaRojo07, amaRojo08;
    private RecyclerView grillaPedana;

    public AsaltoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_asalto, container, false);

        crearReferencias(view);
        ponerListeners();
        grillaPedana = view.findViewById(R.id.recyclerPedana);


        return view;
    }

    private void crearReferencias(View view) {

        verde01 = view.findViewById(R.id.botonVerde01);
        verde02 = view.findViewById(R.id.botonVerde02);
        verde03 = view.findViewById(R.id.botonVerde03);
        verde04 = view.findViewById(R.id.botonVerde04);
        verde05 = view.findViewById(R.id.botonVerde05);

        amaVerde06 = view.findViewById(R.id.botonAmarilloVerde06);
        amaVerde07 = view.findViewById(R.id.botonAmarilloVerde07);
        amaVerde08 = view.findViewById(R.id.botonAmarilloVerde08);

        rojo01 = view.findViewById(R.id.botonRojo01);
        rojo02 = view.findViewById(R.id.botonRojo02);
        rojo03 = view.findViewById(R.id.botonRojo03);
        rojo04 = view.findViewById(R.id.botonRojo04);
        rojo05 = view.findViewById(R.id.botonRojo05);

        amaRojo06 = view.findViewById(R.id.botonAmarilloRojo06);
        amaRojo07 = view.findViewById(R.id.botonAmarilloRojo07);
        amaRojo08 = view.findViewById(R.id.botonAmarilloRojo08);
    }

    private void ponerListeners() {

        verde01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.VERDE_01);
            }
        });

        verde02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.VERDE_02);
            }
        });

        verde03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.VERDE_03);
            }
        });

        verde04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.VERDE_04);
            }
        });

        verde05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.VERDE_05);
            }
        });


        amaVerde06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.AMARILLO_VERDE_06);
            }
        });

        amaVerde07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.AMARILLO_VERDE_07);
            }
        });

        amaVerde08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.AMARILLO_VERDE_08);
            }
        });

        rojo01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.ROJO_01);
            }
        });

        rojo02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.ROJO_02);
            }
        });

        rojo03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.ROJO_03);
            }
        });

        rojo04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.ROJO_04);
            }
        });

        rojo05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.ROJO_05);
            }
        });

        amaRojo06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.AMARILLO_ROJO_06);
            }
        });

        amaRojo07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.AMARILLO_ROJO_07);
            }
        });

        amaRojo08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearGolpe(ConstantContainer.AMARILLO_ROJO_08);
            }
        });



    }

    private void crearGolpe(String idBoton) {

        Toast.makeText(getContext(), idBoton, Toast.LENGTH_SHORT).show();
        crearGrilla();

    }

    private void crearGrilla() {

        RecyclerPedanaAdapter adapter = new RecyclerPedanaAdapter();
        adapter.setGuardadorDeGolpe(this);
        grillaPedana.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 14);
        grillaPedana.setLayoutManager(manager);
    }

    @Override
    public void guardarGolpeEnCelda(Integer posicion) {
        Toast.makeText(getContext(), posicion.toString(), Toast.LENGTH_SHORT).show();
        grillaPedana.setAdapter(null);
    }
}
