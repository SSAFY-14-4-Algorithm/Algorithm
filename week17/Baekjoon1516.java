import java.io.*;
import java.util.*;

/*
 *메모리: 19248KB
 *시간: 176ms
 *
 *위상정렬 , dp(dp[i]:i번 건물 완성까지 걸리는 최소 시간 
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> adj = new ArrayList<>(N + 1);   
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        int[] build = new int[N + 1];   // 건물 짓는게 걸리는 시간
        int[] indeg = new int[N + 1];   //더 우선 되는 건물 개수
        int[] dp    = new int[N + 1];   


        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            build[i] = Integer.parseInt(st.nextToken());   
            dp[i]    = build[i];                           
            while (true) {                                 
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) break;
                adj.get(pre).add(i);  
                indeg[i]++;           
            }
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++)
            if (indeg[i] == 0) q.add(i);                   

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int nxt : adj.get(cur)) {             
                dp[nxt] = Math.max(dp[nxt], dp[cur] + build[nxt]);
                if (--indeg[nxt] == 0) q.add(nxt);         
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++)
            sb.append(dp[i]).append('\n');
        System.out.print(sb);
    }
}