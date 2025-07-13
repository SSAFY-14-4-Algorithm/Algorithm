import java.io.*;
import java.util.*;

/**
 * 15936kb
 * 124ms
 */
public class Baekjoon1477 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 현재 휴게소의 개수 N
     * 더 지으려고 하는 휴게소의 개수 M
     * 고속도로의 길이 L
     */

    static int N,M,L;
    static List<Integer> bridges;
    public static void main(String[] args) throws IOException{
        init();
    
        long left = 1;
        long right = L;

        int ans = 0;
        while(left < right){
            int mid = (int)((left + right) / 2);
            if(isPossible(mid)){
                //다리를 지을 수 있는 경우 -> 최대값을 줄여도 괜찮
                ans = mid;
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        bw.write(ans+"\n");
        bw.flush();
        br.close();
        bw.close();
    }

    static boolean isPossible(int mid){
        int count = 0;
        for(int i=1;i<bridges.size();i++){
            int length = bridges.get(i) - bridges.get(i-1);

            count += (length - 1) / mid;
        }

        return count <= M;
    }

    static void init() throws IOException {
    String[] tokens = br.readLine().split(" ");
    N = Integer.parseInt(tokens[0]);
    M = Integer.parseInt(tokens[1]);
    L = Integer.parseInt(tokens[2]);

    bridges = new ArrayList<>();

    if (N > 0) {
        tokens = br.readLine().split(" ");
        for (String input : tokens) {
            bridges.add(Integer.parseInt(input));
        }
    }

    bridges.add(0);
    bridges.add(L);
    Collections.sort(bridges);
}
}
