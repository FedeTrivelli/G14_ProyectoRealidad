package Persistencia;

import Modelo.Colectivo;
import Modelo.RutaViaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RutaData {

    private Connection con = null;

    public RutaData() {
        con = Conexion.getConexion();
    }

    public void guardarRuta(RutaViaje ruta) {
        String sql = "INSERT INTO rutaviaje (origen, destino, horaSalida, horaLlegada, idColectivo, precio, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, ruta.getOrigen());
            ps.setString(2, ruta.getDestino());
            ps.setTimestamp(3, Timestamp.valueOf(ruta.getHoraSalida()));
            ps.setTimestamp(4, Timestamp.valueOf(ruta.getHoraLlegada()));
            ps.setInt(5, ruta.getColectivo().getIdColectivo());
            ps.setInt(6, ruta.getPrecio());
            ps.setBoolean(7, ruta.isEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ruta.setIdRuta(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Ruta añadida con éxito.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla rutaviaje: " + e.getMessage());
        }
    }

    public void borrarRuta(int idRuta) {
        String sql = "DELETE FROM rutaviaje WHERE idRuta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Se eliminó la ruta.");
            } else {
                JOptionPane.showMessageDialog(null, "Esa ruta no existe.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex.getMessage());
        }
    }

    public void actualizarRuta(RutaViaje ruta) {
        String sql = "UPDATE rutaviaje SET origen = ?, destino = ?, horaSalida = ?, horaLlegada = ?, "
                + "idColectivo = ?, precio = ?, estado = ? WHERE idRuta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ruta.getOrigen());
            ps.setString(2, ruta.getDestino());
            ps.setTimestamp(3, Timestamp.valueOf(ruta.getHoraSalida()));
            ps.setTimestamp(4, Timestamp.valueOf(ruta.getHoraLlegada()));
            ps.setInt(5, ruta.getColectivo().getIdColectivo());
            ps.setInt(6, ruta.getPrecio());
            ps.setBoolean(7, ruta.isEstado());
            ps.setInt(8, ruta.getIdRuta());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "La ruta fue actualizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la ruta a actualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla rutaviaje: " + e.getMessage());
        }
    }

    public RutaViaje buscarRuta(int idRuta) {
        RutaViaje ruta = null;
        String sql = "SELECT * FROM rutaviaje WHERE idRuta = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ruta = new RutaViaje();
                    ruta.setIdRuta(rs.getInt("idRuta"));
                    ruta.setOrigen(rs.getString("origen"));
                    ruta.setDestino(rs.getString("destino"));
                    ruta.setHoraSalida(rs.getTimestamp("horaSalida").toLocalDateTime());
                    ruta.setHoraLlegada(rs.getTimestamp("horaLlegada").toLocalDateTime());

                    Colectivo colectivo = new Colectivo();
                    colectivo.setIdColectivo(rs.getInt("idColectivo"));
                    ruta.setColectivo(colectivo);

                    ruta.setPrecio(rs.getInt("precio"));
                    ruta.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex.getMessage());
        }
        return ruta;
    }

    public List<RutaViaje> listarRutas() {
        List<RutaViaje> rutas = new ArrayList<>();
        String sql = "SELECT r.idRuta, r.origen, r.destino, r.horaSalida, r.horaLlegada, "
                + "r.idColectivo, r.precio, r.estado, c.matricula "
                + "FROM rutaviaje r JOIN colectivo c ON r.idColectivo = c.idColectivo";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RutaViaje r = new RutaViaje();
                r.setIdRuta(rs.getInt("idRuta"));
                r.setOrigen(rs.getString("origen"));
                r.setDestino(rs.getString("destino"));
                r.setHoraSalida(rs.getTimestamp("horaSalida").toLocalDateTime());
                r.setHoraLlegada(rs.getTimestamp("horaLlegada").toLocalDateTime());

                Colectivo colectivo = new Colectivo();
                colectivo.setMatricula(rs.getString("matricula"));
                r.setColectivo(colectivo);

                r.setPrecio(rs.getInt("precio"));
                r.setEstado(rs.getBoolean("estado"));
                rutas.add(r);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar las rutas: " + ex.getMessage());
        }
        return rutas;
    }
}
