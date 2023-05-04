package logic;

public class BTree {

	NodeBTree root;
	int g;// grado

	public BTree(int g) {
		this.g = g;
		root = new NodeBTree(g);
	}

	public void insert(Data key) { // solo verifica si esta llena la raiz
		NodeBTree r = root;

		if (r.nKeys == ((2 * g) - 1)) {
			NodeBTree s = new NodeBTree(g);
			root = s;
			s.leaf = false;
			s.nKeys = 0;
			s.child[0] = r; // hijo izquierdo
			split(s, 0, r); // hijo derecho
			nonFullInsert(s, key);
		} else {
			nonFullInsert(r, key);
		}
	}

	public void nonFullInsert(NodeBTree node, Data key) {
		if (node.leaf) {
			int i = node.nKeys; // Cantidad de valores del nodo

			// busca la posición i donde asignar el valor
			while (i >= 1 && key.number < node.key[i - 1].number) {
				node.key[i] = node.key[i - 1]; // Desplaza los valores mayores a key
				i--;
			}
			node.key[i] = key;
			node.nKeys++;
		} else {
			int j = 0;
			// Busca la posición del hijo
			while (j < node.nKeys && key.number > node.key[j].number)
				j++;
			// Si el nodo hijo esta lleno lo separa
			if (node.child[j].nKeys == (2 * g - 1)) {
				split(node, j, node.child[j]);

				if (key.number > node.key[j].number) {
					j++;
				}
			}
			nonFullInsert(node.child[j], key);
		}

	}

	public void split(NodeBTree father, int index, NodeBTree full) {// cuando nodo esta lleno
		NodeBTree fit = new NodeBTree(g);
		fit.leaf = full.leaf;
		fit.nKeys = g - 1;
		// Copia las claves de full a fit
		for (int j = 0; j < g - 1; j++) {
			fit.key[j] = full.key[j + g];
		}
		// Si full no es hoja copia los hijos de full a fit
		if (!full.leaf) {
			for (int j = 0; j < g; j++) {
				fit.child[j] = full.child[j + g];
			}
		}
		full.nKeys = g - 1;
		// actualizan los punteros del nodo father
		for (int j = father.nKeys; j >= index + 1; j--) {
			father.child[j + 1] = father.child[j];
		}

		father.child[index + 1] = fit;

		for (int j = father.nKeys - 1; j >= index; j--) {
			father.key[j + 1] = father.key[j];
		}

		father.key[index] = full.key[g - 1];

		father.nKeys++;
		full = null;
	}

	// imprime el arbol en inOrder
	public void inOrder() {
		if (root == null) {
			System.out.println("El árbol está vacío.");
		} else {
			inOrder(root);
		}
	}

	private void inOrder(NodeBTree node) {
		if (node != null) {
			int i;
			for (i = 0; i < node.nKeys; i++) {
				inOrder(node.child[i]);
				System.out.println("num: " + node.key[i].number + " ,name:" + node.key[i].name + " ,lastName:"
						+ node.key[i].lastName + " ,city:" + node.key[i].city);
			}
			if (node.child[i] != null) {
				inOrder(node.child[i]);
			}
		}
	}

	// balancea el arbol b
	public void balance() {
		if (root == null) {
			System.out.println("El árbol está vacío.");
		} else {
			balance(root);
		}
	}

	private void balance(NodeBTree node) {
		if (node != null) {
			int i;
			for (i = 0; i < node.nKeys; i++) {
				balance(node.child[i]);
				if (node.child[i] != null && node.child[i].nKeys < 2) {
					if (node.child[i + 1] != null && node.child[i + 1].nKeys > 2) {
						node.child[i].key[node.child[i].nKeys] = node.key[i];
						node.child[i].nKeys++;
						node.key[i] = node.child[i + 1].key[0];
						node.child[i + 1].key[0] = node.child[i + 1].key[1];
						node.child[i + 1].key[1] = node.child[i + 1].key[2];
						node.child[i + 1].nKeys--;
					} else if (node.child[i - 1] != null && node.child[i - 1].nKeys > 2) {
						node.child[i].key[node.child[i].nKeys] = node.key[i - 1];
						node.child[i].nKeys++;
						node.key[i - 1] = node.child[i - 1].key[2];
						node.child[i - 1].nKeys--;
					}
				}
			}
			if (node.child[i] != null) {
				balance(node.child[i]);
			}
		}
	}

	public void search(int key) {
		Data a=search(root, key);
		System.out.println("num: "+a.number+" ,name:"+a.name+" ,lastName:"+a.lastName+" ,city:"+a.city);
		a= null;
	}

	private Data search(NodeBTree node, int key) {
		if (node != null) {
			int i = 0;
			while (i < node.nKeys && key > node.key[i].number) {
				i++;
			}
			if (i < node.nKeys && key == node.key[i].number) {
				return node.key[i];
			} else if (node.leaf) {
				return null;
			} else {
				return search(node.child[i], key);
			}
		} else {
			return null;
		}
	}
}