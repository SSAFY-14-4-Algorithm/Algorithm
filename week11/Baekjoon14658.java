package week11;

import java.io.IOException;

public class Baekjoon14658 {
    private static int L, K;
    private static Point[] stars;

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        readInt();
        readInt();
        L = readInt();
        K = readInt();

        stars = new Point[K];

        for (int i = 0; i < K; i++) {
            int a = readInt();
            int b = readInt();
            stars[i] = new Point(a, b);
        }

        int max = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                // 두 별의 좌상단 좌표
                int x = Math.min(stars[i].x, stars[j].x);
                int y = Math.min(stars[i].y, stars[j].y);
                int count = 0;
                // 위 좌표에 트램펄린을 설치했을때 들어가는 별 카운트
                for (Point p : stars)
                    if (x <= p.x && p.x <= x + L && y <= p.y && p.y <= y + L)
                        count++;
                max = Math.max(max, count);
            }
        }
        System.out.print(K - max);
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
