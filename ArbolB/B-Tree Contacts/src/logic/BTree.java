package logic;

public class BTree {
	
	NodeBTree root;
	int t;
	
	public BTree(int t) {
		this.t = t;
		root = new NodeBTree(t);
				
	}
	
	public void insert(Data key) {
		
		NodeBTree r = root;
		
		if(r.nKeys == ((2 * t) - 1)) {
			NodeBTree s = new NodeBTree(t);
			root = s;
			s.leaf = false;
			s.nKeys = 0;
			s.child[0] = r;
			split(s, 0, r);
		}	
	}
	
	public void nonFullInsert(NodeBTree node, Data key) {
		if(node.leaf) {
			int i = node.nKeys; // Cantidad de valores del nodo
			
			//busca la posición i donde asignar el valor
			while(i >=1 && key.number < node.key[i-1].number) {
				node.key[i] = node.key[i-1]; // Desplaza los valores mayores a key
				i--;
			}
			node.key[i] = key;
			node.nKeys++;
		}else {
			int j = 0;
			//Busca la posición del hijo
			while(j< node.nKeys && key.number > node.key[j].number) 
				j++;
			// Si el nodo hijo esta lleno lo separa
			if (node.child[j].nKeys == (2*t-1)) {
				split(node, j, node.child[j]);
				
				if(key.number > node.key[j].number) {
					j++;
				}
			}
			nonFullInsert(node.child[j], key);
		}
		
	}
	
	public void split(NodeBTree a, int s, NodeBTree d ) {
		
	}
	
	
	
}
