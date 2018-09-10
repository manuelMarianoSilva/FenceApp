package com.app.yonoc.fence.View.Asalto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.yonoc.fence.R;

/**
 * Created by yonoc on 9/5/2018.
 */

public class RecyclerPedanaAdapter extends RecyclerView.Adapter {

    private int[] idPedana = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42};
    private GuardadorDeGolpe guardadorDeGolpe;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.celda_marca_pedana, parent, false);
        CeldaPedana celdaPedana = new CeldaPedana(view, guardadorDeGolpe);
        return celdaPedana;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int posicion = idPedana[position];
        CeldaPedana celdaPedana = (CeldaPedana) holder;
        celdaPedana.cargarPosicion(posicion);
    }

    @Override
    public int getItemCount() {
        return idPedana.length;
    }

    public void setGuardadorDeGolpe(GuardadorDeGolpe guardadorDeGolpe) {
        this.guardadorDeGolpe = guardadorDeGolpe;
    }

    public interface GuardadorDeGolpe{
        void guardarGolpeEnCelda(Integer posicion);
    }

    /***********************************VIEWHOLDER************************************************/

    private class CeldaPedana extends RecyclerView.ViewHolder {

        private Integer posicion;

        public CeldaPedana(View itemView, final GuardadorDeGolpe guardadorDeGolpe) {
            super(itemView);


            ImageView marcaCelda = itemView.findViewById(R.id.celdaMarcaPedana);
            
            marcaCelda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guardadorDeGolpe.guardarGolpeEnCelda(posicion);
                }
            });
        }

        public void cargarPosicion(Integer n){
            posicion = n;
        }
    }


}
