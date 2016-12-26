package utils;

import java.util.Random;

public class LangUtils {

    public static int[] ints2arr(int... ints) { return ints; }
    public static double[] dbls2arr(double... dbls) { return dbls; }
    
    public static double[] rndDoubles(int size) {
        Random rnd = new Random();
        double[] result = new double[size];

        for (int i = 0; i < result.length; i++) {
            result[i] = rnd.nextDouble();
        }

        return result;
    }

}
