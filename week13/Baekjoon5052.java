import java.io.*;
import java.util.*;

public class Baekjoon5052 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Trie
    public static class TrieNode {
        TrieNode[] next = new TrieNode[10];
        boolean end = false;
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            TrieNode root = new TrieNode();
            boolean flag = true;
            for(int i = 0; i < n; i++) {
                String num = br.readLine();
                // 일관성 체크: insert 실패 시 접두어 충돌
                if(flag && !insert(root, num)) flag = false;
            }
            System.out.println(flag ? "YES" : "NO");
        }
    }

    // num을 트라이에 삽입, 접두어 충돌 시 false 반환
    public static boolean insert(TrieNode root, String num) {
        TrieNode cur = root;
        for(int i = 0; i < num.length(); i++) {
            int d = num.charAt(i) - '0';
            if(cur.next[d] == null) cur.next[d] = new TrieNode();
            cur = cur.next[d];
            // 이미 이 지점이 번호 끝이면 기존 번호가 접두어
            if(cur.end) return false;
        }
        // 현재 번호가 다른 번호의 접두어인지 확인
        for(int i = 0; i < 10; i++) {
            if(cur.next[i] != null) return false;
        }
        cur.end = true;
        return true;
    }
}
