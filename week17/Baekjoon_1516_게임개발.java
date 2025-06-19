import java.io.*;
import java.util.*;
/*
 * 메모리:21828kB	
 * 시간:220ms
 */
public class Baekjoon_1516_게임개발 {
    static int N;
    static List<Integer>[] graph;
    static int[] indegree, time, result;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        indegree = new int[N + 1];
        time = new int[N + 1];
        result = new int[N + 1];

        for(int i = 1; i <= N; i++) 
        	graph[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            String[] s = br.readLine().split(" ");
            time[i] = Integer.parseInt(s[0]); // 건설 시간

            for(int j = 1; j < s.length - 1; j++) {
                int pre = Integer.parseInt(s[j]);
                graph[pre].add(i);        
                indegree[i]++;           
            }
        }
        //위상 정렬
        topologySort();
    }

    static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();

        // 진입 차수가 0인 건물은 바로 지을 수 있음
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
                result[i] = time[i]; // 최초 건물은 자기 시간만 필요
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {
                indegree[next]--;

                // 현재 건물을 기준으로 다음 건물의 최소 완성 시간 갱신
                result[next] = Math.max(result[next], result[cur] + time[next]);

                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }
    }
}
