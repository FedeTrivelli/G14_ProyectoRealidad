package Persistencia;

import java.sql.Connection;

public class colectivoData {

    private Connection con = null;

    public colectivoData() {
        con = Conexion.getConexion();

    }

}
