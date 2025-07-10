import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 	11580kb	68ms
 * 사전 순으로 가장 뒷서는 것 -> 가장 작은 것
 * 연속된 두 수만 스왑 가능
 * i씩 순회하면서
 * start 자리 이후의 숫자 중 max 찾기
 *
 * 스왑할 수 있는 범위 내의 max 값을 앞으로 끌어오기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        arr = new int[N];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int S = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N && S > 0; i++) {

            //최대값 idx 찾기
            int maxIdx = i;
            for (int j = i+1; j < N && j <= i + S ; j++) {
                if (arr[maxIdx] < arr[j]) maxIdx = j;
            }

            //최댓값을 앞으로 끌어오기
            for (int j = maxIdx; j > i; j--) {
                swap(j-1, j);
                S--;
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    static int[] arr;

    static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}