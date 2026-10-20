package Modelo;

import java.time.LocalDate;

public class RutaViaje {
    
    private int idRuta;
    private String origen;
    private String destino;
    private LocalDate viaje;

    public RutaViaje(int idRuta, String origen, String destino, LocalDate viaje) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.viaje = viaje;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getVieje() {
        return viaje;
    }

    public void setVieje(LocalDate vieje) {
        this.viaje = vieje;
    }
    
}
