package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main5 {

    public static void main(String[] args) {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("4321-JKL", "Ford", "Focus", 2019, 17000),
                new Vehicle("8765-MNO", "Hyundai", "Ioniq 5", 2023, 42000),
                new Vehicle("2109-PQR", "Peugeot", "308", 2016, 14000)
        );
        final String conexion = "C:\\temp\\mysql.con";
        GestorBBDD gestor = new GestorBBDD(conexion);
        
        try (Stream<String> lineas = Files.lines(Paths.get("C:\\temp\\vehicles.csv")); Connection conn = gestor.getConnectionFromFile()) {
            lineas.filter(linea -> !linea.isBlank() && !linea.startsWith("#"))
                    .map(linea -> linea.split(","))
                    .forEach(partes -> {
                        try {
                            gestor.executaSQL(conn,
                                    "INSERT INTO VEHICLES (matricula, marca, model, any, preu) VALUES (?, ?, ?, ?, ?)",
                                    partes[0].trim(), partes[1].trim(), partes[2].trim(), partes[3].trim(), partes[4].trim());
                        } catch (SQLException e) {
                            try {
                                if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                                    String matricula = partes[0].toUpperCase().trim();
                                    String marca = partes[1].toUpperCase().trim();
                                    String model = partes[2].toLowerCase().trim();
                                    int any = Integer.parseInt(partes[3].trim());
                                    int preu = Integer.parseInt(partes[4].trim());

                                    gestor.executaSQL(conn,
                                            "UPDATE VEHICLES SET matricula = ?, marca = ?, model = ?, any = ?, preu = ?",
                                            matricula, marca, model, any, preu);
                                } else {
                                    throw new RuntimeException(e);
                                }
                            } catch (SQLException e2) {
                                throw new RuntimeException(e2);
                            }
                        }
                    });
        } catch (IOException | SQLException e) {
            e.getMessage();
        }
    }
}
