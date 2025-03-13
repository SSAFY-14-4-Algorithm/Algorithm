package week6;

/**
 * <h1>BAEKJOON 10830번 행렬 제곱</h1>
 * <p>
 * JAVA 8 : 메모리 11,476KB, 시간 64ms <br>
 * JAVA11 : 메모리 14,192KB, 시간 104ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-03-12
 */
import java.io.IOException;

public class Baekjoon10830 {
    private static int N;
    private static long B;
    private static final int MOD = 1000;

    public static void main(String[] args) throws IOException {
        N = readInt();
        B = readLong();

        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] = readInt();

        int[][] result = power(matrix, B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(result[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 반복문 기반 이진 거듭제곱
    public static int[][] power(int[][] a, long exp) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = multiply(result, a);
            a = multiply(a, a);
            exp >>= 1;
        }
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (long) a[i][k] * b[k][j];
                }
                c[i][j] = (int) (sum % MOD);
            }
        }
        return c;
    }

    private static long readLong() throws IOException {
        int c;
        long n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
