package lógica;

public class AVL {
	
	public AVLNode root;
	
	public AVLNode search(int d, AVLNode r) {
		if(root==null)
			return null;
		else if(r.key.number==d)
			return r;
		else if(r.key.number > d)
			return search(d, r.right);
		else
			return search(d, r.left);
	}	
	
	public int getCount(AVLNode x) {
		if(x==null)
			return -1;
		else
			return x.count;
	}
	
	public AVLNode leftRotation(AVLNode c) {
		AVLNode aux=c.left;
		c.left = aux.right;
		aux.right=c;
		c.count=Math.max(getCount(c.left), getCount(c.right)+1);
		aux.count=Math.max(getCount(aux.left), getCount(aux.right)+1);
		return aux; 
		
	}
	
	public AVLNode rightRotation(AVLNode c) {
		AVLNode aux = c.right;
		c.right=aux.left;
		aux.left=c;
		c.count=Math.max(getCount(c.left), getCount(c.right)+1);
		aux.count=Math.max(getCount(aux.left), getCount(aux.right)+1);
		return aux;
	
	}
	
	public AVLNode doubleLeftRotation(AVLNode c) {
		AVLNode temp;
		c.left=rightRotation(c.left);
		temp=leftRotation(c);
		return temp;
	}
	
	public AVLNode doubleRightRotation(AVLNode c) {
		AVLNode temp;
		c.right=leftRotation(c.right);
		temp=rightRotation(c);
		return temp;
		
	}
	
	public AVLNode insertAVL(AVLNode nNode, AVLNode upper ) {
		
		AVLNode nFather = upper;
		if(nNode.key.number < upper.key.number) 
			if(upper.left==null)
				upper.left=nNode;
			else {
				upper.left=insertAVL(nNode, upper.left);
				if(getCount(upper.left) - getCount(upper.right) == 2)
					if(nNode.key.number<upper.left.key.number)
						nFather=leftRotation(upper);
					else
						nFather=doubleLeftRotation(upper);
			}
		else if(nNode.key.number>upper.key.number)
			if(upper.right==null)
				upper.right=nNode;
			else {
				upper.right=insertAVL(nNode, upper.right);
				if(getCount(upper.right) - getCount(upper.left) == 2)
					if(nNode.key.number>upper.right.key.number)
						nFather=rightRotation(upper);
					else
						nFather=rightRotation(upper);
						//nFather=rigthRotation(upper);
			}
				
		else
			System.out.println("[!] Contacto Duplicadoooooooooo");
		
		if(upper.left==null && upper.right!=null)
			upper.count=getCount(upper.right)+1;
		else if(upper.right==null && upper.left!=null)
			upper.count=getCount(upper.left)+1;
		else
			upper.count=Math.max(getCount(upper.left), getCount(upper.right)+1);

		
		return nFather;
	}
	
	public void insert(Data d) {
		
		AVLNode nNode = new AVLNode(d);
		if(root==null)
			root=nNode;
		else
			root=insertAVL(nNode, root);
		
	}
	
	
	public void inOrder() {
		
		root.inOrder();
	}
	
	public void preOrder() {
		root.preOrder();
	}
	
	public void postOrder() {
		root.postOrder();
	}
	
	
	
	
	
	
	
}
    