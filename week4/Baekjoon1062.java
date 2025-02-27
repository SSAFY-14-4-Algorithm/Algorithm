import java.io.*;
import java.util.*;

public class Baekjoon1062 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, maxReadable = 0;
    static boolean[] visited = new boolean[26]; 
    static List<Integer> extraAlphabets = new ArrayList<>(); 
    static boolean[][] wordMask; 

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) { 
            System.out.println(0);
            return;
        } else if (K == 26) { 
            System.out.println(N);
            return;
        }

        wordMask = new boolean[N][26]; 
        visited['a' - 'a'] = visited['n' - 'a'] = visited['t' - 'a'] = visited['i' - 'a'] = visited['c' - 'a'] = true;

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 4; j < word.length() - 4; j++) { // "anta", "tica" 제외
                int c = word.charAt(j) - 'a';
                wordMask[i][c] = true;
                if (!visited[c] && !extraAlphabets.contains(c)) { // 아직 배우지 않은 새 글자 추가
                    extraAlphabets.add(c);
                }
            }
        }

        int learnable = K - 5;
        int selectCount = Math.min(extraAlphabets.size(), learnable);
        combination(0, 0, selectCount);

        System.out.println(maxReadable);
    }


    static void combination(int depth, int start, int limit) {
        if (depth == limit) {
            maxReadable = Math.max(maxReadable, countReadableWords());
            return;
        }
        for (int i = start; i < extraAlphabets.size(); i++) {
            visited[extraAlphabets.get(i)] = true;
            combination(depth + 1, i + 1, limit);
            visited[extraAlphabets.get(i)] = false;
        }
    }


    static int countReadableWords() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            boolean readable = true;
            for (int j = 0; j < 26; j++) {
                if (wordMask[i][j] && !visited[j]) { // 필요한 알파벳인데 배우지 않았다면 읽을 수 없음
                    readable = false;
                    break;
                }
            }
            if (readable) count++;
        }
        return count;
    }
}
