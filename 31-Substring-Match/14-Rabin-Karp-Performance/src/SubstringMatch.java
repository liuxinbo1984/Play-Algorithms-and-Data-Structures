public class SubstringMatch {

    private SubstringMatch(){}

    // 在 s 中寻找 t，并返回匹配的第一个索引 i; 如果没有找到，返回 -1
    public static int bruteforce(String s, String t){

        if(s.length() < t.length()) return -1;
        if(t.length() == 0) return 0;

        for(int i = 0; i + t.length() <= s.length(); i ++){
            int j = 0;
            for(; j < t.length(); j ++)
                if(s.charAt(i + j) != t.charAt(j))
                    break;
            if(j == t.length()) return i;
        }
        return -1;
    }

    public static int rabinKarp(String s, String t){

        if(s.length() < t.length()) return -1;
        if(t.length() == 0) return 0;

        long thash = 0, MOD = (long)1e9 + 7, B = 256;
        for(int i = 0; i < t.length(); i ++)
            thash = (thash * B + t.charAt(i)) % MOD;

        long hash = 0, P = 1;
        for(int i = 0; i < t.length() - 1; i ++)
            P = P * B % MOD;

        for(int i = 0; i < t.length() - 1; i ++)
            hash = (hash * B + s.charAt(i)) % MOD;

        for(int i = t.length() - 1; i < s.length(); i ++){
            hash = (hash * B + s.charAt(i)) % MOD;
            if(hash == thash && equal(s, i - t.length() + 1, t))
                return i - t.length() + 1;
            hash = (hash - s.charAt(i - t.length() + 1) * P % MOD + MOD) % MOD;
        }
        return -1;
    }

    private static boolean equal(String s, int l, String t){

        for(int i = 0; i < t.length(); i ++)
            if(s.charAt(l + i) != t.charAt(i)) return false;
        return true;
    }

    public static void main(String[] args) {

        String s1 = "hello, this is liuyubobobo.";
        String t1 = "bo";
        SubstringMatchHelper.matchTest("bruteforce", s1, t1);
        SubstringMatchHelper.matchTest("rk", s1, t1);

        String s2 = FileOperation.readFile("pride-and-prejudice.txt");
        String t2 = "china";
        SubstringMatchHelper.matchTest("bruteforce", s2, t2);
        SubstringMatchHelper.matchTest("rk", s2, t2);

        SubstringMatchHelper.matchTest("bruteforce", s2, "zyx");
        SubstringMatchHelper.matchTest("rk", s2, "zyx");

        /// Worst case
        int n = 1000000, m = 10000;

        StringBuilder s3 = new StringBuilder();
        for(int i = 0; i < n; i ++)
            s3.append('a');

        StringBuilder t3 = new StringBuilder();
        for(int i = 0; i < m - 1; i ++)
            t3.append('a');
        t3.append('b');

        SubstringMatchHelper.matchTest("bruteforce", s3.toString(), t3.toString());
        SubstringMatchHelper.matchTest("rk", s3.toString(), t3.toString());
    }
}
