/**
* The AVLNode class implements a node in a self-balancing AVL binary search tree.
*
* @author  Kyle Sadler
* @version 1.0
* @since   2018-10-03 
*/

package self_balancing_tree;

public class AVLNode {

	AVLNode left;
	AVLNode right;
	AVLNode parent;

	int value;
	int depth;
	int balanceFactor;
	
	public AVLNode(int n){
		this.right = null;
		this.left = null;
		this.parent = null;

		this.value = n;
		this.depth = 1;
		this.balanceFactor = 0;
	}
	
	public void add(int key){
		/**
		* Adds a key to one of the AVLNode's subtrees recursively. 
		* This function is initially called by AVLTree on the root node
		* 
		* @param  key  the key to add to the tree
		*/

		if(this.value > key){
			// if key is less than, add to the left
			if(this.left != null){
				this.left.add(key);
			} else {
				this.left = new AVLNode(key);
				this.left.parent=this;
			}

		} else {
			// if key is greater than or equal to, add to the right
			if(this.right != null){
				this.right.add(key);
			} else {
				this.right = new AVLNode(key);
				this.right.parent=this;
			}
		}

		this.update(); // O(1)
		this.balance(); // O(1)
	}
	
	public void remove(){
		/**
		* Removes the current node from the tree recursively while maintaining the 
		* AVL binary search tree structure
		*/

		if(this.left != null){
			// if left-child exists, replace current node with next smallest key
			AVLNode nextSmallest = this.left.getMax();
			this.value = nextSmallest.value;
			nextSmallest.remove();

		} else if(this.right != null) {
			// if right-child exists, replace current node with next largest key
			AVLNode nextLargest = this.right.getMin();
			this.value = nextLargest.value;
			nextLargest.remove();

		} else {
			// if node has no children, remove node
			if(this.equals(this.parent.right)){
				this.parent.right = null;
			}else if(this.equals(this.parent.left)){
				this.parent.left = null;
			}
			
			this.parent.maintainTree(); // O(log n)
		}
		
	}
	
	public void maintainTree(){
		/**
		* Maintains AVL binary search tree structure after a node is deleted
		* by recursively updating and balancing each ancestor.
		* One call to this method takes O(h) time where h is the height of the
		* current node
		*/
		
		this.update();
		this.balance();

		if (this.parent != null){
			this.parent.maintainTree();
		}
	}
	
	public AVLNode getMin(){
		/**
		* Returns the minimum AVLNode node in the current node's subtree in O(log n) time
		* @return	node 	AVLNode with minimum key
		*/
		if(this.left != null){
			return this.left.getMin();
		}else{
			return this;
		}
	}
	
	public AVLNode getMax(){
		/**
		* Returns the maximum AVLNode node in the current node's subtree in O(log n) time
		* @return	node 	AVLNode with maximum key
		*/
		if(this.right != null){
			return this.right.getMax();
		}else{
			return this;
		}
	}
	
	public void update(){
		/**
		* Update the depth and balanceFactor for the current node in O(1) time
		*/
		
		int rightDepth, leftDepth;

		if(this.right != null){
			rightDepth = this.right.depth;
		} else {
			rightDepth = 0;
		}
		
		if(this.left != null){
			leftDepth = this.left.depth;
		} else {
			leftDepth = 0;
		}
		
		this.depth = Math.max(rightDepth, leftDepth) + 1;
		this.balanceFactor = rightDepth - leftDepth;
		
	}
	
	public void balance(){
		/**
		* Balances the current AVLNode in O(1) time according to the following rules
		*   1. If the balanceFactor is greater than 1, rotate the current node to the left
		*	2. If the balanceFactor is less than -1. rotate the current node to the right
		*/
		if(balanceFactor > 1){
			this.rotateLeft();
		}else if(balanceFactor < -1){
			this.rotateRight();
		}
	}
	
	public void rotateRight(){
		/** 
		* Enacts a right rotation on the current node. Called by this.balance() when 
		* the current node is left heavy (balanceFactor < -1)
		* 
		* The tree is rotated like so:
		* 
		*			p					    q
		*		  /   \					  /   \
		*   	 q	   c       -->		 a     p
		*	    / \			      		      /	\
		*	   a   b			     		 b	 c
		* 
		* where p is the current node.
		*/
		
		// guaranteed to not be null
		AVLNode p = this;
		AVLNode q = this.left;
		
		// subtrees might be null
		AVLNode a = q.left;
		AVLNode b = q.right;
		AVLNode c = p.right;
		
		// swap p and q
		int pValue = p.value;
		p.value = q.value;
		q.value = pValue;

		AVLNode temp = p;
		p = q;
		q = temp;

		q.right = p;
		p.parent = q;

		
		// set subtrees
		q.left = a;
		p.left = b;
		p.right = c;

		if(a != null){
			a.parent = q;
		}
		if(b != null){
			b.parent = p;
		}
		if(c != null){
			c.parent = p;
		}
		
		q.update();
		p.update();
	}

	public void rotateLeft(){
		/** 
		* Enacts a left rotation on the current node. Called by this.balance() when 
		* the current node is right heavy (balanceFactor > 1). 
		*
		* The tree is rotated like so:
		* 
		*			p					    q
		*		  /   \					  /   \
		*		 c	   q      -->		p      a
		*			  /  \			   / \
		*			 b	  a			  c   b
		* 
		* where p is the current node.
		*/

		// guaranteed to not be null
		AVLNode p = this;
		AVLNode q = this.right;
		
		// subtrees might be null
		AVLNode b = q.left;
		AVLNode a = q.right;
		AVLNode c = p.left;
		
		// swap p and q
		int pValue = p.value;
		p.value = q.value;
		q.value = pValue;

		AVLNode temp = p;
		p = q;
		q = temp;

		q.left = q;
		p.parent = q;
		
		// set subtrees
		q.right = a;
		p.right = b;
		p.left = c;
		
		if(c != null){
			c.parent = p;
		}
		if(b != null){
			b.parent = p;
		}
		if(a != null){
			a.parent = q;
		}
		
		q.update();
		p.update();
	}
	
	public String toString(){
		String output = this.value.toString();

		if(this.left != null){
			output += "(" + this.left.toString() + ")";
		}
		if(this.right != null){
			output += "[" + this.right.toString() + "]";
		}
		
		return output;
	}
}
