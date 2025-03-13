package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 메모리 15932KB 실행시간 120ms
public class Baekjoon10830 {

    static int n;
    static long b;
    static int[][] list;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        b = Long.parseLong(input[1]);

        list = new int[n][n];

        for (int i = 0 ; i < n ; i ++ ) {
            input = br.readLine().split(" ");
            for (int j = 0 ; j < n ; j ++ ) {
                list[i][j] = Integer.parseInt(input[j]) % 1000;
            }
        }

        int[][] result = pow(list,b);
        for (int i = 0 ; i < n ; i ++ ) {
            for (int j = 0 ; j < n ; j ++ ) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[][] pow(int[][] A, long exp) {

        if(exp == 1L) {
            return A;
        }

        int[][] ret = pow(A, exp / 2);

        ret = cal(ret, ret);

        if(exp % 2 == 1L) {
            ret = cal(ret, list);
        }

        return ret;
    }


    public static int[][] cal(int[][] o1, int[][] o2) {

        int[][] ret = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {

                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= 1000;
                }
            }
        }
        return ret;
    }
}
