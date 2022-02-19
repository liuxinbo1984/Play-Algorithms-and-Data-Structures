/// LC 1147
/// https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/

class Solution {

    public int longestDecomposition(String text) {

        return solve(text, 0, text.length() - 1);
    }

    private int solve(String s, int left, int right){

        if(left > right) return 0;

        for(int i = left, j = right; i < j; i ++, j --){
            if(equal(s, left, i, j, right))
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