/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.extratool;


import java.util.Vector;

import zeros.tipodatos.Polinomio;

public class Numerico {
	public static boolean factoresEnteros(Polinomio p){
		for (int i = 0;i<=p.getGrado();i++){
			float factor = p.getFactorat(i);
			if (factor!=Math.floor(factor)) return false;
		}
		return true;
	}
	public static Vector<Integer> listaDivisores(int a){
		if (a==0) {
			System.out.println("El paramtro era cero");
			return null;
		}
		int aux = Math.abs(a);
		Vector<Integer> lista = new Vector<Integer>();
		for (int i=(-1*aux);i<=aux;i++){
			if (i!=0){if (a%i==0) lista.add(i);
		}}
		return lista;
	}
	public static boolean positivo(Polinomio p){
		for (int i=0;i<=p.getGrado();i++){
			if (p.getFactorat(i)<0) return false; 
		}
		return true;
	}
	public static Polinomio deriva(Polinomio p){
		Polinomio derivado=new Polinomio(p.getGrado()-1,true);
		for (int i=1;i<=p.getGrado();i++){
			derivado.setFactorat(i-1, p.getFactorat(i)*i);
		}
		return derivado;
	}
	public static Polinomio inviertePolin(Polinomio p){
		Polinomio invertido = new Polinomio(p.getGrado());
		for (int i=p.getGrado();i>=0;i--){
			invertido.loadFactor(p.getFactorat(i));
		}
		return invertido;
	}
	public static Polinomio negaPolin(Polinomio p){
		Polinomio nega = new Polinomio(p.getGrado());
		nega.loadFactor(p.getFactorat(0));
		for (int i=1;i<=p.getGrado();i++){
			if (i%2==0) nega.loadFactor(p.getFactorat(i));
			else nega.loadFactor((-1)*p.getFactorat(i));
		}
		return nega;
	}
	public static Polinomio invnegPolin(Polinomio p){
		Polinomio inv = inviertePolin(p);
		Polinomio nega = negaPolin(inv);
		return nega;
	}
	
}
