package part_1.chapter_1;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.1.35: Dice simulation
 * 
 * The following code computes the exact probability distribution for the sum of two dices <skipped>
 * Run experiment to validate calculation simulating N dice throws, keeping track of the frequencies
 * of occurrence of each value when you compute the sum of two random integers between 1 and 6. How 
 * large does N have to be before your empirical results the exact results to three decimal places?
 */
public class Task_35 {

    private static int SIDES = 6;
    private static Random rnd = new Random();
    
    private static double[] calculateDiceRollingStatistics() {
        double[] distrib = new double[2*SIDES+1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                distrib[i + j] += 1.0;
            }
        }
        for (int i = 0; i < distrib.length; i++) { distrib[i] /= (SIDES * SIDES); }
        return distrib;
    }
    
    private static int rollDice() {
        return rnd.nextInt(SIDES) + 1;
    }
    
    private static double[] generateDiceRollingStatistics(int trials_cnt) {
        double[] distrib = new double[2*SIDES+1];
        for (int i = 0; i <= trials_cnt; i++) {
            int dice1 = rollDice(), dice2 = rollDice();
            distrib[dice1 + dice2] += 1.0;
        }
        for (int i = 0; i < distrib.length; i++) { distrib[i] /= trials_cnt; }
        return distrib;
    }
    
    private static double maxDisparity(double[] a1, double[] a2) {
        double max_disp = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a1.length; i++) {
            double disp = Math.abs(a1[i] - a2[i]);
            if (max_disp < disp) max_disp = disp;
        }
        return max_disp;
    }
    
    private static int findSufficientTrialsCnt(double eps) {
        double[] theoretical_dist = calculateDiceRollingStatistics();
        int n = 10;
        while (true) {
            double[] experiment_dist = generateDiceRollingStatistics(n);
            if (maxDisparity(theoretical_dist, experiment_dist) < eps) {
                break;
            }
            n *= 10;
        }
        
        return n;
    }
    
    public static void main(String[] args) {
        StdOut.println(findSufficientTrialsCnt(0.001));
    }
    
}
