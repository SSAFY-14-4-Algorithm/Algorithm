import java.io.*;
import java.util.*;
/*
 * 메모리:20688kB
 * 시간:	204ms
 */
public class Baekjoon_17182_우주탐사선 {
    static int N, K;
    static int[][] dist;
    static List<Integer> planets = new ArrayList<>();
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);

        dist = new int[N][N];
        for(int i=0;i<N;i++) {
            String[] s2 = br.readLine().split(" ");
            for(int j=0;j<N;j++) {
                dist[i][j] = Integer.parseInt(s2[j]);
            }
        }
        // 플로이드-워셜로 모든 쌍 최단 거리 계산
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // 시작 행성을 제외한 나머지 행성 리스트 구성
        for(int i=0;i<N;i++) {
            if(i!=K) planets.add(i);
        }

        // 순열로 방문경로 탐색
        Permutation(0, new boolean[N - 1], new ArrayList<>());

        System.out.println(minTime);
    }

    static void Permutation(int depth, boolean[] visited, List<Integer> path) {
        if(depth == N-1) {
        	// 모든 행성을 하나씩 다 골랐으면 총 이동 시간 계산
            int total = 0;
            int from = K;

            for(int to : path) {
                total += dist[from][to];
                from = to;
            }
            //최소값 갱신
            minTime = Math.min(minTime, total);
            return;
        }
        // 아직 경로가 완성되지 않았으면 가능한 행성 선택
        for(int i=0;i<N-1;i++) {
            if(!visited[i]) {
                visited[i] = true;
                path.add(planets.get(i));
                Permutation(depth + 1, visited, path);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
}
