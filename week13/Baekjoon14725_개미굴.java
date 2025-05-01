/*
 * BOJ 14725번 : 개미굴 
 * 메모리 : 14,136kb
 * 시간 : 120ms 
 */

package algorithm;

import java.io.*;
import java.util.*;

public class BOJ14725_개미굴 {
	static int N, K;
	static Map<String, Integer> index;
	static Map<String, Integer>[] trie;
	static int nodeCount = 1;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		index = new HashMap<>();
		trie = new HashMap[15000]; // N*K 최대 1000*15
		for(int i = 0; i < 15000; i++) {
			trie[i] = new HashMap<>();
		}

		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			int curr = 0; // 루트부터 시작 
			for(int k = 0; k < K; k++) {
				String feed = st.nextToken();
				
				if(!trie[curr].containsKey(feed)) {
					trie[curr].put(feed, nodeCount++);
				}
				curr = trie[curr].get(feed);
			}
		}
		
		dfs(0, 0); // node, depth
		
		System.out.println(sb);
		br.close();
	}
	
	private static void dfs(int node, int depth) {
		List<String> keyList = new ArrayList<>(trie[node].keySet());
		Collections.sort(keyList);
		
		for(String key : keyList) {
			for(int i = 0; i < depth; i++) {
				sb.append("--");
			}
			sb.append(key).append("\n"); // 해당 키(먹이이름)
			
			// 키에 연결된 자식 노드로 재귀 호출 
			dfs(trie[node].get(key), depth+1); 
		}
	}
}
