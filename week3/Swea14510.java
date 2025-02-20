package week3;

import java.io.*;
import java.util.*;
 
public class Swea14510 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int testCase = Integer.parseInt(br.readLine());
         
        for(int t = 1; t <= testCase ; t++) {
             
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int maxNumber = 0;
            int oneAddCount = 0;
            int twoAddCount = 0;
            int result = 0;
             
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                maxNumber = Math.max(maxNumber, arr[i]);
            }
             
            // one, two 카운트 세기
            for(int i = 0; i < N ; i++) {
                twoAddCount += (maxNumber - arr[i]) / 2;
                oneAddCount += (maxNumber - arr[i]) % 2;
            }
             
            // one, two 카운트 조절
            if (twoAddCount >= oneAddCount + 2) {
                int term = (twoAddCount - oneAddCount + 1) / 3;
                twoAddCount -= term;
                oneAddCount += term * 2;
            }
             
            // 경우 1 : one 카운트가 하나 더 많음
            if (twoAddCount + 1 == oneAddCount)
                result = twoAddCount * 2 + 1;
             
             
            // 경우 2 : 카운트가 같거나 two카운트가 하나 더 많음
            else if (twoAddCount == oneAddCount || twoAddCount == oneAddCount + 1)
                result = twoAddCount * 2;
             
             
            // 경우 3 : one 카운트가 2개이상 많음
            else
                result = oneAddCount * 2 - 1;
             
            System.out.printf("#%d %d\n", t, result);
        }
    }
}