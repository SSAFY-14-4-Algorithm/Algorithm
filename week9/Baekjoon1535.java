package week9;

/**
 * JAVA11 : 메모리 14,224 KB 시간 116ms
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;

public class Baekjoon1535 {
    private static int N;
    private static int max;
    private static int[] L;
    private static int[] J;

    public static void main(String[] args) throws IOException {
        N = readInt();
        L = new int[N];
        J = new int[N];
        for (int i = 0; i < N; i++)
            L[i] = readInt();
        for (int i = 0; i < N; i++)
            J[i] = readInt();
        dfs(0, 0, 0);
        System.out.println(max);
    }

    private static void dfs(int depth, int health, int happiness) {
        if (health < 100) {
            max = Math.max(max, happiness);
        } else {
            return;
        }
        if (depth != N) {
            dfs(depth + 1, health + L[depth], happiness + J[depth]);
            dfs(depth + 1, health, happiness);
        }
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 47)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
