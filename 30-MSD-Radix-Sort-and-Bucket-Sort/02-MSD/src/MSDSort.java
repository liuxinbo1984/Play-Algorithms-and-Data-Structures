import java.util.Arrays;

// 注意：这一小节，这个算法没有完成
public class MSDSort {

    private MSDSort(){}

    public static void sort(String[] arr){

        sort(arr, 0, arr.length - 1, 0);
    }

    // 根据 r 位置的字符，处理 arr[left, right]
    // 注意：这一小节，这个算法没有完成
    private static void sort(String[] arr, int left, int right, int r){

        if(left >= right) return;

        int R = 256;
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];
        String[] temp = new String[right - left + 1];

        // O(n)
        for(int i = left; i <= right; i ++)
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] ++;

        // O(R)
        for(int i = 0; i < R + 1; i ++)
            index[i + 1] = index[i] + cnt[i];

        // O(n)
        for(int i = left; i <= right; i ++){
            int p = r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1);
            temp[index[p]] = arr[i];
            index[p] ++;
        }

        // O(n)
        for(int i = left; i <= right; i ++)
            arr[i] = temp[i - left];

        // TODO: 递归下去
    }

    public static void main(String[] args) {
    }
}
