/*
 * 백준 22856번 : 트리 순회 
 * 메모리 : 63,744kb
 * 시간 : 392ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon22856 {
	static int N;
	static Node[] node;
	static int cnt;
	static int lastNode;

	static class Node {
		int left, right;
		
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		node = new Node[N+1]; 
		for (int i = 1; i <= N; i++) {
			node[i] = new Node(-1, -1);
		}

		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			node[a] = new Node(b, c);
		}

		inOrder(1);
		search(1);
		
		sb.append(cnt);
		System.out.print(sb);
		br.close();
	}
	
	// 마지막 노드 찾기 
	private static void inOrder(int idx) {
		if (node[idx].left != -1) {
			inOrder(node[idx].left);
		}
		lastNode = idx;
		if (node[idx].right != -1) {
			inOrder(node[idx].right);
		}
	}

	// 이동 횟수 세기 
	private static void search(int idx) {
		if (node[idx].left != -1) {
			cnt++; 
			search(node[idx].left);
			if (node[idx].left == lastNode) {
				System.out.println(cnt);
				System.exit(0);
			}
			cnt++; 
		}

		if (node[idx].right != -1) {
			cnt++;  
			search(node[idx].right);
			if (node[idx].right == lastNode) {
				System.out.println(cnt);
				System.exit(0);
			}
			cnt++; 
		}
	}
}
