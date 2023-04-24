package es.pablosg.gestionobrasfcm.Clases;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private int ID_USER;
    private String USER;
    private String NOMBRE;
    private String CARGO;
    private String PASSWORD;

    public Usuario(int ID_USER, String USER, String NOMBRE, String CARGO, String PASSWORD) {
        this.ID_USER = ID_USER;
        this.USER = USER;
        this.NOMBRE = NOMBRE;
        this.CARGO = CARGO;
        this.PASSWORD = PASSWORD;
    }

    public int getID_USER() {
        return ID_USER;
    }
    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }
    public String getUSER() {
        return USER;
    }
    public void setUSER(String USER) {
        this.USER = USER;
    }
    public String getNOMBRE() {
        return NOMBRE;
    }
    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    public String getCARGO() {
        return CARGO;
    }
    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return ID_USER == usuario.ID_USER;
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID_USER);
    }
}
