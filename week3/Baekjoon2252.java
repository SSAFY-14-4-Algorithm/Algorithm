package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Baekjoon2252{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N,M;
    private static int [] arr;
    private static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        arr = new int[N+1];
        graph = new ArrayList<>();

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);

            arr[b]++;
            graph.get(a).add(b);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<arr.length;i++){
            if(arr[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()){
            int curNode = queue.poll();
            bw.write(curNode+" ");

            for(int nextNode : graph.get(curNode)){
                arr[nextNode]--;
                if(arr[nextNode] == 0) queue.add(nextNode);
            }
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}