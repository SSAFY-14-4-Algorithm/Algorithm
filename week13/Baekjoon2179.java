import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon2179 {

    //메모리 17424kb 시간 1308ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        // 문자열 인덱스 쌍
        int sIdx = -1;
        int tIdx = -1;

        int max = Integer.MIN_VALUE; //최대 공통 접두사 길이
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                int cnt = check(arr[i], arr[j]);
                if (cnt > max) {
                    max = cnt;
                    sIdx = i;
                    tIdx = j;
                }
            }
        }
        System.out.println(arr[sIdx]);
        System.out.println(arr[tIdx]);

    }

    static int check(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            cnt++;
        }
        return cnt;
    }

}
