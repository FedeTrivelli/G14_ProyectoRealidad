package Persistencia;

import java.sql.Connection;

public class pasajeroData {

    private Connection con = null;

    public pasajeroData() {
        con = Conexion.getConexion();

    }

}
