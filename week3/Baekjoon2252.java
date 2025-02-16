import java.io.*;
import java.util.*;

public class Baekjoon2252 {
	//위상 정렬
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static int[] edgeCount;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		edgeCount = new int[n+1]; // 진입 차수 넣는 배열 초기화
		for(int i=0;i<=n;i++) {
			graph.add(new ArrayList<Integer>()); // graph 내부 초기화
		}
		for(int i=0;i<m;i++) {
			String[] students = br.readLine().split(" ");
			//ex) 1 3을 입력받으면 1번 인덱스 배열에 3값 추가
			graph.get(Integer.parseInt(students[0])).add(Integer.parseInt(students[1])); 
			edgeCount[Integer.parseInt(students[1])]++; // 뒷 값의 진입 차수 1증가
		}
		TopologicalSort();
		System.out.println(sb.toString());
	}
	public static void TopologicalSort() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1;i<edgeCount.length;i++) {
			if(edgeCount[i]==0) { //진입 차수가 0인 노드 큐에 저장
				q.offer(i);
			}
		}
		while(!q.isEmpty()) { // 큐가 비어있을때까지 반복
			int student = q.poll(); //하나 꺼냄
			sb.append(student+" ");
			for(int i=0;i<graph.get(student).size();i++) { //해당 번호 인덱스의 배열만큼 반복
				edgeCount[graph.get(student).get(i)]--; //끊어졌으므로 해당 노드와 연결된 노드들 진입차수 1감소
			}
			for(int i : graph.get(student)) { // 해당 번호 인덱스의 배열 값들 중에
				if(edgeCount[i]==0) { //진입차수가 0인 노드이면
					q.offer(i); // 큐에 저장
				}
			}
		}
		
	}
}