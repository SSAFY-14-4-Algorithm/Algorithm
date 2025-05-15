import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2746_좋은배열만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        int[] arr=new int[N];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int count=0;
        for(int i=N-1;i>=2;i--){
            int start=0;
            int end=i-1;
            while(start<end){
                int sum=arr[start]+arr[end];
                if(sum==arr[i]){
                    start++;
                    end--;
                    count++;
                } else if(sum>arr[i]){
                  end--;
                } else if (sum<arr[i]) {
                    start++;
                }

            }
        }
        System.out.println(count);
    }
}
