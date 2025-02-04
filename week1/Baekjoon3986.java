package week1;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon3986 {

    //A와 B가 겹치지않는 선으로 연결되면 된다
    //문자를 스택에 넣는다
    //넣을때 마다 맨위에 값을 가져와 넣는 문자와 같으면 pop
    //아닐시 add 한다
    //스택이 비여있으면 카운트를 1 상승시킨다

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int result=0;
        int T=Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            String word=br.readLine();
            int wordLength=word.length();
            Stack<Character> stack=new Stack<>();
            for(int j=0;j<wordLength;j++){
                if(!stack.empty()&&stack.peek()==word.charAt(j)){
                    stack.pop();
                }else{
                    stack.add(word.charAt(j));
                }
            }
            if(stack.empty()){
                result++;
            }
        }
        System.out.println(result);



    }
}
