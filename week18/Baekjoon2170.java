import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class  Baekjoon2170 {

    //메모리 276216kb 시간 2704ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }
        int ans = 0;

        //오름차순 정렬
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int start = arr[0][0];
        int end = arr[0][1];

        for (int i = 1; i < arr.length; i++) {
            if (end >= arr[i][0]) {
                end = Math.max(end, arr[i][1]);
            } else {
                ans += end - start;
                start = arr[i][0];
                end = arr[i][1];
            }
        }

        //마지막 구간
        ans += end - start;

        System.out.println(ans);


    }
}
