import java.io.*;
import java.util.*;
// 메모리 58592KB 실행시간 508ms
public class Baekjoon22856 {
	static class Node {
		int left, right, parent;
		Node(int left, int right) {
			this.left = left;
			this.right = right;
			this.parent = -1;
		}
	}

	static Node[] tree;
	static int lastNode, moves = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		tree = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new Node(-1, -1);
		}

		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);

			tree[a].left = b;
			tree[a].right = c;

			if (b != -1) tree[b].parent = a;
			if (c != -1) tree[c].parent = a;
		}

		lastNode = findLastNode(1);
		traverse(1);
		System.out.println(moves);
	}

	static int findLastNode(int node) {
		if (node == -1) return -1;
		int rightmost = findLastNode(tree[node].right);
		if (rightmost == -1) return node;
		return rightmost;
	}

	static void traverse(int node) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[tree.length];
		int current = node;

		while (true) {
			if (tree[current].left != -1 && !visited[tree[current].left]) {
				stack.push(current);
				current = tree[current].left;
				moves++;
			} else if (tree[current].right != -1 && !visited[tree[current].right]) {
				stack.push(current);
				current = tree[current].right;
				moves++;
			} else if (current == lastNode) {
				break;
			} else {
				current = stack.pop();
				moves++;
			}
			visited[current] = true;
		}
	}
}
