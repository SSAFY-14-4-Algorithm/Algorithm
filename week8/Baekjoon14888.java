package week8;

/**
 * <h1>BAEKJOON 14888번 연산자 끼워넣기 SILVER I</h1>
 * <p>
 * JAVA11 : 메모리 15,816 KB 시간 108ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;

public class Baekjoon14888 {
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int N;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        nums = new int[N = readInt()];
        for (int i = 0; i < N; i++)
            nums[i] = readInt();
        dfs(0, nums[0], readInt(), readInt(), readInt(), readInt());
        System.out.print(new StringBuilder().append(max).append("\n").append(min));
    }

    public static void dfs(int depth, int value, int add, int sub, int mul, int div) {
        if (depth == N - 1) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }
        int nextDepth = depth + 1;
        if (add > 0)
            dfs(nextDepth, value + nums[nextDepth], add - 1, sub, mul, div);
        if (sub > 0)
            dfs(nextDepth, value - nums[nextDepth], add, sub - 1, mul, div);
        if (mul > 0)
            dfs(nextDepth, value * nums[nextDepth], add, sub, mul - 1, div);
        if (div > 0)
            dfs(nextDepth, value / nums[nextDepth], add, sub, mul, div - 1);
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        return n;
    }
}