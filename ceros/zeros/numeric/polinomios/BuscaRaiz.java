/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.polinomios;

import java.text.NumberFormat;
import java.util.Vector;

import zeros.extratool.Numerico;
import zeros.tipodatos.Polinomio;

public class BuscaRaiz {
	static int iter;
	public static Vector<Integer> raicesEnteras(Polinomio p){
		if (!Numerico.factoresEnteros(p)) {
			System.err.println("No era un polinomio de factores enteros");
			return null;
		}
		return Numerico.listaDivisores((int)p.getFirst());
	}
	public static Vector<Float> raicesRacional(Polinomio p){
		if (!Numerico.factoresEnteros(p)) {
			System.err.println("No era un polinomio de factores enteros");
			return null;
		}
		Vector<Integer> divA0 = Numerico.listaDivisores((int)p.getLast());
		Vector<Integer> divAn = Numerico.listaDivisores((int)p.getFirst());
		Vector<Float> posibles = new Vector<Float>();
		for (int i=0;i<divAn.size()/2;i++){
			for (int j=0;j<divA0.size();j++){
				if ((divA0.elementAt(j)!=1)&(divA0.elementAt(j)!=-1)) {
					float posible = (float)divAn.elementAt(i)/(float)divA0.elementAt(j);
					posibles.add(posible);
				}
			}
		}
		return posibles;
	}
	public static Vector<Float> sacaRaices(Polinomio p,double epsilon,int iteraciones){
		Vector<Float> raices = new Vector<Float>(p.getGrado());
		float raiz;
		int index = 0;
		boolean flag = true;
		Polinomio[] resultado= new Polinomio[1];
		resultado[0]=p;
		raiz = newtonPolinomios(resultado[0], epsilon, iteraciones);
		if (iter>=iteraciones) return raices;
		while (flag){
			
			if (Math.abs(p.evalua(raiz))>1) return raices;
			raices.add(raiz);
			resultado = OperaPolinomio.horner(resultado[0], -1*raiz);
			raiz = newtonPolinomios(resultado[0], epsilon, iteraciones);
			if (iter>=iteraciones) return raices;
			index++;
			if (resultado[0].getGrado()==1) {
				flag = false;
				raices.add(raiz);
			}
			
		}
		return raices;
	}
	public static float newtonPolinomios(Polinomio p,double epsilon,int iteraciones){
		float cero =0;
		iter = 0;
		Polinomio[] result= null;
		float pa = 0,pda= 0;
		while ( (Math.abs(p.evalua(cero))>epsilon) & (iter<iteraciones) ){
			if (iter==0){
				boolean derivadacero = true;
				while(derivadacero){
				result = OperaPolinomio.horner(p, -1*cero);
				pa = result[1].getFirst();
				result = OperaPolinomio.horner(result[0], -1*cero);
				pda =result[1].getFirst();
				if (pda==0){
					cero+=0.1;
				}
				else derivadacero=false;
				}
			}
			if (iter !=0){
			result = OperaPolinomio.horner(p, -1*cero);
			pa = result[1].getFirst();
			result = OperaPolinomio.horner(result[0], -1*cero);
			pda =result[1].getFirst();
			cero = cero-pa/pda;
			iter++;}
			else {
				cero = cero-pa/pda;
				iter++;
			}
		}
		System.out.println(p.polinomioAString());
		System.out.println("el cero es "+cero+" itera"+iter);
		return cero;
	}
	/**
	 * Tener cuidado se modifica el polinomio p
	 * @param p
	 * @param epsilon
	 * @param iteraciones
	 * @return un String con la lista de raices
	 */
	public static String bairstow(Polinomio p, double epsilon, int iteraciones){
		iter = 0;
		//Polinomio;
		float[] real = new float[p.getGrado()], 
				imag = new float[p.getGrado()];
		float r = -1,dr;
		float error_r = 1;
		float s = -1,ds;
		float error_s = 1;
		Polinomio[] result;
		while( (p.getGrado()>2) & (iter<iteraciones) ){
			do{
				result = OperaPolinomio.hornerDoble(p, r, s);
				Polinomio qpormenos1 = OperaPolinomio.multiplicaPolinomios(result[0], -1);
				Polinomio[] derivq = OperaPolinomio.hornerDoble(qpormenos1, r, s);
				Polinomio qpormenosX = OperaPolinomio.multiplicaPolinomios(result[0], new Polinomio("0 -1"));
				Polinomio[] derivp = OperaPolinomio.hornerDoble(qpormenosX, r, s);
				//Polinomio restoNegado = OperaPolinomio.multiplicaPolinomios(result[1],-1);
				float det = derivp[1].getFactorat(1)*derivq[1].getFactorat(0) - 
							derivp[1].getFactorat(0)*derivq[1].getFactorat(1);
				if ( det!=0){
					float detr = -1*result[1].getFactorat(1)*derivq[1].getFactorat(0) - 
								 -1*result[1].getFactorat(0)*derivq[1].getFactorat(1);
					dr = detr/det;
					float dets = -1*result[1].getFactorat(0)*derivp[1].getFactorat(1) - 
					 			 -1*result[1].getFactorat(1)*derivp[1].getFactorat(0);
					ds = dets/det;
					r = r + dr;
					s = s + ds;
					System.out.println(s+" "+r+" 1"+" iteraci"+iter);
					if ( r != 0 )
						error_r = Math.abs(dr/r)*100;
					if ( s != 0 )
						error_s = Math.abs(ds/s)*100;
				}
				else {
					r = 5*r+1;
					s = s+1;
					iter = 0;
				}
			}while( (error_r>epsilon) & (error_s>epsilon));
			raices(r,s,real,imag,p.getGrado());
			p = result[0];
			if (p.getGrado()<3) break;
		}
		if (p.getGrado() == 2)
		{
			//System.out.println("n = " + n);
			r = p.getFactorat(1) / p.getFactorat(2);
			s = p.getFactorat(0) / p.getFactorat(2);
			//imprime(a, n);
			raices(r, s, real, imag, p.getGrado());
		}
		else
		{
			real[p.getGrado() - 1] = -p.getFactorat(0) / p.getFactorat(1);
			imag[p.getGrado() - 1] = 0;
		}
		String out = "{";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		nf.setMinimumFractionDigits(0);
		for (int i = 0; i < real.length; i++){
			//out = out + "X_"+i+"=";
			if (imag[i] != 0) 
			out = out + " ("+nf.format(real[i])+";"+nf.format(imag[i])+"i) ";
			else out = out + nf.format(real[i])+"  "; 
		}
		out = out + "} ";
		return out;
	}
	public static void raices(float r, float s, float re[], float im[], int n){
		double d = r * r - 4 * s;
		if (d > 0){
			re[n - 1] = (float)((-r + Math.sqrt(d)) / 2.0);
			re[n - 2] = (float)((-r - Math.sqrt(d)) / 2.0);
			im[n - 1] = 0;
			im[n - 2] = 0;
		}
		else{
			re[n - 1] = (float)(-r / 2.0);
			re[n - 2] = re[n - 1];
			im[n - 1] = (float)(Math.sqrt(-d) / 2.0);
			im[n - 2] = -im[n - 1];
		}
	}

}
