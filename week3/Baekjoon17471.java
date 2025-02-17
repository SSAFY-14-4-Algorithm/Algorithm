package week3;

import java.io.*;
import java.util.*;

public class Baekjoon17471{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N, answer;
	private static int [] people;
	private static ArrayList<ArrayList<Integer>> graph;
	
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		graph = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		String [] tokens = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			people[i+1] = Integer.parseInt(tokens[i]);
		}
		
		for(int i=1;i<=N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=1;j<tokens.length;j++) {
				graph.get(i).add(Integer.parseInt(tokens[j]));
			}
		}
		
		backtracking(1, 0, new boolean [N+1]);
		
		if(answer == Integer.MAX_VALUE) {
			bw.write("-1\n");
		}else {
			bw.write(answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void backtracking(int depth, int selectedCnt, boolean [] zip) {
		if(depth == N+1) {
			if(selectedCnt == 0 || selectedCnt == N) return;
			
			//check Really Connected
			ArrayList<Integer> zipA = new ArrayList<>();
			ArrayList<Integer> zipB = new ArrayList<>();
			
			for(int i=1;i<=N;i++) {
				if(zip[i]) zipA.add(i);
				else zipB.add(i);
			}
			
			int popA = getTotalPeople(zipA, zip);
			int popB = getTotalPeople(zipB, zip);
			
			if(popA == -1 || popB == -1) {
				//Not Connected
				return;
			}
			
			answer = Math.min(answer, Math.abs(popA - popB));
			return;
		}
		
		zip[depth] = true;
		backtracking(depth+1, selectedCnt+1, zip);
		
		zip[depth] = false;
		backtracking(depth+1, selectedCnt, zip);
	}
	
	private static int getTotalPeople(ArrayList<Integer> zip, boolean [] isZiped) {
		int firstArea = zip.get(0);
		boolean [] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(firstArea);
		visited[firstArea] = true;
		
		int totalCnt = 0;
		int totalPop = 0;
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			totalCnt++;
			totalPop += people[curNode];
			for(int nextNode : graph.get(curNode)) {
				if(visited[nextNode] || isZiped[curNode] != isZiped[nextNode]) continue;
				
				visited[nextNode] = true;
				queue.add(nextNode);
			}
		}
		
		if(totalCnt != zip.size()) return -1;
		
		return totalPop;
	}
}
