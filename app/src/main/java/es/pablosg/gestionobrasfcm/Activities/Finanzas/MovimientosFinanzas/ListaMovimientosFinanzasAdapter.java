package es.pablosg.gestionobrasfcm.Activities.Finanzas.MovimientosFinanzas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.R;

public class ListaMovimientosFinanzasAdapter extends RecyclerView.Adapter<MovimientosFinanzasViewHolder>{

    private Context c;
    private ArrayList<MovimientoFinanza> listaMovimientos;
    private LayoutInflater mInflater;

    public ListaMovimientosFinanzasAdapter(Context c, ArrayList<MovimientoFinanza> listaMovimientos){
        this.c = c;
        this.listaMovimientos = listaMovimientos;
        mInflater = LayoutInflater.from(c);
    }
    public ListaMovimientosFinanzasAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }
    public ArrayList<MovimientoFinanza> getListaMovimientos() { return listaMovimientos; }
    public void setListaMovimientos(ArrayList<MovimientoFinanza> listaMovimientos) { this.listaMovimientos = listaMovimientos; }
    public LayoutInflater getmInflater() { return mInflater; }
    public void setmInflater(LayoutInflater mInflater) { this.mInflater = mInflater; }

    @NonNull
    @Override
    public MovimientosFinanzasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_movimiento, parent, false);
        return new MovimientosFinanzasViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientosFinanzasViewHolder holder, int position) {
        if (listaMovimientos != null){
            MovimientoFinanza movimiento_Actual = listaMovimientos.get(position);
            holder.txt_rv_dinero.setText(ObrasActivity.format.format(movimiento_Actual.getDINERO()) + "€");
            if(movimiento_Actual.getMOVIMIENTO() == 0){
                holder.txt_rv_movimiento.setText("Ingreso");
            }
            else{
                holder.txt_rv_movimiento.setText("Gasto");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (listaMovimientos != null){
            return listaMovimientos.size();
        }
        else{
            return 0;
        }
    }
}
