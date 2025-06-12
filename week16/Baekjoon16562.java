import java.io.*;
import java.util.*;

public class Baekjoon16562 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static int[] parent, cost;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        cost = new int[N + 1];
        
        makeSet();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            union(v, w);
        }

        Set<Integer> roots = new HashSet<>();
        int total = 0;
        for (int i = 1; i <= N; i++) {
            int root = find(i);
            if (!roots.contains(root)) {
                total += cost[root];
                roots.add(root);
            }
        }

        System.out.println(total <= K ? total : "Oh no");
    }

    static void makeSet() {
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    static int find(int a) {
        if (parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;
        
        if (cost[rootA] < cost[rootB]) parent[rootB] = rootA;
        else parent[rootA] = rootB;
    }
}
