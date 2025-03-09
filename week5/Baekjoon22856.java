package week5;

import java.io.*;
import java.util.*;

public class Baekjoon22856; {
    static class Node {
        int left, right;
        
        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    
    static Node[] tree;
    static int totalEdges = 0;
    static int lastVisitNode = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        tree = new Node[n + 1];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a] = new Node(b, c);
        }
        
        findLastVisitNode(1);
        
        int edgeCount = similarInorder(1, 0);
        
        System.out.println(edgeCount);
    }
    
    static void findLastVisitNode(int node) {
        if (node == -1) return;
        
        findLastVisitNode(tree[node].left);
        
        lastVisitNode = node;
        
        findLastVisitNode(tree[node].right);
    }
    
    static int similarInorder(int node, int edgeCount) {
        if (node == -1) return edgeCount;
        
        edgeCount = similarInorder(tree[node].left, edgeCount + (tree[node].left != -1 ? 1 : 0));
        
        edgeCount = similarInorder(tree[node].right, edgeCount + (tree[node].right != -1 ? 1 : 0));
        
        if (node == lastVisitNode) {
            return edgeCount;
        }
        
        return edgeCount + (node != 1 ? 1 : 0);
    }
}