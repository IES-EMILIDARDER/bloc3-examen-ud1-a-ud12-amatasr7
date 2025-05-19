package examen;

import java.util.function.BiFunction;


/**
 *
 * @author amata
 */
public interface incrementaPreu {
    
    /**
     *
     */
    public BiFunction<Vehicle, Double, Vehicle> incrementaPreu2 = (v, d) -> v*d;
    
}
