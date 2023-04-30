package es.pablosg.gestionobrasfcm.Clases;

import java.io.Serializable;
import java.util.Objects;

public class FinanzaObra implements Serializable {

    private int ID_OBRA;
    private String OBRA;
    private Double GASTOS;
    private Double INGRESOS;

    public FinanzaObra(int ID_OBRA, String OBRA, Double GASTOS, Double INGRESOS) {
        this.ID_OBRA = ID_OBRA;
        this.OBRA = OBRA;
        this.GASTOS = GASTOS;
        this.INGRESOS = INGRESOS;
    }

    public int getID_OBRA() { return ID_OBRA; }
    public void setID_OBRA(int ID_OBRA) { this.ID_OBRA = ID_OBRA; }
    public Double getGASTOS() { return GASTOS; }
    public void setGASTOS(Double GASTOS) { this.GASTOS = GASTOS; }
    public Double getINGRESOS() { return INGRESOS; }
    public void setINGRESOS(Double INGRESOS) { this.INGRESOS = INGRESOS; }
    public String getOBRA() { return OBRA; }
    public void setOBRA(String OBRA) { this.OBRA = OBRA; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinanzaObra)) return false;
        FinanzaObra that = (FinanzaObra) o;
        return ID_OBRA == that.ID_OBRA;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_OBRA);
    }
}
