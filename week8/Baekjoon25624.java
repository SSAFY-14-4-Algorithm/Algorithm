package week8;

/**
 * <h1>BAEKJOON 25624번 SNUPTI SILVER III</h1>
 * <p>
 * JAVA11 : 메모리 18,588 KB 시간 136ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 */

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon25624 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        int[] usedAlphabet = new int[N];
        Set<String> indicatorSet = new HashSet<>();
        byte[] indicatorByte = new byte[N + 1];
        char[] indicatorChar = new char[N];
        for (int count = 0; count < M; count++) {
            System.in.read(indicatorByte);
            for (int i = 0; i < N; i++) {
                // 직접 'A'를 빼서 0부터 25 범위로 변환
                int letter = indicatorByte[i] - 'A'; // 'A'→0, 'B'→1, …, 'Z'→25
                int bit = 1 << letter; // 'A'→1<<0, 'B'→1<<1, …, 'Z'→1<<25
                usedAlphabet[i] |= bit; // 해당 척도 j에 등장한 알파벳을 누적
                indicatorChar[i] = (char) ('A' + letter); // 결과 문자열 구성
            }
            indicatorSet.add(new String(indicatorChar));
        }

        // (1) 각 척도의 집합끼리 겹치지 않는지 검사:
        // 모든 i != j에 대해, masks[i] & masks[j] == 0 이어야 함.
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((usedAlphabet[i] & usedAlphabet[j]) != 0) { // 겹치는 알파벳이 있다면
                    System.out.println("NO");
                    return;
                }
            }
        }

        // (2) 각 척도의 집합 크기의 곱이 M과 같은지 검사 ||
        // (3) 입력된 문자열의 개수(중복 없이)가 M과 같아야 함.
        int totalWords = 1;
        for (int i = 0; i < N; i++) {
            totalWords *= Integer.bitCount(usedAlphabet[i]);
        }
        if ((totalWords != M) || (indicatorSet.size() != M)) {
            System.out.println("NO");
            return;
        }

        // 조건을 모두 만족하면, 각 척도의 가능한 결과(비트마스크)를 알파벳 순으로 출력
        StringBuilder sb = new StringBuilder();
        sb.append("YES").append("\n");
        for (int i = 0; i < N; i++) {
            for (int bit = 0; bit < 26; bit++) {
                if ((usedAlphabet[i] & (1 << bit)) != 0) {
                    sb.append((char) ('A' + bit));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        // if (c == 13)
        // System.in.read();
        return n;
    }
}
