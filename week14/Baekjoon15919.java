import java.io.*;
import java.util.*;
/*
 *   메모리:18564KB
 *   시간:220ms
 */
public class Baekjoon15919 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        // 여행 기간 후보
        int[][] travel = new int[m+1][2];
        travel[0][0] = 0;
        travel[0][1] = 0;
        int[] dp = new int[m+1];
        Arrays.fill(dp, Integer.MAX_VALUE);  
        
        for (int i=0;i<m;i++) {
            String[] s = br.readLine().split(" ");
            travel[i][0] = Integer.parseInt(s[0]);  // 시작일
            travel[i][1] = Integer.parseInt(s[1]);  // 종료일
        }
        Arrays.sort(travel, (a,b) -> a[0]-b[0]);
        //dp[i]: travel[i]까지 선택했을 때 "지금까지의 최대 비여행 기간"의 최소 가능 값
        dp[0] = 0;

        for (int i=1;i<=m;i++) {
            for (int j=0;j<i;j++) {
                // 겹치지 않으려면 지난 구간의 끝 < 이번 구간의 시작
                if (travel[j][1] < travel[i][0]) {
                    // 두 구간 사이에 생기는 갭
                    int gap = travel[i][0]-travel[j][1]-1;
                    // 이전까지의 최대 갭과 이번 갭 중 더 큰 값 선택
                    int maxGap = Math.max(dp[j], gap);
                    //최대값 중 최소
                    dp[i] = Math.min(dp[i], maxGap);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i=0;i<=m;i++) {					//뒤쪽 남은 기간
            result = Math.min(result, Math.max(dp[i], n-travel[i][1]));
        }
        System.out.println(result);
    }
}
