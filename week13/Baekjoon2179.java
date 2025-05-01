package week13;

import java.io.*;
import java.util.*;

/**
 * 걍 시간 널널해서 완탐 가능할듯
 * 
 *  메모리 : 15764
 *  시간 : 184
 */

public class Baekjoon2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] strArr = new String[n];
        for (int i = 0; i < n; i++) {
            strArr[i] = br.readLine();
        }

        int a = 0, b = 0, max = 0;
        for (int i = 0; i < n - 1; i++) {
        	
            if(max >= strArr[i].length()) continue;
            for (int j = i+1; j < n; j++) {
            	
                if(max >= strArr[j].length()) continue;
                int cnt = 0;
                int repeat = Math.min(strArr[i].length(), strArr[j].length());
                for (int k = 0; k < repeat; k++) {
                    if(strArr[i].charAt(k) == strArr[j].charAt(k)) cnt++;
                    else break;
                }

                if(cnt > max){
                    max = cnt;
                    a = i;
                    b = j;
                }
            }
        }

        sb.append(strArr[a]).append("\n").append(strArr[b]);
        System.out.println(sb);
    }
}