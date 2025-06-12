import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 
 * 47852kb	300ms
 * 
 * 다음 줄로 내려가는 조건
 * 바로 아래의 수로 넘어가거나, 바로 아래의 수와 붙어있는 수만 이동 가능
 *
 * dp[i][0] = arr[i][0] + max(dp[i-1][0],dp[i-1][1])
 * dp[i][1] = arr[i][1] + max(dp[i-1][0], dp[i-1][1], dp[i-1][2])
 * dp[i][2] = arr[i][2] + max(dp[i-1][1], dp[i-1][2])
 *
 * 10만 x 10만 = 10억, int 4byte, 40억 -> 400mb
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(reader.readLine());

        int[][] arr = new int[n][3];
        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];

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
            if(i%2==1) { //홀수 행일 땐 1행에 저장
                for (int j = 0; j < 3; j++) {
                    if(j==0) {
                        maxDp[1][j] = arr[i][j] + Math.max(maxDp[0][0], maxDp[0][1]);
                        minDp[1][j] = arr[i][j] + Math.min(minDp[0][0], minDp[0][1]);
                    }
                    else if(j==2) {
                        maxDp[1][j] = arr[i][j] + Math.max(maxDp[0][2], maxDp[0][1]);
                        minDp[1][j] = arr[i][j] + Math.min(minDp[0][2], minDp[0][1]);
                    }
                    else {
                        maxDp[1][j] = arr[i][j] + Math.max(Math.max(maxDp[0][j-1], maxDp[0][j]), maxDp[0][j+1]);
                        minDp[1][j] = arr[i][j] + Math.min(Math.min(minDp[0][j-1], minDp[0][j]), minDp[0][j+1]);
                    }
                }
            }
            else { //짝수 행일 땐 0행에 저장
                for (int j = 0; j < 3; j++) {
                    if(j==0) {
                        maxDp[0][j] = arr[i][j] + Math.max(maxDp[1][0], maxDp[1][1]);
                        minDp[0][j] = arr[i][j] + Math.min(minDp[1][0], minDp[1][1]);
                    }
                    else if(j==2) {
                        maxDp[0][j] = arr[i][j] + Math.max(maxDp[1][2], maxDp[1][1]);
                        minDp[0][j] = arr[i][j] + Math.min(minDp[1][2], minDp[1][1]);
                    }
                    else {
                        maxDp[0][j] = arr[i][j] + Math.max(Math.max(maxDp[1][j-1], maxDp[1][j]), maxDp[1][j+1]);
                        minDp[0][j] = arr[i][j] + Math.min(Math.min(minDp[1][j-1], minDp[1][j]), minDp[1][j+1]);
                    }
                }
            }

        }


        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        if(n%2==0) { //n이 짝수일 땐 1행에 결과값 저장됨
            for (int i = 0; i < 3; i++) {
                max = Math.max(maxDp[1][i], max);
                min = Math.min(minDp[1][i], min);
            }
        }
        else { //n이 홀수일 땐 0행에 결과값 저장됨
            for (int i = 0; i < 3; i++) {
                max = Math.max(maxDp[0][i], max);
                min = Math.min(minDp[0][i], min);
            }
        }


        System.out.println(max + " " + min);
    }
}

