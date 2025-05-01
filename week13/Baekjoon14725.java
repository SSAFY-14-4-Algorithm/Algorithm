import java.io.*;
import java.util.*;

/* 개미굴
 * 	16188kb, 140ms	
 */
public class Baekjoon14725 {
	static class Node {
		TreeMap<String, Node> child;
		public Node() {
			this.child = new TreeMap<>();
		}
	}
	
	static class Trie {
		Node root;
		
		public Trie() {
			this.root = new Node();
		}
		public void insert(String[] str) {
			Node node = this.root;
			for(int i = 1; i < str.length; i++) {
				node.child.putIfAbsent(str[i], new Node());
				node = node.child.get(str[i]);
			}
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	public static void solution(Node node, int cnt) {
		for(String s : node.child.keySet()) {
			for(int i = 0; i < cnt; i++) {
				sb.append("--");
			}
			sb.append(s).append("\n");
			solution(node.child.get(s), cnt+1);
		}
	}

	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	Trie t = new Trie();
    	for(int i = 0; i < N; i++) {
    		String[] str = br.readLine().split(" ");
    		t.insert(str);
    	}
    	solution(t.root, 0);
    	System.out.print(sb);
    }
}