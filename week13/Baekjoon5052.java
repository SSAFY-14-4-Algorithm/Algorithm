import java.io.*;
import java.util.*;
/* 전화번호 목록
 * 102540kb, 308ms
 */
public class Baekjoon5052 {
	static class Word{
		Word[] child;
		boolean check;
		public Word() {
			this.child = new Word[10];
			this.check = false;
		}
	}
	
	
	static class Trie {
		Word root;
		
		public Trie() {
			this.root = new Word();
		}
		public boolean insert(String str) {
			Word word = this.root;
			for(int i = 0; i < str.length(); i++) {
				int n = str.charAt(i) - '0';
				if(word.child[n] == null) {
					word.child[n] = new Word();
				} else if(word.child[n].check || i == str.length()-1) {
					return true;
				}
				word = word.child[n];
			}
			word.check = true;
			return false;
		}
	}
	
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int testCase = 0; testCase < T; testCase++) {
        	int N = Integer.parseInt(br.readLine());
        	Trie t = new Trie();
        	boolean flag = false;
        	for(int i = 0; i < N; i++) {
        		String s = br.readLine();
        		if(!flag) {
        			if(t.insert(s)) {
        				flag = true;
        			}
        		}
        	}
        	if(flag) {
        		sb.append("NO").append("\n");
        	} else {
        		sb.append("YES").append("\n");
        	}
        }
        System.out.print(sb);
    }
}