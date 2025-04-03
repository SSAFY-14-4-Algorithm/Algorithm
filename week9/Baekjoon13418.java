import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Baekjoon13418 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static ArrayList<Edge> edges;
    static int[] parents;
    
    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]) +1;
        M = Integer.parseInt(tokens[1]) +1;
        edges = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            tokens = br.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);
            edges.add(new Edge(from, to, cost));
        }
        
        int good = 0;
        int bad = 0;
        
        // 최악의 경우 (최소 비용 MST): 0인 간선 개수 최대화
        Collections.sort(edges, (e1, e2) -> Integer.compare(e1.cost, e2.cost));
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
        
        int count = 0;
        int idx = 0;
        
        while(count < N && idx < edges.size()) {
            Edge edge = edges.get(idx++);
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            
            if(find(from) != find(to)) {
                union(from, to);
                count++;
                if(cost == 0) bad++;
            }
        }
        
        // 최선의 경우 (최대 비용 MST): 0인 간선 개수 최소화
        Collections.sort(edges, (e1, e2) -> Integer.compare(e2.cost, e1.cost));
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
        
        count = 0;
        idx = 0;
        while(count < N && idx < edges.size()) {
            Edge edge = edges.get(idx++);
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            
            if(find(from) != find(to)) {
                union(from, to);
                count++;
                if(cost == 0) good++;
            }
        }
        
        bw.write((bad * bad - good * good) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    static void union(int i, int j) {
        int rootA = find(i);
        int rootB = find(j);
        if(rootA != rootB) {
            if(rootA < rootB)
                parents[rootB] = rootA;
            else
                parents[rootA] = rootB;
        }
    }
    
    static int find(int index) {
        if(index == parents[index])
            return index;
        else
            return parents[index] = find(parents[index]);
    }
    
    static class Edge {
        int from, to, cost;
        
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
