import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int n = 10000;
//        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("QuickSort2Ways", arr);
        SortingHelper.sortTest("QuickSort3Ways", arr2);
        SortingHelper.sortTest("MergeSort", arr3);
        SortingHelper.sortTest("BucketSort", arr4);
        SortingHelper.sortTest("BucketSort2", arr5);

    }
}
