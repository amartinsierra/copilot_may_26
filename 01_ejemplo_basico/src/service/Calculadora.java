package service;

public class Calculadora {
		public int sumar(int a, int b) {
		return a + b;
	}

	public int restar(int a, int b) {
		return a - b;
	}

	public int multiplicar(int a, int b) {
		return a * b;
	}

	public double dividir(int a, int b) {
		if (b == 0) {
			throw new IllegalArgumentException("No se puede dividir por cero");
		}
		return (double) a / b;
	}
	//implementa el método factorial que reciba un número entero y retorne su factorial. El factorial de un número n se define como el producto de todos los enteros positivos desde 1 hasta n. Por ejemplo, el factorial de 5 (5!) es 1 * 2 * 3 * 4 * 5 = 120.
	public long factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("El número debe ser no negativo");
		}
		long resultado = 1;
		for (int i = 1; i <= n; i++) {
			resultado *= i;
		}
		return resultado;
	}
	
	public long sumatorio(int ... numeros) {
		long resultado = 0;
		for (int numero : numeros) {
			resultado += numero;
		}
		return resultado;
	}
}
