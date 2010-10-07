/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.matrices;

import zeros.tipodatos.Matriz;

public class Linealesindirectos {
	
	public Linealesindirectos(){
		
	}
	public Matriz jacobi(double epsilon, int iteraciones, Matriz aoriginal,Matriz boriginal, Matriz xorig){
		Matriz a = new Matriz(aoriginal.getN(), aoriginal.getM());
		Matriz b = new Matriz(boriginal.getN(), boriginal.getM());
		Matriz x = xorig.copiaCompl();
		for (int i = 0; i < a.getN(); i++) {
			double d = 1/aoriginal.getElement(i, i);
			b.setElement(i, 0, d*boriginal.getElement(i, 0));
			for (int j = 0; j < a.getN(); j++) {
				a.setElement(i, j, d*aoriginal.getElement(i, j));
			}
		}
		int iter = 0;
		double err;
		Matriz error;
		Matriz xnuevo = new Matriz(b.getN(), 1);
		do {
			iter++;
			for (int i = 0; i < a.getN(); i++) {
				xnuevo.setElement(i, 0, x.getElement(i, 0));
			}
			for (int i = 0; i < a.getN(); i++) {
				x.setElement(i, 0, b.getElement(i, 0));
				for (int j = 0; j < a.getN(); j++) {
					if (i!=j) {
						double value = x.getElement(i, 0)-a.getElement(i, j)*x.getElement(i, 0);
						x.setElement(i, 0, value);
					}
				}
			}
			error = OperaMatriz.resta(x, xnuevo);
			err = OperaMatriz.norma(error);
			System.out.println("iterac ="+iter+" norma = "+err);
		} while ((iter!=iteraciones)&(err>epsilon));
		return x;
	}
	
	public Matriz gausSeidel(double epsilon, int iteraciones, Matriz a,Matriz b, Matriz aproximado){
		int iter=0;
		double err;
		Matriz error;
		Matriz x = aproximado.copiaCompl();
		Matriz xnuevo = new Matriz(b.getN(), 1);
		do {
			iter++;
			for (int i = 0; i < a.getN(); i++) {
				xnuevo.setElement(i, 0, x.getElement(i, 0));
			}
			for (int i = 0; i < a.getN(); i++) {
				x.setElement(i, 0, b.getElement(i, 0));
				for (int j = i+1; j < a.getN(); j++) {
					double value = x.getElement(i, 0)-a.getElement(i, j)*xnuevo.getElement(j, 0);
					x.setElement(i, 0, value);
				}
				for (int j = 1; j < i-1; j++) {
					double value = x.getElement(i, 0)-a.getElement(i, j)*x.getElement(j, 0);
					x.setElement(i, 0, value);
				}
				x.setElement(i, 0, x.getElement(i, 0)/a.getElement(i, i));
			}
			error = OperaMatriz.resta(x, xnuevo);
			err = OperaMatriz.norma(error);
		} while ((iter!=iteraciones)&(err>epsilon));
		System.out.println("iteraciones = "+iter);
		return x;
	}
	
	public Matriz relax(double epsilon, int iteraciones, Matriz a,Matriz b, Matriz aproximado){
		Matriz residuo;
		residuo = OperaMatriz.resta(b, OperaMatriz.mul(a,aproximado));
		Matriz xnuevo = aproximado.copiaCompl();
		int iter= 0;
		//residuo.mostrar();
		while ((OperaMatriz.norma(residuo)>epsilon)&(iter<iteraciones)) {
			iter++;
			int posicion =-1;
			double max = 0;
			for (int i = 0; i < residuo.getN(); i++) {
				if (Math.abs(residuo.getElement(i, 0))>max){
					max=Math.abs(residuo.getElement(i, 0));
					posicion = i;
				}
			}
			//System.out.println("max "+max+" pos "+posicion);
			double nuevoxi = xnuevo.getElement(posicion, 0)+(residuo.getElement(posicion, 0)/a.getElement(posicion, posicion));
			xnuevo.setElement(posicion, 0,nuevoxi );
			residuo = OperaMatriz.resta(b, OperaMatriz.mul(a,xnuevo));
		}
		System.out.println("iteraciones = "+iter);
		return xnuevo;
	}
//	private boolean valid(Matriz na,Matriz ba){
//		if (na.getN()==ba.getN()){return true;}
//		else {return false;}
//	}
	

}
