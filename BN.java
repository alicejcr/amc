package redes_bayes;

import java.util.ArrayList;

import amostra.Amostra;

public class BN {
	int[] arvore;
	ArrayList<double[][]> DFO;
	
	
	/*
	 * public BN (int[] arvore, Amostra amostra, double S){
	 * 
	 * arvore = [3,0,1,5]
	 * recebe uma lista (arvore) e olha para cada posicao (filho) e tira o pai (entrada)
	 * 
	 * 
	 * para cada par de pai/filho, calcula matriz de prob condicionada domain cenas
	 * quando nao tem pais, fazemos so uma linha/coluna so com as proprias contagens
	 * 
	 * tem que devolver o tuplo arvore e teta (matrizes)
	 * prob condic e aquela continha (slide 13)
	 * 
	 * }
	 */
	
	public BN (int[] arvore, Amostra amostra, double S){
		this.DFO = new ArrayList<double[][]>();
		for (int i = 0; i < arvore.length ; i++) { // i percorre os nos
			int pai = arvore[i];
			int[] Fz = {i}; //Fz e a variavel (cor)
			int[] Pz = {pai}; //Pz e a variavel (tamanho)
			// i e o filho
			
			if (pai==-1) { //se encontrarmos a raiz
				double[][] matriz = bob_mbo(i,amostra);
				for (int k = 0; k<amostra.domain(Fz); k++) {
					int[] K = {k}; //ks sao os valores da variavel (branco, castanho, azul...)
					matriz[k][0] = (amostra.count(Fz, K) + S) / (amostra.length() + S* amostra.domain(Fz)) ;
				}
				this.DFO.add(matriz);
			}
			
			else {
				double[][] matriz = bob_mb(i, pai, amostra);
				for (int k = 0; k < amostra.domain(Fz); k++) {
					for (int j = 0; j < amostra.domain(Pz) ; j++) {
						int[] var = {i, pai};
						int[] val = {k,j};
						//int[] K= {k};
						int[] J = {j};
						matriz[k][j] = (amostra.count(var, val) + S) / (amostra.count(Pz,J) + S* amostra.domain(Fz)) ;
					}
				}
				this.DFO.add(matriz);
			}
		}
	}
	
	public double[][] bob_mb(int f, int p, Amostra amostra){
		int[] pai = {p};
		int[] filho = {f};
		double[][] r = new double[amostra.domain(filho)][amostra.domain(pai)];
		return r;
	}
	
	public double[][] bob_mbo(int r, Amostra amostra){
		int[] raiz= {r};
		double[][] res = new double[amostra.domain(raiz)][1];
		return res;
	}
 	
	
	/*public double continha(Amostra amostra, double S, int Pz, int p, int Fz, int f) {
		int[] var = {Pz,Fz};
		int[] val = {p,f};
		int[] Pai = {Pz};
		int[] pai = {p};
		int[] Filho = {Fz};
		return (amostra.count(var,val)+S)/(amostra.count(Pai,pai) + S*amostra.domain(Filho)); 
	}
	*/
	
	//PERGUNTA o que e um vetor? (no, valor_no, valor_pai)?
	
	public double prob ( int no, int valor_no, int valor_pai) {
		double[][] matriz = this.DFO.get(no);
		return matriz[valor_no][valor_pai];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
