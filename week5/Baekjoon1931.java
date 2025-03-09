import java.io.IOException;
import java.util.PriorityQueue;

public class Baekjoon1931 {
    private static class Time implements Comparable<Time> {
        private int start;
        private int end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 빨리 끝나는 회의 우선 -> 같으면 더 빨리 시작하는 회의
        @Override
        public int compareTo(Time o) {
            if (this.end == o.end) {
                return this.start - o.start;
            } else {
                return this.end - o.end;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int result = 0;
        int N = readInt();
        PriorityQueue<Time> pq = new PriorityQueue<>(N);
        while (N-- > 0) {
            int start = readInt();
            int end = readInt();
            pq.offer(new Time(start, end));
        }
        int lastEndTime = 0;
        while (!pq.isEmpty()) {
            Time time = pq.poll();
            if (lastEndTime <= time.start) {
                lastEndTime = time.end;
                result++;
            }
        }
        System.out.print(result);
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}