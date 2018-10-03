package self_balancing_tree;

public class Tree {
	
	Node root;
	
	public Tree(){
		root=null;
	}
	
	public void add(int n){
		if(root!=null){
			root.add(n);
		}else{
			root=new Node(n);
		}
	}

	public int getDepth(){
		return root.depth;
	}
	
	public void remove(int n){
		if(root.value==n&&root.left==null&&root.right==null){
			root=null;
			return;
		}
		Node current=root;
		while(current!=null&&current.value!=n){
			if(current.value>n){
				current=current.left;
			}else{
				current=current.right;
			}
		}
		
		if(current!=null){
			current.remove();
		}
		
	}
	
	public String toString(){
		if(root==null){
			return "null";
		}
		return root.toString();
	}
}
