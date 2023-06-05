package es.pablosg.gestionobrasfcm.Activities.Obras.GestionMaterial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.R;

public class ListaGestionMaterialesAdapter extends RecyclerView.Adapter<GestionMaterialesViewHolder> {

    private Context c;
    private ArrayList<GestionMaterial> listaGestionMateriales;
    private LayoutInflater mInflater;

    public ListaGestionMaterialesAdapter(Context c, ArrayList<GestionMaterial> listaGestionMateriales){
        this.c = c;
        this.listaGestionMateriales = listaGestionMateriales;
        mInflater = LayoutInflater.from(c);
    }
    public ListaGestionMaterialesAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }
    public ArrayList<GestionMaterial> getListaGestionMateriales() { return listaGestionMateriales; }
    public void setListaGestionMateriales(ArrayList<GestionMaterial> listaGestionMateriales) { this.listaGestionMateriales = listaGestionMateriales; }
    public LayoutInflater getmInflater() { return mInflater; }
    public void setmInflater(LayoutInflater mInflater) { this.mInflater = mInflater; }

    @NonNull
    @Override
    public GestionMaterialesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_gestion_materiales, parent, false);
        return new GestionMaterialesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GestionMaterialesViewHolder holder, int position) {
        if (listaGestionMateriales != null){
            GestionMaterial gestionMaterial = listaGestionMateriales.get(position);
            holder.txt_rv_material.setText(gestionMaterial.getMATERIAL() + "   " + ObrasActivity.format.format(gestionMaterial.getCANTIDAD()) + " " + gestionMaterial.getUNIDAD_MEDIDA());
        }
    }

    @Override
    public int getItemCount() {
        if (listaGestionMateriales != null){
            return listaGestionMateriales.size();
        }
        else{
            return 0;
        }
    }
}
