package Persistencia;

import java.sql.Connection;
import Modelo.Pasajero;
import java.sql.*;
import javax.swing.JOptionPane;

public class pasajeroData {

    private Connection con = null;

    public pasajeroData() {
        con = Conexion.getConexion();

    }
    
    public void guardarPasajero(Pasajero pasajero) {
        String sql = "INSERT INTO pasajero (`dni`, `nombre`, `apellido`, `correo`, `telefono`, `estado`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pasajero.getDni());
            ps.setString(2, pasajero.getNombre());
            ps.setString(3, pasajero.getApellido());
            ps.setString(4, pasajero.getCorreo());
            ps.setInt(5, pasajero.getTelefono());
            ps.setBoolean(6, pasajero.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pasajero.setIdPasajero(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Pasajero añadido con exito.");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasajero" + e.getMessage());
        }
    }
    
    public void BorrarAlumno(int dni) {
        String sql = "UPDATE pasajero SET estado = '0' WHERE pasajero.dni = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Se eliminó ese pasajero!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese pasajero no existe.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex);
        }
    }
    
    public void actualizarPasajero(Pasajero pasajero) {
        String sql = "UPDATE pasajero SET dni = ?, nombre = ?, apellido = ?, correo = ?, telefono = ? , estado = ? WHERE idAlumno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pasajero.getDni());
            ps.setString(2, pasajero.getNombre());
            ps.setString(3, pasajero.getApellido());
            ps.setString(4, pasajero.getCorreo());
            ps.setInt(5, pasajero.getTelefono());
            ps.setBoolean(6, pasajero.isEstado());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "El pasajero fue actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el pasajero a actualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasajero: " + e.getMessage());
        }
    }
}
