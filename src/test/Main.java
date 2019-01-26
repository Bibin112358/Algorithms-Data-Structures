package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

//solution to https://www.spoj.com/problems/SDITSAVL/

public class Main {
	
	static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        long nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }

	public static void main(String[] args) {
		FastReader input = new FastReader();
		//Scanner input = new Scanner(System.in); //too slow
		
		AVL_Tree tree = new AVL_Tree();
		
		long N = input.nextLong();
		
		for(long i=0; i<N; i++) {
			if(input.nextInt() == 1) {
				tree.insert(input.nextLong());
			}else {
				long ind = tree.search(input.nextLong());
				if(ind < 0) {
					System.out.println("Data tidak ada");
				}else {
					System.out.println(ind);
				}
			}
		}
	}
}


class AVL_Tree {
	
	class Node{
		Node left, right;
		long value;
		long height;
		long size;
		
		public Node(long v) {
			value = v;
			height = 1;
			size = 1;
		}
	}
	
	Node root;
	
	private long height(Node cur) {
		if(cur == null) return 0;
		return cur.height;
	}
	
	private long size(Node cur) {
		if(cur == null) return 0;
		return cur.size;
	}
	
	private long balance(Node cur) {
		if(cur == null) return 0;
		return height(cur.right) - height(cur.left);
	}
	
	private Node rightRotate(Node y) {
		if(y == null || y.left == null) return y;
		
		Node x = y.left;
		Node T2= x.right;
		
		//rotation
		x.right = y;
		y.left = T2;
		
		//update heights
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		
		//update size correct order: child y first then parent x
		y.size = size(y.left) + size(y.right) + 1;
		x.size = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	
	private Node leftRotate(Node x) {
		if(x == null || x.right == null) return x;
		
		Node y = x.right;
		Node T2= y.left;
		
		//rotation
		y.left = x;
		x.right = T2;
		
		//update heights
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		
		//update size correct order: child x first then parent y
		x.size = size(x.left) + size(x.right) + 1;
		y.size = size(y.left) + size(y.right) + 1;
		
		return y;
	}
	
	
	
	public long search(long v) {
		return search(root, v);
	}
	
	private long search(Node cur, long v) {
		if(cur == null) return -1;
		
		if(v == cur.value) return size(cur.left) + 1;
		
		long sol = -1;
		if(v < cur.value) {
			sol = search(cur.left, v);
		}else {
			sol = search(cur.right, v);
			if(sol >= 0) sol += size(cur.left) + 1;
		}
		
		if(sol >= 0) return sol;
		return -1;
	}
	
	
	
	
	public void insert(long v) {
		root = insert(root, v);
	}
	
	private Node insert(Node cur, long v) {
		
		//normal insert
		if(cur == null) return new Node(v);
		
		if(v < cur.value) {
			cur.left = insert(cur.left, v);
		}else if(v > cur.value) {
			cur.right = insert(cur.right, v);
		}else {
			return cur;
		}
		
		//height
		cur.height = Math.max(height(cur.left), height(cur.right)) + 1;
		cur.size = size(cur.left) + size(cur.right) + 1;
		
		
		//rebalance
		if(balance(cur) < -1) { //left heavy
			
			if(v > cur.left.value) { //left right => left left
				cur.left = leftRotate(cur.left);
			}
			
			//left left
			return rightRotate(cur);
			
		}else if(balance(cur) > 1) {//right heavy
			
			if(v < cur.right.value) { //right left => right right
				cur.right = rightRotate(cur.right);
			}
			
			//right right
			return leftRotate(cur);
		}		
		
		return cur;
	}
}
