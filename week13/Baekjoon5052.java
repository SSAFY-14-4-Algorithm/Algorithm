package week13;

/**
 * <h1>BAEKJOON 5052번 전화번호 목록</h1>
 * <p>
 * JAVA11 : 메모리 106,796KB, 시간 652ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-04-29
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Baekjoon5052 {
    public static class Contact {
        Contact[] contacts;
        boolean exists;

        Contact() {
            contacts = new Contact[10];
        }

        public boolean addContacts(String phoneNumber, int index) {
            int digit = phoneNumber.charAt(index) - '0';
            if (this.contacts[digit] == null) {
                this.contacts[digit] = new Contact();
            } else if (this.contacts[digit].exists) {
                return false;
            }
            if (phoneNumber.length() - 1 == index) {
                this.contacts[digit].exists = true;
            } else {
                return this.contacts[digit].addContacts(phoneNumber, index + 1);
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader(System.in);
        int T = fr.nextInt();
        while (T-- > 0) {
            Contact root = new Contact();
            int N = fr.nextInt();
            String[] numbers = new String[N];
            for (int j = 0; j < N; j++)
                numbers[j] = fr.nextLine();
            Arrays.sort(numbers);
            boolean able = true;
            for (int j = 0; j < N; j++) {
                if (!root.addContacts(numbers[j], 0)) {
                    able = false;
                    break;
                }
            }
            sb.append(able ? "YES" : "NO").append('\n');
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