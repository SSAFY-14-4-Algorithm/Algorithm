import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

/**
 *
 * @author SSAFY
 *
 * 양의 정수 N
 * N의 자릿수를 모두 더한 값 A,
 * N의 자릿수를 모두 곱한 값 B
 * f(n) = A,B를 이어 붙인 수
 * f(x) = x 가능 -> g(N) = 1, 불가능 -> g(N) = 0
 * f(x)가 10만보다 크다면 g(N) = -1
 * L = 1, R = 10
 * g(1) + g(2) + g(3) ... g(10) 의 값
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(reader.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        dp = new int[100_001];

        int ans = 0;
        for (int i = L; i <= R; i++) {
            ans += g(i);
        }

        System.out.println(ans);
    }

    static int[] dp;

    static int g(int n) {
        HashSet<Integer> cycle = new HashSet<>();
        int cur = n;
        while(true) {
            int next = f(cur);

            if(next == cur) return 1;
            if(next >= 100_000) return -1;
            if(cycle.contains(next)) return 0; //사이클 발생

            cycle.add(next);
            cur = next;
        }
    }

    static int f(int n) {

        if(dp[n]>0) return dp[n]; //메모이제이션

        String num = String.valueOf(n);

        //덧셈
        int added = 0;
        for (int i = 0; i < num.length(); i++) {
            added += Integer.parseInt(String.valueOf(num.charAt(i)));
        }

        int multiplied = 1;
        for (int i = 0; i < num.length(); i++) {
            multiplied *= Integer.parseInt(String.valueOf(num.charAt(i)));
        }

        String result = String.valueOf(added) + "" + String.valueOf(multiplied);
        return dp[n] = Integer.parseInt(result);
    }
}

