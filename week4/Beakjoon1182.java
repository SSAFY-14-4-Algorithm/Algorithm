import java.io.*;
import java.util.*;

public class Beakjoon1182{
    static int N, S, count;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
        subset(0, 0);

        if (S == 0) count--;

        System.out.println(count);
    }

    static void subset(int depth, int sum) {
        if (depth == N) {
            if (sum == S) count++;
            return;
        }

        subset(depth + 1, sum + num[depth]);
        subset(depth + 1, sum);
    }
}
