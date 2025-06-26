import java.io.*;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/2170
 * 시간: 1032ms
 * 메모리: 49316KB
 */
public class Baekjoon_2170_선긋기 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Line implements Comparable<Line> {
		int x, y;
		
		Line() { }
		Line(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Line other) {
			return this.x - other.x;
		}
	}
	// constants
	// variables
	static int N, Q;
	static PriorityQueue<Line> pq = new PriorityQueue<>();
	static int[] ids;
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		
		for(int i = 0; i < N; ++i) {
			pq.offer(new Line(readInt(), readInt()));
		}
		
		int sum = 0;
		int lastPos = Integer.MIN_VALUE;
		while(!pq.isEmpty()) {
			Line line = pq.poll();
			
			if(lastPos < line.x) { // 선이 이어지지지 않음
				sum += line.y - line.x;
			} else if(line.y > lastPos){ // 선이 이어졌고 길이가 연장됨
				sum += line.y - lastPos;
			}
			lastPos = Math.max(lastPos, line.y);
		}
		
		
		System.out.print(sum);
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == ('-' & 0x0F)) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}