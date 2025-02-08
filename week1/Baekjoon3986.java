package week1;

public class Baekjoon3986 {
  public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//스택 활용
		
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		for(int i=0;i<N;i++) {
			Stack<Character> stack = new Stack<>();
			String line = br.readLine();
			for(char c : line.toCharArray()) {
				if(!stack.isEmpty() && stack.peek()==c) {
					stack.pop();
				} else {
					stack.push(c);
				}
			}
			if(stack.isEmpty()) {
				cnt++;
			}
		}
		
		bw.write(cnt+"\n");
		bw.flush();
		br.close();
		bw.close();
	

	}
}
