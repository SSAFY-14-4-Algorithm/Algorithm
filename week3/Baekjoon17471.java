import java.io.*;
import java.util.*;

public class Baekjoon17471 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static int[] populations;
	public static int[] arr;
	public static boolean[] visited;
	public static int count = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		populations = new int[n+1];
		arr = new int[n+1];
		for(int i=1;i<=s.length;i++) {
			populations[i] = Integer.parseInt(s[i-1]);
		}
		for(int i=0;i<=n;i++) {
			graph.add(new ArrayList<Integer>());
		}
		for(int i=1;i<=n;i++) {
			String[] zones = br.readLine().split(" ");
			for(int j=1;j<=Integer.parseInt(zones[0]);j++) {
				graph.get(i).add(Integer.parseInt(zones[j]));
			}
		}
		for(int i=1;i<=n;i++) {
			arr[i] = i;
		}
		for(int i=1;i<=n/2;i++) {
			int[] result = new int[i];
			boolean[] isCom = new boolean[n+1];
			Combination(isCom,result,1,0,n,i);	
		}
		if(count == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(count);
	}
	public static void Combination(boolean[] isCom,int[] result,int start,int depth,int n,int r) {
		if(depth == r) {
			int[] restArr = new int[n-r];
			int pos = 0;
			for(int i=1;i<=n;i++) {
				if(!isCom[i]) {
					restArr[pos] = arr[i];
					pos++;
				}
			}
			ArrayList<Integer> flag1 = new ArrayList<>();
			HashSet<Integer> group1 = new HashSet<>();
			for (int num : result) {
			    group1.add(num);
			}
			visited = new boolean[n+1];
			isDfs(flag1,result[0],group1);
			ArrayList<Integer> flag2 = new ArrayList<>();
			HashSet<Integer> group2 = new HashSet<>();
			for (int num : restArr) {
			    group2.add(num);
			}
			visited = new boolean[n+1];
			isDfs(flag2,restArr[0],group2);
			if(flag1.size() == result.length && flag2.size() == restArr.length) {
				int sum1 = 0;
				int sum2 = 0;
				for(int i=0;i<result.length;i++) {
					sum1 += populations[result[i]];
				}
				for(int i=0;i<restArr.length;i++) {
					sum2 += populations[restArr[i]];
				}
				if(count>Math.abs(sum1-sum2)) {
					count = Math.abs(sum1-sum2);
				}
			}
			return;
		}
		for(int i=start;i<=n;i++) {
			result[depth]= arr[i];
			isCom[i] = true;
			Combination(isCom,result,i+1,depth+1,n,r);
			isCom[i] = false;
		}
	}
	public static void isDfs(ArrayList<Integer> flag1,int node,HashSet<Integer> group) {
		flag1.add(node);
		visited[node] = true;

		for(int i : graph.get(node)) {
			if(!visited[i] && group.contains(i)) {
				isDfs(flag1,i,group);
			}
		}
	}
}