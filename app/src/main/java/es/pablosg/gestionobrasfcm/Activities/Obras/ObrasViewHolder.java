package es.pablosg.gestionobrasfcm.Activities.Obras;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Bitmap.ImagenesBlobBitmap;
import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.R;

public class ObrasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public static final String EXTRA_OBJETO_OBRA = "espablosggestionobrasfcmobjetoobra";

    public TextView txt_rv_obra;
    public TextView txt_rv_direccion;
    public TextView txt_rv_localizacion;
    ListaObrasAdapter loAdapter;

    public ObrasViewHolder(@NonNull View itemView, ListaObrasAdapter loAdapter){
        super(itemView);
        txt_rv_obra = (TextView) itemView.findViewById(R.id.txt_rv_obra);
        txt_rv_direccion =(TextView) itemView.findViewById(R.id.txt_rv_direccion);
        txt_rv_localizacion = (TextView) itemView.findViewById(R.id.txt_rv_localizacion);

        this.loAdapter = loAdapter;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        ArrayList<Obra> obras = this.loAdapter.getListaObras();
        Obra o = obras.get(position);
        Intent intent = new Intent(loAdapter.getC(), DetallesObrasActivity.class);
        intent.putExtra(EXTRA_OBJETO_OBRA, o);
        loAdapter.getC().startActivity(intent);
    }
}
