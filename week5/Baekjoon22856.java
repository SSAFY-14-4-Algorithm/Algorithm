import java.io.*;
import java.util.*;
/*
 * 메모리 61736KB
 * 시간	396ms
 */
public class Baekjoon22856 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<int[]> graph = new ArrayList<int[]>();
	static int n;
	static int toFin=0; //시작 노드부터 오른쪽 탐색해서 중위순회 끝 노드까지 가는 노드수
	static ArrayList<Integer> inorderArr = new ArrayList<>(); 
	static ArrayList<Integer> similarInorder = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		n = Integer.parseInt(br.readLine());
		for(int i=0;i<=n;i++) {
			graph.add(new int[2]);
		}
		for(int i=1;i<=n;i++) {
			String[] s = br.readLine().split(" ");
			graph.get(Integer.parseInt(s[0]))[0] = Integer.parseInt(s[1]);
			graph.get(Integer.parseInt(s[0]))[1] = Integer.parseInt(s[2]);
		}
		ToInorderFinal(1);
		//유사 중위 순회 간선 수 = 양방향 간선수 {(전체 노드-1) * 2} - 중위순회 끝에서 시작노드까지 올라오는 간선 수 (toFin - 1)
		int count = 2*(n-1) - (toFin-1);
		
		System.out.println(count);
	}	
	public static void ToInorderFinal(int i) {
		//시작점에서 중위순회 맨 끝까지 몇개의 노드가 있는지 체크 -> right만 계속 탐색
		if(i==-1) return;
		
		toFin++;
		ToInorderFinal(graph.get(i)[1]);
	}
}