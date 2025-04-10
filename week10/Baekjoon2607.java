package BAEKJOON.Silver.Silver_II.P2607번_비슷한_단어;

import java.io.IOException;
import java.io.InputStream;

public class Baekjoon2607 {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader(System.in);
        int N = fr.nextInt();
        String target = fr.nextLine();
        int[] targetCount = new int[26];
        for (int i = 0; i < target.length(); i++) {
            targetCount[target.charAt(i) - 'A']++;
        }
        int similarCount = 0;
        for (int i = 0; i < N - 1; i++) {
            String str = fr.nextLine();
            int lengthDiff = Math.abs(str.length() - target.length());
            if (lengthDiff > 1) {
                continue;
            }
            int[] strCount = new int[26];
            for (int j = 0; j < str.length(); j++) {
                strCount[str.charAt(j) - 'A']++;
            }
            int diffCount = 0;
            for (int j = 0; j < 26; j++) {
                diffCount += Math.abs(strCount[j] - targetCount[j]);
            }
            if (diffCount <= 2) {
                similarCount++;
            }
        }
        System.out.println(similarCount);
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
