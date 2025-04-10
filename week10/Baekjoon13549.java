package BAEKJOON.Gold.Gold_IV.P13549번_숨바꼭질_3;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon13549 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();
        int[] time = new int[100_001];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[N] = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);
        while (!dq.isEmpty()) {
            int now = dq.poll();
            if (now == K) {
                System.out.println(time[K]);
                break;
            }
            int tp = now << 1;
            if (tp <= 100000 && time[tp] > time[now]) {
                time[tp] = time[now];
                dq.addFirst(tp);
            }
            int walkLeft = now - 1;
            if (0 <= walkLeft && time[walkLeft] > time[now]) {
                time[walkLeft] = time[now] + 1;
                dq.addLast(walkLeft);
            }
            int walkRight = now + 1;
            if (walkRight <= 100000 && time[walkRight] > time[now]) {
                time[walkRight] = time[now] + 1;
                dq.addLast(walkRight);
            }
        }
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}