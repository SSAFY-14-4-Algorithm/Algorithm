import java.io.*;
import java.util.*;

/**
 * 432628kb, 3256ms
 */
public class Baekjoon7662 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int test = Integer.parseInt(br.readLine());
        for(int x=0; x<test;x++){
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int i=0;i<k;i++){
                String [] tokens = br.readLine().split(" ");
                int inputNum = Integer.parseInt(tokens[1]);
                if(tokens[0].equals("I")){
                    //삽입
                    map.put(inputNum,map.getOrDefault(inputNum, 0)+1);
                }else{
                    // -1 : 최소값 삭제
                    // 1 : 최대값 삭제
                    if(map.isEmpty()){
                        continue;
                    }else{
                        if(inputNum == -1){
                            int n = map.firstKey();
                            if(map.put(n,map.get(n)-1) == 1){
                                map.remove(n);
                            }
                        }else{
                            int n = map.lastKey();
                            if(map.put(n,map.get(n)-1) == 1){
                                map.remove(n);
                            }
                        }
                    }
                }
            }
            if(map.isEmpty()){
                bw.write("EMPTY\n");
            }else{
                bw.write(map.lastKey()+" "+ map.firstKey()+"\n");
            }
        }
        bw.flush();
    }
}


