
import java.io.*;
import java.util.*;

public class Beakjoon16938 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int[] problem;
    static int n, l, r, x, res;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        problem = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            problem[i] = Integer.parseInt(st.nextToken());
        }

        subset(0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        System.out.println(res);
    }

    public static void subset(int cnt, int sum, int count, int min, int max) {
        if (sum > r) return; 

        if (cnt == n) {
            if (count >= 2 && sum >= l && max - min >= x) {
                res++;
            }
            return;
        }

      
        subset(cnt + 1, sum + problem[cnt], count + 1, Math.min(min, problem[cnt]), Math.max(max, problem[cnt]));
     
        subset(cnt + 1, sum, count, min, max);
    }
}
