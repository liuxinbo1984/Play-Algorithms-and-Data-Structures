/// Leetcode 0028
/// https://leetcode.com/problems/implement-strstr/

class Solution {

    public int strStr(String s, String t) {

        if (s.length() < t.length()) return -1;

        for (int i = 0; i + t.length() <= s.length(); i++) {
            int j = 0;
            for (; j < t.length(); j++)
                if (s.charAt(i + j) != t.charAt(j))
                    break;
            if (j == t.length()) return i;
        }
        return -1;
    }
}