import java.io.*;
import java.util.*;
/*
 * 메모리:107148KB	
 * 시간: 2176ms
 */
public class Baekjoon4195 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;
	static int[] size;
	static HashMap<String,Integer> map;
	//Union-Find
	public static void union(int x,int y) {
		int a = find(x);
		int b = find(y);
		
		if(a!=b) {
			parent[b] = a;
			//union 할때마다 Size계산
			size[a] += size[b];
		}
	}
	
	public static int find(int x) {
		if(parent[x] != x)
			return parent[x] = find(parent[x]);
		return parent[x];
	}
	//size값 반환
	public static int getSize(int x) {
		return size[find(x)];
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int i=1;i<=T;i++) {			
			int f = Integer.parseInt(br.readLine());
			parent = new int[2*f+1];
			size = new int[2*f+1];
			map = new HashMap<>();
			for(int j=0;j<2*f+1;j++) {
				parent[j] = j;
				size[j] = 1;
			}
			int idx = 0;
			for(int j=0;j<f;j++) {
				String[] s = br.readLine().split(" ");
				//맵에 해당 친구가 존재하지 않으면 저장
				if(!map.containsKey(s[0])) {
					map.put(s[0], idx);
					idx++;
				}
				if(!map.containsKey(s[1])) {
					map.put(s[1], idx);
					idx++;
				}
				union(map.get(s[0]),map.get(s[1]));
				System.out.println(getSize(map.get(s[0])));
			}
		}
	}
}
