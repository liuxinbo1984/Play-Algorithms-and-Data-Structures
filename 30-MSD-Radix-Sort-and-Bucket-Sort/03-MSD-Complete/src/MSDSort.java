import java.util.Arrays;

public class MSDSort {

    private MSDSort(){}

    public static void sort(String[] arr){

        int N = arr.length;
        String[] temp = new String[N];
        sort(arr, 0, N - 1, 0, temp);
    }

    private static void sort(String[] arr, int left, int right, int r, String[] temp){

        if(left >= right) return;

        int R = 256;
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];

        // O(n)
        for(int i = left; i <= right; i ++)
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] ++;

        // O(R)
        for(int i = 0; i < R + 1; i ++)
            index[i + 1] = index[i] + cnt[i];

        // O(n)
        for(int i = left; i <= right; i ++){
            temp[index[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] + left] = arr[i];
            index[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] ++;
        }

        // O(n)
        for(int i = left; i <= right; i ++)
            arr[i] = temp[i];

        for(int i = 0; i < R; i ++)
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for(String s: arr)
            System.out.println(s);
    }
}
