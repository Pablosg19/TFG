package es.pablosg.gestionobrasfcm.Clases;

import java.io.Serializable;
import java.util.Objects;

public class Proveedor implements Serializable {

    private int ID_PROVEEDOR;
    private String PROVEEDOR;

    public Proveedor(int ID_PROVEEDOR, String PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
        this.PROVEEDOR = PROVEEDOR;
    }

    public int getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }
    public void setID_PROVEEDOR(int ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }
    public String getPROVEEDOR() {
        return PROVEEDOR;
    }
    public void setPROVEEDOR(String PROVEEDOR) {
        this.PROVEEDOR = PROVEEDOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proveedor)) return false;
        Proveedor proveedor = (Proveedor) o;
        return ID_PROVEEDOR == proveedor.ID_PROVEEDOR;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_PROVEEDOR);
    }
}
