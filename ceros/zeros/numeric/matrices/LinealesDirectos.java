/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.matrices;

import zeros.tipodatos.Matriz;

public class LinealesDirectos {
	static Matriz diagonal(Matriz a,Matriz b){
		Matriz aux= new Matriz (a.getN(),1);
		for (int i=0;i<a.getN();i++){
			aux.setElement(i, 0, b.getElement(i,0)/a.getElement(i, i));
		}
		return aux;
	}
	static Matriz triaInf(Matriz a,Matriz b){
		Matriz aux =new Matriz(a.getN(),1);
		for (int i=0;i<a.getN();i++){
			double acu=0;
			for(int j=0;j<i;j++){
				acu+=(a.getElement(i, j)*aux.getElement(j, 0));
			}
			double pep=(b.getElement(i, 0)-acu)/a.getElement(i, i);
			aux.setElement(i, 0,pep );
		}
		return aux;
	}
	static Matriz triaSup(Matriz a,Matriz b){
		Matriz aux =new Matriz(a.getN(),1);
		for (int i=(a.getN()-1);i>-1;i--){
			double acu=0;
			for(int j=i+1;j<a.getN();j++){
				acu+=a.getElement(i, j)*aux.getElement(j, 0);
			}
			aux.setElement(i, 0, (b.getElement(i, 0)-acu)/a.getElement(i, i));
		}
		return aux;	
	}
	public static Matriz crout(Matriz a, Matriz b){
		Matriz c =new Matriz(a.getN(),true);
		Matriz t =new Matriz(a.getN(),true);
		for (int m=0;m<a.getN();m++){
			for (int j=m;j<a.getN();j++){
				double acu=0;
				for (int k=0;k<m;k++){
					acu+=t.getElement(m, k)*c.getElement(k, j);
				}
				c.setElement(m, j, a.getElement(m, j)-acu);
			}
			for (int i=m+1;i<a.getN();i++){
				double acu=0;
				for (int k=0;k<m;k++){
					acu+=t.getElement(i, k)*c.getElement(k, m);
				}
				t.setElement(i, m, (a.getElement(i, m)-acu)/c.getElement(m, m));
			}
		
		}//t and c obtained
		for(int j=0;j<a.getN();j++){t.setElement(j,j,1.0);}
		//fill T[j,j] whit 1
		Matriz y=triaInf(t,b);
		return triaSup(c,y);
	}
	public static Matriz gauss(Matriz a,Matriz b){
		Matriz l = new Matriz(a.getN(),false);
		Matriz u = a.copiaCompl();
		for (int k = 0; k < a.getN()-1; k++) {
			l.setElement(k, k, 1.0);
			for (int i = k+1; i < a.getN(); i++) {
				double multiplicador = u.getElement(i, k)/u.getElement(k, k);
				for (int j = k+1; j < a.getN(); j++) {
					double elem = u.getElement(i, j)-multiplicador*u.getElement(k, j);
					u.setElement(i, j, elem);
				}
				l.setElement(i, k, multiplicador);
			}
		}
		l.setElement(a.getN()-1, a.getN()-1, 1.0);
		Matriz y=triaInf(l,b);
		return triaSup(u,y);
	}
	public static Matriz jordan(Matriz a, Matriz b){
		Matriz ampliada = OperaMatriz.ampliada(a, b);
		if (ampliada == null) return null;
		for (int k = 0; k < a.getN() ; k++) {
			OperaMatriz.pivoteoParcial(ampliada, k, k);
			double diag=ampliada.getElement(k, k);
			if (diag!=0){
				for (int j = 0; j < ampliada.getM(); j++) {
					double value = ampliada.getElement(k, j)/diag;
					ampliada.setElement(k, j, value);
				}
				for (int i = 0; i < a.getN(); i++) {
					if (i!=k){
						double multiplicador=ampliada.getElement(i, k);
						for (int j = 0; j < ampliada.getM(); j++) {
							double value = ampliada.getElement(i, j)-multiplicador*ampliada.getElement(k, j);
							ampliada.setElement(i, j, value);
						}
					}
				}
			}
		}
		//creo una matriz de nx1 para guardar el resultado en un vector columna
		Matriz resul= new Matriz(a.getN(),1);
		for (int i = 0; i < resul.getN(); i++) {
			resul.setElement(i, 0, ampliada.getElement(i, a.getM()));
		}
		return resul;
	}
	public static Matriz cholesky(Matriz a, Matriz b){
		Matriz l = new Matriz(a.getN(),a.getM());
		for (int k = 0; k < a.getN(); k++) {
			double suma=0;
			for (int j = 0; j < k-1; j++) {
				suma+=l.getElement(k, j)*l.getElement(k, j);
			} 
			double value = Math.sqrt(a.getElement(k, k)-suma);
			l.setElement(k, k, value);
			for (int i = k+1; i < a.getN(); i++) {
				suma=0;
				for (int j = 0; j < k-1; j++) {
					suma+=l.getElement(i, j)*l.getElement(k, j);
				}
				value = (a.getElement(i, k)-suma)/l.getElement(k, k);
				l.setElement(i, k, value);
			}
		}
		Matriz ltrans = OperaMatriz.trans(l);
		Matriz y =triaInf(l, b);
		return triaSup(ltrans, y);
	}
	public static Matriz mejoramiento(double epsilon, int iteraciones, Matriz a,Matriz b, Matriz aproximado) {
		Matriz residuo;
		residuo = OperaMatriz.resta(b, OperaMatriz.mul(a,aproximado));
		Matriz zeta;
		Matriz xold = aproximado.copiaCompl();
		int iter= 0;
		//residuo.mostrar();
		while ((OperaMatriz.norma(residuo)>epsilon)&(iter<iteraciones)) {
			iter++;
			zeta = jordan(a,residuo); 
			xold=OperaMatriz.suma(zeta, xold);
			
			residuo = OperaMatriz.resta(b, OperaMatriz.mul(a,xold));
		}
		System.out.println("mejoramiento iteraciones = "+iter);
		return xold;
	}
	
}
