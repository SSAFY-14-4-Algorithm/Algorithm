import java.io.*;
import java.util.*;

public class Beakjoon1759 {
    static int L, C;
    static char[] chars;
    static char[] password;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        chars = new char[C];
        password = new char[L]; 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars); 
        generatePassword(0, 0, 0, 0);
    }

    static void generatePassword(int start, int length, int vowels, int consonants) {
        if (length == L) {
            if (vowels >= 1 && consonants >= 2) {
                System.out.println(new String(password));
            }
            return;
        }

        for (int i = start; i < C; i++) {
            password[length] = chars[i];
            
            if (isVowel(chars[i])) {
                generatePassword(i + 1, length + 1, vowels + 1, consonants);
            } else {
                generatePassword(i + 1, length + 1, vowels, consonants + 1);
            }
        }
    }

    
    static boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}

