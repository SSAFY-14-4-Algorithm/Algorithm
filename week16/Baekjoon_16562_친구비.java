import java.io.*;
import java.util.*;
/*
 * 메모리:19544KB
 * 시간:	176ms
 */
public class Baekjoon_16562_친구비 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent, cost;  // 부모 배열과 친구비 배열
    
    // Union
    static void union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	if (a != b) {
    		parent[b] = a;
    	}
    }

    // Find 
    static int find(int x) {
    	if (parent[x] == x) return x;
    	return parent[x] = find(parent[x]);
    }
    
    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int K = Integer.parseInt(s[2]);

        cost = new int[N+1];
        s = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(s[i-1]);
        }

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            union(a, b);
        }

        // 루트별 최소 친구비 계산
        int[] minCost = new int[N+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            int r = find(i);
            minCost[r] = Math.min(minCost[r], cost[i]);
        }

        long total = 0;
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i && minCost[i] != Integer.MAX_VALUE) {
                total += minCost[i];
            }
        }

        if (total <= K) {
            System.out.println(total);
        } else {
            System.out.println("Oh no");
        }
    }


}
