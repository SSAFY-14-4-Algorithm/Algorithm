import java.io.*;
import java.util.*;
/*
 * 메모리 53624KB
 * 시간 424ms
 */
public class Baekjoon11437 {
    // 최대 노드 수, 2^16 = 65536 > 50000 이므로 LOG=16 고정
    static final int MAXN = 50000;
    static final int LOG  = 16;

    static ArrayList<Integer>[] tree = new ArrayList[MAXN+1];
    // up[k][v]: 정점 v의 2^k 번째 조상
    static int[][] up   = new int[LOG+1][MAXN+1];
    // depth[v]: 루트(1번)로부터 v까지의 깊이
    static int[] depth  = new int[MAXN+1];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int i = 0; i < N-1; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            tree[u].add(v);
            tree[v].add(u);
        }
        //BFS로 depth[] 와 up[0][] (1차 부모) 채우기
        Queue<Integer> q = new LinkedList<>();
        depth[1] = 1;      // 루트 깊이 1
        q.add(1);
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : tree[u]){
                if(depth[v] == 0){      // 아직 방문 안 했으면
                    depth[v] = depth[u] + 1;  // 깊이 설정
                    up[0][v] = u;             // 부모 저장
                    q.add(v);
                }
            }
        }

        //이진 리프팅 테이블(up[k][v]) 채우기
        //    up[k][v] = up[k-1][ up[k-1][v] ]
        for(int k = 1; k <= LOG; k++){
            for(int v = 1; v <= N; v++){
                up[k][v] = up[k-1][ up[k-1][v] ];
            }
        }

        //쿼리 처리: M 쌍의 LCA 구하기
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            sb.append(lca(a, b)).append('\n');
        }

        // 결과 출력
        System.out.print(sb);
    }

    /**
     * LCA(u, v)를 반환
     * 1) 두 노드의 깊이를 같게 맞춘 뒤
     * 2) 가장 높은 2^k부터 내려오며 서로 다르면 같이 올려준다
     * 3) 결국 바로 위 부모(up[0][...])가 공통 조상
     */
    static int lca(int u, int v){
        // 1) 깊이 맞추기: u가 더 깊도록 swap
        if(depth[u] < depth[v]){
            int tmp = u; u = v; v = tmp;
        }
        int diff = depth[u] - depth[v];
        // diff의 비트마다 u를 위로 올림
        for(int k = 0; k <= LOG; k++){
            if((diff & (1 << k)) != 0){
                u = up[k][u];
            }
        }
        // 깊이 같아졌으면
        if(u == v) return u;
        // 2) 2^k 단위로 동시에 올려, 마지막에 부모가 LCA
        for(int k = LOG; k >= 0; k--){
            if(up[k][u] != up[k][v]){
                u = up[k][u];
                v = up[k][v];
            }
        }
        // 3) 최종 한 단계 위가 공통 조상
        return up[0][u];
    }
}