package es.pablosg.gestionobrasfcm.Clases;

import java.io.Serializable;
import java.util.Objects;

public class MovimientoFinanza implements Serializable {

    private int ID_MOVIMIENTO_FINANZA;
    private int MOVIMIENTO;
    private Double DINERO;
    private int ID_OBRA;
    private String OBRA;

    public MovimientoFinanza(int ID_MOVIMIENTO_FINANZA, int ID_OBRA, int MOVIMIENTO, Double DINERO) {
        this.ID_MOVIMIENTO_FINANZA = ID_MOVIMIENTO_FINANZA;
        this.MOVIMIENTO = MOVIMIENTO;
        this.DINERO = DINERO;
        this.ID_OBRA = ID_OBRA;
    }

    public MovimientoFinanza(int ID_MOVIMIENTO_FINANZA, String OBRA, int MOVIMIENTO, Double DINERO) {
        this.ID_MOVIMIENTO_FINANZA = ID_MOVIMIENTO_FINANZA;
        this.MOVIMIENTO = MOVIMIENTO;
        this.DINERO = DINERO;
        this.OBRA = OBRA;
    }

    public int getID_MOVIMIENTO_FINANZA() {
        return ID_MOVIMIENTO_FINANZA;
    }
    public void setID_MOVIMIENTO_FINANZA(int ID_MOVIMIENTO_FINANZA) {
        this.ID_MOVIMIENTO_FINANZA = ID_MOVIMIENTO_FINANZA;
    }
    public int getMOVIMIENTO() {
        return MOVIMIENTO;
    }
    public void setMOVIMIENTO(int MOVIMIENTO) {
        this.MOVIMIENTO = MOVIMIENTO;
    }
    public Double getDINERO() {
        return DINERO;
    }
    public void setDINERO(Double DINERO) {
        this.DINERO = DINERO;
    }
    public int getID_OBRA() {
        return ID_OBRA;
    }
    public void setID_OBRA(int ID_OBRA) {
        this.ID_OBRA = ID_OBRA;
    }
    public String getOBRA() {
        return OBRA;
    }
    public void setOBRA(String OBRA) {
        this.OBRA = OBRA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimientoFinanza)) return false;
        MovimientoFinanza that = (MovimientoFinanza) o;
        return ID_MOVIMIENTO_FINANZA == that.ID_MOVIMIENTO_FINANZA;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_MOVIMIENTO_FINANZA);
    }
}
