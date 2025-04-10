import java.io.*;
import java.util.*;
/*
 * 메모리: 17240KB
 * 시간: 252ms
 * 
 * 도시N,노선 M
 * 노선: 출발도시, 도착도시,소요시간
 * 도시 이동의 최단 시간을 순서대로 출력 
 * 
 * 벨만포드
 * 1번 도시에서 시작  -> 1번에 연결된 도시들 갱신 -> 갱신된 도시 기반으로 그 도시에 연결된 도시들 또 갱신-> 계속 반복 
 */
public class Main {
	
    static class Edge { //출발,도착, 소요시간 
        int from, to, time;
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.time = cost;
        }
    }
    static final long max = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, time));
        }

        long[] minTime = new long[N + 1]; //1번 도시부터 각 도시까지 가는데 걸리는 최소시간 
        Arrays.fill(minTime, max);
        minTime[1] = 0; 
        for (int i = 1; i < N; i++) {
            for (Edge e : edges) {
                if ((minTime[e.from] != max) && (minTime[e.to] > minTime[e.from] + e.time)) { //출발도시까지 최소 시간 계산 된 상황 && 새 경로가 더 빠르면 >> 갱신  
                    minTime[e.to] = minTime[e.from] + e.time;
                }
            }
        }

        boolean hasNegativeCycle = false; // 음수 사이클 확인(N-1번 반복 후 또 되면(N번 가능) 음수 사이클이 존재 
        for (Edge e : edges) {
            if ((minTime[e.from] != max )&& (minTime[e.to] > minTime[e.from] + e.time)) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (minTime[i] == max) {
                    System.out.println("-1");
                } else {
                    System.out.println(minTime[i]);
                }
            }
        }
    }
}
