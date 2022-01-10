package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import tuple.Tuple;


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
	
	/*
	 * public int[] max_spanning_tree() {
	 
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
		return r;
	}
	
*/
	
	
	

	
	public static void quickSort(ArrayList<Tuple> vec, int left, int right) { //do maior para o menor
		int r;
		if (right > left) {
		r = partition(vec, left, right); 
		quickSort(vec, left, r - 1); 
		quickSort(vec, r + 1, right); 
		} 
		} 
	
	private static int partition(ArrayList<Tuple> vec, int left, int right) {
		int i=left, j=left + 1;
		while(j<=right) {
			if (vec.get(j).weight>=vec.get(left).weight){
				i++;
				swap(vec,i,j);
				
				}
			
			j++;
		}
		swap(vec,left, i);
		return i;
		} 
	
	private static void swap(ArrayList<Tuple> vec,int a,int b) {
		Tuple tmp = vec.get(a);
		vec.set(a, vec.get(b));
		vec.set(b, tmp);
		
		}
	


	
	/* public ArrayList<Tuple> see_edges(int node) {
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
		for(int i= 0; i<this.dim;i++) {
			if (this.ma[i][node]!=0) {
				Tuple aresta = new Tuple();
				aresta.weight = lista_nos[i];
				aresta.o = node;
				aresta.d = i;
				r.add(aresta);
			}
		}
		return r;
	}
	
	*/
	
	/*public Tuple fattest_edge(ArrayList<Tuple> lista_arestas) {
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
	*/
	
	public static void retira(ArrayList<Tuple> arestas, int pai) {
		for (int i=0; i < arestas.size(); i++) {
			if (arestas.get(i).d == pai) {
				arestas.remove(i);
			}
		}
	}
	
	public int[] max_spanning_tree() {
		return max_spanning_tree(0);
	}
	
	public int[] max_spanning_tree(int no_inicial) {
		Tuple maior = new Tuple();
		ArrayList<Tuple> n = new ArrayList<Tuple>();
		ArrayList<Integer> nos = new ArrayList<Integer>();
		nos.add(no_inicial);
		ArrayList<Tuple> r= new ArrayList<Tuple>();
		for (int i =0; i<this.dim-1; i++) {
			see_edges(n, r ,nos.get(i));
			maior= n.get(0);
			if (nos.contains(maior.o) ) { //maior.o == nos.get(i)
				nos.add(maior.d);
			}
			else {
				nos.add(maior.o);
			}
			
			r.add(maior);
			n.remove(0);
			retira(n,nos.get(i)); //isto ta a fazer alguma coisa?
		}
		return find_parents(r);
		
	}

	
	//funcao que adiciona a lista de arestas todas as arestas do no em que estamos
	//sem repetir uma que la esteja ou que ja foi visitada
	//e no fim fica ordenado
	public void see_edges(ArrayList<Tuple> n, ArrayList<Tuple> r, int node) {
		double[] lista_nos=this.ma[node];
		for (int i = 0; i< this.dim; i++) {
			if(lista_nos[i]!=0) {
				Tuple aresta = new Tuple();
				aresta.weight = lista_nos[i];
				aresta.o = node;
				aresta.d = i;
				if (!Tuple.contain(n,aresta) && !Tuple.contain(r, aresta)) {
					n.add(aresta);
					}
			}
		}
		for(int i= 0; i<this.dim;i++) {
			if (this.ma[i][node]!=0) {
				
				Tuple aresta = new Tuple();
				aresta.weight = this.ma[i][node];
				aresta.o = node;
				aresta.d = i;
				if (!Tuple.contain(n,aresta) && !Tuple.contain(r, aresta)) {
					n.add(aresta);
					}
			}
		}
		quickSort(n,0,n.size()-1);
	}
	

	public static int[] find_parents(ArrayList<Tuple> caminho) { //funciona
		int[] r = new int[caminho.size()];
		int node = caminho.size();
		Stack<Integer> s = new Stack<Integer>();
		boolean[] visited = new boolean[caminho.size()+1];
		s.push(node);
		while (!s.isEmpty()) {
			int no_atual = s.pop();
			for (int i =0; i<caminho.size(); i++) {
				if (caminho.get(i).o == no_atual && !visited[caminho.get(i).d]) {
					r[caminho.get(i).d] = no_atual;
					s.push(caminho.get(i).d);
				}
				if (caminho.get(i).d == no_atual && !visited[caminho.get(i).o]) {
					r[caminho.get(i).o] = no_atual;
					s.push(caminho.get(i).o);
				}
			}
			visited[no_atual] =true;
		}
		return r;
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
 		System.out.println(Arrays.toString(exp.max_spanning_tree()));
		
		
	}
	}
