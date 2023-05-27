package es.pablosg.gestionobrasfcm.Clases;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Parcelable {

    private int ID_MATERIAL;
    private String MATERIAL;
    private String UNIDAD_MEDIDA;
    private String ABREVIATURA_UNIDAD_MEDIDA;
    private String FAMILIA;

    public Material(int ID_MATERIAL, String MATERIAL, String UNIDAD_MEDIDA, String ABREVIATURA_UNIDAD_MEDIDA, String FAMILIA) {
        this.ID_MATERIAL = ID_MATERIAL;
        this.MATERIAL = MATERIAL;
        this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
        this.ABREVIATURA_UNIDAD_MEDIDA = ABREVIATURA_UNIDAD_MEDIDA;
        this.FAMILIA = FAMILIA;
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
    public String getFAMILIA() { return FAMILIA; }
    public void setFAMILIA(String FAMILIA) { this.FAMILIA = FAMILIA; }

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

    protected Material(Parcel in) {
        ID_MATERIAL = in.readInt();
        MATERIAL = in.readString();
        UNIDAD_MEDIDA = in.readString();
        ABREVIATURA_UNIDAD_MEDIDA = in.readString();
        FAMILIA = in.readString();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }
        @Override
        public Material[] newArray(int size) { return new Material[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ID_MATERIAL);
        dest.writeString(MATERIAL);
        dest.writeString(UNIDAD_MEDIDA);
        dest.writeString(ABREVIATURA_UNIDAD_MEDIDA);
        dest.writeString(FAMILIA);
    }
}
