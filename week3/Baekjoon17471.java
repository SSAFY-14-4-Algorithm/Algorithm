package week3;

import java.util.*;
import java.io.*;

public class Baekjoon17471 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb;
	
	static List<Integer>[] graph;
	static int[] distance;
	static int N;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		distance = new int[N+1];
		answer = Integer.MAX_VALUE;
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int nearNodeCnt = Integer.parseInt(st.nextToken());
			
			for(int j=0;j<nearNodeCnt;j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		backtracking(1,0,new boolean [N+1]);
		
		if(answer == Integer.MAX_VALUE) {
			bw.write("-1\n");
		}else
			bw.write(answer+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void backtracking(int depth, int selectedCnt, boolean [] zip) {
		if(depth == N) {
			if(selectedCnt == 0 || selectedCnt == N) return;
			
			ArrayList<Integer> zipA = new ArrayList<>();
			ArrayList<Integer> zipB = new ArrayList<>();
			
			for(int i=1;i<=N;i++) {
				if(zip[i]) zipA.add(i);
				else zipB.add(i);
			}
			
			int result1 = bfs(zipA,zip);
			int result2 = bfs(zipB,zip);
			
			if(result1 == -1 || result2 == -1) {
				return;
			}
			
			int peopleA = 0;
			int peopleB = 0;
			for(int i : zipA) {
				peopleA+=distance[i];
			}
			
			for(int i : zipB) {
				peopleB+=distance[i];
			}
			
			answer = Math.min(answer, Math.abs(peopleA-peopleB));
			return;
		}
		
		zip[depth] = true;		//1번 선거구에 포함되는 경우
		backtracking(depth+1, selectedCnt+1, zip);
		
		zip[depth] = false;		//1번 선거구에 포함시키지 않는 경우
		backtracking(depth+1,selectedCnt, zip);
	}
	
	private static int bfs(ArrayList<Integer> zip, boolean [] contain) {
		int targetSize = zip.size();
		boolean [] visited = new boolean[N+1];
		
		int startNode = zip.get(0);
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(startNode);
		visited[startNode] = true;
		int count = 0;
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			count++;
			for(int nextNode : graph[curNode]) {
				if(!visited[nextNode] && (contain[curNode] == contain[nextNode])) {
					visited[nextNode] = true;
					queue.add(nextNode);
				}
			}
		}
		
		if(targetSize != count) return -1;
		
		return 0;
	}
}