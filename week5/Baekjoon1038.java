import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		List<Long> Numbers = new ArrayList<>(); //감소하는 수 목록을 미리 모두 작성
		makeNumbers(Numbers);
		
		if(N>= Numbers.size()) {
			System.out.println(-1);
		}else {
			System.out.println(Numbers.get(N));
		}
	}
	
	public static void makeNumbers(List<Long> list) {
		Queue<Long> queue = new LinkedList<>();
		
		for (int i=0;i<=9;i++) { //큐에 0~9를 넣어 감소하는 수를 만들기 위해서 기반을 만들어 두는 것 
			queue.offer((long)i);
			list.add((long) i);
		}
		
		while(!queue.isEmpty()) { // 감소하는 수를 생성하기 (bfs이용)
			long num = queue.poll();
			long lastDigit = num%10; // 마지막 자리 숫자가 뭔지 알기 위해서 수
			
			for(int i=0;i<lastDigit;i++) { //마지막 자리보다 작은 수를 맨 뒤에 붙여서 새로운 감소하는 수를 생
				long newNum = num*10+i;
				queue.offer(newNum);
				list.add(newNum);
			}
		}
		
		Collections.sort(list); //만든 감소하는 수의 리스트를 오름차순으로 정
		
	}
}
