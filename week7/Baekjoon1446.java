import java.util.*;
import java.io.*;

public class Baekjoon1446{
    static int N,D;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] distance;
    
    static class Node {
        int node;
        int weight;

        Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        for(int i=0;i<=10001;i++) graph.add(new ArrayList<>());
        distance = new int[10001];
        for(int i=0;i<distance.length;i++) distance[i] = i;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b,w));
        }

        dijkstra(0);

        System.out.println(distance[D]);
    }

    static void dijkstra(int start){
        if(start>D) return;

        if(distance[start+1] > distance[start] + 1) distance[start+1] = distance[start]+1;

        for(int i=0;i<graph.get(start).size();i++){
            if(distance[start]+graph.get(start).get(i).weight < distance[graph.get(start).get(i).node])
                distance[graph.get(start).get(i).node] = distance[start]+graph.get(start).get(i).weight;
        }

        dijkstra(start+1);
    }

}