import java.io.*;
import java.util.*;
/*
 * 메모리: 20080KB
 * 시간: 500ms
 * 
 * 최장 증가하는 부분 수열, dp(최장증가수열의 길이) 
 * 
 * 
 * 
 */
public class Baekjoon2550{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] line1 = new int[N];
        int[] line2 = new int[N];
        int[] idx = new int[N + 1];
        int max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            line1[i] =Integer.parseInt(st.nextToken());
            idx[line1[i]] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            line2[i] = Integer.parseInt(st.nextToken());
        }

        int[] idxArr = new int[N]; //2번째 라인의 전구가 첫번째 라인에서 어디서 나오는지 저장 
        for (int i = 0; i < N; i++) {
            idxArr[i] = idx[line2[i]];
        }

        int[] dp = new int[N];
        int[] prev = new int[N];
        Arrays.fill(dp, 1); //최소한 스스로는 포함
        Arrays.fill(prev, -1);

        int maxLength = 1;
        int lastIndex = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (idxArr[j] < idxArr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }


       ArrayList<Integer> result = new ArrayList<>(); //역추적 
        while (lastIndex != -1) {
            result.add(line2[lastIndex]);
            lastIndex = prev[lastIndex];
        }
        Collections.sort(result);  
        System.out.println(result.size());
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
