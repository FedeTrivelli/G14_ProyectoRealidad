package Vistas;

import Modelo.Asiento;
import Persistencia.AsientoData;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Asiento> asientos = new ArrayList<>();
        Asiento asiento = new Asiento(1, 200, true);
        AsientoData ad = new AsientoData();
        ad.guardarAsiento(asiento);

    }

}
