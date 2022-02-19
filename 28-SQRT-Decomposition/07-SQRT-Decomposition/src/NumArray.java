/// 使用我们自己封装的 SQRTDecomposition 类
/// 解决 Leetcode 307
import java.util.Arrays;

public class NumArray {

    private interface Merger<E> {
        E merge(E a, E b);
    }

    private class SQRTDecomposition<E> {

        private E[] data, blocks;
        private int N;  // 元素总数
        private int B;  // 每组元素个数
        private int Bn; // 组数
        private Merger<E> merger;

        /// 构造函数
        public SQRTDecomposition(E[] arr, Merger<E> merger) {

            this.merger = merger;

            N = arr.length;
            if(N == 0) return;

            B = (int)Math.sqrt(N);
            Bn = N / B + (N % B > 0 ? 1 : 0);

            data = (E[])new Object[N];
            for(int i = 0 ; i < N; i ++)
                data[i] = arr[i];

            blocks = (E[])new Object[Bn];
            for(int i = 0; i < N; i ++)
                if(i % B == 0)
                    blocks[i / B] = data[i];
                else
                    blocks[i / B] = merger.merge(blocks[i / B], data[i]);
        }

        /// 区间查询
        public E queryRange(int x, int y) {

            if(x < 0 || x >= N || y < 0 || y >= N || x > y) return null;

            int bstart = x / B, bend = y / B;

            E res = data[x];
            if(bstart == bend){
                for(int i = x + 1; i <= y; i ++)
                    res = merger.merge(res, data[i]);
                return res;
            }

            for(int i = x + 1; i < (bstart + 1) * B; i ++)
                res = merger.merge(res, data[i]);
            for(int b = bstart + 1; b < bend; b ++)
                res = merger.merge(res, blocks[b]);
            for(int i = bend * B; i <= y; i ++)
                res = merger.merge(res, data[i]);
            return res;
        }

        /// 单元素更新
        public void update(int index, E val){

            if(index < 0 || index >= N) return;

            int b = index / B;
            data[index] = val;

            blocks[b] = data[b * B];
            for(int i = b * B + 1; i < Math.min((b + 1) * B, N); i ++)
                blocks[b] = merger.merge(blocks[b], data[i]);
        }
    }

    private SQRTDecomposition<Integer> sumSQRT;

    public NumArray(int[] nums) {
        sumSQRT = new SQRTDecomposition<Integer>(Arrays.stream(nums).boxed().toArray(Integer[]::new), (a, b)-> a + b);
    }

    public void update(int i, int val) {
        sumSQRT.update(i, val);
    }

    public int sumRange(int i, int j) {
        return sumSQRT.queryRange(i, j);
    }
}
