import java.io.*;
import java.util.*;
/*
 * 메모리:68700KB
 * 시간:	728ms
 */
public class Baekjoon_1939_중량제한 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent;
    
    static void union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	if (a == b) return;
    	parent[a] = b;
    }
    
    static int find(int x) {
    	if (parent[x] == x) return x;
    	return parent[x] = find(parent[x]);
    }
    
    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            s = br.readLine().split(" ");
            edges[i][0] = Integer.parseInt(s[0]);
            edges[i][1] = Integer.parseInt(s[1]);
            edges[i][2] = Integer.parseInt(s[2]);
        }
        s = br.readLine().split(" ");
        int start = Integer.parseInt(s[0]);
        int end   = Integer.parseInt(s[1]);
        
        // 간선 중량 기준 내림차순 정렬
        Arrays.sort(edges, (a, b) -> Integer.compare(b[2], a[2]));
        
        // Union-Find 초기화
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        //간선을 하나씩 연결하면서 start, end가 연결되는 순간의 C 출력
        for (int i = 0; i < M; i++) {
            union(edges[i][0], edges[i][1]);
            if (find(start) == find(end)) {
                System.out.println(edges[i][2]);
                break;
            }
        }
    }
    
}
