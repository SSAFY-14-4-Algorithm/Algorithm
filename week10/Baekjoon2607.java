import java.io.*;
import java.util.*;

/*
 * 메모리: 11,536KB
 * 시간: 64ms
 */
public class Baekjoon2607 {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static String origin, other;
	static int[] originFreq = new int[0x01 << 7];
	static int[] otherFreq = new int[0x01 << 7];
	
    public static void main(String[] args) throws IOException {
    	N = readInt() - 1;
    	int cnt = 0;
    	if(N >= 0) {
    		origin = br.readLine();
    		for(char ch : origin.toCharArray()) ++originFreq[ch];
    		
    		while(N-- > 0) {
    			other = br.readLine();
        		System.arraycopy(originFreq, 'A', otherFreq, 'A', 26);
        		for(char ch : other.toCharArray()) --otherFreq[ch];
        		
        		int cntOne = 0, notZero = 0;
        		for(int ch = 'A'; ch <= 'Z'; ++ch) {
        			if(otherFreq[ch] != 0) ++notZero;
        			if(Math.abs(otherFreq[ch]) == 1) ++cntOne;
        		}
        		
        		if(notZero == cntOne 
        			&& (origin.length() == other.length() && cntOne <= 2
        			|| origin.length() != other.length() && cntOne == 1)) ++cnt;
        	}
    	}
    	System.out.print(cnt);
    }
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}