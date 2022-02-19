import java.util.Random;

public class ArrayGenerator {

    private ArrayGenerator(){}

    public static Integer[] generateOrderedArray(int n){

        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i ++)
            arr[i] = i;
        return arr;
    }

    // 生成一个长度为 n 的随机数组，每个数字的范围是 [0, bound)
    public static Integer[] generateRandomArray(int n, int bound){

        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for(int i = 0; i < n; i ++)
            arr[i] = rnd.nextInt(bound);
        return arr;
    }

    public static String[] generateRandomStringArray(int n, int w){

        // https://www.ascii-code.com/
        // 33-126 可打印字符
        String[] arr = new String[n];
        Random rnd = new Random();
        for(int i = 0; i < n; i ++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < w; j ++)
                sb.append((char)(rnd.nextInt(94) + 33));
            arr[i] = sb.toString();
        }
        return arr;
    }

    private static <E> void swap(E[] arr, int i, int j){

        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}