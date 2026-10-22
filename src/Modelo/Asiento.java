package Modelo;

public class Asiento {

    private int idLugar;
    private Colectivo colectivo;
    private int numeroAsiento;
    private boolean estado;

    public Asiento() {
    }

    public Asiento(int idLugar, Colectivo colectivo, int numeroAsiento, boolean estado) {
        this.idLugar = idLugar;
        this.colectivo = colectivo;
        this.numeroAsiento = numeroAsiento;
        this.estado = estado;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
