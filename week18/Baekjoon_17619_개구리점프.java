import java.io.*;
import java.util.*;

/*
 * 시간: 296ms
 * 메모리: 19568KB
 */
public class Baekjoon_17619_개구리점프 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Log implements Comparable<Log> {
		int no, x1, x2;
		
		Log() { }
		Log(int no, int x1, int x2) {
			this.no = no;
			this.x1 = x1;
			this.x2 = x2;
		}
		
		@Override
		public int compareTo(Log other) {
			return this.x1 - other.x1;
		}
	}
	// constants
	// variables
	static int N, Q;
	static PriorityQueue<Log> pq = new PriorityQueue<>();
	static int[] ids;
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		Q = readInt();
		
		ids = new int[N + 1];
		for(int i = 1; i <= N; ++i) {
			ids[i] = i;
			pq.offer(new Log(i, readInt(), readInt())); readInt();
		}
		
		int lastId = -1;
		int lastPos = -1;
		while(!pq.isEmpty()) {
			Log log = pq.poll();
			
			if(lastPos < log.x1) { // 수직방향으로 이동할 수 없다면 새로운 그룹을 만듦
				lastId = ids[log.no];
			} else { // 수직방향으로 이동할 수 있으므로 같은 그룹으로 등록
				ids[log.no] = lastId;
			}
			lastPos = Math.max(lastPos, log.x2);
		}
		
		while(Q-- > 0) {
			int from = readInt(), to = readInt();
			sb.append(ids[from] == ids[to] ? 1 : 0).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}