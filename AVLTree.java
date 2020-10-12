// package tree;
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

public class AVLTree {
	
	private AVLNode root;
	
	public AVLTree(){
		this.root = null;
	}

	/**
	* Adds a key, object pair to the self-balancing tree in O(log n) time
	* @param  key  		the key to add to the tree
	* @param  object  	the object to add to the tree at key
	*/
	public void insert(int key, Object object){
		if(this.root != null) {
			this.root.insert(key, object);
		} else {
			this.root = new AVLNode(key, object);
		}
	}

	/**
	* Retrieves an object from the self-balancing tree in O(log n) time
	* @param  key  	the key of the object to retrieve
	* @return 		the object with the given key. returns null if not found
	* @throws 		IllegalStateException if the tree is empty
	*/	
	public Object get(int key){
		if(this.root != null) {
			return this.root.get(key);
		} else {
			throw new IllegalStateException("AVL Tree is empty");
		}
	}

	/**
	* Returns the maximum object in the AVLTree in O(log n) time
	* @return		Object stored in AVLTree with maximum key
	* @throws 		IllegalStateException if the tree is empty
	*/
	public Object getMax(){
		if(this.root != null) {
			return this.root.getMax().object;
		} else {
			throw new IllegalStateException("AVL Tree is empty");
		}
	}

	/**
	* Returns the minimum object in the AVLTree in O(log n) time
	* @return		Object stored in AVLTree with minimum key
	* @throws 		IllegalStateException if the tree is empty
	*/
	public Object getMin(){
		if(this.root != null) {
			return this.root.getMin().object;
		} else {
			throw new IllegalStateException("AVL Tree is empty");
		}
	}

	/** 
	* Deletes a key from the self-balancing tree in O(log n) time
	* @param  key  	the key to remove from the tree
	*/	
	public void delete(int key){
		// if root is the only node, delete it
		if(root.key == key && root.left == null && root.right == null){
			this.root = null;
			return;
		}

		// traverse tree to find node with key to remove
		AVLNode current = this.root;
		while(current != null && current.key != key){
			if(current.key > key) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		if(current != null){ // remove if found
			current.delete();
		}
		
	}

	/** 
	* Returns the total height of the tree
	* @return  		height of tree
	* @throws 		IllegalStateException if the tree is empty
	*/	
	public int getHeight(){
		if(this.root != null) {
			return this.root.depth;
		} else {
			throw new IllegalStateException("AVL Tree is empty");
		}
	}
	
	/** 
	* Returns a string representation of the self-balancing tree in the form
	* root(leftSubtree)[rightSubtree]
	* @return 	string representation of tree
	*/	
	public String toString(){
		if(root == null){
			return "null";
		}
		return root.toString();
	}
}
