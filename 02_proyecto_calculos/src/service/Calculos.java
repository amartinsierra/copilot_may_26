package service;

import java.util.List;

public interface Calculos {
	Integer verificar(int n, List<Integer> lista);
	List<Integer> buscarMayores(int n, List<Integer> lista);
	Integer obtenerMayor(int n1, int n2);
}