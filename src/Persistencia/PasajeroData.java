package Persistencia;

import java.sql.Connection;
import Modelo.Pasajero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PasajeroData {

    private Connection con = null;

    public PasajeroData() {
        con = Conexion.getConexion();

    }

    public void guardarPasajero(Pasajero pasajero) {
        String sql = "INSERT INTO pasajero (`dni`, `nombre`, `apellido`, `correo`, `telefono`, `estado`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pasajero.getDni());
            ps.setString(2, paraColocarMayusculas(pasajero.getNombre()));
            ps.setString(3, paraColocarMayusculas(pasajero.getApellido()));
            ps.setString(4, pasajero.getCorreo().toLowerCase());
            ps.setString(5, pasajero.getTelefono());
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

    public void BorrarPasajero(int dni) {
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
        String sql = "UPDATE pasajero SET dni = ?, nombre = ?, apellido = ?, correo = ?, telefono = ? , estado = ? WHERE dni = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pasajero.getDni());
            ps.setString(2, paraColocarMayusculas(pasajero.getNombre()));
            ps.setString(3, paraColocarMayusculas(pasajero.getApellido()));
            ps.setString(4, pasajero.getCorreo());
            ps.setString(5, pasajero.getTelefono());
            ps.setBoolean(6, pasajero.isEstado());
            ps.setInt(7, pasajero.getDni());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "El pasajero fue actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el pasajero a actualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasajero: " + e.getMessage());
        }
    }

    public Pasajero buscarPasajero(int dni) {
        Pasajero pasajero = null;

        String sql = "SELECT * FROM pasajero WHERE pasajero.dni = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pasajero = new Pasajero();
                    pasajero.setIdPasajero(rs.getInt("idPasajero"));
                    pasajero.setDni(rs.getInt("dni"));
                    pasajero.setNombre(rs.getString("nombre"));
                    pasajero.setApellido(rs.getString("apellido"));
                    pasajero.setCorreo(rs.getString("correo"));
                    pasajero.setTelefono(rs.getString("telefono"));
                    pasajero.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex);
        }
        return pasajero;
    }

    public List<Pasajero> listarPasajeros() {
        List<Pasajero> pasajeros = new ArrayList<>();
        String sql = "SELECT idPasajero, dni, nombre, apellido, correo, telefono, estado FROM pasajero";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pasajero r = new Pasajero();
                r.setIdPasajero(rs.getInt("idPasajero"));
                r.setDni(rs.getInt("dni"));
                r.setNombre(rs.getString("nombre"));
                r.setApellido(rs.getString("apellido"));
                r.setCorreo(rs.getString("correo"));
                r.setTelefono(rs.getString("telefono"));
                r.setEstado(rs.getBoolean("estado"));
                pasajeros.add(r);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar pasajeros: " + ex.getMessage());
        }
        return pasajeros;
    }

    public boolean correoExiste(String correo) {
        String sql = "SELECT COUNT(*) FROM pasajero WHERE LOWER(correo) = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo.toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar correo: " + ex.getMessage());
            return true;
        }
        return false;
    }

    public boolean dniExiste(String dni) {
        String sql = "SELECT COUNT(*) FROM pasajero WHERE dni = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(dni));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar dni: " + ex.getMessage());
            return true;
        }
        return false;
    }

    public static String paraColocarMayusculas(String texto) {
        String[] palabras = texto.toLowerCase().trim().split("\\s+");
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < palabras.length; i++) {
            if (!palabras[i].isEmpty()) {
                resultado.append(Character.toUpperCase(palabras[i].charAt(0))).append(palabras[i].substring(1)).append(" ");
            }
        }
        return resultado.toString().trim();
    }
}
