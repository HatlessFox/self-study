package chapter_2;

public class SortingUtils {
    
    public static void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static void is_sorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] <= arr[i]) { continue; }
            throw new RuntimeException("Array is not sorted");
        }
    }

}
