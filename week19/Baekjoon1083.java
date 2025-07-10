import java.util.*;
import java.io.*;
/* 소트
 * 14316kb, 108ms
 */
public class Baekjoon1083 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        int S = Integer.parseInt(br.readLine());
        int cnt = 0;
        ArrayList<Integer> sort = (ArrayList<Integer>) arr.clone();
        Collections.sort(sort, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        while(S > 0){
            boolean check = true;
            A: for(int max : sort) {
                for(int i = 0; i < arr.size(); i++){
                    if(arr.get(i) == max && i <= S){
                        S -= i;
                        sb.append(max).append(" ");
                        arr.remove(i);
                        check = false;
                        break A;
                    }
                }
            }
            if(check){
                break;
            }
        }


        for(int num : arr){
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}