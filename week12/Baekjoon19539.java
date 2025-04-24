package week12;

import java.io.IOException;

public class Baekjoon19539 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int sum = 0;
        // (count) : +2 성장 횟수
        int _2count = 0;

        for (int i = 0; i < N; i++) {
            int h = readInt();
            sum += h;
            _2count += h / 2;
        }

        String result = "NO";
        // 전체 합이 3으로 나누어지는지 확인
        if (sum % 3 == 0) {
            // (sum / 3) : +1+2 성장 횟수
            if ((sum / 3) <= _2count)
                result = "YES";
        }
        System.out.print(result);
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        while (c <= ' ')
            c = System.in.read();
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
