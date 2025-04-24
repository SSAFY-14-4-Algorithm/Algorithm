import java.io.*;
import java.util.*;

/*
 * 메모리: 11,564KB
 * 시간: 64ms
 */
public class Baekjoon1744 {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static PriorityQueue<Integer> gteq2 = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> lteq0 = new PriorityQueue<Integer>(); 
	static int cntOne = 0;
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 0; i < N; ++i) {
			int v = readInt();
			if(v == 1) ++cntOne;
			else if(v >= 2) gteq2.offer(v);
			else lteq0.offer(v);
		}
		// Output
		int sum = cntOne;
		if(gteq2.size() % 2 == 1) sum += gteq2.poll();
		while(!gteq2.isEmpty()) sum += gteq2.poll() * gteq2.poll();
		while(lteq0.size() > 1) sum += lteq0.poll() * lteq0.poll();
		while(lteq0.size() > 0) sum += lteq0.poll();
		System.out.print(sum);
		
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == 13) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}
