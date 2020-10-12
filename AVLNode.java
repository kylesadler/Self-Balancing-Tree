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
		* Removes the current node from the tree
		* 
		*/

		if(this.left != null){
			// get next smallest key
			AVLNode a = this.left.getMax();
			this.value = a.value;
			a.remove();
		} else if(this.right != null) {
			//get next largest number
			AVLNode a = this.right.getMin();
			this.value = a.value;
			a.remove();
		} else {
			//both right and left are null
			AVLNode par = this.parent;
			// remove node
			if(this.value > par.value){
				par.right=null;
			}else if(this.value < par.value){
				par.left=null;
			}else{
				//value is equal to parent's
				if(par.left != null && this.value == par.left.value){
					par.left=null;
				}else if(par.right != null){
					par.right=null;
				}
			}
			par.updateAllToRoot(); //O(log(n))
		}
		
	}
	
	public void updateAllToRoot(){
		AVLNode current=this;
		do{
			current.update();
			current.balance();
			current=current.parent;
		}while(current != null);
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
			this.rotateToLeft();
		}else if(balanceFactor < -1){
			this.rotateToRight();
		}
	}
	
	//left heavy
	public void rotateToRight(){
		
		// guaranteed to not be null
		AVLNode p = this;
		AVLNode q = this.left;
		int pValue = p.value;
		
		//might be null
		AVLNode b = q.right;
		AVLNode a = q.left;
		AVLNode c = p.right;
		
		//values of p and q are switched
		p.value=q.value;
		q.value=pValue;
		
		p.left=a;
		p.right=q;
		q.left=b;
		q.right=c;

		if(c != null){
			c.parent=q;
		}
		if(b != null){
			b.parent=q;
		}
		if(q != null){
			q.parent=p;
		}
		if(a != null){
			a.parent=p;
		}
		
		q.update();
		p.update();
	}

	public void rotateToLeft(){
		
		//guaranteed to not be null
		AVLNode p=this;
		AVLNode q=this.right;
		int pValue=p.value;
		
		//might be null
		AVLNode b = q.left;
		AVLNode a = q.right;
		AVLNode c = p.left;
		
		//values of p and q are switched
		p.value=q.value;
		q.value=pValue;
		
		p.right=a;
		p.left=q;
		q.right=b;
		q.left=c;
		
		if(c != null){
			c.parent=q;
		}
		if(b != null){
			b.parent=q;
		}
		if(q != null){
			q.parent=p;
		}
		if(a != null){
			a.parent=p;
		}
		
		q.update();
		p.update();
	}
	
	public String toString(){
		String out=this.value+"";
		if(this.left != null){
			out+="("+this.left+")";
		}
		if(this.right != null){
			out+="["+this.right+"]";
		}
		
		return out;
	}
}
