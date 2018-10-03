package self_balancing_tree;

public class Node {
	Node right;
	Node left;
	int value;
	int depth;
	int balanceFactor;
	Node parent;
	
	public Node(int n){
		right=null;
		left=null;
		value=n;
		depth=1;
		balanceFactor=0;
		parent=null;
	}
	
	public void add(int n){
		if(this.value>n){
			if(this.left!=null){
				this.left.add(n);
			}else{
				this.left=new Node(n);
				this.left.parent=this;
			}
		}else{
			if(this.right!=null){
				this.right.add(n);
			}else{
				this.right=new Node(n);
				this.right.parent=this;
			}
		}
		this.update();//O(1)
		this.balance();//O(1)
	}
	
	
	public void remove(){
		if(this.left!=null){
			//get next smallest number
			Node a =this.left.getMax();
			value=a.value;
			a.remove();
		}else if(this.right!=null){
			//get next largest number
			Node a =this.right.getMin();
			value=a.value;
			a.remove();
		}else{
			//both right and left are null
			Node par=this.parent;
			//remove node
			if(this.value>par.value){
				par.right=null;
			}else if(this.value<par.value){
				par.left=null;
			}else{
				//value is equal to parent's
				if(par.left!=null&&this.value==par.left.value){
					par.left=null;
				}else if(par.right!=null){
					par.right=null;
				}
			}
			par.updateAllToRoot(); //O(log(n))
		}
		
	}
	
	public void updateAllToRoot(){
		Node current=this;
		do{
			current.update();
			current.balance();
			current=current.parent;
		}while(current!=null);
	}
	
	public Node getMin(){
		if(this.left!=null){
			return this.left.getMin();
		}else{
			return this;
		}
	}
	
	//returns min Node in subtree with node at root
	public Node getMax(){
		if(this.right!=null){
			return this.right.getMax();
		}else{
			return this;
		}
	}
	
	public void update(){
		
		int r,l;
		if(this.right!=null){
			r=this.right.depth;
		}else{
			r=0;
		}
		
		if(this.left!=null){
			l=this.left.depth;
		}else{
			l=0;
		}
		
		this.depth = Math.max(r, l)+1;
		this.balanceFactor=r-l;
		
	}
	
	public void balance(){
		if(balanceFactor>1){
			rotateToLeft();
		}else if(balanceFactor<-1){
			rotateToRight();
		}
	}
	
	//left heavy
	public void rotateToRight(){
		
		//guaranteed to not be null
		Node p=this;
		Node q=this.left;
		int pValue=p.value;
		
		//might be null
		Node b = q.right;
		Node a = q.left;
		Node c = p.right;
		
		//values of p and q are switched
		p.value=q.value;
		q.value=pValue;
		
		p.left=a;
		p.right=q;
		q.left=b;
		q.right=c;

		if(c!=null){
			c.parent=q;
		}
		if(b!=null){
			b.parent=q;
		}
		if(q!=null){
			q.parent=p;
		}
		if(a!=null){
			a.parent=p;
		}
		
		q.update();
		p.update();
	}

	public void rotateToLeft(){
		
		//guaranteed to not be null
		Node p=this;
		Node q=this.right;
		int pValue=p.value;
		
		//might be null
		Node b = q.left;
		Node a = q.right;
		Node c = p.left;
		
		//values of p and q are switched
		p.value=q.value;
		q.value=pValue;
		
		p.right=a;
		p.left=q;
		q.right=b;
		q.left=c;
		
		if(c!=null){
			c.parent=q;
		}
		if(b!=null){
			b.parent=q;
		}
		if(q!=null){
			q.parent=p;
		}
		if(a!=null){
			a.parent=p;
		}
		
		q.update();
		p.update();
	}
	
	public String toString(){
		String out=this.value+"";
		if(this.left!=null){
			out+="("+this.left+")";
		}
		if(this.right!=null){
			out+="["+this.right+"]";
		}
		
		return out;
	}
}
