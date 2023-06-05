package es.pablosg.gestionobrasfcm.Activities.Obras.GestionMaterial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.pablosg.gestionobrasfcm.R;

public class GestionMaterialesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_rv_material;
    public TextView txt_rv_cantidad;
    ListaGestionMaterialesAdapter loAdapter;

    public GestionMaterialesViewHolder(@NonNull View itemView, ListaGestionMaterialesAdapter loAdapter){
        super(itemView);
        txt_rv_material = (TextView) itemView.findViewById(R.id.txt_gestionMateriales_material);
        this.loAdapter = loAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
