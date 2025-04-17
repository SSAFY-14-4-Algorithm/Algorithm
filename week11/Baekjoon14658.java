import java.io.*;
import java.util.*;
/*
 * 메모리: 13376KB
 * 시간: 132ms
 * 
 * 부르트 포스 알고리즘(완탐)
 * 
 * 
 */
public class Baekjoon14658{
    static int N, M, L, K;
    static int[][] stars;
    static int maxCatch = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); 

        stars = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }


        for (int[] s1 : stars) { 
            for (int[] s2 : stars) {
                int sX = s1[0]; 
                int sY = s2[1];
                int cnt = 0;
                for (int[] star : stars) {
                    int x = star[0];
                    int y = star[1];
                    if (sX <= x && x <= sX + L &&  sY <= y && y <= sY + L) { //트램펄린 범위에 들어가는 별똥별이 몇개인지 카운트 
                        cnt++;
                    }
                }
                maxCatch = Math.max(maxCatch, cnt);
            }
        }
        System.out.println(K - maxCatch);
    }
}
