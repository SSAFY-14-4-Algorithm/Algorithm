import java.util.*;
import java.io.*;
/* 전구
 * 19136kb, 192ms
 */
public class Baekjoon2550 {
	public static class Node{
		int num, index;
		public Node(int num, int index) {
			this.num = num;
			this.index = index;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", index=" + index + "]";
		}
		
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] index = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			index[Integer.parseInt(st.nextToken())] = i;
		}
		Node[] arr2 = new Node[N+1];
		for(int i = 1; i <= N; i++) {
			arr2[i] = new Node(arr[i], index[arr[i]]);
		}
		int[] dp1 = new int[N+1];
		int[] dp2 = new int[N+1];
		int dlen = 1;
		dp1[1] = 1;
		dp2[1] = arr2[1].index;
		for(int i = 2; i <= N; i++) {
			if(arr2[i].index > dp2[dlen]) {
				dp2[++dlen] = arr2[i].index;
				dp1[i] = dlen;
			} else {
				int left = 1;
				int right = dlen;
				//lower bound
				int num = arr2[i].index;
				while(left <= right) {
					int mid = (left+right)/2;
					int target = dp2[mid];
					if(target >= num) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
				dp2[left] = arr2[i].index;
				dp1[i] = left;
			}
		}
		int[] ans = new int[dlen];
		int len = dlen;
		int alen = 0;
		for(int i = N; i >= 1; i--) {
			if(dp1[i] == len) {
				len--;
				ans[alen++] = arr2[i].num;
			}
		}
		Arrays.sort(ans);
		StringBuilder sb = new StringBuilder();
		sb.append(dlen).append("\n");
		for(int i = 0; i < dlen; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}	
