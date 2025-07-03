import java.io.*;
import java.util.*;
/*
 * 메모리:308076KB
 * 시간:	1028ms
 */
public class Baekjoon_9466_텀프로젝트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] choice;      // 학생 i가 선택한 학생 번호
    static boolean[] visited; // DFS 방문 표시
    static boolean[] finished;// DFS 완료 표시 (사이클 처리 후)
    static int teamCount;     // 팀에 속한 학생 수 카운트

    // DFS로 사이클(팀)을 찾고, 팀원 수를 teamCount에 더함
    static void dfs(int u) {
        visited[u] = true;           
        int v = choice[u];          
        if (!visited[v]) {
            dfs(v);
        } else if (!finished[v]) {
            for (int x = v; x != u; x = choice[x]) {
                teamCount++;
            }
            teamCount++; // 마지막 u 학생 포함
        }
        finished[u] = true;          
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim()); 
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim()); 
            choice    = new int[n + 1];
            visited   = new boolean[n + 1];
            finished  = new boolean[n + 1];
            teamCount = 0;

            // 선택 결과 입력
            String[] s = br.readLine().split(" ");
            for (int i=1;i<=n;i++) {
                choice[i] = Integer.parseInt(s[i-1]);
            }

            // 모든 학생에 대해 사이클 탐색
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            // 팀에 속하지 못한 학생 = 전체 학생 - teamCount
            sb.append((n-teamCount)).append('\n');
        }

        // 결과 출력
        System.out.print(sb);
    }
}
