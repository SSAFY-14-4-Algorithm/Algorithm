package Baekjoon;

import java.io.*;
import java.util.*;
/*
 * 메모리: 14156KB
 * 시간: 100ms
 */

public class Baekjoon13171{
    static final long Mod = 1000000007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A = Long.parseLong(br.readLine()); 
        long X = Long.parseLong(br.readLine());
        
        System.out.println(modCal(A, X));
    }
    
    static long modCal(long A, long X) {
        long res = 1;
        A%=Mod;
        while (X > 0) {
            if (X % 2 == 1) res = (res * A) % Mod;
            A = (A * A) % Mod;
            X /= 2;
        }
        return res;
    }
}
