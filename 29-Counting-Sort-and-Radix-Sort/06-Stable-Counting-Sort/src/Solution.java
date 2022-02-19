class Solution {

    public void sortColors(int[] nums) {

        int[] cnt = new int[3];
        for(int num: nums)
            cnt[num] ++;

        int[] index = new int[4];
        for(int i = 0; i < 3; i ++)
            index[i + 1] = index[i] + cnt[i];

        for(int i = 0; i + 1 < index.length; i ++)
            // index[i] 到 index[i + 1] 的值为 i
            for(int j = index[i]; j < index[i + 1]; j ++)
                nums[j] = i;
    }
}