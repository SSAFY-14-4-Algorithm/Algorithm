import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//240ms
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        int[] h=new int[N];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            h[i]=Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        int need2 = 0;
        for (int i = 0; i < N; i++) {
            sum += h[i];
            need2+=h[i]/2;

        }
        if (sum % 3 != 0 ||need2 < sum / 3) {
            System.out.println("NO");

        }
        else
            System.out.println("YES");

    }
}
