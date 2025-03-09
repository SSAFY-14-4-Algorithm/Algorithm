import java.io.IOException;
import java.util.PriorityQueue;

public class Baekjoon1715 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(N);
        int result = 0;
        while (N-- > 0)
            pq.offer(readInt());
        while (pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            pq.offer(sum);
            result += sum;
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