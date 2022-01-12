package amostra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Amostra {
	private ArrayList<int []> list;


	public Amostra() {
		this.list = new ArrayList<int []>();
	}

	public Amostra(String csvFile) {
		this.list = new ArrayList<int []>();;

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country     = line.split(cvsSplitBy);
				int[] stringToIntVec = new int[country.length];
				for (int i = 0; i < country.length; i++)
					stringToIntVec[i] = Integer.parseInt(country[i]);	
				add(stringToIntVec);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void add(int[] v) {
		// deve ser preciso adicionar alguma coisa aqui
		// pode ter a ver com o tamanho do que estamos a acrescentar (garantir que tem o mesmo tamanho)
		this.list.add(v);
	}
	
	
	public int count(int[] var, int[] val) {
		int r = 0;
		ArrayList<int[]> pos = new ArrayList<int[]>();
		pos = this.list;
		boolean[] visited = new boolean[pos.size()];
		int i = 0;
		while (!pos.isEmpty() && i<var.length) {
			for (int j = 0; j<pos.size() && !visited[j]; j++ ) { //PODE MELHORAR
				if (pos.get(j)[var[i]] != val[i] ) {
					visited[j] = true;
				}
			}
			
			i++;
		}
		
		for (int k = 0; k<pos.size() ; k++) {
			if (!visited[k]) {
				r+=1;
			}
		}
		return r;
	}
	
	
	
	public int length() {
		ArrayList<int[]> pos = new ArrayList<int[]>();
		pos=this.list;
		return pos.size();		
	}
	
	
	public int[] element (int p) {
		ArrayList<int[]> pos = new ArrayList<int[]>();
		pos=this.list;
		return pos.get(p);
	}
	
	
	public int domain (int [] posicoes) { // Não está a funcioanar no caso de vermos duas colunas
		ArrayList<int[]> pos = new ArrayList<int[]>();
		// fazer com a transposta para ler as linhas
		// recebe uma amostra??? ver slack
		pos=this.list;
		int res =1;
		for (int i : posicoes) {
			int maximo=0;
			for (int j =0 ; j<pos.size(); j++) {
				int e = pos.get(j)[posicoes[i]];
				if (e>maximo) {
					maximo=e;
				}
			}
			res = res*(maximo+1);
		}
		return res;
	}

	
	public boolean[] exp1(int a) {
		boolean[] r = new boolean[a];
		return r;
	}

	
	@Override

  	public String toString() {
		String s="[";
		if (list.size()>0) s+=Arrays.toString(list.get(0));
		for (int i=1; i<list.size();i++)
			s+= "," + Arrays.toString(list.get(i)) ;
		s+="]";
			
		return " Amostra = " + s;
	}
	
	

		
	

	public static void main(String[] args) {
		// Amostra amostra = new Amostra("bcancer.csv");
		Amostra exp = new Amostra();
		int[] u1 = {0,0,1};
		int[] u2 = {1,1,0};
		int[] u3 = {2,3,1};
		int[] u4 = {0,3,1};
		int[] var = {0,2};
		int[] val = {0,1};
		int[] d0 = {0};
		exp.add(u1);
		exp.add(u2);
		exp.add(u3);
		exp.add(u4);
		System.out.println(exp);
		System.out.println(exp.count(var,val));
		System.out.println(Arrays.toString(exp.element(2)));
		System.out.println(exp.domain(d0));
	}

}

