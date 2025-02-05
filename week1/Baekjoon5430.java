package week1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Baekjoon5430 {

  public static StringBuilder st = new StringBuilder();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = Integer.parseInt(sc.nextLine());

    for (int t = 0; t < T; t++) {
      String AC = sc.nextLine();
      int numSize = Integer.parseInt(sc.nextLine());
      String numbersRead = sc.nextLine();
      String[] numberArray = numbersRead.substring(1, numbersRead.length() - 1).split(",");
      boolean reverseCheck = false;
      Deque<Integer> deque = new ArrayDeque<>();
      for (int i = 0; i < numSize; i++) {
        deque.add(Integer.parseInt(numberArray[i]));
      }
      boolean error = false;
      for (int i = 0; i < AC.length(); i++) {
        if (AC.charAt(i) == 'D' && deque.isEmpty()) {
          error = true;
          break;
        }
        if (AC.charAt(i) == 'R') {
          reverseCheck = !reverseCheck;
          continue;
        } else if (AC.charAt(i) == 'D') {
          if (reverseCheck) {
            deque.pollLast();
          } else {
            deque.pollFirst();
          }
        }
      }
      if (error == true) {
        st.append("error\n");
      } else {
        StringBuilder result = new StringBuilder("[");
        if (reverseCheck == true) {
          while (!deque.isEmpty()) {
            result.append(deque.pollLast()).append(",");
          }
        } else {
          while (!deque.isEmpty()) {
            result.append(deque.pollFirst()).append(",");
          }
        }
        if (result.length() > 1) {
          result.setLength(result.length() - 1);
        }
        result.append("]");
        st.append(result).append("\n");
      }
    }
    System.out.println(st);
  }

}
