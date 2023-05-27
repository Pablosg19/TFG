package es.pablosg.gestionobrasfcm.Activities.Materiales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.R;

public class ListaMaterialesAdapter extends RecyclerView.Adapter<MaterialesViewHolder> {

    private Context c;
    private ArrayList<Material> listaMateriales;
    private LayoutInflater mInflater;

    public ListaMaterialesAdapter(Context c, ArrayList<Material> listaMateriales){
        this.c = c;
        this.listaMateriales = listaMateriales;
        mInflater = LayoutInflater.from(c);
    }
    public ListaMaterialesAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }
    public ArrayList<Material> getListaMateriales() { return listaMateriales; }
    public void setListaMateriales(ArrayList<Material> listaMateriales) { this.listaMateriales = listaMateriales; }
    public LayoutInflater getmInflater() { return mInflater; }
    public void setmInflater(LayoutInflater mInflater) { this.mInflater = mInflater; }

    @NonNull
    @Override
    public MaterialesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_material, parent, false);
        return new MaterialesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialesViewHolder holder, int position) {
        if (listaMateriales != null){
            Material material_actual = listaMateriales.get(position);
            holder.txt_rv_material.setText(material_actual.getMATERIAL());
            holder.txt_rv_unidadMedida.setText("Unidad de medida: "+material_actual.getUNIDAD_MEDIDA() + " (" +material_actual.getABREVIATURA_UNIDAD_MEDIDA() + ")");
        }
    }

    @Override
    public int getItemCount() {
        if (listaMateriales != null){
            return listaMateriales.size();
        }
        else{
            return 0;
        }
    }
}
