package es.pablosg.gestionobrasfcm.Activities.Obras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.R;

public class ListaObrasAdapter extends RecyclerView.Adapter<ObrasViewHolder> {

    private Context c;
    private ArrayList<Obra> listaObras;
    private LayoutInflater mInflater;

    public ListaObrasAdapter(Context c, ArrayList<Obra> listaObras){
        this.c = c;
        this.listaObras = listaObras;
        mInflater = LayoutInflater.from(c);
    }
    public ListaObrasAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }
    public ArrayList<Obra> getListaObras() { return listaObras; }
    public void setListaObras(ArrayList<Obra> listaObras) { this.listaObras = listaObras; }
    public LayoutInflater getmInflater() { return mInflater; }
    public void setmInflater(LayoutInflater mInflater) { this.mInflater = mInflater; }

    @NonNull
    @Override
    public ObrasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_obra, parent, false);
        return new ObrasViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ObrasViewHolder holder, int position) {
        if (listaObras != null){
            Obra obra_actual = listaObras.get(position);
            holder.txt_rv_obra.setText(obra_actual.getOBRA());
            holder.txt_rv_direccion.setText(obra_actual.getDIRECCION() + ", " + obra_actual.getLOCALIZACION());
        }
    }

    @Override
    public int getItemCount() {
        if (listaObras != null){
            return listaObras.size();
        }
        else{
            return 0;
        }
    }
}
