import java.io.*;
import java.util.*;

/*
 * 메모리:11556KB	
 * 시간:72ms
 */
public class Baekjoon_1563_개근상 {
  static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  static final int MOD=1_000_000;

  public static void main(String[] args)throws IOException {
    int n= Integer.parseInt(br.readLine());

    // dp[i][j][k] = i일째까지 출결했고, 지각 j번, 연속 결석 k번일 때 가능한 경우의 수
    int[][][] dp=new int[n+1][2][3];
    dp[0][0][0]=1;

    for(int i=0;i<n;i++) {
      for(int j=0;j<=1;j++) {
        for(int k=0;k<=2;k++) {
          int val=dp[i][j][k];
          
          if(val==0)
        	  continue;

          // 출석(O): 지각은 그대로, 결석 0으로 초기화
          dp[i+1][j][0]=(dp[i+1][j][0]+val)%MOD;

          // 지각(L): 지각 1회 추가, 결석 0으로 초기화 (지각 1회까지만 허용)
          if(j<1)
        	  dp[i+1][j+1][0]=(dp[i+1][j+1][0]+val)%MOD;

          // 결석(A): 연속 결석 1회 추가 (3회 연속까진 안됨)
          if(k<2)
        	  dp[i+1][j][k+1]=(dp[i+1][j][k+1]+val)%MOD;
        }
      }
    }

    // n일째 상태 중 유효한 모든 경우 합산
    int result=0;
    for(int l=0;l<=1;l++) {
      for(int a=0;a<=2;a++) {
        result=(result+dp[n][l][a])%MOD;
      }
    }

    System.out.println(result);
  }
}
