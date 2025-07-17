import java.io.*;
import java.util.*;
/*
 * 메모리:11540KB
 * 시간:	76ms
 */
public class Baekjoon_1477_휴게소세우기{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    public static void main(String[]args) throws IOException{
        String[] s=br.readLine().split(" ");
        
        int N=Integer.parseInt(s[0]);
        int M=Integer.parseInt(s[1]);
        int L=Integer.parseInt(s[2]);
        
        int[] stops = new int[N+2];       
        stops[0] = 0;
        stops[N+1] = L;                                 
        
        if(N>0){
            String[] s2 = br.readLine().split(" ");                  
            for(int i=0;i<N;i++)
            	stops[i+1] = Integer.parseInt(s2[i]);
        }else 
        	br.readLine();  // N=0일 때 빈 줄 처리
        
        Arrays.sort(stops);                                       

        int low = 1;
        int high = L;
        int answer = L; 
        
        // 이분 탐색 
        while(low<=high){
        	// mid: 허용 최대 거리, rest: 추가 필요 휴게소 수
            int mid=(low+high)/2;
            int rest=0;                           
            for(int i=1;i<stops.length;i++){
                int gap = stops[i] - stops[i-1];                      // 인접 휴게소 사이 간격
                if(gap>mid)
                	rest+=(gap+mid-1)/mid-1;                // 간격을 mid 이하로 만들기 위해 필요한 개수
            }
            if(rest>M){
                low = mid+1;   // 필요 개수가 M보다 많으면 mid가 작으므로 범위 상향
            } else {
                answer = mid;  // 충분히 지을 수 있으면 답 갱신
                high = mid-1;  // 범위 변경
            }
        }
        System.out.println(answer);                               
    }
}
