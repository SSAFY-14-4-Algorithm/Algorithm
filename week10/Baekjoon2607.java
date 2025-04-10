import java.io.*;
import java.util.*;
/*
 * 메모리: 11588KB
 * 시간: 68ms
 * 
 * 
 * 1. 단어 길이 차이 (2미만인지 확인)
 * 2. 바꿈: 길이는 동일하고 두 단어의 다른 알파벳 개수가 2이하
 * 3. 하나 추가하거나 삭제한 경우: 길이는 1차이, 두 단어의 다른 알파벳 개수가 1 혹은 2 
 *
 */

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String OrgWord = br.readLine();
        int[] OrgCount = getCharCount(OrgWord);
        int ans = 0;

        for (int i = 1; i < N; i++) {
            String word = br.readLine();
            int[] wordCount = getCharCount(word);

            if (isSimilar(OrgCount, wordCount, OrgWord.length(), word.length())) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static int[] getCharCount(String s) { //단어를 각 알파벳의 조합으로 만드는 메소드 
        int[] count = new int[26]; 
        for (char c : s.toCharArray()) {
            count[c - 'A']++;
        }
        return count;
    }

    private static boolean isSimilar(int[] a, int[] b, int lenA, int lenB) {
        int diff = 0; //두 단어의 길이 차이 
        for (int i = 0; i < 26; i++) {
            diff += Math.abs(a[i] - b[i]); //a는 기준 단어,b는 비교할 단어를 알파벳 조합으로 만든 
        }

        if (lenA == lenB) {
            return diff <= 2;
        } else if (Math.abs(lenA - lenB) == 1) {           
            return diff <= 1 || diff == 2;
        }
        return false;
    }
}
