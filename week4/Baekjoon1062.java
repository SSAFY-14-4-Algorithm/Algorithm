import java.io.*;
import java.util.*;

public class Baekjoon1062 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] output;
	static boolean[][] alphabet;
	static int n;
	static ArrayList<Character> c;
	static int result=-1;
	public static void main(String[] args) throws IOException{
		  String[] s = br.readLine().split(" ");
		  n = Integer.parseInt(s[0]);
		  int k = Integer.parseInt(s[1]);
		  //읽을수있는지 확인하는 배열 
		  alphabet = new boolean [n][26];
		  // 배워야하는 새로운 글자 담는 배열 a n t i c 제외
		  c = new ArrayList<>();
		  for(int i=0;i<n;i++) {
			  char[] word = br.readLine().toCharArray();
			  for(char alpha : word) {
				  //해당 단어를 읽기 위해 새로운 글자를 배워야 한다면 해당 글자 인덱스 위치 true 하고 새로운 글자 c 배열에 추가
				  if(alpha!='a' && alpha!='n' && alpha!='t' && alpha!='i' && alpha!='c') {
					  alphabet[i][alpha-'a'] = true;
					  if(!c.contains(alpha))
						  c.add(alpha);
				  }
			  }
		  }
		  if(k<5) {
			  //k가 5보다 작으면 anta와 tica를 읽을수가 없기 때문에 0 출력
			  System.out.println(0);
		  }
		  else if(c.size()==0) {
			  //k가 5보다 큰데 단어들이 a,n,t,i,c만으로 구성된 단어면 n출력
			  System.out.println(n);
		  }
		  else{
			  //뽑아야할 개수는 새로운 글자수와 가르칠수 있는 글자 개수 중 작은거 선택
			  // 이때 a n t i c 는 항상 먼저 배워야 하므로 k-5
			  output = new char[Math.min(c.size(), k-5)];
			  //새로 배워야하는 글자들 c.size()중에 Math.min(c.size(), k-5)개 뽑는 조합
			  Teaching(0,0,Math.min(c.size(), k-5));
			  System.out.println(result);
		  }
	}
	public static void Teaching(int depth,int start,int k) {
		//다 뽑았다면
		if(depth==k) {
			int count=0;
			boolean[][] copyAlphabet = new boolean[n][26];
			for(int i=0;i<n;i++) {
				copyAlphabet[i] = Arrays.copyOf(alphabet[i], 26);
			}
			for(int i=0;i<n;i++) {
				//뽑은 새로운 글자 인덱스 위치 false
				for(char j : output) {
					copyAlphabet[i][j-'a'] = false;
				}
				//단어를 탐색하였을때 못배운 글자가 없다면 count++
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
			Teaching(depth+1,i+1,k);
		}
	}
}
