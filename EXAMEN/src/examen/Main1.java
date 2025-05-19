package examen;

import java.util.Arrays;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {

        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("1234-HJK", "Toyota", "Corolla", 2010, 12000),
                new Vehicle("5678-KLM", "Volkswagen", "Golf", 2018, 18000),
                new Vehicle("9012-NPR", "Seat", "Ibiza", 2015, 10000),
                new Vehicle("3456-STU", "Tesla", "Model 3", 2022, 40000),
                new Vehicle("7890-VWX", "Renault", "Clio", 2012, 9000)
        );

        vehicles.stream()
                .filter(v -> v.getAny() > 2014)
                
                .forEach(v -> System.out.println(v.getMarca()));
                
        
    }
}
