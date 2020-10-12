/**
* The AVLTree class implements a self-balancing AVL binary search tree where
* insertion, deletion, and lookup are guaranteed to take O(log n) in the worst-
* case. The tree is maintained by ensuring that the heights of each node's child
* subtrees differ by at most 1.
*
* @author  Kyle Sadler
* @version 1.0
* @since   2018-10-03 
*/
package self_balancing_tree;

public class AVLTree {
	
	AVLNode root;
	
	public AVLTree(){
		this.root = null;
	}
	
	public void add(int key){
		/**
		* Adds a key to the self-balancing tree in O(log n) time
		* @param  key  the key to add to the tree
		*/
		if(this.root != null) {
			this.root.add(key);
		} else {
			this.root = new AVLNode(key);
		}
	}

	public int getHeight(){
		/** 
		* Returns the total height of the tree
		* @return  height of tree
		*/
		return root.depth;
	}
	
	public void remove(int key){
		/** 
		* Removes a key from the self-balancing tree in O(log n) time
		* @param  key  	the key to remove from the tree
		*/
		
		// if root is the only node, delete it
		if(root.value == key && root.left == null && root.right == null){
			this.root = null;
			return;
		}

		// traverse tree to find node with key to remove
		AVLNode current = this.root;
		while(current != null && current.value != key){
			if(current.value > key) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		if(current != null){ // remove if found
			current.remove();
		}
		
	}
	
	public String toString(){
		/** 
		* Returns a string representation of the self-balancing tree in the form
		* root(leftSubtree)[rightSubtree]
		* @return 	string representation of tree
		*/
		if(root == null){
			return "null";
		}
		return root.toString();
	}
}
