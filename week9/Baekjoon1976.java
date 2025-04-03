package week9;

/**
 * JAVA11 : 메모리 14,324 KB 시간 120ms
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;

public class Baekjoon1976 {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        parent = new int[N + 1];
        int M = readInt();
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                if (readInt() == 1 && i < j)
                    union(i, j);

        int from = readInt();
        M--;
        while (M-- > 0) {
            int to = readInt();
            if (find(from) != find(to)) {
                System.out.println("NO");
                return;
            }
            from = to;
        }
        System.out.println("YES");
    }

    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB)
            return false;
        parent[rootB] = rootA;
        return true;
    }

    public static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 47)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
