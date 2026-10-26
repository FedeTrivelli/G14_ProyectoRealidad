package Modelo;

public class Asiento {

    private int idLugar;
    private Colectivo IdColectivo;
    private int numeroAsiento;
    private boolean estado;

    public Asiento() {
    }

    public Asiento(int idLugar, Colectivo IdColectivo, int numeroAsiento, boolean estado) {
        this.idLugar = idLugar;
        this.IdColectivo = IdColectivo;
        this.numeroAsiento = numeroAsiento;
        this.estado = estado;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public Colectivo getIdColectivo() {
        return IdColectivo;
    }

    public void setIdColectivo(Colectivo IdColectivo) {
        this.IdColectivo = IdColectivo;
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