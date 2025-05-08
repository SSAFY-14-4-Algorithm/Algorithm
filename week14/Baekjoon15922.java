package week14;

import java.io.IOException;

public class Baekjoon15922 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int length = 0;
        int x = readInt(), y = readInt();
        for (int i = 0; i < N - 1; i++) {
            int tempX = readInt(), tempY = readInt();
            // 겹치거나 붙어있을 때
            if (tempX <= y) {
                y = Math.max(y, tempY);
            } // 겹치지 않을 때
            else {
                length += Math.abs(y - x);
                x = tempX;
                y = tempY;
            }
        }
        System.out.print(length + Math.abs(y - x));
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        boolean isNegative = false;
        while (c <= ' ') {
            c = System.in.read();
        }
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return isNegative ? -n : n;
    }
}
