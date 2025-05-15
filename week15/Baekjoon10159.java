import java.io.*;
import java.util.*;
/*
 * 메모리: 16640
 * 시간: 116
 * 
 * 물건 무게 비교 
 * 플로이드
 * 
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int heavier = Integer.parseInt(st.nextToken());
            int lighter = Integer.parseInt(st.nextToken());
            graph[heavier][lighter] = true;
        }
        
        for (int k = 1; k <= N; k++) { //플로이드 워셜로 비교를 할 수 있도록 연결되는 물건들을 찾아서 true처리 
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (!graph[i][j] && !graph[j][i]) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
