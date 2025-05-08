import java.io.*;
import java.util.*;

/* 사자는 여행왕이야!!
 * 	14860kb 140ms
 */

public class Baekjoon15919 {
    static class Node {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        Node[] arr = new Node[M];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        // 시작점 기준 정렬
        Arrays.sort(arr, (a, b) -> a.start - b.start);
        
        // DP 초기화
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //여행 시작점, 0인 이유는 1로 시작하는 여행계획과 겹치지 않기 위한 것
        dp[0] = 0;
        
        // DP 계산
        //dp[i] = i일까지의 여행 중이 아닌 기간의 최댓값의 최소값
        //각 노드별로 겹치지 않는 값들과 계산
        for (Node node : arr) {
        	//고른 여행계획의 시작일 보다 이전 dp값 중 값이 정해진 것과 계산
            for (int i = node.start - 1; i >= 0; i--) {
                if (dp[i] != Integer.MAX_VALUE) {
                    dp[node.end] = Math.min(dp[node.end], Math.max(dp[i], node.start - i - 1));
                }
            }
        }
        
        //dp[i] = i일까지의 여행 중이 아닌 기간의 최대값의 최소값이므로
        //N-i값이 계산이 안되어 있으므로 계산해주고, 그 값 중 가장 작은 값 찾기
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                ans = Math.min(ans, Math.max(dp[i], N - i));
            }
        }

        System.out.println(ans);
    }
}
