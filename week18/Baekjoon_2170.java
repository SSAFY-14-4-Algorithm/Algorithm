import java.util.*;
import java.io.*;

/**
 * 52712 KB, 1120 ms
 */

public class Main {

    private static class Line implements Comparable<Line>{
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        PriorityQueue<Line> pq = new PriorityQueue<>();

        for (int i=0;i<N;i++){
            pq.offer(new Line(readInt(), readInt()));
        }

        int end = Integer.MIN_VALUE;
        int result = 0;

        while(!pq.isEmpty()){
            Line cur = pq.poll();
            if (cur.start < end && cur.end > end){
                result += cur.end - end;
                end = cur.end;
            } else if (cur.start >= end){
                result += cur.end - cur.start;
                end = cur.end;
            }
        }

        System.out.print(result);

    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
