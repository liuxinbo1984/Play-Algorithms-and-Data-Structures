import java.util.Arrays;

/// 使用 SQRT 分解解决区间最小值查询
public class MinSQRT {

    private int[] data, blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    /// 构造函数
    public MinSQRT(int[] nums) {

        N = nums.length;
        if(N == 0) return;

        B = (int)Math.sqrt(N);
        Bn = N / B + (N % B > 0 ? 1 : 0);

        data = Arrays.copyOf(nums, N);

        blocks = new int[Bn];
        Arrays.fill(blocks, Integer.MAX_VALUE);
        for(int i = 0; i < N; i ++)
            blocks[i / B] = Math.min(blocks[i / B], nums[i]);
    }

    /// 区间最小值查询
    public int minRange(int x, int y) {

        if(x < 0 || x >= N || y < 0 || y >= N || x > y) return 0;

        int bstart = x / B, bend = y / B;

        int res = Integer.MAX_VALUE;
        if(bstart == bend){
            for(int i = x; i <= y; i ++) res = Math.min(res, data[i]);
            return res;
        }

        for(int i = x; i < (bstart + 1) * B; i ++)
            res = Math.min(res, data[i]);
        for(int b = bstart + 1; b < bend; b ++)
            res = Math.min(res, blocks[b]);
        for(int i = bend * B; i <= y; i ++)
            res = Math.min(res, data[i]);
        return res;
    }

    /// 单元素更新
    public void update(int index, int val){

        if(index < 0 || index >= N) return;

        int b = index / B;
        data[index] = val;

        blocks[b] = Integer.MAX_VALUE;
        for(int i = b * B; i < Math.min((b + 1) * B, N); i ++)
            blocks[b] = Math.min(blocks[b], data[i]);

    }
}
