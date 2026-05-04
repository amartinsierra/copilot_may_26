package service;

import java.util.List;

public class CalculosImpl implements Calculos {

	@Override
	public int verificar(int n, List<Integer> lista) {
		//sustituye el codigo de este método por programación funcional con streams
		return (int) lista.stream()
				.filter(num -> num > n)
				.count();	
		
		
	}

	@Override
	public List<Integer> buscarMayores(int n, List<Integer> lista) {
		//devuelve una lista con los números mayores a n
		//utilizando programación funcional con streams
		return lista.stream()
				.filter(num -> num > n)
				.toList();
	}

}
