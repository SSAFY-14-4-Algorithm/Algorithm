import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Baekjoon1647 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static int N,M;
    private static String [] tokens;
    private static int [] parents;
    private static ArrayList<Node> graph;
    
    public static void main(String [] args) throws IOException{
    	tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	graph = new ArrayList<>();
    	parents = new int[N+1];
    	for(int i=0;i<M;i++) {
    		tokens = br.readLine().split(" ");
    		int from = Integer.parseInt(tokens[0]);
    		int to = Integer.parseInt(tokens[1]);
    		int cost = Integer.parseInt(tokens[2]);
    		
    		graph.add(new Node(from, to, cost));
    	}
    	
    	for(int i=0;i<=N;i++) {
    		parents[i] = i;
    	}
    	
    	//cost순으로 정렬
    	Collections.sort(graph);
    	
    	int totalCost = 0;
    	int count = 0;
    	int idx = 0;
    	while(count < N-2) {
    		//사이클이 존재하지 않는 경우에만 유니온 연산 수행
    		Node node = graph.get(idx++);
    		int from = node.from;
    		int to = node.to;
    		
    		if(find(from) != find(to)) {
    			union(from,to);
    			totalCost += node.cost;
    			count++;	//선택된 간선 증가
    		}
    	}
    	bw.write(totalCost+"\n");
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    private static void union(int i,int j) {
    	int rootA = find(i);
    	int rootB = find(j);
    	
    	if(rootA != rootB) {
    		if(rootA < rootB) {
    			parents[rootA] = rootB;
    		}else {
    			parents[rootB] = rootA;
    		}
    	}
    }
    
    private static int find(int index) {
    	if(index == parents[index]) return index;
    	else return parents[index] = find(parents[index]);
    }
    
    private static class Node implements Comparable<Node>{
    	int from,to,cost;
    	public Node(int from, int to, int cost) {
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Node o) {
    		return this.cost - o.cost;
    	}
    }
}
