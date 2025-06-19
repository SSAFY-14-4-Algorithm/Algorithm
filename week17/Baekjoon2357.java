import java.io.*;
import java.util.*;

/**
 * 182284kb, 796ms
 */
public class Baekjoon2357 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static int N,M;
    private static int [] num;
    private static int [][] tree;

    public static void main(String [] args) throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	num = new int[N];
    	tree = new int[N*4][2];
    	for(int i=0;i<N;i++) {
    		num[i] = Integer.parseInt(br.readLine());
    	}

    	init(0,N-1,1);
    	
    	for(int i=0;i<M;i++) {
    		tokens = br.readLine().split(" ");
    		int a = Integer.parseInt(tokens[0])-1;
    		int b = Integer.parseInt(tokens[1])-1;
    		
    		int [] answer = query(0,N-1,1,a,b);
    		
    		bw.write(answer[0] + " " + answer[1]+"\n");
    	}
    	bw.flush();
    }
    
    private static int [] init(int start, int end, int node) {
    	if(start == end) {
    		tree[node][0] = num[start];
    		tree[node][1] = num[start];
    		
    		return new int[] {tree[node][0], tree[node][1]};
    	}
    	
    	int mid = (start + end) /2;
    	
    	int [] leftChild = init(start,mid,node*2);
    	int [] rightChild = init(mid+1,end,node*2+1);
    	

    	tree[node][0] = Math.min(leftChild[0], rightChild[0]);
    	tree[node][1] = Math.max(leftChild[1], rightChild[1]);
    	
    	return tree[node];
    }
    
    private static int [] query(int start, int end, int node, int left, int right) {
    	if (right < start || left > end) return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};

    	//범위 내에 있을 때
    	if (left <= start && end <= right) {
            return tree[node];
        }
    	
    	int mid = (start + end) / 2;
    	
    	int [] leftArr = query(start,mid,node*2,left,right);
    	int [] rightArr = query(mid+1,end,node*2+1,left,right);

    	int min = Math.min(leftArr[0], rightArr[0]);
        int max = Math.max(leftArr[1], rightArr[1]);
        return new int[] { min, max };
    }
}
