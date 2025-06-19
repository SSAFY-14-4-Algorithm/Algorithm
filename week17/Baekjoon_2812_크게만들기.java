import java.io.*;
import java.util.*;
/*
 * 메모리:19368KB
 * 시간:	160ms
 */
public class Baekjoon_2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]); 
        int K = Integer.parseInt(s[1]);

        String number = br.readLine(); // 전체 숫자 문자열
        StringBuilder sb = new StringBuilder();

        int start = 0; // 현재 탐색 시작 인덱스
        //남길 숫자만큼 반복
        for(int i=0; i<N-K; i++) {
            // 끝값은 K부터 시작해서 1씩 추가
            int end = K + i;
            char maxDigit = '0';
            int maxIndex = start;

            // [start ~ end] 구간 중 가장 큰 숫자 찾기
            for(int j=start; j<=end; j++) {
                if(number.charAt(j) > maxDigit) {
                	//큰값이면 저장
                    maxDigit = number.charAt(j);
                    maxIndex = j;

                    // 가장 큰 숫자인 9면 바로 break 
                    if(maxDigit == '9') break;
                }
            }

            sb.append(maxDigit);
            start = maxIndex + 1; // 다음 시작값은 그 다음 자리부터
        }

        // 결과 출력
        System.out.println(sb.toString());
    }
}
