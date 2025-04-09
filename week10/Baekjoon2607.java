import java.io.*;

public class Baekjoon2607 {

	//메모리 14264kb 시간 104ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String target = br.readLine();
        
        int[] targetCount = new int[26];
        for (char c : target.toCharArray()) {
        	targetCount[c - 'A']++;
        }

        int ans = 0;

        for (int i = 1; i < n; i++) {
            String word = br.readLine();
            int[] wordCount = new int[26];
            for (char c : word.toCharArray()) {
                wordCount[c - 'A']++;
            }

            // 두 배열 차이 비교
            int diff = 0;
            for (int j = 0; j < 26; j++) {
                diff += Math.abs(targetCount[j] - wordCount[j]);
            }

            if (diff <= 2) {
                // 다만, 길이 차이가 2 이상이면 한 번의 연산으론 못 바꿈
                if (Math.abs(target.length() - word.length()) <= 1) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
