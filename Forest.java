package floresta;

import java.util.ArrayList;
import java.util.Arrays;

public class Forest {
	int dim;
	int[] pais;
	
	public Forest(int n) {
		this.dim = n;
		this.pais = new int[n]; // ver se da para ter null em vez de zero
	}
	
	public void set_parent(int n, int m) {
		this.pais[m]=n;
	}
	
	public boolean treeQ() {
		int r = 0;
		for (int i = 0; i< this.pais.length ; i++) {
			if (this.pais[i]==0) {
				r+=1;
			}
		}
		return r==1 ;
	}
	


	@Override
	public String toString() {
		return "Forest [dim=" + dim + ", pais=" + Arrays.toString(pais) + "]";
	}

	public static void main(String[] args) {
		Forest f = new Forest(4);
		System.out.println(f);
		f.set_parent(1, 2);
		f.set_parent(3, 1);
		f.set_parent(2, 0);
		System.out.println(f);
		System.out.println(f.treeQ());

	}

}
