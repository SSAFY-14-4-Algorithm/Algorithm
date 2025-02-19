import java.util.*;
import java.io.*;

public class Swea1767 {
	static ArrayList<Node> arr;
	static int N;
	static int[][] map;
	static int cellNum;
	static int maxCellNum;
	static int minConnectedLength;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void selectCells(int cnt, int length, int index) {
		if(index == cellNum) {
			if(cnt > maxCellNum) {
				maxCellNum = cnt;
				minConnectedLength = length;
			} else if(cnt == maxCellNum) {
				minConnectedLength = Math.min(minConnectedLength, length);
			}
			return;
		}
		if(cnt + cellNum-index < maxCellNum) {
			return;
		}
		for(int i = 0; i < 4; i++) {
			int d = check(i, arr.get(index));
			if(d != 0) {
				make(i, arr.get(index), 2);
				selectCells(cnt+1, length + d, index+1);
				make(i, arr.get(index), 0);
			}
		}
		selectCells(cnt, length, index+1);
	}
	
	public static void make(int d, Node node, int value) {
		int x = node.x;
		int y = node.y;
		while(true) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if(0 <= nx && nx < N && 0 <= ny && ny < N) {
				x = nx;
				y = ny;
				map[nx][ny] = value;
			} else {
				break;
			}
		}
	}
	
	public static int check(int d, Node node) {
		int ret = 0;
		int x = node.x;
		int y = node.y;
		while(true) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if(0 <= nx && nx < N && 0 <= ny && ny < N) {
				if(map[nx][ny] != 0) {
					return 0;
				} else {
					ret++;
					x = nx;
					y = ny;
				}
			} else {
				break;
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			arr = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(i != N-1 && i != 0 && j != N-1 && j != 0) {
							arr.add(new Node(i, j));
						}
					}
				}
			}
			cellNum = arr.size();
			maxCellNum = 0;
			minConnectedLength = Integer.MAX_VALUE;
			selectCells(0, 0, 0);
			sb.append(minConnectedLength).append("\n");
		}
		System.out.print(sb);
	}
}
