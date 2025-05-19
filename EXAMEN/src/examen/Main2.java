package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
        String rutaArchivo = "C:\\temp\\vehicles.csv";
        try (FileReader fileReader = new FileReader(rutaArchivo); BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (!linea.substring(0, 1).equals("#")) {
                    List<Vehicle> mayor15000 = linea.stream()
                            .filter(p -> p.getPreu() > 15000)
                            .count()
                            .collect(Collectors.toList());

                    int max = linea.stream().mapToInt(v -> v.getPreu()).max().orElse(0);
                    int min = linea.stream().mapToInt(v -> v.getPreu()).min().orElse(0);
                    double avg = linea.stream().mapToDouble(v -> v.getPreu()).average().orElse(0);
                }
                System.out.println("Vehicles amd preu major de 15000 EUR: " + mayor15000);
                System.out.println("Preu mitjà: " + avg);
                System.out.println("Més car: " + max);
                System.out.println("Més barat: "+ min);
            }
        } catch (IOException e) {
            System.err.println("Error llegint l'arxiu: " + e.getMessage());
        }
    }
}
