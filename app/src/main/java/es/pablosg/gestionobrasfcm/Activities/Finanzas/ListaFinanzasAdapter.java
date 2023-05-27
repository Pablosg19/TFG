package es.pablosg.gestionobrasfcm.Activities.Finanzas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.R;

public class ListaFinanzasAdapter extends RecyclerView.Adapter<FinanzasViewHolder>{

    private Context c;
    private ArrayList<FinanzaObra> listaFinanzas;
    private LayoutInflater mInflater;

    public ListaFinanzasAdapter(Context c, ArrayList<FinanzaObra> listaFinanzas){
        this.c = c;
        this.listaFinanzas = listaFinanzas;
        mInflater = LayoutInflater.from(c);
    }
    public ListaFinanzasAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }
    public ArrayList<FinanzaObra> getListaFinanzas() { return listaFinanzas; }
    public void setListaFinanzas(ArrayList<FinanzaObra> listaFinanzas) { this.listaFinanzas = listaFinanzas; }
    public LayoutInflater getmInflater() { return mInflater; }
    public void setmInflater(LayoutInflater mInflater) { this.mInflater = mInflater; }

    @NonNull
    @Override
    public FinanzasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_finanza, parent, false);
        return new FinanzasViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FinanzasViewHolder holder, int position) {
        if (listaFinanzas != null){
            FinanzaObra finanza_Actual = listaFinanzas.get(position);
            holder.txt_rv_finanzasObra.setText(finanza_Actual.getOBRA());
            holder.txt_rv_beneficio.setText("Beneficio: "+ ObrasActivity.format.format(finanza_Actual.getINGRESOS() - finanza_Actual.getGASTOS()) + "€");
        }
    }

    @Override
    public int getItemCount() {
        if (listaFinanzas != null){
            return listaFinanzas.size();
        }
        else{
            return 0;
        }
    }
}
