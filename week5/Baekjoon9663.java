import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//숏코딩 JAVA11 14152KB	100ms
//dfs JAVA11 14844KB	2496ms
public class Baekjoon9663 {
    public static int N;
    public static int count = 0;
    public static boolean[] col;
    public static boolean[] slash; // "/" 대각선
    public static boolean[] backslash; // "\" 대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        col = new boolean[N];
        slash = new boolean[2 * N];
        backslash = new boolean[2 * N];
        solve(0);
        System.out.println(count);
    }

    public static void solve(int x) {
        if (x == N) {
            count++;
            return;
        }

        for (int y = 0; y < N; y++) {
            if (!col[y] && !slash[x + y] && !backslash[x - y + (N - 1)]) {
                col[y] = true;
                slash[x + y] = true;
                backslash[x - y + (N - 1)] = true;
                solve(x + 1);
                col[y] = false;
                slash[x + y] = false;
                backslash[x - y + (N - 1)] = false;
            }
        }
    }
}

// 숏코딩
// interface Main {
// static void main(String[] a) throws Exception {
// int c, n = System.in.read() & 15;
// while ((c = System.in.read()) >= 48)
// n = n * 10 + (c & 15);
// System.out.print(new int[] { 1, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680,
// 14200, 73712, 365596, 2279184 }[n]);
// }
// }