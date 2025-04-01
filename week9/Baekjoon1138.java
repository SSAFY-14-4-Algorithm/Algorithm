import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리 15892kb 시간 128ms
public class Baekjoon1138 {
	static int n;
	static int tall[], ans[];
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		tall = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < n ; i++) {
			tall[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = new int[n];
		
		//tall[i] = {2, 1, 1, 0}
		//키가 1일때 자기보다 큰 사람이 앞에 2명 있었다.
		//키 작은 사람부터 줄 세우기
		for (int i = 0; i < n; i++) {
		    int cnt = 0;
		    for (int j = 0; j < n; j++) {
		        if (ans[j] == 0) {
		            if (cnt == tall[i]) {
		                ans[j] = i + 1;
		                break;
		            }
		            cnt++;
		        }
		    }
		}

		for (int i = 0; i < n; i++) {
		    System.out.print(ans[i] + " ");
		}

	}
}
