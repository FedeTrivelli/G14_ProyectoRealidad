package Modelo;
import java.time.LocalDate;

public class Pasaje {
    
    private int idPasaje;
    private Pasajero pasajero;
    private String lugar;
    private String rutaViaje;
    private LocalDate pasajeHora;
    private int precio;
    private boolean estado;

    public Pasaje(int idPasaje, Pasajero pasajero, String lugar, String rutaViaje, LocalDate pasajeHora, int precio, boolean estado) {
        this.idPasaje = idPasaje;
        this.pasajero = pasajero;
        this.lugar = lugar;
        this.rutaViaje = rutaViaje;
        this.pasajeHora = pasajeHora;
        this.precio = precio;
        this.estado = estado;
    }

    public int getIdPasaje() {
        return idPasaje;
    }

    public void setIdPasaje(int idPasaje) {
        this.idPasaje = idPasaje;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getRutaViaje() {
        return rutaViaje;
    }

    public void setRutaViaje(String rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    public LocalDate getPasajeHora() {
        return pasajeHora;
    }

    public void setPasajeHora(LocalDate pasajeHora) {
        this.pasajeHora = pasajeHora;
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
