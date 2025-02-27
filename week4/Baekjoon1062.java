import java.io.*;
import java.util.*;

public class Baekjoon1062 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] output;
	static boolean[][] alphabet;
	static ArrayList<Character> c;
	static int result=-1;
	public static void main(String[] args) throws IOException{
		  String[] s = br.readLine().split(" ");
		  int n = Integer.parseInt(s[0]);
		  int k = Integer.parseInt(s[1]);
		  alphabet = new boolean [n][26];
		  c = new ArrayList<>();
		  for(int i=0;i<n;i++) {
			  char[] word = br.readLine().toCharArray();
			  for(char alpha : word) {
				  if(alpha!='a' && alpha!='n' && alpha!='t' && alpha!='i' && alpha!='c') {
					  alphabet[i][alpha-'a'] = true;
					  if(!c.contains(alpha))
						  c.add(alpha);
				  }
			  }
		  }
		  if(k<5) {
			  System.out.println(0);
		  }
		  else if(c.size()==0) {
			  System.out.println(n);
		  }
		  else{
			  output = new char[Math.min(c.size(), k-5)];
			  Teaching(0,0,n,Math.min(c.size(), k-5));
			  System.out.println(result);
		  }
	}
	public static void Teaching(int depth,int start,int n,int k) {
		if(depth==k) {
			int count=0;
			boolean[][] copyAlphabet = new boolean[n][26];
			for(int i=0;i<n;i++) {
				copyAlphabet[i] = Arrays.copyOf(alphabet[i], 26);
			}
			for(int i=0;i<n;i++) {
				for(char j : output) {
					copyAlphabet[i][j-'a'] = false;
				}
				boolean flag = false;
				for(boolean j : copyAlphabet[i]) {
					if(j) {
						flag = true;
						break;
					}
				}
				if(!flag)
					count++;
			}
			result = Math.max(result,count);
			return;
		}
		for(int i=start;i<c.size();i++) {
			output[depth] = c.get(i);
			Teaching(depth+1,i+1,n,k);
		}
	}
}
