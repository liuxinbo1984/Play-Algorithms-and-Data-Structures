import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // int n = 100000, w = 200;
        int n = 1000000, w = 2;
        String[] arr = ArrayGenerator.generateRandomStringArray(n, w);
        String[] arr2 = Arrays.copyOf(arr, arr.length);
        String[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("LSDSort", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);
    }
}
