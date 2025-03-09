import java.io.IOException;

//JAVA11  14832KB	120ms
public class Baekjoon22856 {
    private static class Node {
        Node left, right;
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < N; i++) {
            int a = readInt();
            int b = readInt();
            int c = readInt();
            if (b != -1)
                nodes[a].left = nodes[b];
            if (c != -1)
                nodes[a].right = nodes[c];
        }
        int rightLastNodeDepth = 0;
        Node cur = nodes[1];
        while (cur.right != null) {
            rightLastNodeDepth++;
            cur = cur.right;
        }
        int result = (N > 1) ? (2 * (N - 1) - rightLastNodeDepth) : 0;
        System.out.print(result);
    }

    private static int readInt() throws IOException {
        int n = 0, c = System.in.read();
        boolean isNegative = false;
        while (c <= ' ')
            c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        while ('0' <= c && c <= '9') {
            n = n * 10 + (c & 15);
            c = System.in.read();
        }
        return isNegative ? -n : n;
    }
}
