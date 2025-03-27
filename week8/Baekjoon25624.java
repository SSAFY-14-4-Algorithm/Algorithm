import java.io.*;
import java.util.*;
/*
 * 메모리 21832KB
 *  시간 240ms
 */
public class Baekjoon25624 {
    private static int n, m;
    private static String[] inputStrings;
    private static Set<Character>[] possibleResults;
    private static List<String> generatedCombinations = new ArrayList<>();
    private static char[] selected;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        // 입력: n = 자리수, m = 문자열 개수
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);

        inputStrings = new String[m];
        possibleResults = new HashSet[n];
        selected = new char[n];

        for (int i = 0; i < n; i++) {
            possibleResults[i] = new HashSet<>();
        }

        // 입력 문자열 읽기 및 가능한 문자 집합 저장
        for (int i = 0; i < m; i++) {
            inputStrings[i] = br.readLine();
            for (int j = 0; j < n; j++) {
                possibleResults[j].add(inputStrings[i].charAt(j));
            }
        }

        // 조건 1: 각 자리에서 사용 가능한 문자가 중복되지 않는지 확인
        int[] charCount = new int[26];
        for (int i = 0; i < n; i++) {
            for (char c : possibleResults[i]) {
                charCount[c - 'A']++;
            }
        }
        for (int count : charCount) {
            if (count > 1) {
                bw.write("NO\n");
                bw.flush();
                bw.close();
                return;
            }
        }

        // 조건 2: 총 조합 수가 m과 일치하는지 확인
        long totalCombinations = 1;
        for (int i = 0; i < n; i++) {
            totalCombinations *= possibleResults[i].size();
            if (totalCombinations > m) {
                bw.write("NO\n");
                bw.flush();
                bw.close();
                return;
            }
        }
        if (totalCombinations != m) {
            bw.write("NO\n");
            bw.flush();
            bw.close();
            return;
        }

        // DFS를 사용하여 가능한 모든 조합 생성
        generateCombinations(0);

        // 조건 3: 생성된 조합과 입력 문자열 비교
        Arrays.sort(inputStrings);
        Collections.sort(generatedCombinations);

        for (int i = 0; i < m; i++) {
            if (!generatedCombinations.get(i).equals(inputStrings[i])) {
                bw.write("NO\n");
                bw.flush();
                bw.close();
                return;
            }
        }

        // 모든 조건을 만족한 경우
        bw.write("YES\n");
        for (int i = 0; i < n; i++) {
            List<Character> sorted = new ArrayList<>(possibleResults[i]);
            Collections.sort(sorted);
            for (char c : sorted) {
                bw.write(c);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    // DFS를 사용하여 가능한 모든 조합 생성
    static void generateCombinations(int depth) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (char c : selected) {
                sb.append(c);
            }
            generatedCombinations.add(sb.toString());
            return;
        }

        for (char c : possibleResults[depth]) {
            selected[depth] = c;
            generateCombinations(depth + 1);
        }
    }
}