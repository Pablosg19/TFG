package es.pablosg.gestionobrasfcm.Clases;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Serializable {

    private int ID_MATERIAL;
    private String MATERIAL;
    private String UNIDAD_MEDIDA;
    private String ABREVIATURA_UNIDAD_MEDIDA;

    public Material(int ID_MATERIAL, String MATERIAL, String UNIDAD_MEDIDA, String ABREVIATURA_UNIDAD_MEDIDA) {
        this.ID_MATERIAL = ID_MATERIAL;
        this.MATERIAL = MATERIAL;
        this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
        this.ABREVIATURA_UNIDAD_MEDIDA = ABREVIATURA_UNIDAD_MEDIDA;
    }

    public int getID_MATERIAL() {
        return ID_MATERIAL;
    }
    public void setID_MATERIAL(int ID_MATERIAL) {
        this.ID_MATERIAL = ID_MATERIAL;
    }
    public String getMATERIAL() {
        return MATERIAL;
    }
    public void setMATERIAL(String MATERIAL) {
        this.MATERIAL = MATERIAL;
    }
    public String getUNIDAD_MEDIDA() {
        return UNIDAD_MEDIDA;
    }
    public void setUNIDAD_MEDIDA(String UNIDAD_MEDIDA) {
        this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
    }
    public String getABREVIATURA_UNIDAD_MEDIDA() {
        return ABREVIATURA_UNIDAD_MEDIDA;
    }
    public void setABREVIATURA_UNIDAD_MEDIDA(String ABREVIATURA_UNIDAD_MEDIDA) { this.ABREVIATURA_UNIDAD_MEDIDA = ABREVIATURA_UNIDAD_MEDIDA; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        Material material = (Material) o;
        return ID_MATERIAL == material.ID_MATERIAL;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_MATERIAL);
    }
}
