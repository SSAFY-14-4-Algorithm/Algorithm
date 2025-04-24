/*
 * BOJ 1744_수묶기
 * 메모리 : 11,644kb
 * 시간 : 68ms
 * 
 * 1. 음수
 * 1-1. 음수가 2개 이상이면 음수 2개 곱해서 더함
 * 1-2. 음수가 홀수이면 결과는 무조건 음수
 * 1-3. 음수가 홀수일 때 0이 있으면 0으로 음수 제거(음수를 없앨 수 있음), 0이 없으면 그냥 더하기
 * 2. 양수
 * 2-1. 양수 2개 곱해서 더함
 * 2-1. 양수 중 1이 있으면 곱하지 말고 더하기 (더하는게 더 이득)
 */

import java.io.*;
import java.util.*;

public class Baekjoon1744 {
	static int N;
	static Num[] num;
	static List<Integer> sumList;
	static int maxSum;
	
	public static class Num implements Comparable<Num>{
		int num;
		boolean check;
		
		public Num(int num) {
			this.num = num;
			this.check = false;
		}

		@Override
		public int compareTo(Num o) {
			return this.num-o.num;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        num = new Num[N];
        sumList = new ArrayList<>();
        
        for(int n = 0; n < N; n++) {
        	num[n] = new Num(Integer.parseInt(br.readLine()));
        }

        Arrays.sort(num);

        // 1. 음수 처리
        // 1-1. 정렬된 리스트 앞쪽에서 음수 2개씩 짝지어서 곱함
        // 1-2. 짝이 없거나 다음 수가 0보다 크면 break
        for(int n = 0; n < N; n += 2) {
        	if(n+1 < N) {
        		if(num[n].num <= 0 && num[n+1].num <= 0) {
        			num[n].check = true;
        			num[n+1].check = true;
        			sumList.add(num[n].num * num[n+1].num);
        		} else {
        			break;
        		}
        	}
        }
        // 1-3. 0이 있고, 짝이 없는 음수가 있을 경우
        boolean isZero = false;
        for(int n = 0; n < N; n++) {
        	if(num[n].num==0) {
        		isZero = true;
        		break;
        	}
        }
        for(int n = 0; n < N; n++) {
        	if(num[n].num < 0 && !num[n].check) {
        		if(isZero) {
        			num[n].check = true; 
        			break; // 음수 하나만 제거해주고 break
        		}
        	}
        }

        // 2. 양수 처리
        // 2-1. 정렬된 리스트 뒤쪽에서 양수 2개씩 짝지어서 곱합
        // 2-2. 1은 곱해도 변화없음 (더해줘야 함)
        for(int n = N-1; n >= 0; n -= 2) {
        	if(n-1 >= 0) {
        		if(num[n].num > 1 && num[n-1].num > 1) {
        			num[n].check = true;
        			num[n-1].check = true;
        			sumList.add(num[n].num * num[n-1].num);
        		} else {
        			break;
        		}
        	}
        }
        
        // 안묶인 남은 숫자들은 그냥 더하기
       for(int i = 0; i < N ; i++){
            if(!num[i].check){
                sumList.add(num[i].num);
            }
        }

        maxSum = 0;
        
        for(int sum : sumList) {
        	maxSum += sum;
        }

        sb.append(maxSum);
        System.out.print(sb);
        br.close();
    }
}
