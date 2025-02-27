import java.io.*;
import java.util.*;

public class Beakjoon14889 {
    static int N;
    static int[][] S;
    static boolean[] isSelected;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(input[j]);
            }
        }

        divideTeams(0, 0);
        System.out.println(minDiff);
    }

    static void divideTeams(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                divideTeams(i + 1, count + 1);
                isSelected[i] = false;
            }
        }
    }

 
    static void calculateDifference() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSelected[i] && isSelected[j]) {
                    startTeam += S[i][j] + S[j][i];
                } else if (!isSelected[i] && !isSelected[j]) {
                    linkTeam += S[i][j] + S[j][i];
                }
            }
        }

        minDiff = Math.min(minDiff, Math.abs(startTeam - linkTeam));
    }
}
