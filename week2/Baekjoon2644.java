package week2;

import java.io.*;
import java.util.*;

public class Baekjoon2644{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static ArrayList<ArrayList<Integer>> graph;
    private static int N;

    private static class Node{
        int num;
        int depth;

        public Node(int num,int depth){
            this.num = num;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        String [] tokens = br.readLine().split(" ");
        int start = Integer.parseInt(tokens[0]);
        int target = Integer.parseInt(tokens[1]);

        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bw.write(bfs(start,target,new boolean[N+1])+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int start, int target, boolean [] visited){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start,0));
        visited[start] = true;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();

            if(curNode.num == target){
                return curNode.depth;
            }

            for(int j=0;j<graph.get(curNode.num).size();j++){
                int nextNode = graph.get(curNode.num).get(j);
                if(visited[nextNode]) continue;

                visited[nextNode] = true;
                queue.add(new Node(nextNode, curNode.depth+1));
            }
        }

        return -1;
    }
}