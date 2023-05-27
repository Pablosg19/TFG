package es.pablosg.gestionobrasfcm.Activities.Finanzas.MovimientosFinanzas;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.pablosg.gestionobrasfcm.R;

public class MovimientosFinanzasViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_rv_dinero;
    public TextView txt_rv_movimiento;
    ListaMovimientosFinanzasAdapter loAdapter;

    public MovimientosFinanzasViewHolder(@NonNull View itemView, ListaMovimientosFinanzasAdapter loAdapter){
        super(itemView);
        txt_rv_dinero = (TextView) itemView.findViewById(R.id.txt_rv_dinero);
        txt_rv_movimiento = (TextView) itemView.findViewById(R.id.txt_rv_movimiento);

        this.loAdapter = loAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
