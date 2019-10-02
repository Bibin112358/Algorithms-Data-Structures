
public class AVL_Tree {
	
	class Node{
		Node left, right;
		int value;
		int height;
		
		public Node(int v) {
			value = v;
			height = 1;
		}
	}
	
	Node root;
	
	private int height(Node cur) {
		if(cur == null) return 0;
		return cur.height;
	}
	
	private int balance(Node cur) {
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

		//update heights, first child y then parent x
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		
		return x;
	}
	
	private Node leftRotate(Node x) {
		if(x == null || x.right == null) return x;
		
		Node y = x.right;
		Node T2= y.left;
		
		//rotation
		y.left = x;
		x.right = T2;
		
		//update heights, first child x then parent y
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		
		return y;
	}
	
	
	public void insert(int v) {
		root = insert(root, v);
	}
	
	private Node insert(Node cur, int v) {
		
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
	
	

	//testing purpose
	void preOrder(Node cur) { 
        if (cur != null) { 
            System.out.print(cur.value + " "); 
            preOrder(cur.left); 
            preOrder(cur.right); 
        } 
    } 
  
    public static void main(String[] args) { 
        AVL_Tree tree = new AVL_Tree(); 
  
        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10); tree.preOrder(tree.root); System.out.println();
        tree.root = tree.insert(tree.root, 20); tree.preOrder(tree.root); System.out.println(); 
        tree.root = tree.insert(tree.root, 30); tree.preOrder(tree.root); System.out.println();
        tree.root = tree.insert(tree.root, 40); tree.preOrder(tree.root); System.out.println();
        tree.root = tree.insert(tree.root, 50); tree.preOrder(tree.root); System.out.println();
        tree.root = tree.insert(tree.root, 25); tree.preOrder(tree.root); System.out.println();
  
  
        /* The constructed AVL Tree would be 
             30 
            /  \ 
          20   40 
         /  \     \ 
        10  25    50 
        */
        System.out.println("Preorder traversal" + 
                        " of constructed tree is : "); 
        tree.preOrder(tree.root); 
    }
    
}

//useful image
/*

		    y                               x
		   / \      Right Rotation        /   \
		  x   T3    – – – – – – – >      T1    y 
		 / \        < - - - - - - -           / \
		T1  T2      Left Rotation           T2  T3

*/

//alternative rebalance
/*
if(balance(cur) < -1) { //left heavy
	if(v < cur.left.value) { //left left
		return rightRotate(cur);
	}else { //left right
		cur.left = leftRotate(cur.left);
		return rightRotate(cur);
	}	
}else if(balance(cur) > 1) {//right heavy
	if(v > cur.right.value) { //right right
		return leftRotate(cur);
	}else { //right left
		cur.right = rightRotate(cur.right);
		return leftRotate(cur);
	}	
}*/