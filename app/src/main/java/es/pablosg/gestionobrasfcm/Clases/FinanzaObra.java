package es.pablosg.gestionobrasfcm.Clases;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class FinanzaObra implements Parcelable {

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

    protected FinanzaObra(Parcel in) {
        ID_OBRA = in.readInt();
        OBRA = in.readString();
        INGRESOS = in.readDouble();
        GASTOS = in.readDouble();
    }

    public static final Creator<FinanzaObra> CREATOR = new Creator<FinanzaObra>() {
        @Override
        public FinanzaObra createFromParcel(Parcel in) {
            return new FinanzaObra(in);
        }
        @Override
        public FinanzaObra[] newArray(int size) { return new FinanzaObra[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ID_OBRA);
        dest.writeString(OBRA);
        dest.writeDouble(INGRESOS);
        dest.writeDouble(GASTOS);
    }
}
