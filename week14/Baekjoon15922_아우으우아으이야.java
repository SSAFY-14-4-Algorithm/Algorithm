import java.io.*;
import java.util.*;

/*
[문제]
수직선 위에 선불을 여러 개 그릴 것이다.
선분을 겹치게 그리는 것도 가능하다.
선분을 모두 그렸을 때, 수직선 위에 그려진 선분 길이의 총합은 얼마인지 구하라.

[입력]
첫째 줄에 수직선 위에 그릴 선분의 개수 N이 주어진다.
둘째 줄 부터 N개의 줄에 좌표를 나타내느 정수쌍(x, y)가 주어진다.
이는 [x, y] 구간 (x와 y를 포함하는 구간)에 선분을 그린하는 의미이다.
좌표는 x가 증가하는 순으로, x가 같다면 y가 증가하는 순으로 주어진다.
(-1,000,000,000 ≤ x < y ≤ 1,000,000,000)

[출력]
N개의 선분을 모두 그렸을 때, 수직선 위에 그어진 선분 길이의 총합을 출력한다.
 */


/**
 * Java8
 * 메모리: 12,328KB
 * 시간: 136ms
 * Java11
 * 메모리: 14,792KB
 * 시간: 160ms
 * 
 * @author 배준수
 */
public class Baekjoon15922_아우으우아으이야 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static int sum; 
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		
		int right = Integer.MIN_VALUE;
		while(N-- > 0) {
			int x = readInt(), y = readInt();
			if(y <= right) continue;
			sum += y - Math.max(right, x);
			right = y;
		}
		System.out.println(sum);
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == 0x0D) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}