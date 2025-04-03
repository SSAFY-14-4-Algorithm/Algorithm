import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon14500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static int [][] map;
    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        map = new int[N][M];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int oneByFour = findMaxSumOneByFour();
        int twoByTwo = findMaxSumTwoByTwo();
        int oneByThreePlusOne = findMaxSumOneByThreePlusOne();
        int four = findMaxSumFourth();
        int fifth = findMaxSumFifth();

        int answer = Math.max(Math.max(Math.max(oneByFour,twoByTwo), Math.max(oneByThreePlusOne,four)),fifth);
        bw.write(answer+"\n");
        bw.flush();
        br.close();
        bw.close();
    }
    static int findMaxSumFifth(){
        int maxSum = Integer.MIN_VALUE;
        // ㅗ 모양
        for(int i=0;i<N-1;i++){
            for(int j=0;j<M-2;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i+1][j+x];
                }
                sum+=map[i][j+1];
                maxSum = Math.max(sum,maxSum);
            }
        }
        // ㅓ 모양
        for(int i=0;i<N-2;i++){
            for(int j=0;j<M-1;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i+x][j+1];
                }
                sum+=map[i+1][j];
                maxSum = Math.max(sum,maxSum);
            }
        }

        // ㅜ 모양
        for(int i=0;i<N-1;i++){
            for(int j=0;j<M-2;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i][j+x];
                }
                sum+=map[i+1][j+1];
                maxSum = Math.max(sum,maxSum);
            }
        }

        // ㅏ 모양
        for(int i=0;i<N-2;i++){
            for(int j=0;j<M-1;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i+x][j];
                }
                sum+=map[i+1][j+1];
                maxSum = Math.max(sum,maxSum);
            }
        }
        return maxSum;
    }

    static int findMaxSumFourth(){
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<N-2;i++){
            for(int j=0;j<M-1;j++){
                int sum = 0;
                for(int x=0;x<2;x++){
                    sum+=map[i+x][j];
                }
                for(int x=0;x<2;x++){
                    sum+=map[i+x+1][j+1];
                }
                maxSum = Math.max(sum,maxSum);
            }
        }

        for(int i=0;i<N-2;i++){
            for(int j=0;j<M-1;j++){
                int sum = 0;
                for(int x=0;x<2;x++){
                    sum+=map[i+x][j+1];
                }
                for(int x=0;x<2;x++){
                    sum+=map[i+x+1][j];
                }
                maxSum = Math.max(sum,maxSum);
            }
        }

        for(int i=0;i<N-1;i++){
            for(int j=0;j<M-2;j++){
                int sum =0;
                for(int x=0;x<2;x++){
                    sum+=map[i][j+x+1];
                }
                for(int x=0;x<2;x++){
                    sum+=map[i+1][j+x];
                }
                maxSum = Math.max(sum,maxSum);
            }
        }

        for(int i=0;i<N-1;i++){
            for(int j=0;j<M-2;j++){
                int sum = 0;
                for(int x=0;x<2;x++){
                    sum+=map[i][j+x];
                }
                for(int x=0;x<2;x++){
                    sum+=map[i+1][j+1+x];
                }
                maxSum = Math.max(sum,maxSum);
            }
        }
        return maxSum;
    }

    // L자 모양
    static int findMaxSumOneByThreePlusOne() {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j+2];
                maxSum = Math.max(maxSum, sum);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = 0;

                for (int x = 0; x < 3; x++) {
                    sum += map[i + 1][j + x];
                }

                sum += map[i][j + 2];
                maxSum = Math.max(maxSum, sum);
            }
        }

        // 기본 L자 모양 270도 회전
        for (int i = 0; i < N - 2; i++) {
            for (int j = 1; j < M; j++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j - 1];
                maxSum = Math.max(maxSum, sum);
            }
        }

        // 좌우 대칭 L자 모양 180도 회전
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i - 1][j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        // 좌우 대칭 L자 모양 270도 회전
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i][j + 1];
                maxSum = Math.max(maxSum, sum);
            }
        }

        for(int i=0;i<N-2;i++){
            for(int j=0;j<M-1;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i+x][j];
                }
                sum+=map[i+2][j+1];
                maxSum = Math.max(sum,maxSum);
            }
        }

        for(int i=0;i<N-2;i++){
            for(int j=0;j<M-1;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i+x][j+1];
                }
                sum+=map[i][j];
                maxSum = Math.max(sum,maxSum);
            }
        }

        for(int i=0;i<N-1;i++){
            for(int j=0;j<M-2;j++){
                int sum = 0;
                for(int x=0;x<3;x++){
                    sum+=map[i][j+x];
                }
                sum+=map[i+1][j];
                maxSum = Math.max(sum,maxSum);
            }
        }
        return maxSum;
    }

    static int findMaxSumOneByFour() {
        int maxSum = Integer.MIN_VALUE;

        // 세로 방향
        for (int i = 0; i < N - 3; i++) {
            for (int j = 0; j < M; j++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 3][j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        // 가로 방향
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 3; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3];
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
    static int findMaxSumTwoByTwo() {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}

