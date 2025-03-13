import java.io.*;
import java.util.*;
/*
 * 메모리 11564KB	
 * 시간 68ms
 */
public class Baekjoon10830 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] matrix; 
	static int[][][] pow;
	
	//행렬 곱셈
	public static int[][] multiply_pow(int[][] matrix1, int[][] matrix2, int n) {
        int[][] result = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                    result[i][j] %= 1000;
                }
            }
        }
        return result;
    }
	
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		long b = Long.parseLong(s[1]);
		matrix = new int[n][n];
		for(int i=0;i<n;i++) {
			String[] s2 =br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				matrix[i][j] = Integer.parseInt(s2[j])%1000;
			}
		}
		//각 인덱스마다 A^2^idx 값을 넣어놓는 배열
		pow = new int[40][n][n];
		int power = 0;
		pow[power++] = matrix;
		
		//2^idx가 b를 넘지않을때까지 구함
		while(Math.pow(2, power)<= b) {
	        pow[power] = multiply_pow(pow[power - 1], pow[power - 1], n);
	        power++;
		}
		//b를 비트로 변환
		String bit_b = Long.toBinaryString(b);
		int[][] result = null;
		
		//뒤에서 부터 1일때 곱셈
		for(int idx=bit_b.length()-1;idx>=0;idx--) {
			if(bit_b.charAt(idx)=='1') {
				if (result == null) {
                    result = pow[bit_b.length() - 1 - idx]; // 첫 번째 곱셈은 pow를 바로 대입
                }
				else
					result = multiply_pow(result, pow[bit_b.length()-idx-1], n);
			}
		}
		//result 출력
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}
}
