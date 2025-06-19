import java.io.*;
import java.util.*;


/**
 * 시간: 248ms
 * 메모리: 26,456KB
 */
public class Baekjoon_21939_문제추천시스템Version1 {
	// IO
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// tpyes
	static class Problem implements Comparable<Problem> {
		Problem() {}
		Problem(int P, int L) { this.P = P; this.L = L; }
		
		int P, L;

		@Override
		public int compareTo(Problem o) {
			if(this.L != o.L) return this.L - o.L;
			return this.P - o.P;
		}
	}
	// variables
	static int N, M;
	static PriorityQueue<Problem> minHeap = new PriorityQueue<>();
	static PriorityQueue<Problem> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	static Map<Integer, Problem> pool = new HashMap<>();
	
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	for(int i = 0; i < N; ++i) {
    		int P = readInt();
    		int L = readInt();
    		
    		Problem problem = new Problem(P, L); 
    		minHeap.add(problem);
    		maxHeap.add(problem);
    		pool.put(P, problem);
    	}
    	
    	M = readInt();
    	while(M-- > 0) {
    		int cmd = getWordFirst();
    		if(cmd == 'r') {
    			int x = readInt();
    			if(x > 0) {
    				while(pool.getOrDefault(maxHeap.peek().P, null) != maxHeap.peek()) {
    					maxHeap.poll();
    				}
    				sb.append(maxHeap.peek().P).append("\n");
    			} else {
    				while(pool.getOrDefault(minHeap.peek().P, null) != minHeap.peek()) {
    					minHeap.poll();
    				}
    				sb.append(minHeap.peek().P).append("\n");
    			}
    		} else {
    			if(cmd == 'a') {
    				int P = readInt();
        			int L = readInt();
        			
        			Problem problem = new Problem(P, L); 
            		minHeap.add(problem);
            		maxHeap.add(problem);
            		pool.put(P, problem);
    			} else {
    				int P = readInt();
    				pool.remove(P);
    			}
    		}
    	}
    	System.out.print(sb);
    }
    
    static int getWordFirst() throws IOException {
    	int n = System.in.read();
    	while(System.in.read() > 0x20) continue;
    	return n;
    }
    
    static int readInt() throws IOException {
    	int c, n = System.in.read() & 0x0F, s = 1;
    	if(n == ('-' & 0x0F)) {
    		n = 0;
    		s = -1;
    	}
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n*s;
    }
}