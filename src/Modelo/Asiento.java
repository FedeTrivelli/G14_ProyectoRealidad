package Modelo;

public class Asiento {
    
    private int idLugar;
    private int colectivo;
    private int numeroAsiento;
    private boolean estado;

    public Asiento(int idLugar, int colectivo, int numeroAsiento, boolean estado) {
        this.idLugar = idLugar;
        this.colectivo = colectivo;
        this.numeroAsiento = numeroAsiento;
        this.estado = estado;
    }
    
    
}
