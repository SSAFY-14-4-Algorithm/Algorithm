import java.io.*;
import java.util.*;

public class Baekjoon15686 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int CHICKEN = 2, HOUSE = 1;
	private static int N,M;
	private static int answer;
	private static int [][] map;
	private static ArrayList<Node> chickens;
	private static ArrayList<Node> homes;
	private static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
		public Node() {
			
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}	
	}
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		answer = Integer.MAX_VALUE;
		map = new int[N][N];
		chickens = new ArrayList<>();
		homes = new ArrayList<>();
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
				if(map[i][j] == CHICKEN) {
					chickens.add(new Node(i,j));
				}else if(map[i][j] == HOUSE) {
					homes.add(new Node(i,j));
				}
			}
		}
		
		backtracking(0,0,new boolean[chickens.size()+1]);
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void backtracking(int depth, int selectedCnt, boolean [] zip) {
		if(selectedCnt == M) {
			ArrayList<Node> selectedChicken = new ArrayList<>();
			for(int i=0;i<zip.length;i++) {
				if(zip[i]) selectedChicken.add(chickens.get(i));
			}
			int totalLength = simulation(selectedChicken);
			answer = Math.min(totalLength, answer);
			return;
		}
		
		if(depth >= chickens.size()) return;
		
		zip[depth] = true;
		backtracking(depth+1,selectedCnt+1,zip);
		
		zip[depth] = false;
		backtracking(depth+1,selectedCnt,zip);
	}

	private static int simulation(ArrayList<Node> selectedChicken) {
		int totalLength = 0;
		for(Node home : homes) {
			Node selected = null;
			int minDistance = Integer.MAX_VALUE;
			
			for(Node chicken : selectedChicken) {
				int xDis = Math.abs(home.x - chicken.x);
				int yDis = Math.abs(home.y - chicken.y);
				
				if(minDistance > (xDis + yDis)) {
					minDistance = xDis + yDis;
					selected = chicken;
				}
			}
			
			totalLength += minDistance;
		}
		
		return totalLength;
	}
}
