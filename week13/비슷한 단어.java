import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 정렬한 후, 가장 긴 공통 접두어를 찾고,
 * 그 접두어를 공유하는 두 단어를 입력된 순서대로 출력
 *
 * 처음에는 공통 접두사를 구할 필요가 없다고 판단,
 * 인접한 문자열만 비교했지만,
 * 그럴 경우 접두사 길이가 같을 경우를 제대로 처리하지 못함.
 * 또한 입력 순서대로 기억하려고 하다보니 코드가 복잡해짐
 *
 * 따라서 최대 공통 접두사를 기록해두고,
 * 그 접두사를 가진 단어 중 먼저 입력된 쌍을 출력하는 방식을 적용함
 */
public class Main {

    static class Word implements Comparable<Word> {
        String word;
        int idx;

        Word(String word, int idx) {
            this.word = word;
            this.idx = idx;
        }

        @Override
        public int compareTo(Word o) { //단어를 사전순 정렬
            return this.word.compareTo(o.word);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Word[] words = new Word[n];
        for (int i = 0; i < n; i++) {
            words[i] = new Word(reader.readLine(), i);
        }

        // 사전순 정렬
        Arrays.sort(words);

        String prefix = "";
        int answerMinIdx = Integer.MAX_VALUE;

        // 인접한 단어들 간 접두사 비교
        for (int i = 0; i < n - 1; i++) {
            Word w1 = words[i];
            Word w2 = words[i + 1];

            if (w1.word.charAt(0) != w2.word.charAt(0)) continue; //처음부터 다르면 무시

            int maxLen = Math.min(w1.word.length(), w2.word.length());
            int j = 0;
            while (j < maxLen && w1.word.charAt(j) == w2.word.charAt(j)) { //공통 접두어의 길이를 구함
                j++;
            }

            if (j == 0) continue;

            int minIdx = Math.min(w1.idx, w2.idx);

            //공통 접두사의 길이가 더 길 경우, 혹은 같다면 더 먼저 나온 경우에 갱신
            if (prefix.length() < j || (prefix.length() == j && minIdx < answerMinIdx)) {
                prefix = w1.word.substring(0, j);
                answerMinIdx = minIdx;
            }
        }

        // prefix로 시작하는 단어 중 입력순으로 정렬
        List<Word> candidates = new ArrayList<>();
        for (Word w : words) {
            if (w.word.startsWith(prefix)) {
                candidates.add(w);
            }
        }

        candidates.sort(Comparator.comparingInt(w -> w.idx));

        System.out.println(candidates.get(0).word);
        System.out.println(candidates.get(1).word);
    }
}
