package Modelo;

public class Colectivo {
    
    private int idColectivo;
    private String matricula;
    private String marca;
    private String modelo;
    private int capacidad;
    private String chofer;
    private boolean estado;

    public Colectivo(int idColectivo, String matricula, String marca, String modelo, int capacidad, String chofer, boolean estado) {
        this.idColectivo = idColectivo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.chofer = chofer;
        this.estado = estado;
    }
    
}
