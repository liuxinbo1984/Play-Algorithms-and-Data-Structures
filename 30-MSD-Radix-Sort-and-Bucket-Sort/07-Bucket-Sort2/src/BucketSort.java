import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    private BucketSort(){}

    public static void sort(Integer[] arr, int B){

        if(B <= 1)
            throw new IllegalArgumentException("B must be > 1");

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

        if(maxv == minv) return;

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
            int p = (arr[i] - minv) / d;
            temp[left + index[p]] = arr[i];
            index[p] ++;
        }

        // O(n)
        for(int i = left; i <= right; i ++)
            arr[i] = temp[i];

        // 递归下去：
        sort(arr, left, left + index[0] - 1, B, temp);
        for(int i = 0; i < B - 1; i ++)
            sort(arr, left + index[i], left + index[i + 1] - 1, B, temp);
    }

    public static void sort2(Integer[] arr, int c){

        if(c <= 0)
            throw new IllegalArgumentException("c must be > 0");

        int maxv = Integer.MIN_VALUE, minv = Integer.MAX_VALUE;
        for(int e: arr){
            maxv = Math.max(maxv, e);
            minv = Math.min(minv, e);
        }

        int range = maxv - minv + 1; // arr 中的数据范围
        int B = range / c + (range % c > 0 ? 1 : 0); // 根据数据范围决定桶的个数

        LinkedList<Integer>[] buckets = new LinkedList[B];
        for(int i = 0; i < B; i ++)
            buckets[i] = new LinkedList<>();

        for(int e: arr)
            buckets[(e - minv) / c].add(e);

        for(int i = 0; i < B; i ++)
            Collections.sort(buckets[i]);

        int index = 0;
        for(int i = 0; i < B; i ++)
            for(int e: buckets[i])
                arr[index ++] = e;
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("BucketSort", arr);
        SortingHelper.sortTest("BucketSort2", arr2);
    }
}
