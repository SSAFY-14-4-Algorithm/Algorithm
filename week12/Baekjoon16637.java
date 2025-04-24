package week12;

import java.io.IOException;

public class Baekjoon16637 {
    private static char[] op;
    private static int[] num;
    private static int N, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = readInt();
        num = new int[(N >> 1) + 1];
        op = new char[(N >> 1)];
        // System.in.read(); CRLF 윈도우에서 주석 제거
        for (int i = 0; i < N; i++) {
            int c = System.in.read();
            if (i % 2 == 0) {
                num[i >> 1] = c & 15;
            } else {
                op[i >> 1] = (char) c;
            }
        }
        dfs(0, num[0]);
        System.out.println(max);
    }

    static void dfs(int idx, int nowResult) {
        if (idx >= op.length) {
            max = Math.max(max, nowResult);
            return;
        }
        // 괄호 X
        int tempResult = calc(nowResult, num[idx + 1], op[idx]);
        dfs(idx + 1, tempResult);

        // 괄호 O
        if (idx + 1 < op.length) {
            int parenthesesResult = calc(num[idx + 1], num[idx + 2], op[idx + 1]);
            int afterResult = calc(nowResult, parenthesesResult, op[idx]);
            dfs(idx + 2, afterResult);
        }
    }

    static int calc(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

    private static int readInt() throws IOException {
        int n = 0, c = System.in.read();
        while (c <= ' ')
            c = System.in.read();
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
