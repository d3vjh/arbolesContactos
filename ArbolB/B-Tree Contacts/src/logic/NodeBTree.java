package logic;

public class NodeBTree {
	int nKeys; // Numero de claves almacenadas en el nodo
	boolean leaf; //If the node is a leaf
	Data key[];
	NodeBTree child[];
	
	public NodeBTree(int g) {
		nKeys = 0;
		leaf = true;
		key = new Data[((2 * g) - 1)];
		child = new NodeBTree[(2 * g)];
	}
}

