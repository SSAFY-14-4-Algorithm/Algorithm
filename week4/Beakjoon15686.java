import java.io.*;
import java.util.*;

public class Beakjoon15686 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int minDiff = Integer.MAX_VALUE;
    static ArrayList<int[]> home = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static int[][] dist;
    static int[] output;

    public static void main(String[] args) throws IOException {
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int m = Integer.parseInt(s1[1]);
        map = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            String[] s2 = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(s2[j]);
                if (map[i][j] == 1) {
                    home.add(new int[]{i, j});
                }
                if (map[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }


        dist = new int[home.size()][chicken.size()];
        for (int i = 0; i < home.size(); i++) {
            for (int j = 0; j < chicken.size(); j++) {
                dist[i][j] = Math.abs(home.get(i)[0] - chicken.get(j)[0]) + Math.abs(home.get(i)[1] - chicken.get(j)[1]);
            }
        }
        
        for (int i = 1; i <= m; i++) {
            output = new int[i];
            Combination(0, 0, chicken.size(), i);
        }
        
        System.out.println(minDiff);
    }

    public static void Combination(int depth, int start, int n, int r) {
        if (depth == r) {
            int totalDist = 0;
            for (int i = 0; i < home.size(); i++) {
                int minDist = Integer.MAX_VALUE;
                for (int j : output) {
                    minDist = Math.min(minDist, dist[i][j]);
                }
                totalDist += minDist;
            }
            minDiff = Math.min(minDiff, totalDist);
            return;
        }
        
        for (int i = start; i < n; i++) {
            output[depth] = i;
            Combination(depth + 1, i + 1, n, r);
        }
    }
}
