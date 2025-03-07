
import java.io.*;

/**
 * 
 * 메모리 12156 kb
 * 시간 2488 ms
 *
 *
 */
public class Baekjoon9663 {
    static int N, result;
    static boolean[] col, diag1, diag2; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];

        backtrack(0);
        System.out.println(result);
    }

    public static void backtrack(int row) {
        if (row == N) { 
            result++;
            return;
        }
        
        for (int c = 0; c < N; c++) {
            if (col[c] || diag1[row + c] || diag2[row - c + (N - 1)]) continue;

            col[c] = diag1[row + c] = diag2[row - c + (N - 1)] = true;
            backtrack(row + 1);
            col[c] = diag1[row + c] = diag2[row - c + (N - 1)] = false;
        }
    }
}
