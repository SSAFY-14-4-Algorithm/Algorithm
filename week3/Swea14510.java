package week3;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Swea14510{
    public static void main(String[] args) {

        //max값을 구한다
        //max-각수 를 구한다
        //각 수의 2의 횟수 1의 횟수를 구한다
        //count가 2일때 2의 횟수를 깍고 1일때 1의 횟수를 깍는다

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int t = 1; t <= T; t++) {
            List<Integer> list = new ArrayList<>();
            int treeCount = sc.nextInt();
            int day = 0;
            int max = 0;
            int twoCount = 0;
            int oneCount = 0;
            for (int i = 0; i < treeCount; i++) {
                int num = sc.nextInt();
                list.add(num);
                max = Math.max(max, num);
            }
            list.removeAll(Collections.singleton(max));

            //뺴야할 수
            for (int i = 0; i < list.size(); i++) {
                list.set(i, max - list.get(i));
            }
            for (int i = 0; i < list.size(); i++) {
                twoCount = twoCount + (list.get(i) / 2);
                oneCount = oneCount + (list.get(i) % 2);
            }

            if (oneCount > twoCount) {
                day = twoCount * 2;
                day=day+((oneCount-twoCount)*2)-1;
            } else if (twoCount > oneCount) {
                day=oneCount*2;
                int minus=twoCount-oneCount;
                boolean flog=false;
                while(minus>0){
                    day++;
                    //홀수날을 하루 하고 또 홀수날 일때
                    if(flog&&day%2==1){
                        minus--;
                        flog=false;
                    }else if(!flog&&day%2==1){
                        flog=true;
                    }
                    //홀수날이지만 flog가 false일떄

                    if(day%2==0){
                        minus--;
                    }

                }
            } else if (oneCount==twoCount) {
                day=oneCount*2;
            }
            System.out.println("#"+t+" "+day);




        }
    }
}