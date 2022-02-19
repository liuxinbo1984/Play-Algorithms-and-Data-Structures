import java.util.Arrays;

public class NumArray {

    private int[] data, blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    public NumArray(int[] nums) {

        N = nums.length;
        if(N == 0) return;

        B = (int)Math.sqrt(N);
        Bn = N / B + (N % B > 0 ? 1 : 0);

        data = Arrays.copyOf(nums, N);

        blocks = new int[Bn];
        for(int i = 0; i < N; i ++)
            blocks[i / B] += nums[i];
    }

    public int sumRange(int x, int y) {

        if(x < 0 || x >= N || y < 0 || y >= N || x > y) return 0;

        int bstart = x / B, bend = y / B;

        int res = 0;
        if(bstart == bend){
            for(int i = x; i <= y; i ++) res += data[i];
            return res;
        }

        for(int i = x; i < (bstart + 1) * B; i ++)
            res += data[i];
        for(int b = bstart + 1; b < bend; b ++)
            res += blocks[b];
        for(int i = bend * B; i <= y; i ++)
            res += data[i];

        return res;
    }

    public void update(int i, int val){

        if(i < 0 || i >= N) return;

        int b = i / B;

        blocks[b] -= data[i];
        blocks[b] += val;

        data[i] = val;
    }
}
