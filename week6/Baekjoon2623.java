import java.io.*;
import java.util.*;

public class Baekjoon2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 가수의 수
        int M = Integer.parseInt(st.nextToken()); // PD의 수
        
        // 그래프 초기화: 인접 리스트로 구현
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 진입 차수 배열 초기화
        int[] inDegree = new int[N + 1];
        
        // PD들이 정한 순서 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken()); // 해당 PD가 담당한 가수의 수
            
            if (count <= 1) continue; // 담당 가수가 1명 이하면 순서가 없음
            
            int prev = Integer.parseInt(st.nextToken());
            
            // 순서대로 간선 추가
            for (int j = 1; j < count; j++) {
                int current = Integer.parseInt(st.nextToken());
                graph[prev].add(current); // prev -> current로 간선 추가
                inDegree[current]++; // current의 진입 차수 증가
                prev = current;
            }
        }
        
        // 위상 정렬 수행
        Queue<Integer> queue = new LinkedList<>();
        
        // 진입 차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            
            // 현재 노드와 연결된 모든 간선 제거
            for (int next : graph[current]) {
                inDegree[next]--; // 진입 차수 감소
                
                // 진입 차수가 0이 된 노드를 큐에 삽입
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // 위상 정렬 결과 출력
        if (result.size() == N) { // 모든 노드를 방문한 경우
            StringBuilder sb = new StringBuilder();
            for (int singer : result) {
                sb.append(singer).append('\n');
            }
            System.out.println(sb);
        } else { // 사이클이 존재하는 경우
            System.out.println(0);
        }
    }
}