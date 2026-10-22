package Modelo;

import java.time.LocalDate;

public class RutaViaje {

    private int idRuta;
    private String origen;
    private String destino;
    private LocalDate horaSalida;
    private LocalDate horaLlegada;
    private Colectivo colectivo;
    int precio;
    boolean estado;

    public RutaViaje() {
    }

    public RutaViaje(int idRuta, String origen, String destino, LocalDate horaSalida, LocalDate horaLlegada, Colectivo colectivo, int precio, boolean estado) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.colectivo = colectivo;
        this.precio = precio;
        this.estado = estado;
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

    public LocalDate getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDate horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalDate getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalDate horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
