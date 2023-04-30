package es.pablosg.gestionobrasfcm.Clases;

import java.io.Serializable;
import java.util.Objects;

public class GestionMaterial implements Serializable {

    private int ID_GESTION_MATERIALES;
    private int ID_OBRA;
    private String OBRA;
    private int ID_PROVEEDOR;
    private String PROVEEDOR;
    private int ID_MATERIAL;
    private String MATERIAL;
    private Double PRECIO;
    private Double CANTIDAD;

    public GestionMaterial(int ID_GESTION_MATERIALES, int ID_OBRA, int ID_PROVEEDOR, int ID_MATERIAL, Double PRECIO, Double CANTIDAD) {
        this.ID_GESTION_MATERIALES = ID_GESTION_MATERIALES;
        this.ID_OBRA = ID_OBRA;
        this.ID_PROVEEDOR = ID_PROVEEDOR;
        this.ID_MATERIAL = ID_MATERIAL;
        this.PRECIO = PRECIO;
        this.CANTIDAD = CANTIDAD;
    }

    public GestionMaterial(int ID_GESTION_MATERIALES, String OBRA, String PROVEEDOR, String MATERIAL, Double PRECIO, Double CANTIDAD) {
        this.ID_GESTION_MATERIALES = ID_GESTION_MATERIALES;
        this.OBRA = OBRA;
        this.PROVEEDOR = PROVEEDOR;
        this.MATERIAL = MATERIAL;
        this.PRECIO = PRECIO;
        this.CANTIDAD = CANTIDAD;
    }

    public int getID_GESTION_MATERIALES() {
        return ID_GESTION_MATERIALES;
    }
    public void setID_GESTION_MATERIALES(int ID_GESTION_MATERIALES) {
        this.ID_GESTION_MATERIALES = ID_GESTION_MATERIALES;
    }
    public int getID_OBRA() {
        return ID_OBRA;
    }
    public void setID_OBRA(int ID_OBRA) {
        this.ID_OBRA = ID_OBRA;
    }
    public int getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }
    public void setID_PROVEEDOR(int ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }
    public int getID_MATERIAL() {
        return ID_MATERIAL;
    }
    public void setID_MATERIAL(int ID_MATERIAL) {
        this.ID_MATERIAL = ID_MATERIAL;
    }
    public Double getPRECIO() {
        return PRECIO;
    }
    public void setPRECIO(Double PRECIO) {
        this.PRECIO = PRECIO;
    }
    public Double getCANTIDAD() {
        return CANTIDAD;
    }
    public void setCANTIDAD(Double CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }
    public String getOBRA() {
        return OBRA;
    }
    public void setOBRA(String OBRA) {
        this.OBRA = OBRA;
    }
    public String getPROVEEDOR() {
        return PROVEEDOR;
    }
    public void setPROVEEDOR(String PROVEEDOR) {
        this.PROVEEDOR = PROVEEDOR;
    }
    public String getMATERIAL() {
        return MATERIAL;
    }
    public void setMATERIAL(String MATERIAL) {
        this.MATERIAL = MATERIAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GestionMaterial)) return false;
        GestionMaterial that = (GestionMaterial) o;
        return ID_GESTION_MATERIALES == that.ID_GESTION_MATERIALES;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_GESTION_MATERIALES);
    }
}
