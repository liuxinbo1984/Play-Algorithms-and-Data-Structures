/// LC 1147
/// https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/
class Solution2 {

    private long MOD = (long)(1e9 + 7);
    private long[] pow26;

    public int longestDecomposition(String text) {

        pow26 = new long[text.length()];
        pow26[0] = 1;
        for(int i = 1; i < pow26.length; i ++)
            pow26[i] = pow26[i - 1] * 26 % MOD;

        return solve(text, 0, text.length() - 1);
    }

    private int solve(String s, int left, int right){

        if(left > right) return 0;

        long prehash = 0, posthash = 0;
        for(int i = left, j = right; i < j; i ++, j --){
            prehash = (prehash * 26 + (s.charAt(i) - 'a')) % MOD;
            posthash = ((s.charAt(j) - 'a') * pow26[right - j] + posthash) % MOD;
            if(prehash == posthash && equal(s, left, i, j, right))
                return 2 + solve(s, i + 1, j - 1);
        }
        return 1;
    }

    private boolean equal(String s, int l1, int r1, int l2, int r2){

        for(int i = l1, j = l2; i <= r1 && j <= r2; i ++, j ++)
            if(s.charAt(i) != s.charAt(j)) return false;
        return true;
    }
}