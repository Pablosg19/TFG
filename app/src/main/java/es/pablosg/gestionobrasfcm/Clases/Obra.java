package es.pablosg.gestionobrasfcm.Clases;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Obra implements Parcelable {

    private int ID_OBRA;
    private String OBRA;
    private String DIRECCION;
    private String LOCALIZACION;
    private double PRECIO_TERRENO;
    private boolean TERMINAR;
    private boolean VENDIDA;

    public Obra(int ID_OBRA, String OBRA, String DIRECCION, String LOCALIZACION, double PRECIO_TERRENO, boolean TERMINAR, boolean VENDIDA) {
        this.ID_OBRA = ID_OBRA;
        this.OBRA = OBRA;
        this.DIRECCION = DIRECCION;
        this.LOCALIZACION = LOCALIZACION;
        this.PRECIO_TERRENO = PRECIO_TERRENO;
        this.TERMINAR = TERMINAR;
        this.VENDIDA = VENDIDA;
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
    public String getDIRECCION() {
        return DIRECCION;
    }
    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }
    public String getLOCALIZACION() {
        return LOCALIZACION;
    }
    public void setLOCALIZACION(String LOCALIZACION) {
        this.LOCALIZACION = LOCALIZACION;
    }
    public boolean isTERMINAR() { return TERMINAR; }
    public void setTERMINAR(boolean TERMINAR) { this.TERMINAR = TERMINAR; }
    public double getPRECIO_TERRENO() { return PRECIO_TERRENO; }
    public void setPRECIO_TERRENO(double PRECIO_TERRENO) { this.PRECIO_TERRENO = PRECIO_TERRENO; }
    public boolean isVENDIDA() { return VENDIDA; }
    public void setVENDIDA(boolean VENDIDA) { this.VENDIDA = VENDIDA; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obra)) return false;
        Obra obra = (Obra) o;
        return ID_OBRA == obra.ID_OBRA;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_OBRA);
    }

    protected Obra(Parcel in) {
        ID_OBRA = in.readInt();
        OBRA = in.readString();
        DIRECCION = in.readString();
        LOCALIZACION = in.readString();
        PRECIO_TERRENO = in.readDouble();
        TERMINAR = in.readBoolean();
        VENDIDA = in.readBoolean();
    }

    public static final Creator<Obra> CREATOR = new Creator<Obra>() {
        @Override
        public Obra createFromParcel(Parcel in) {
            return new Obra(in);
        }
        @Override
        public Obra[] newArray(int size) {
            return new Obra[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ID_OBRA);
        dest.writeString(OBRA);
        dest.writeString(DIRECCION);
        dest.writeString(LOCALIZACION);
        dest.writeDouble(PRECIO_TERRENO);
        dest.writeBoolean(TERMINAR);
        dest.writeBoolean(VENDIDA);
    }
}
