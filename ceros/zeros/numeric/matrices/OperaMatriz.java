/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.matrices;

import zeros.tipodatos.Matriz;

public class OperaMatriz extends Matriz{
	
	OperaMatriz(int ne,int me){
		super(ne, me);
	}
	//if it's not possible to add Suma() returns null
	public Matriz Suma(Matriz b){
		if ((getN()==b.getN())&&(getM()==b.getM())){
			Matriz aux=  b;
			for (int i=0;i<getN();i++){
				for (int j=0;j<getM();j++){
					aux.setElement(i, j,getElement(i,j)+b.getElement(i,j));
					}
			}
			return aux;
		}
		else{System.out.println("imposible sumar matrices diferentes");
			return null;
		}
	}
	public static Matriz resta(Matriz a, Matriz b){
		if ((a.getN()==b.getN())&&(a.getM()==b.getM())){
			Matriz aux=  b;
			for (int i=0;i<a.getN();i++){
				for (int j=0;j<a.getM();j++){
					aux.setElement(i, j,a.getElement(i,j)-b.getElement(i,j));
					}
			}
			return aux;
		}
		else{System.out.println("imposible sumar matrices diferentes");
			return null;
		}
	}
	public static Matriz suma(Matriz a, Matriz b){
		if ((a.getN()==b.getN())&&(a.getM()==b.getM())){
			Matriz aux=  b;
			for (int i=0;i<a.getN();i++){
				for (int j=0;j<a.getM();j++){
					aux.setElement(i, j,a.getElement(i,j)+b.getElement(i,j));
					}
			}
			return aux;
		}
		else{System.out.println("imposible sumar matrices diferentes");
			return null;
		}
	}
	//if it's not possible to multiply mul() returns null
	public static Matriz mul(Matriz a, Matriz b){
		if (a.getM()==b.getN()){
			Matriz aux = new Matriz(a.getN(),b.getM());
			for (int i=0;i<a.getN();i++){
				for (int j=0;j<b.getM();j++){
					double acu=0;
					for (int k=0;k<a.getM();k++){
						acu+=(a.getElement(i,k)*b.getElement(k, j));
					}
					aux.setElement(i,j,acu);
				}
			}
		return aux;
		}
		else {return null;
			
		}
		
	}
	public static Matriz trans(Matriz p){
		Matriz ptrans = new Matriz(p.getN(), p.getN());
		for (int i = 0; i < p.getN(); i++) {
			for (int j = 0; j < p.getN(); j++) {
				ptrans.setElement(j, i, p.getElement(i, j));
			}
		}
		return ptrans;
	}
	/**
	 * Calcula el determinante de la matriz. 
	 * Para ello coge la primera fila y se va multiplicando cada coeficiente por el 
	 * determinante de la matriz de orden n-1 que resulta de suprimir la fila y columna
	 * del coeficiente. Hay que ir alternando los signos.
	 * Ver http://www.marcevm.com/determinantes/determinantes_def.php
	 * @param matriz
	 * @return el determinante de la matriz
	 */
	public static double determinante (Matriz matriz)
	{
		assert matriz != null;
		double determinante = 0.0;
		
		int filas = matriz.getN();
		int columnas = matriz.getM();
		
		// Si la matriz es 1x1, el determinante es el elemento de la matriz
		if ((filas==1) && (columnas==1))
			return matriz.getElement(0, 0);
		

		int signo=1;
		
		for (int columna=0;columna<columnas;columna++)
		{
			// Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
			Matriz submatriz = getSubmatriz(matriz,columna);
			determinante = determinante + signo*matriz.getElement(0, columna)*determinante(submatriz);
			signo*=-1;
		}
		
		return determinante;
	}

	/**
	 * Obtiene la matriz que resulta de eliminar la primera fila y la columna que se
	 * pasa como parámetro.
	 * @param matriz Matriz original
	 * @param columna Columna que se quiere eliminar, junto con la fila=0
	 * @return Una matriz de N-1 x N-1 elementos
	 */
	public static Matriz getSubmatriz(Matriz matriz,  
			int columna) {
		int filas = matriz.getN();
		int columnas = matriz.getM();
		Matriz submatriz = new Matriz(filas-1,columnas-1);
		int contador=0;
		for (int j=0;j<columnas;j++)
		{
			if (j==columna) continue;
			for (int i=1;i<filas;i++)
				submatriz.setElement(i-1, contador, matriz.getElement(i, j));
			contador++;
		}
		return submatriz;
	}
	public static boolean copiaFila(Matriz origen, Matriz destino, int fila){
		if (origen.getM()==destino.getM()){
			for (int i = 0; i < origen.getM(); i++) {
				destino.setElement(fila, i, origen.getElement(fila, i));
			}
			return true;
		}
		else return false;
	}
	public static boolean pivoteoParcial(Matriz a, int fila, int columna){
		int i=0;
		while (a.getElement(fila,columna )== 0.0){
			if (i<a.getN()) a.swapfila(fila, i);
			else return false;
			i++;
		}
		return true;
	}
	public static Matriz ampliada(Matriz a ,Matriz b){
		if ((b.getM()==1)&(a.getN()==b.getN())){
			Matriz ampliada = new Matriz(a.getN(),a.getM()+1);
			for (int i = 0; i < a.getN(); i++) {
				for (int j = 0; j < a.getM(); j++) {
					ampliada.setElement(i, j, a.getElement(i, j));
				}
			}
			for (int i = 0; i < b.getN(); i++) {
				ampliada.setElement(i, a.getM(), b.getElement(i, 0));
			}
			return ampliada;
		}
		else return null;
	}
	/**
	 * El metodo es general pues si es una matriz columna o fila saca la norma de un vector
	 * si no la norma de una Matriz. En ambos casos saca la norma 1, que para vectores es la suma
	 * de los valores absolutos de sus componentes y para matrices es el maximo de las sumas de las 
	 * columnas
	 * @param p Matriz a evaluar
	 * @return la norma 1, si es p(n*n) norma matriz, si no norma de un vector
	 */
	public static double norma(Matriz p){
		double norma = 0;
		//chequea si es vector 
		if ((p.isColumn())|(p.isFila())){
			//si es vector columna
			if (p.isColumn()){
				for (int i = 0; i < p.getN(); i++) {
					norma += Math.abs(p.getElement(i, 0));
				} 
				return norma;
			}
			//si es vector fila
			else{ 
				for (int i = 0; i < p.getM(); i++) {
				norma += Math.abs(p.getElement(0, i));
				}
				return norma;
			}
		}
		//si es matriz
		else {
			double suma = 0;
			for (int i = 0; i < p.getN(); i++) {
				for (int j = 0; j < p.getM(); j++) {
					suma += Math.abs(p.getElement(i, j));
				}
				if (suma>norma) norma=suma;
			}
			return norma;
		}
	}
	
}
