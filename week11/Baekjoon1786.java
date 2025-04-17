package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1786 {
    protected static int count = 0;
    protected static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String pattern = br.readLine();
        kmpSearch(text, pattern);
        sb.insert(0, '\n');
        sb.insert(0, count);
        System.out.print(sb.toString());
    }

    public static int[] buildPMT(String pattern) {
        int[] pmt = new int[pattern.length()];
        int length = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (length != 0 && pattern.charAt(i) != pattern.charAt(length)) {
                length = pmt[length - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(length)) {
                pmt[i] = ++length;
            }
        }
        return pmt;
    }

    public static void kmpSearch(String text, String pattern) {
        int pLen = pattern.length();
        int[] table = buildPMT(pattern);
        int index = 0;
        for (int i = 0; i < text.length(); i++) {
            while (index > 0 && text.charAt(i) != pattern.charAt(index)) {
                index = table[index - 1];
            }
            if (text.charAt(i) == pattern.charAt(index)) {
                if (index == pLen - 1) {
                    count++;
                    sb.append(i - pLen + 2).append('\n');
                    index = table[index];
                } else {
                    index++;
                }
            }
        }
    }
}