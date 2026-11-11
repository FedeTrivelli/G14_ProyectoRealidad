package Persistencia;

import Modelo.Asiento;
import Modelo.Pasaje;
import Modelo.Pasajero;
import Modelo.RutaViaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PasajeData {

    private Connection con = null;

    public PasajeData() {
        con = Conexion.getConexion();
    }

    public boolean generarPasaje(Pasaje pasaje, Pasajero pasajero, Asiento asiento, RutaViaje rutaViaje) {
        String sql = "INSERT INTO pasajes (idPasajero, idLugar, idRuta, fechaVenta, horaVenta, precio, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pasajero.getIdPasajero());
            ps.setInt(2, asiento.getIdLugar());
            ps.setInt(3, rutaViaje.getIdRuta());
            ps.setDate(4, java.sql.Date.valueOf(pasaje.getPasajeFecha()));
            ps.setTime(5, java.sql.Time.valueOf(pasaje.getPasajeHora()));
            ps.setInt(6, pasaje.getPrecio());
            ps.setBoolean(7, pasaje.isEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pasaje.setIdPasaje(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Pasaje registrado con éxito.");
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla pasaje: " + e.getMessage());
        }
        return false;
    }

    public List<Pasaje> listarPasajes() {
        List<Pasaje> pasajes = new ArrayList<>();
        String sql = "SELECT idPasaje, idPasajero, idLugar, idRuta, fechaVenta, horaVenta, precio, estado FROM pasajes";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pasaje r = new Pasaje();
                r.setIdPasaje(rs.getInt("idPasaje"));
                r.setPasajeFecha(rs.getDate("fechaVenta").toLocalDate());
                r.setPasajeHora(rs.getTime("horaVenta").toLocalTime());
                r.setPrecio(rs.getInt("precio"));
                r.setEstado(rs.getBoolean("estado"));

                Pasajero c = new Pasajero();
                c.setIdPasajero(rs.getInt("idPasajero"));
                r.setPasajero(c);

                Asiento a = new Asiento();
                a.setIdLugar(rs.getInt("idLugar"));
                r.setLugar(a);

                RutaViaje ruta = new RutaViaje();
                ruta.setIdRuta(rs.getInt("idRuta"));
                r.setRutaViaje(ruta);

                pasajes.add(r);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar pasajes: " + ex.getMessage());
        }
        return pasajes;
    }
}
