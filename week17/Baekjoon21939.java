package week17;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.TreeSet;

public class Baekjoon21939 {
    public static void main(String[] args) throws IOException {
        FastReader fs = new FastReader(System.in);
        @SuppressWarnings("unchecked")
        TreeSet<Integer>[] levels = new TreeSet[101]; // [레벨] 문제번호
        for (int i = 1; i <= 100; i++) {
            levels[i] = new TreeSet<>();
        }
        HashMap<Integer, Integer> problems = new HashMap<>(); // <문제번호, 레벨>
        int N = fs.nextInt();// 문제 개수
        while (N-- > 0) {
            int P = fs.nextInt(); // 문제번호
            int L = fs.nextInt(); // 난이도
            levels[L].add(P);
            problems.put(P, L);
        }
        StringBuilder sb = new StringBuilder();
        int M = fs.nextInt(); // 명령문 개수
        while (M-- > 0) {
            String cmd = fs.next();
            if (cmd.charAt(0) == 'a') { // add
                int P = fs.nextInt(); // 문제번호
                int L = fs.nextInt(); // 난이도
                levels[L].add(P);
                problems.put(P, L);
            } else if (cmd.charAt(0) == 'r') { // recommend
                int x = fs.nextInt(); // 1이면 난이도 높은순 큰 번호, -1이면 난이도 낮은순 작은 번호
                for (int l = ((x == 1) ? 100 : 1); ((0 <= l) && (l <= 100)); l += -x) {
                    if (!levels[l].isEmpty()) {
                        sb.append((x == 1) ? levels[l].last() : levels[l].first()).append("\n");
                        break;
                    }
                }
            } else { // solved
                int P = fs.nextInt(); // 문제번호
                int L = problems.get(P); // 레벨
                levels[L].remove(P);
                problems.remove(P);
            }
        }
        System.out.print(sb.toString());
    }

    public static class FastReader {
        private final int BUFFER_SIZE = 1 << 16;
        private final InputStream in;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;
        private final StringBuilder sb;

        public FastReader(InputStream in) {
            this.in = in;
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = 0;
            bytesRead = 0;
            sb = new StringBuilder();
        }

        private int read() throws IOException {
            if (bufferPointer == bytesRead) {
                bytesRead = in.read(buffer, 0, BUFFER_SIZE);
                if (bytesRead == -1)
                    return -1;
                bufferPointer = 0;
            }
            return buffer[bufferPointer++] & 0xff;
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= ' ')
                c = read();
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            int result = 0;
            while ('0' <= c && c <= '9') {
                result = result * 10 + (c & 15);
                c = read();
            }
            return negative ? -result : result;
        }

        public String next() throws IOException {
            int c = read();
            while (c <= ' ')
                c = read();
            sb.setLength(0);
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        public String nextLine() throws IOException {
            int c = read();
            while (c != -1 && (c == '\n' || c == '\r'))
                c = read();
            if (c == -1)
                return null;
            sb.setLength(0);
            while (c != -1 && c != '\n' && c != '\r') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }
}
