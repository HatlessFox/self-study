package utils;

import java.util.function.BiPredicate;
import java.util.function.Function;

import edu.princeton.cs.algs4.StdOut;

public class QA<T, R> {

    private int test_id;
    private Function<T, R> testee;

    public QA(Function<T, R> testee) { this.testee = testee; }

    public void runTest(T input, BiPredicate<T, R> validator) {
        R output = testee.apply(input);
        String test_resut = validator.test(input, output) ? "OK" :
            String.format("FAILED.\n %s is not valid", output);
        StdOut.println(String.format("%d. %s", test_id++, test_resut));
    }

    public void runTest(T input, R expected_output) {
        StdOut.println(runTest(test_id++, testee, input, expected_output));
    }

    public void runTest(T input, Function<T, R> expected_gen) {
        StdOut.println(runTest(test_id++, testee, input, expected_gen.apply(input)));
    }

    public static <T, R> String runTest(int test_id, Function<T, R> testee,
                                        T input, R expected_output) {
        R output = testee.apply(input);
        String test_resut = 
                output != null && output.equals(expected_output) || 
                output == null && expected_output == null ? "OK" :
            String.format("FAILED.\n %s vs %s", output, expected_output);
        return String.format("%d. %s", test_id, test_resut);
    }

}
