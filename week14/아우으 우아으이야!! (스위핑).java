import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(reader.readLine());

        long totalLen = 0;
        long prevX = Integer.MIN_VALUE;
        long prevY = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //겹치면서, 구간이 연장될 때
            if(prevY >= x && prevY < y) {
                prevY = y;
            }

            //겹치지 않는 새로운 선분
            else if (prevY < x){
                totalLen += prevY - prevX; //이전 구간 길이 더하기
                prevX = x; //새로운 구간 시작
                prevY = y;
                //System.out.println("새로운 구간" + prevX + " " + prevY);
            }
        }

        totalLen += prevY - prevX; //마지막 구간 더하기
        System.out.print(totalLen);
    }


}

