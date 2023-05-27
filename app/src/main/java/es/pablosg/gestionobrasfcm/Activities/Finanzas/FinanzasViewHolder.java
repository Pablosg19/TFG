package es.pablosg.gestionobrasfcm.Activities.Finanzas;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Finanzas.MovimientosFinanzas.MovimientosFinanzasObraActivity;
import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.R;

public class FinanzasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public static final String EXTRA_OBJETO_FINANZAS = "espablosggestionobrasfcmobjetofinanzas";

    public TextView txt_rv_finanzasObra;
    public TextView txt_rv_beneficio;
    ListaFinanzasAdapter loAdapter;

    public FinanzasViewHolder(@NonNull View itemView, ListaFinanzasAdapter loAdapter){
        super(itemView);
        txt_rv_finanzasObra = (TextView) itemView.findViewById(R.id.txt_rv_finanzaObra);
        txt_rv_beneficio =(TextView) itemView.findViewById(R.id.txt_rv_beneficio);

        this.loAdapter = loAdapter;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        ArrayList<FinanzaObra> finanzas = this.loAdapter.getListaFinanzas();
        FinanzaObra fo = finanzas.get(position);
        Intent intent = new Intent(loAdapter.getC(), MovimientosFinanzasObraActivity.class);
        intent.putExtra(EXTRA_OBJETO_FINANZAS, fo);
        loAdapter.getC().startActivity(intent);
    }
}
