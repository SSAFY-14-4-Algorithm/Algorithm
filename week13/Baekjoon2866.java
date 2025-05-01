import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon2866 {

    static int count;
    static String[] ipt;

    //메모리 41564kb 시간 468ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        count = 0;

        ipt = new String[r];
        for (int i = 0; i < r; i++) {
            ipt[i] = br.readLine();
        }

        //이분 탐색
        //맨 위에서부터 n개 행을 잘랐을 때, 가장 큰 n
        int low = 0, high = r; // 최소/최대 잘라낼 수 있는 행 수
        while (low <= high) {
            int mid = (low + high) / 2;
            Set<String> set = new HashSet<>();
            boolean flag = false;

            for (int i = 0; i < c; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = mid; j < r; j++) {
                    sb.append(ipt[j].charAt(i));
                }
                String col = sb.toString();

                //이미 단어가 set에 있다면 false반환
                if(!set.add(col)) {
                    flag = true;
                    break;
                }
            }

            if(flag) {
                // mid를 너무 크게 잡으면 중복이 발생
                // 삭제 시작 인덱스를 줄여야 함
                high = mid - 1;
            } else {
                // 중복 없이 모두 유일 → 이 mid는 가능한 값
                // 더 큰 mid도 시도
                count = mid;
                low = mid + 1;
            }
        }
        System.out.println(count);
    }
}
