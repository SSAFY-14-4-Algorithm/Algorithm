package week4;
import java.util.*;
import java.io.*;

public class BaekJoon15686 {
	
	/* 
	 * 1. 집, 치킨 집의 위치, 갯수 기록
	 * 2. 도시에 있는 치킨 집에서 모든 집까지 거리 계산
	 * 3. 치킨집 조합을 구하여 해당 경우의 수로 거리 계산 (조합)
	 * 4. 최소값 갱신
	 */
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int[][] city;
    static int[][] storeArr; // 치킨집 위치 배열
    static int[][] homeArr; // 집 위치 배열
    static int[][] storeTohomeDistance; // 치킨집과 집과의 거리 차이 배열
    static int N, M, homeCnt, storeCnt, minDis;
    
    // 치킨 집과 모든 집까지의 모든 거리 차이 계산
    private static void calcultateDiffDistance() {
    
    	for(int i=0;i<storeCnt;i++) {
    		for(int j=0;j<homeCnt;j++) {
    			
    			int dx = Math.abs(storeArr[i][0] - homeArr[j][0]);
    			int dy = Math.abs(storeArr[i][1] - homeArr[j][1]);
    			int sum = dx + dy;
    			
    			storeTohomeDistance[i][j] = sum;
    		}
    	}
    }
    
    // 치킨 집 조합 경우의 수 구하기 ( {storeCnt} C {M})
    private static void CombinationStore(int depth, int start, int[] result) {
    	if(depth == M) {
    		minDis = Math.min(minDis, calculateDistance(result));
    		return;
    	}
    	
    	for(int i=start;i<storeCnt;i++) {
    		result[depth] = i;
    		CombinationStore(depth+1, i+1, result);
    	}
    }
    
    // 각 집에서 부터 모든 치킨 집까지의 거리를 확인 후, 최소 거리 모두 더하여 반환
    private static int calculateDistance(int[] result) {
    	
    	int sum = 0;
    	int min;
    	
    	for(int i=0;i<homeCnt;i++) { //집에서 모든 치킨집까지의 거리 확인
    		min = Integer.MAX_VALUE;
    		
    		for(int j=0;j<result.length;j++) {	
    			min = Math.min(storeTohomeDistance[result[j]][i], min);
    		}
    		sum += min; // 가장 최소 거리의 치킨 집을 찾아 거리를 모두 더함
    	}
    	return sum;
    }
    
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minDis = Integer.MAX_VALUE;
        homeCnt = 0;
        storeCnt = 0;
        
        city = new int[N][N];
        storeArr = new int[13][2];
        homeArr = new int[2*N][2];
        storeTohomeDistance = new int[13][2*N];
        
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
            	city[i][j] = Integer.parseInt(st.nextToken());
            	
            	if(city[i][j] == 1) {
            		homeArr[homeCnt][0] = i;
            		homeArr[homeCnt++][1] = j;
            	}else if(city[i][j] == 2) {
            		storeArr[storeCnt][0] = i;
            		storeArr[storeCnt++][1] = j;
            	}
            }
        }
       
        calcultateDiffDistance();
        	
        CombinationStore(0,0, new int[M]);
        
        System.out.println(minDis);
    }

}