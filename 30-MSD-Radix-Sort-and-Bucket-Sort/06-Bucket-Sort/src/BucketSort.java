import java.util.Arrays;

public class BucketSort {

    private BucketSort(){}

    public static void sort(Integer[] arr, int B){

        if(B <= 1)
            throw new IllegalArgumentException("B must > 1");

        Integer[] temp = new Integer[arr.length];
        sort(arr, 0, arr.length - 1, B, temp);
    }

    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp){

        if(left >= right) return;

        int maxv = Integer.MIN_VALUE, minv = Integer.MAX_VALUE;
        for(int i = left; i <= right; i ++){
            maxv = Math.max(maxv, arr[i]);
            minv = Math.min(minv, arr[i]);
        }

        if(minv == maxv) return;

        int d = (maxv - minv + 1) / B + ((maxv - minv + 1) % B > 0 ? 1 : 0);

        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        // O(n)
        for(int i = left; i <= right; i ++)
            cnt[(arr[i] - minv) / d] ++;

        // O(R)
        for(int i = 0; i < B; i ++)
            index[i + 1] = index[i] + cnt[i];

        // O(n)
        for(int i = left; i <= right; i ++){
            temp[index[(arr[i] - minv) / d] + left] = arr[i];
            index[(arr[i] - minv) / d] ++;
        }

        // O(n)
        for(int i = left; i <= right; i ++)
            arr[i] = temp[i];

        sort(arr, left, left + index[0] - 1, B, temp);
        for(int i = 0; i < B - 1; i ++)
            sort(arr, left + index[i], left + index[i + 1] - 1, B, temp);
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("BucketSort", arr);
    }
}
