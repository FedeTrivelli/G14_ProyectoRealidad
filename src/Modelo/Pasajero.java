package Modelo;

public class Pasajero {
    
    private int idPasajero;
    private int dni;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
    private boolean estado;

    public Pasajero(int idPasajero, int dni, String nombre, String apellido, String correo, int telefono, boolean estado) {
        this.idPasajero = idPasajero;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }
   
}
