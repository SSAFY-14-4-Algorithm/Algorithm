import java.util.*;
import java.io.*;

/**
 * 25240 KB, 456 ms
 */

public class Main {

    private static class Log implements Comparable<Log>{
        int x1, x2, y, idx;
        Log(int x1, int x2, int y, int idx){
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.idx = idx;
        }

        @Override
        public int compareTo(Log o) {
            return Integer.compare(this.x1, o.x1);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt(); // 통나무 개수
        int Q = readInt(); // 질문 개수

        Log[] logs = new Log[N];
        for (int i=0;i<N;i++){
            logs[i] = new Log(readInt(), readInt(), readInt(), i);
        }
        Arrays.sort(logs);

        int right = 0;
        int group = 0;
        int[] groupNum = new int[N];

        for (int i=0;i<N;i++){
            Log c = logs[i];
            if (c.x1 <= right) right = Math.max(right, c.x2);
            else {
                group++;
                right = c.x2;
            }
            groupNum[c.idx] = group;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<Q;i++){
            int a = readInt() - 1;
            int b = readInt() - 1;
            sb.append(groupNum[a]==groupNum[b] ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }

    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
