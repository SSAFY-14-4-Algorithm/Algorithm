import java.io.*;
/*
 * 메모리 11516KB
 * 시간 68ms
 */
public class Baekjoon13171 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final long modular = 1000000007;
	public static void main(String[] args) throws IOException{
		long A = Long.parseLong(br.readLine());
		long X = Long.parseLong(br.readLine());
		//2^0 부터 2^63까지 저장하는 배열
		long[] pow = new long[64];
		int i = 0;
		long current = A;
		long result = 1;
		//2^i 가 X보다 작을때까지 반복
		while(Math.pow(2, i) <= X) {
			
			pow[i] = current;
			//a^2 mod  = ( a mod * a mod ) mod
			current = ((current % modular) * (current % modular)) % modular;
			i++;
		}
		//X의 값을 비트로 표현
		String x_bit = Long.toBinaryString(X);
		
		//뒤에서 부터 값이 1이면 해당 인덱스 값 곱함 ex) 1101 = 13 = 1+4+8 = a^2^0 * a^2^2 * a^2^3
		for(int idx = x_bit.length()-1;idx>=0;idx--) {
			if(x_bit.charAt(idx)=='1') {
				result *= pow[x_bit.length()-idx-1];
				result %= modular;
			}
		}
		System.out.println(result);
	}
}
