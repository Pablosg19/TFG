package es.pablosg.gestionobrasfcm.Activities.Materiales;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.R;

public class MaterialesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public static final String EXTRA_OBJETO_MATERIAL = "espablosggestionobrasfcmobjetomaterial";

    public TextView txt_rv_material;
    public TextView txt_rv_unidadMedida;
    public TextView txt_rv_abreviaturaUnidadMedida;
    ListaMaterialesAdapter loAdapter;

    public MaterialesViewHolder(@NonNull View itemView, ListaMaterialesAdapter loAdapter){
        super(itemView);
        txt_rv_material = (TextView) itemView.findViewById(R.id.txt_rv_material);
        txt_rv_unidadMedida =(TextView) itemView.findViewById(R.id.txt_rv_unidadMedida);
        txt_rv_abreviaturaUnidadMedida = (TextView) itemView.findViewById(R.id.txt_rvObras_direccion);

        this.loAdapter = loAdapter;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        ArrayList<Material> materiales = this.loAdapter.getListaMateriales();
        Material m = materiales.get(position);
        Intent intent = new Intent(loAdapter.getC(), DetallesMaterialesActivity.class);
        intent.putExtra(EXTRA_OBJETO_MATERIAL, m);
        loAdapter.getC().startActivity(intent);
    }
}
