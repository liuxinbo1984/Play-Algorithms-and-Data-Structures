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
        System.out.println(SubstringMatch.bruteforce(s1, t1));
    }
}
