package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_16500_문자열판별 {
    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
    }

    // Trie에 단어 삽입
    private static void insert(TrieNode root, String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (current.next[idx] == null)
                current.next[idx] = new TrieNode();
            current = current.next[idx];
        }
        current.isWord = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력
        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());

        // Trie 초기화 및 단어 삽입
        TrieNode root = new TrieNode();
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            String w = br.readLine().trim();
            insert(root, w);
            maxLen = Math.max(maxLen, w.length());
        }

        int S_length = S.length();
        boolean[] dp = new boolean[S_length + 1];
        dp[0] = true;

        // DP + Trie 탐색
        for (int i = 0; i < S_length; i++) {
            if (!dp[i]) {
                continue;
            }
            TrieNode p = root;
            // 최대 단어 길이만큼만 내려간다
            for (int j = i; j < S_length && j < i + maxLen; j++) {
                p = p.next[S.charAt(j) - 'a'];
                if (p == null) {
                    break;
                }
                if (p.isWord) {
                    dp[j + 1] = true;
                }
            }
        }

        // 출력
        System.out.print(dp[S_length] ? "1" : "0");
    }
}
