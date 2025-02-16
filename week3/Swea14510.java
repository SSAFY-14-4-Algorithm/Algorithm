package week3;

import java.util.*;
import java.io.*;

public class Swea14510{
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int answer = solution();
            bw.write("#"+t+" "+answer+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        String [] tokens = br.readLine().split(" ");

        int maxHeight = 0;
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(tokens[i]);
            maxHeight = Math.max(maxHeight, arr[i]);
        }

        int odd = 0;
        int even = 0;

        for(int i=0;i<N;i++){
            int diff = maxHeight - arr[i];
            
            if(diff == 0) continue;

            odd += diff % 2;
            even += diff / 2;
        }

        if(even > odd){
            while(Math.abs(even - odd) > 1){
                even--;
                odd += 2;
            }
        }

        int result = 0;
        if(odd > even){
            result = odd * 2 -1;
        }else if(even > odd){
            result = even * 2;
        }else{
            result = odd + even;
        }
        return result;
    }
}