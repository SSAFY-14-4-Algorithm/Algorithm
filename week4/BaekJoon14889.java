package week4;
import java.util.*;
import java.io.*;

/*
 * 메모리 : 15,448 kb
 * 실행시간 : 328 ms
 */

public class BaekJoon14889 {
	
	/* 
	 * 1. 팀을 N/2로 나눈다 -> 조합
	 * 2. 각 팀의 능력치 합을 구한다.
	 *   - 팀 N/2명에서 2명씩뽑아 계산 -> 조합
	 * 3. 두 팀의 능력치 차이를 구한다
	 * 4. 능력치의 최솟값(diffMin)보다 작다면 최솟값을 갱신한다.
	 */
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] ability;
    static int[] aTeam;
    static int[] bTeam;
    static boolean[] visited;
    static int N, sumA, sumB, diffMin = Integer.MAX_VALUE;
    
    // 팀 나누기
    private static void CombinationTeam(int depth, int start, int r) {
    
        if(depth == r) {
            int ida = 0;
            int idb = 0;
            for(int i=1;i<=N;i++) {
                if(visited[i]) { 
                	aTeam[ida++] = i;
                }else {
                	bTeam[idb++] = i;
                }
            }
            CombinationTwo(0, aTeam, new int[2], 0, 2, "A"); // A팀에서 2명씩 뽑기
            CombinationTwo(0, bTeam, new int[2], 0, 2, "B"); // B팀에서 2명씩 뽑기 
            
            // a팀, b팀 능력치 차이
            diffMin = Math.min(diffMin, Math.abs(sumA-sumB));
            
            // 합 초기화
            sumA = 0;
            sumB = 0;
            
            return;
        }
        
        for(int i=start;i<=N;i++) {
        	visited[i] = true;
        	CombinationTeam(depth+1, i+1, r);
            visited[i] = false;
        }
        
    }
    // 각 팀에서 2명씩 뽑기
    private static void CombinationTwo(int depth, int[] target, int[] result, int start, int r, String team) {
        
        if(depth == r) {
          	CalculateAbility(result, team); // 뽑은 2명으로 능력치 계산
            return;
        }
        
        for(int i=start;i<N/2;i++) {
        	result[depth] = target[i];
        	CombinationTwo(depth+1,target, result, i+1, r, team);
        }
        
    }
    
    // 배열 조회 후 능력치 합 계산
    private static void CalculateAbility(int[] target, String team) {
        
        // 능력치 합 계산
        int u = target[0];
        int v = target[1];
        int sum = ability[u-1][v-1] + ability[v-1][u-1]; 
        
        if(team.equals("A")) sumA += sum;
        else if(team.equals("B")) sumB += sum;

    }
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        
        ability = new int[N][N];
        aTeam = new int[N/2];
        bTeam = new int[N/2];
        visited = new boolean[N+1];
        
        sumA = 0;
        sumB = 0;
        
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 팀 나누기
        CombinationTeam(0, 1, N/2);
        
        System.out.println(diffMin);
        
    }

}