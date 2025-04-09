import java.io.*;
import java.util.*;


public class Baekjoon9084 {
	static int t, n, m;
	static int[] money;
    static int ans;
    static int[] dp; //각 인덱스(금액)을 만들 수 있는 경우의 수

    //메모리 14156kb 시간 108ms
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            money = new int[n+1];
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            
            for (int i = 1 ; i <= n; i++) {
            	money[i] = Integer.parseInt(st.nextToken());
            }
            m = Integer.parseInt(br.readLine());
            
            dp = new int[m+1];
            
            for (int i = 1; i <= n; i++) {
            	for (int j = 1; j <= m; j++) {
                	if (j-money[i] > 0) {
                		dp[j] = dp[j] + dp[j-money[i]];
                	} else if (j-money[i] == 0) {
                		dp[j]++;
                	}
                }
            }
            
            System.out.println(dp[m]);
            
        }

    }
}
