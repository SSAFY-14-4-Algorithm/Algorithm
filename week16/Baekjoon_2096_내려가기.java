import java.io.*;
import java.util.*;

/**
 * 시간: 124ms
 * 메모리: 20,240KB
 */
public class Baekjoon_2096_내려가기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static final int WIDTH = 3;
	static int N;
	static int[][] maxMat, minMat;
	
    public static void main(String[] args) throws IOException {
        N = readInt();
        maxMat = new int[N][WIDTH];
        minMat = new int[N][WIDTH];
        for(int y = 0; y < N; ++y) {
        	for(int x= 0; x < WIDTH; ++x) {
        		maxMat[y][x] = minMat[y][x] = readInt();
        	}
        }
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int y = N - 2; y >= 0; --y) {
        	maxMat[y][0] += Math.max(maxMat[y + 1][0], maxMat[y + 1][1]);
        	minMat[y][0] += Math.min(minMat[y + 1][0], minMat[y + 1][1]);
        	
        	maxMat[y][1] += Math.max(Math.max(maxMat[y + 1][0], maxMat[y + 1][1]), maxMat[y + 1][2]);
    		minMat[y][1] += Math.min(Math.min(minMat[y + 1][0], minMat[y + 1][1]), minMat[y + 1][2]);
    		
        	maxMat[y][2] += Math.max(maxMat[y + 1][1], maxMat[y + 1][2]);
        	minMat[y][2] += Math.min(minMat[y + 1][1], minMat[y + 1][2]);
        }
        for(int x = 0; x < WIDTH; ++x) {
        	max = Math.max(max, maxMat[0][x]);
        	min = Math.min(min, minMat[0][x]);
        }
        
        sb.append(max).append(" ").append(min);
        System.out.print(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
