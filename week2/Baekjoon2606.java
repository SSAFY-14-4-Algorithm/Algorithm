package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon2606{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static ArrayList<ArrayList<Integer>> graph;
    private static int N;
    public static void main(String[] args) throws IOException{
        graph = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            String [] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int answer = bfs(1, new boolean[N+1]);
        bw.write(answer-1+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int start, boolean [] visited){
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()){
            int curNode = queue.poll();
            count++;

            for(int nextNode : graph.get(curNode)){
                if(visited[nextNode]) continue;

                visited[nextNode] = true;
                queue.add(nextNode);
            }
        }
        return count;
    }
}