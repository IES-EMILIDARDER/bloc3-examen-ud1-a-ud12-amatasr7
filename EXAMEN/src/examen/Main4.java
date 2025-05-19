package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Main4 {

    public static void main(String[] args) {
        final String conexion = "C:\\temp\\mysql.con";
        GestorBBDD gestor = new GestorBBDD(conexion);

        try (Stream<String> lineas = Files.lines(Paths.get("C:\\temp\\vehicles.csv")); Connection conn = gestor.getConnectionFromFile()) {
            lineas.filter(linea -> !linea.isBlank() && !linea.startsWith("#"))
                    .map(linea -> linea.split(","))
                    .forEach(partes -> {
                        try {
                            gestor.executaSQL(conn,
                                    "SELECT * FROM VEHICLES WHERE ANY = ?",
                                    Integer.parseInt(partes[0].trim()), partes[1].trim(), partes[2].trim());
                        } catch (SQLException e) {
                            if (e.getSQLState().equals("2020") && e.getErrorCode() == 1062) {
                                int id = Integer.parseInt(partes[0].trim());
                                String nombre = partes[1].toUpperCase().trim();
                                String email = partes[2].toLowerCase().trim();
                            }
                        }
                    });
        } catch (IOException | SQLException e) {
            e.getMessage();
        }
    }
}
