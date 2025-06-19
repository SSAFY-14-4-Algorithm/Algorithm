package week17;

import java.io.IOException;

public class Baekjoon2812 {
    public static void main(String[] args) throws IOException {
        int N = readInt(); // 전체 숫자 자리수
        int K = readInt(); // 지울 자리수
        String number = readNumber(N);
        char[] result = new char[N];
        int idx = 0;
        int deleted = 0;
        for (int i = 0; i < N; i++) {
            char c = number.charAt(i);
            // 마지막 숫자보다 c가 더 크면
            // 마지막 숫자의 자리로 이동하고(idx--)
            // 삭제한 수 증가(deleted++)
            while (0 < idx && deleted < K && result[idx - 1] < c) {
                idx--;
                deleted++;
            }
            result[idx++] = c;
        }
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - K; i++)
            sb.append(result[i]);
        System.out.print(sb.toString());
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

    private static String readNumber(int n) throws IOException {
        StringBuilder sb = new StringBuilder(n);
        int cnt = 0;
        int c = System.in.read();
        while (c <= ' ')
            c = System.in.read();
        while (cnt < n && c >= '0' && c <= '9') {
            sb.append((char) c);
            cnt++;
            c = System.in.read();
        }
        return sb.toString();
    }
}
