import java.io.*;
import java.util.*;


public class Baekjoon5430 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<T;i++) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String x = br.readLine();
			String[] s = x.replace("[", "").replace("]", "").split(",");
			Deque<String> dq = new LinkedList<>();
			boolean reversed = false;
			boolean error = false;
			for(String str : s) {
				dq.offer(str);
			}
			
			for(int j=0;j<p.length();j++) {
				if(String.valueOf(p.charAt(j)).equals("R")) {
					reversed = !reversed;
				}
				if(String.valueOf(p.charAt(j)).equals("D")) {
					if(dq.isEmpty() || dq.peek().equals("")) {
						sb.append("error\n");
						error = true;
						break;
					}
					else if(reversed) {
						dq.pollLast();
					}
					else {
						dq.pollFirst();
					}
				}
			}
			if(error) continue;
			sb.append("[");
			if(dq.isEmpty()) {
				sb.append("]\n");
				continue;
			}
			if(reversed) {
				sb.append(dq.pollLast());
				while(!dq.isEmpty()) {
					if(dq.peekLast()!="")
						sb.append(","+dq.pollLast());
				}
			}
			else {
				sb.append(dq.pollFirst());
				while(!dq.isEmpty()) {
					if(dq.peekFirst()!="")
						sb.append(","+dq.pollFirst());
				}
			}
			sb.append("]\n");
		}
		System.out.println(sb.toString());
    }
    
}
