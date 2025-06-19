import java.io.*;
import java.util.*;

/**
 * 모든 건물을 짓는데 걸리는 최소의 시간을 이용해 근사
 *
 * 입력
 * 건물 종류 수 N
 * 각 건물을 짓는데 걸리는 시간과 그 건물을 짓기 위해 먼저 지어져야 하는 건물들의 번호
 *
 * 각 건물이 완성되기 까지 걸리는 최소 시간
 *
 * 풀이법
 * 각 건물들을 짓기 위해서 필요한 건물들을 짓는 데 필요한 시간을 더해서 계산
 * 이때, 필요한 건물들의 순서가 중요!
 * -> 위상 정렬을 이용하여 올바른 순서를 통해서 각 건물들을 짓는데 필요한 최소 시간을 갱신
 *
 * 위상정렬 + DP
 * 진입 차수가 없으면 큐에 넣고, 그 노드와 연결된 노드들의 진입차수를 차감
 * 진입차수를 감소시키면서, 최소시간을 갱신
 * -> result[cur] + time[next], result[next] 와 비교
 * : 이미 최대 시간인지 or 나를 짓는게 최대 시간인지 (건물을 모두 짓기 위해 필요한 시간 : 최대 시간)
 * 따라서 최소 시간 = 모든 건물을 짓기 위한 최대 시간
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(reader.readLine());
        eachTime = new int[N+1]; //각 인덱스 번호의 건물을 짓기 위한 단일 시간
        depth = new int[N+1]; //진입차수
        totalTime = new int[N+1]; //각 인덱스 번호의 건물을 짓기 위한 최종 시간
        graph = new ArrayList[N+1]; //1-based 각각 건물 번호와 연결된 인접리스트 배열


        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(reader.readLine());
            eachTime[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if(num==-1) break;
                graph[num].add(i);
                depth[i]++;
            }
        }

        sorting();
        for (int i = 1; i <= N; i++) {
            sb.append(totalTime[i]).append("\n");
        }
        System.out.print(sb);
    }

    static int N;
    static int[] eachTime,depth,totalTime;
    static ArrayList<Integer>[] graph;

    //위상 정렬
    static void sorting() {
        Queue<Integer> q = new ArrayDeque<>();

        //진입차수 0인 것들 추가
        for (int i = 1; i <= N; i++) if(depth[i] == 0) {
            totalTime[i] = eachTime[i]; //필요한 건물들이 없을 때
            q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) { //나와 연결된 건물들
                totalTime[next] = Math.max(totalTime[next], totalTime[cur] + eachTime[next]); //나를 짓는게 최종 시간인지, 이미 최종 시간인지
                depth[next]--; //진입차수 감소
                if(depth[next] == 0) q.offer(next);
            }
        }

    }

}
