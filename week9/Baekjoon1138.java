import java.io.*;
import java.util.Arrays;

public class Baekjoon1956 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 987654321;
    private static int V, E;
    private static int[][] cost;

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        V = Integer.parseInt(tokens[0]);
        E = Integer.parseInt(tokens[1]);
        cost = new int[V + 1][V + 1];

        // 초기화
        for (int i = 1; i <= V; i++) {
            Arrays.fill(cost[i], MAX);
            cost[i][i] = 0;
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);

            cost[a][b] = c;
        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        // 최소 사이클 찾기
        int ans = MAX;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i == j) continue;
                if (cost[i][j] != MAX && cost[j][i] != MAX) {
                    ans = Math.min(ans, cost[i][j] + cost[j][i]);
                }
            }
        }

        if (ans == MAX) bw.write("-1\n");
        else bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}