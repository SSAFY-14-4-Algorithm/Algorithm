package week2;

import java.io.*;
import java.math.BigInteger;

public class Baekjoon1914 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
   public static void main(String[] args) throws IOException{
    N = Integer.parseInt(br.readLine());
    bw.write(BigInteger.TWO.pow(N).subtract(BigInteger.ONE) + "\n");
    if(N <= 20){
        hanoi(N,1,2,3);
    }
    bw.flush();
    bw.close();
    br.close();
   }

   private static void hanoi(int n,int start, int mid, int end) throws IOException{
    if(n == 1){
        bw.write(start + " " + end+"\n");
        return;
    }

    hanoi(n-1,start,end,mid);
    bw.write(start + " " + end+"\n");
    hanoi(n-1,mid,start,end);
   }
}
