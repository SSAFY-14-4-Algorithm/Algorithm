import java.io.*;
import java.util.*;

/*
 * 메모리:
 * 시간: 	
 * 
 * N개 도시, 일부 도시 -> 도로로 연결
 * N*N 행렬: 도시 간 연결 여부
 * 여행계획 
 * 연결: yes, 연결x: no
 *
 */

public class Main {
	
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j); //연결되어있는 도시를 같은 집합으로 묶어줌 
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int fCity = Integer.parseInt(st.nextToken()); //시작 도시 
        int root = find(fCity);

        boolean p = true; //가능 여부 
        for (int i = 1; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());
            if (find(city) != root) {
                p = false;
                break;
            }
        }

        System.out.println(p ? "YES" : "NO");
    }
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); //루트를 찾
        }
        return parent[x];
    }
    static void union(int a, int b) {
        int rootA = find(a); 
        int rootB = find(b); 
        if (rootA != rootB) { //같은 집합이 아닌 경우 하나의 집합으로 합침 
            parent[rootB] = rootA; 
        }
    }
}
