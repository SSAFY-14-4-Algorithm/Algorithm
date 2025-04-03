package week9;

/**
 * JAVA11 : 메모리 42,136 KB 시간 340ms
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon4195 {
    private static final int BUFFER_SIZE = 1 << 16;
    private static final InputStream in = System.in;
    private static final byte[] buffer = new byte[BUFFER_SIZE];
    private static final StringBuilder tempSB = new StringBuilder();
    private static int bufferPointer = 0, bytesRead = 0;

    private static Map<String, Node> nameIndexMap;

    private static class Node {
        Node parent;
        int setSize;

        Node() {
            parent = this;
            setSize = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        while (T-- > 0) {
            int F = readInt();
            nameIndexMap = new HashMap<>();
            while (F-- > 0) {
                String name1 = readString();
                String name2 = readString();
                nameIndexMap.putIfAbsent(name1, new Node());
                nameIndexMap.putIfAbsent(name2, new Node());
                Node friend1 = nameIndexMap.get(name1);
                Node friend2 = nameIndexMap.get(name2);
                union(friend1, friend2);
                sb.append(find(friend1).setSize).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static Node find(Node x) {
        if (x.parent == x)
            return x;
        return x.parent = find(x.parent);
    }

    private static void union(Node x, Node y) {
        Node rootX = find(x);
        Node rootY = find(y);

        if (rootX == rootY)
            return;

        if (rootX.setSize < rootY.setSize) {
            rootX.parent = rootY;
            rootY.setSize += rootX.setSize;
        } else {
            rootY.parent = rootX;
            rootX.setSize += rootY.setSize;
        }
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        c = read();
        boolean isNegative = false;
        while (c <= ' ')
            c = read();
        if (c == '-') {
            isNegative = true;
            c = read();
        }
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = read();
        }
        return isNegative ? -n : n;
    }

    private static String readString() throws IOException {
        int c = read();
        while (c <= ' ')
            c = read();
        tempSB.setLength(0);
        while (c > ' ') {
            tempSB.append((char) c);
            c = read();
        }
        return tempSB.toString();
    }

    private static int read() throws IOException {
        if (bufferPointer == bytesRead) {
            bytesRead = in.read(buffer, 0, BUFFER_SIZE);
            if (bytesRead == -1)
                return -1;
            bufferPointer = 0;
        }
        return buffer[bufferPointer++] & 0xff;
    }

}
