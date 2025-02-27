
import java.util.*;
import java.io.*;

public class Beakjoon11051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

       
        int[][] comb = new int[n+1][k+1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || i == j) {
                    comb[i][j] = 1;
                } else {
                    
                    comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % 10007;
                }
            }
        }

        System.out.println(comb[n][k]);
    }
}

