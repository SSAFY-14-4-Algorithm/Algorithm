import java.io.*;
import java.util.*;

public class Baekjoon28423 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int LIMIT = 100000;

    public static void main(String[] args) throws Exception {
        String[] s = br.readLine().split(" ");
        int L = Integer.parseInt(s[0]);
        int R = Integer.parseInt(s[1]);

        long result = 0;
        for (int i=L;i<=R;i++) {
            result += g(i);
        }
        System.out.println(result);
    }
    public static int g(int n) {
        Set<Integer> set = new HashSet<>();
        int x = n;

        while(true){
            if(x>LIMIT) {
                // 100000 초과하면 -1 반환
                return -1;
            }
            if(set.contains(x)) {
                // 순환이 발생하거나 고정점 못 찾으면 0 반환
                return 0;
            }
            set.add(x);
            //f(x)계산
            int y = f(x);
            if(y==x) {
                // 고정점 도달하면 1반환
                return 1;
            }
            x = y;
        }
    }

    //f(n) 계산
    public static int f(int n) {
        int sum = 0;
        int prod = 1;
        while(n>0) {
            int d = n % 10;
            sum += d;
            prod *= d;
            n /= 10;
        }
        // 문자열로 이어붙여서 정수 변환
        String result = String.valueOf(sum) + String.valueOf(prod);
        return Integer.parseInt(result);
    }
}
