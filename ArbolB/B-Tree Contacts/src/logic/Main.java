package logic;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		// Cada Nodo soporta 2*grado hijos y 2*grado-1 claves
		int grado = 8;// entre 5-9 puede ser el grado
		BTree btree = new BTree(grado);
		long time_i = System.nanoTime(); //tiempo inicial

		for (int i = 0; i < 2000000; i++) {
			Data contacto = new Data(i, (char) (random.nextInt(26) + 'a') + "" + (char) (random.nextInt(26) + 'a')
			+ (char) (random.nextInt(26) + 'a'), (char) (random.nextInt(26) + 'a') + "" + (char) (random.nextInt(26) + 'a')
			+ (char) (random.nextInt(26) + 'a'), (char) (random.nextInt(26) + 'a') + "" + (char) (random.nextInt(26) + 'a')
			+ (char) (random.nextInt(26) + 'a'));
			btree.insert(contacto);
			if(i% 100000 == 0){
				btree.balance();
			}
		}
		long double_f = (System.nanoTime() - time_i)/1000000;
        System.out.println("tiempo de ejecucion: " + double_f+" mili segundos");
		//System.out.println("Arbol en inOrder");
		//btree.inOrder();
		//btree.search(5000);
	}

}
