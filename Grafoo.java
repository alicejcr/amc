package grafo;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafoo {
	int dim;
	double[][] ma;
	
	public Grafoo(int n) {
		this.dim = n;
		this.ma = new double[n][n];
	}
	
	public void add_edge(int o, int d, double p) {
		// falta proteger a funcao (peso tem que dar - entre 0 e 1?)
		if (o<this.dim && d < this.dim && o>=0 && d>= 0 ) { //&& p>= 0 && p<=1
			this.ma[o][d]=p;
		}
		else {
			if(p < 0 || p >1) {
				throw new AssertionError("Edge is not supported");
			}
			else {
				throw new AssertionError("Node is not in graph");
			}
		}
	}
	
	public int[] max_spanning_tree() {
		//começar pelo no 0
		//ver arestas e adicionar as possiveis 
		// escolher a mais pesada entre as possiveis 
		//retira-la das possiveis
		//ir para esse no
		//adicionar o no aos visitados
		//repeat
		int[] r = new int[this.dim -1];
		//lista guardar os nos visitados
		// lista guardar arestas visitadas
		// lista de arestas possiveis, ordenadas por tamanho
		//garantir que aresta possivel nao vai para no visitado
		//funcao auxiliar para ver arestas (check)
		// funcao auxiliar para escolher a mais pesada (check)
		// ver se a mais pesada pode ser
		// retirar a escolhida
		
		//lista de arestas
	}
	
	class Tuple{

	    private double weight;
	    private int o;
	    private int d;
		@Override
		public String toString() {
			return "[w=" + weight + ", o=" + o + ", d=" + d + "]";
		}
	    
	    
	}

	
	public ArrayList<Tuple> see_edges(int node) {
		ArrayList<Tuple> r = new ArrayList<Tuple>();
		double[] lista_nos=this.ma[node];
		for (int i = 0; i< this.dim; i++) {
			if(lista_nos[i]!=0) {
				Tuple aresta = new Tuple();
				aresta.weight = lista_nos[i];
				aresta.o = node;
				aresta.d = i;
				r.add(aresta);
			}
		}
		return r;
	}
	
	public Tuple fattest_edge(ArrayList<Tuple> lista_arestas) {
		double w = 0;
		Tuple res = new Tuple();
		for (int i =0; i<lista_arestas.size() ; i++) {
			if (lista_arestas.get(i).weight > w ) {
				w=lista_arestas.get(i).weight;
				res= lista_arestas.get(i);
				
			}
		}
		return res;
	}
	

	@Override
	public String toString() {
		return "Grafoo [dim=" + dim + ", ma=" + Arrays.deepToString(ma) + "]";
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafoo exp = new Grafoo(6);
		exp.add_edge(0, 1, 6);
		exp.add_edge(0, 2, 1);
		exp.add_edge(0, 3, 5);
		exp.add_edge(1, 2, 2);
		exp.add_edge(1, 4, 5);
		exp.add_edge(2, 3, 2);
		exp.add_edge(2, 5, 4);
		exp.add_edge(2, 4, 6);
		exp.add_edge(4, 5, 3);
		exp.add_edge(3, 5, 4);
		System.out.println(exp);
		System.out.println(exp.see_edges(1));
		System.out.println(exp.fattest_edge(exp.see_edges(1)));
	}

}
