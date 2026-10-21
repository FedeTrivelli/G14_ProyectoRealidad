package Persistencia;

import Modelo.Colectivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class colectivoData {

    private Connection con = null;


    public colectivoData() {
        con = Conexion.getConexion();

    }
    
    public void guardarColectivo(Colectivo colectivo) {
        String sql = "INSERT INTO `colectivo` (`matricula`, `marca`, `modelo`, `capacidad`, `chofer`, `estado`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, colectivo.getMatricula());
            ps.setString(2, colectivo.getMarca());
            ps.setString(3, colectivo.getModelo());
            ps.setInt(4, colectivo.getCapacidad());
            ps.setString(5, colectivo.getChofer());
            ps.setBoolean(6, colectivo.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                colectivo.setIdColectivo(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Colectivo añadido con exito.");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Colectivo" + e.getMessage());
        }
    }
    
    public void BorrarAlumno(String matricula) {
        String sql = "UPDATE colectivo SET estado = '0' WHERE colectivo.matricula = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Se eliminó ese colectivo!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese colectivo no existe.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex);
        }
    }
    
    public void actualizarColectivo(Colectivo colectivo) {
        String sql = "UPDATE colectivo SET matricula = ?, marca = ?, modelo = ?, capacidad = ?, chofer = ? , estado = ? WHERE idAlumno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, colectivo.getMatricula());
            ps.setString(2, colectivo.getMarca());
            ps.setString(3, colectivo.getModelo());
            ps.setInt(4, colectivo.getCapacidad());
            ps.setString(5, colectivo.getChofer());
            ps.setBoolean(6, colectivo.isEstado());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "El colectivo fue actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el colectivo a actualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Colectivo: " + e.getMessage());
        }
    }

}
