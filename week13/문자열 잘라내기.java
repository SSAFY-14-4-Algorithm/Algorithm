import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

/**
 *
 * @author SSAFY
 *
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] input = new char[R][C];
        LinkedList<String>[] str = new LinkedList[C];
        for (int i = 0; i < C; i++) {
            str[i] = new LinkedList<>();
        }

        for (int i = 0; i < R; i++) {
            input[i] = reader.readLine().toCharArray();
        }

        //해쉬에 저장하고, 중복여부 판단
        int cnt=0;
        for (int r = 1; r < R; r++) { //행 지우기
            HashSet<String> hashSet = new HashSet<>();
            boolean isDuplicate = false;

            for (int c= 0; c < C; c++) {
                StringBuilder sb = new StringBuilder();

                //문자열 만들기
                for (int i = r; i < R; i++) {
                    sb.append(input[i][c]);
                }
                if(!hashSet.add(sb.toString())) { //중복 제거가 안되면 true, 아니면 false
                    isDuplicate = true;
                    break;
                }
            }
            if(isDuplicate) break;
            cnt++;
        }

        System.out.println(cnt);
    }




}

