import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  static List<Long> list = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    if (N > 1022) {
      System.out.println(-1);
      System.exit(0);
    }else {
      for (int i = 0; i <= 9; i++) {
        permutation(1, i);
      }
//      System.out.println(list);

      Collections.sort(list);
      System.out.println(list.get(N));
    }
  }

  static void permutation(int cnt, long num) {

    if (cnt > 10) {
      return;
    }
    list.add(num);
    for (int i = 0; i < num % 10; i++) {
      permutation(cnt + 1, (num * 10) + i);
    }
  }

}
