/*
 * BOJ 1464번 : 뒤집기 3
 * 메모리 : 11,524kb
 * 시간 : 68ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_1464_뒤집기3 {
    static String S = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        S = br.readLine();
        sb.append(S.charAt(0));

        for(int s = 1; s < S.length(); s++) {
            if(S.charAt(s) <= sb.charAt(0)) {
                sb.insert(0, S.charAt(s));
            } else {
                sb.append(S.charAt(s));
            }
        }

        System.out.println(sb);
        br.close();
    }
}
