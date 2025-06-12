import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 다음 줄로 내려가는 조건
 * 바로 아래의 수로 넘어가거나, 바로 아래의 수와 붙어있는 수만 이동 가능
 *
 * dp[i][0] = arr[i][0] + max(dp[i-1][0],dp[i-1][1])
 * dp[i][1] = arr[i][1] + max(dp[i-1][0], dp[i-1][1], dp[i-1][2])
 * dp[i][2] = arr[i][2] + max(dp[i-1][1], dp[i-1][2])
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(reader.readLine());

        int[][] arr = new int[n][3];
        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp배열 채우기
        for (int i = 0; i < 3; i++) {
            maxDp[0][i] = arr[0][i];
            minDp[0][i] = arr[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if(j==0) {
                    maxDp[i][j] = arr[i][j] + Math.max(maxDp[i-1][0], maxDp[i-1][1]);
                    minDp[i][j] = arr[i][j] + Math.min(minDp[i-1][0], minDp[i-1][1]);
                }
                else if(j==2) {
                    maxDp[i][j] = arr[i][j] + Math.max(maxDp[i-1][1], maxDp[i-1][2]);
                    minDp[i][j] = arr[i][j] + Math.min(minDp[i-1][1], minDp[i-1][2]);
                }
                else {
                    maxDp[i][j] = arr[i][j] + Math.max(Math.max(maxDp[i-1][j-1], maxDp[i-1][j]), maxDp[i-1][j+1]);
                    minDp[i][j] = arr[i][j] + Math.min(Math.min(minDp[i-1][j-1], minDp[i-1][j]), minDp[i-1][j+1]);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(maxDp[n-1][i], max);
            min = Math.min(minDp[n-1][i], min);
        }

        System.out.println(max + " " + min);
    }
}

