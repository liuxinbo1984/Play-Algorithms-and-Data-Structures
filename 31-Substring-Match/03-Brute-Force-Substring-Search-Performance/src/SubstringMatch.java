public class SubstringMatch {

    private SubstringMatch(){}

    // 在 s 中寻找 t，并返回匹配的第一个索引 i; 如果没有找到，返回 -1
    public static int bruteforce(String s, String t){

        if(s.length() < t.length()) return -1;

        // s[i, i + t.length - 1]
        for(int i = 0; i + t.length() - 1 < s.length(); i ++){
            int j = 0;
            for(; j < t.length(); j ++)
                if(s.charAt(i + j) != t.charAt(j))
                    break;
            if(j == t.length()) return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        String s1 = "hello, this is liuyubobobo.";
        String t1 = "bo";
        SubstringMatchHelper.matchTest("bruteforce", s1, t1);

        String s2 = FileOperation.readFile("pride-and-prejudice.txt");
        String t2 = "china";
        SubstringMatchHelper.matchTest("bruteforce", s2, t2);

        SubstringMatchHelper.matchTest("bruteforce", s2, "zyx");

        /// Worst case
        int n = 1000000, m = 1000;

        StringBuilder s3 = new StringBuilder();
        for(int i = 0; i < n; i ++)
            s3.append('a');

        StringBuilder t3 = new StringBuilder();
        for(int i = 0; i < m - 1; i ++)
            t3.append('a');
        t3.append('b');

        SubstringMatchHelper.matchTest("bruteforce", s3.toString(), t3.toString());
    }
}
