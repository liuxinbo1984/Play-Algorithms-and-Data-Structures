import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        int n = 100000, bound = 200;
        int n = 500000, bound = 20;
        String[] arr = ArrayGenerator.generateRandomStringArray(n, bound);
        String[] arr2 = Arrays.copyOf(arr, arr.length);
        String[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("QuickSort2Ways", arr);
        SortingHelper.sortTest("MSDSort", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);
    }
}
