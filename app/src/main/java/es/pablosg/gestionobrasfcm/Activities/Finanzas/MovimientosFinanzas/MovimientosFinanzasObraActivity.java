package es.pablosg.gestionobrasfcm.Activities.Finanzas.MovimientosFinanzas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Finanzas.FinanzasViewHolder;
import es.pablosg.gestionobrasfcm.Activities.Materiales.ListaMaterialesAdapter;
import es.pablosg.gestionobrasfcm.Activities.Materiales.MaterialesViewHolder;
import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Controladores.FinanzaObraCtrl;
import es.pablosg.gestionobrasfcm.Controladores.MovimientoFinanzaCtrl;
import es.pablosg.gestionobrasfcm.R;

public class MovimientosFinanzasObraActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView txt_user;
    private TextView txt_obra;
    private Spinner sp_ordenar;
    private RecyclerView rv_movimientosFinanzas;
    private ListaMovimientosFinanzasAdapter movimientosAdapter;
    private String ordenSeleccionado;

    private Intent intent;
    FinanzaObra fo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos_finanzas_obra);

        intent = getIntent();
        fo = (FinanzaObra) intent.getParcelableExtra(FinanzasViewHolder.EXTRA_OBJETO_FINANZAS);
        txt_obra = (TextView) findViewById(R.id.txt_movimientoFinanza_obra);
        txt_user = (TextView) findViewById(R.id.txt_movimientoFinanza_user);
        sp_ordenar = (Spinner) findViewById(R.id.sp_movimientoFinanza_ordenar);
        rv_movimientosFinanzas = (RecyclerView) findViewById(R.id.rv_movimientosFinanzas);

        txt_obra.setText(fo.getOBRA());

        String[] orden = {"Todo","Gastos", "Ingresos"};
        sp_ordenar.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.estilos_spinner,orden);
        sp_ordenar.setAdapter(adapter);

        movimientosAdapter = new ListaMovimientosFinanzasAdapter(this);
        ArrayList<MovimientoFinanza> movimientos = MovimientoFinanzaCtrl.getMovimientoFinanzaFiltro(fo.getOBRA());
        if(movimientos != null){
            movimientosAdapter.setListaMovimientos(movimientos);
        }
        rv_movimientosFinanzas.setAdapter(movimientosAdapter);
        rv_movimientosFinanzas.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        ordenSeleccionado = adapterView.getItemAtPosition(i).toString();

        ArrayList<MovimientoFinanza> movimientos = MovimientoFinanzaCtrl.getMovimientoFinanzaMostrar(ordenSeleccionado, fo.getOBRA());
        if(movimientos != null){
            movimientosAdapter.setListaMovimientos(movimientos);
        }
        rv_movimientosFinanzas.setAdapter(movimientosAdapter);
        rv_movimientosFinanzas.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        ordenSeleccionado ="Todo";
    }
}