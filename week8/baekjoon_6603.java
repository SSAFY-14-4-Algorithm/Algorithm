import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6603_로또_전재욱 {

  static int[] result;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      if (n == 0) {
        System.out.println(sb);
        System.exit(0);
      }
        result = new int[6];
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        int num = Integer.parseInt(st.nextToken());


        nums[i] = num;
      }
      combination(nums, n, 0, 0);
      sb.append('\n');
    }

  }

  private static void combination(int[] nums, int n, int cnt, int start) {
    if (cnt == 6) {
      for (int r : result) {
        sb.append(r).append(" ");
      }
      sb.append('\n');
      return;

    }

    for (int i = start; i < n; i++) {
      result[cnt] = nums[i];
      combination(nums, n, cnt + 1, i + 1);
    }

  }
}
