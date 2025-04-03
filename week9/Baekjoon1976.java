import java.io.*;
import java.util.*;
/*
 * 메모리: 16544KB	
 * 시간 : 116ms
 */
public class Baekjoon1976 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;
	public static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if(a>b)
			parent[a] = b;
		else
			parent[b] = a;
	}
	
	public static int find(int x) {
		if(parent[x] != x)
			return parent[x] = find(parent[x]);
		
		return parent[x];
	}
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=1;i<=n;i++) {
			parent[i] = i;
		}
		
		for(int i=1;i<=n;i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1;j<=n;j++) {
				if(s[j-1].equals("1")) {
					union(i,j);
				}
			}
		}
		String[] s2 = br.readLine().split(" ");
		boolean result = true;
		for(int i=0;i<m-1;i++) {
			if(find(Integer.parseInt(s2[i]))!=find(Integer.parseInt(s2[i+1]))) {
				result = false;
				break;
			}
		}
		System.out.println(result?"YES":"NO");
	}
}
