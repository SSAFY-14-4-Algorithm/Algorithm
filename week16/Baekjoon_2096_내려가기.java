import java.io.*;

/**
 * 250612
 * Java8 | 실행시간: 196 ms, 메모리: 17412 KB
 */

public class Baekjoon_2096_내려가기 {

    public static void main(String[] args) throws IOException{

        int N = readInt();
        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];
        for (int i=0;i<3;i++) maxDp[0][i] = minDp[0][i] = readInt();

        for (int i=1;i<N;i++){
            int a = readInt(), b = readInt(), c = readInt();
            maxDp[1][0] = Math.max(maxDp[0][0], maxDp[0][1]) + a;
            maxDp[1][1] = Math.max(Math.max(maxDp[0][0], maxDp[0][1]), maxDp[0][2]) + b;
            maxDp[1][2] = Math.max(maxDp[0][1], maxDp[0][2]) + c;

            minDp[1][0] = Math.min(minDp[0][0], minDp[0][1]) + a;
            minDp[1][1] = Math.min(Math.min(minDp[0][0], minDp[0][1]), minDp[0][2]) + b;
            minDp[1][2] = Math.min(minDp[0][1], minDp[0][2]) + c;

            for (int j=0;j<3;j++){
                maxDp[0][j] = maxDp[1][j];
                minDp[0][j] = minDp[1][j];
            }
        }

        int maxResult = Math.max(Math.max(maxDp[0][0], maxDp[0][1]), maxDp[0][2]);
        int minResult = Math.min(Math.min(minDp[0][0], minDp[0][1]), minDp[0][2]);

        System.out.printf("%d %d", maxResult, minResult);
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
