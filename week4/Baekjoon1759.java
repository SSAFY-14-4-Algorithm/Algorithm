import java.io.*;
import java.util.*;
public class Baekjoon1759 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int L,C;
    static String[] alphabets;
    static String[] output;
    static ArrayList<String> answers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        L = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        alphabets = new String[C];
        output = new String[L];
        tokens = br.readLine().split(" ");
        for(int i=0;i<C;i++){
            alphabets[i] = tokens[i];
        }
        Arrays.sort(alphabets);
        backtracking(0,0,0,0,"");
        Collections.sort(answers);
        for (String answer : answers) {
            bw.write(answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void backtracking(int depth, int index,int vowels, int consonants,String password){
        if(depth == L){
            if(vowels >= 1 && consonants >= 2 ){
                answers.add(password);
            }
            return;
        }

        if(index >= C) return;

        char currentChar = alphabets[index].charAt(0);
        if(isVowel(currentChar)){
            backtracking(depth+1,index+1,vowels+1,consonants,password+alphabets[index]);
        }else{
            backtracking(depth+1,index+1,vowels,consonants+1,password+alphabets[index]);
        }
        //현재 보고 있는 character를 스킵
        backtracking(depth,index+1,vowels,consonants,password);
    }
    private static boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
