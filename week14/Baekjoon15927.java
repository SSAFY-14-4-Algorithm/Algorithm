package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon15927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if (!isPalindrome(str)) {
            System.out.println(str.length());
        } else {
            boolean allSame = true;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) != str.charAt(0)) {
                    allSame = false;
                    break;
                }
            }
            System.out.println(allSame ? -1 : str.length() - 1);
        }
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}
