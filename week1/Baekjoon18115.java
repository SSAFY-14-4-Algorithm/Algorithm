package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon18115 {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int num=Integer.parseInt(br.readLine());
        int nums[]=new int[num];
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder stringBuilder=new StringBuilder();
        Deque<Integer> deque=new LinkedList<>();

        for(int i=num-1;i>=0;i--){
            nums[i]=Integer.parseInt(st.nextToken());;
        }
        for(int i=0;i<num;i++) {
            switch (nums[i]) {
                case 1: {
                    deque.addFirst(i+1);
                    break;
                }
                case 2: {
                    int top = deque.removeFirst();
                    deque.addFirst(i+1);
                    deque.addFirst(top);
                    break;
                }
                case 3: {
                    deque.addLast(i+1);
                    break;
                }
            }
        }
        for(int i=0;i<num;i++){
            stringBuilder.append(deque.pop()+" ");
        }
        System.out.println(stringBuilder);

    }

}
