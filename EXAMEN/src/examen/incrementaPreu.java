package examen;

import java.util.function.BiFunction;

/**
 *
 * @author amata
 */
public interface incrementaPreu {
    
    public BiFunction<Vehicle, Double, Vehicle> incrementarPreu2 = (a, b) -> a * b;
    
}
