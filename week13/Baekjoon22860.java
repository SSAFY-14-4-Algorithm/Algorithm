import java.io.*;
import java.util.*;

/**
 * 폴더 정리 (small), 22860
 * 메모리: 88,464KB
 * 시간: 544ms
 */
public class Baejoon22860 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		List<Node> subDict = new ArrayList<Node>(); // 바로 아래에 위치한 폴더
		Set<String> fileNames = new HashSet<>(); // 현재 폴더 이하의 경로에 위치한 파일의 이름들
		int fileNum; // 현재 폴더 이하의 경로에 위치한 파일의 수
	}
	// constants
	// variables
	static int N, M, Q;
	static Node root;
	// 디렉토리 이름에는 중복이 없다. -> 디렉토리 이름과 인덱스는 1:1 대응관게가 성립
	static Map<String, Node> mapping = new HashMap<String, Node>();
	
	
	static Node getNode(String name) {
		Node node = mapping.getOrDefault(name, null); 
		if(node == null) mapping.put(name, node = new Node());
		return node;
	}
	
	static void insert(String P, String F, int C) {
		Node nodeP = getNode(P);
		if(C == 0) { // F가 파일
			++nodeP.fileNum;
			nodeP.fileNames.add(F);
		} else { // F가 폴더
			Node nodeF = getNode(F);
			nodeP.subDict.add(nodeF);
		}
	}
	
	static void build(Node root) {
		for(Node next : root.subDict) {
			build(next);
			root.fileNum += next.fileNum;
			root.fileNames.addAll(next.fileNames);
		}
	}
	
	static int[] query(String[] path) {
		String target = path[path.length - 1];
		Node node = mapping.get(target);
		return new int[] {node.fileNames.size(),   node.fileNum};
	}
	
	public static void main(String[] args) throws IOException {
		root = new Node();
		mapping.put("main", root);
		
		// 구성
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N + M; ++i) {
			st = new StringTokenizer(br.readLine());
			String P = st.nextToken();
			String F = st.nextToken();
			int C = Integer.parseInt(st.nextToken());
			
			insert(P, F, C);
		}
		build(root);
		// 쿼리
		Q = Integer.parseInt(br.readLine());
		while(Q-- > 0) {
			String[] path = br.readLine().split("/");
			int[] ans = query(path); 
			sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
		}
		System.out.print(sb);
	}
}