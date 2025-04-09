import java.util.*;
import java.io.*;
/* dp풀이 O(N^2); (정석풀이)
 * 14788kb, 124ms
 */ 

public class Baekjoon11055 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dp = new int[N];
		int[] arr = new int[N];
		int answer = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			int max = 0;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					max = Math.max(dp[j], max);
				}
			}
			dp[i] = max + arr[i];
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
}

//아래 풀이들은 가장 큰 증가하는 부분수열 2의 풀이들
//(세그 or 펜윅) + (우선순위큐 or dp + 좌표압축) 으로 풀이 가능
/* dp + 세그먼트 트리 풀이 O(NlogN) (좌표압축 추가해야 가장 큰 증가하는 부분수열 2 풀이 가능)
 * 14860kb, 116ms
 */
/*
 import java.util.*;
import java.io.*;

public class Main {
	static long[] maxSeg;
	
	static long update(int idx, long s, long e, long t, long value) {
		if(t < s || e < t) return maxSeg[idx];
		if(s == e) return maxSeg[idx] = value;
		long mid = (s + e)/2;
		return maxSeg[idx] = Math.max(update(2*idx, s, mid, t, value), update(2*idx+1, mid+1, e, t, value));
	}
	
	static long getMax(int idx, long s, long e, long ts, long te) {
		if(e < ts || te < s) return Integer.MIN_VALUE;
		if(ts <= s && e <= te) return maxSeg[idx];
		long mid = (s + e)/2;
		return Math.max(getMax(2*idx, s, mid, ts, te), getMax(2*idx+1, mid+1, e, ts, te));
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N+1];
		maxSeg = new long[4*N];
		long answer = 0;
		if(N==1) {
			System.out.println(Long.parseLong(br.readLine()));
			return;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		long maxNum = 0;
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			maxNum = Math.max(maxNum, arr[i]);
		}
		maxSeg = new long[4*(int)maxNum];
		for(int i = 1; i <= N; i++) {
			long max = getMax(1, 0, maxNum, 0, arr[i]-1);
			update(1, 0, maxNum, arr[i], max+arr[i]);
			answer = Math.max(answer, max+arr[i]);
		}
		System.out.println(answer);
	}
}
 */

/* 우선순위큐  + 펜윅 트리 풀이 O(NlogN)
 * 14660kb, 120ms
 */

/*
import java.util.*;
import java.io.*;

public class Main {
	static long[] pTree; //펜윅트리
	static int N;
	public static void update(int i, long num) {
		while(i <= N) {
			pTree[i] = Math.max(pTree[i], num);
			i += i &-i;
		}
	}
	
	public static long getMax(int i) {
		long max = 0;
		while(i > 0) {
			max = Math.max(pTree[i], max);
			i -= i &-i;
		}
		return max;
	}
	
	
	public static class Node{
		int idx;
		long num;
		Node(int idx, long num){
			this.idx = idx;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		long answer = 0;
		if(N==1) {
			System.out.println(Long.parseLong(br.readLine()));
			return;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> ((o1.num==o2.num) ? o2.idx-o1.idx : Long.compare(o1.num, o2.num)));
		for(int i = 1; i <= N; i++) {
			pq.add(new Node(i, Long.parseLong(st.nextToken())));
		}
		pTree = new long[N+1];
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			long max = getMax(cur.idx-1);
			update(cur.idx, max + cur.num);
			answer = Math.max(answer, max+cur.num);
		}
		System.out.println(answer);
	}
}
 */

/* dp + 세그 + 좌표압축풀이 O(NlogN)
 * 17048kb, 128ms
 */

/*
import java.util.*;
import java.io.*;

public class Main {
	static long[] maxSeg;
	
	static long update(int idx, long s, long e, long t, long value) {
		if(t < s || e < t) return maxSeg[idx];
		if(s == e) return maxSeg[idx] = value;
		long mid = (s + e)/2;
		return maxSeg[idx] = Math.max(update(2*idx, s, mid, t, value), update(2*idx+1, mid+1, e, t, value));
	}
	
	static long getMax(int idx, long s, long e, long ts, long te) {
		if(e < ts || te < s) return Integer.MIN_VALUE;
		if(ts <= s && e <= te) return maxSeg[idx];
		long mid = (s + e)/2;
		return Math.max(getMax(2*idx, s, mid, ts, te), getMax(2*idx+1, mid+1, e, ts, te));
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N+1];
		long answer = 0;
		if(N==1) {
			System.out.println(Long.parseLong(br.readLine()));
			return;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		long maxNum = 0;
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		long[] temp = arr.clone();
		Arrays.sort(temp);
		long[] compressed = new long[300300];
		int clen = 0;
		for(int i = 1; i <= N; i++) {
			if(temp[i-1] != temp[i]) {
				compressed[clen++] = temp[i];
			}
		}
		maxSeg = new long[4*(clen+1)];
		for(int i = 1; i <= N; i++) {
			int left, right;
			left = 0;
			right = clen-1;
			int mid = 1;
			while(left <= right) {
				mid = (left+right)/2;
				if(compressed[mid] == arr[i]) {
					break;
				} else if(compressed[mid] > arr[i]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			int pos = mid+1;
			long max = getMax(1, 0, clen, 0, pos-1);
			update(1, 0, clen, pos, max+arr[i]);
			answer = Math.max(answer, max+arr[i]);
		}
		System.out.println(answer);
	}
}
 */


