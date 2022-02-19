import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/// Leetcode 164
public class Solution {
    public int maximumGap(int[] nums) {

        if(nums.length < 2) return 0;

        Integer[] data = Arrays.stream(nums).boxed().toArray( Integer[]::new );
        BucketSort.sort2(data, 10000);
        for(int e: data) System.out.println(e);

        int res = data[1] - data[0];
        for(int i = 2; i < data.length; i ++)
            res = Math.max(res, data[i] - data[i - 1]);
        return res;
    }
}
