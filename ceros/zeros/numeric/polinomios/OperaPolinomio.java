/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.polinomios;

import zeros.tipodatos.Polinomio;

public class OperaPolinomio {

	/**
	 * Un simple método que suma polinomios nada que aclarar, salvo que creo que
	 * se puede mejorar
	 * 
	 * @param p sumando 1
	 * @param q sumando 2
	 * @return El Polinomio suma de p y q 
	 */
	public static Polinomio sumaPolinomio(Polinomio p, Polinomio q) {
		int gradoSuma = Math.max(p.getGrado(), q.getGrado());
		int gradoMinimo = Math.min(p.getGrado(), q.getGrado());
		Polinomio suma = new Polinomio(gradoSuma);
		for (int i = 0; i <= gradoSuma; i++) {
			if (i <= gradoMinimo) {
				suma.loadFactor((Float) (p.getFactorat(i) + q.getFactorat(i)));
			} else {
				if (gradoSuma == p.getGrado())
					suma.loadFactor(p.getFactorat(i));
				else
					suma.loadFactor(q.getFactorat(i));
			}
		}
		return suma;
	}

	/**
	 * Usa el método sumaPolinomio anterior y realiza la operación p + (-q)
	 * 
	 * @param p
	 *            minuendo
	 * @param q
	 *            sustraendo
	 * 
	 */
	public static Polinomio restaPolinomio(Polinomio p, Polinomio q) {
		Polinomio menosq = multiplicaPolinomios(q, -1);
		Polinomio resta = sumaPolinomio(p, menosq);
		// como el grado de el polinomio resta es a lo sumo el MAX{grado(p), grado(q)} compruebo si
		//el factor a[grado] es cero si es asi disminuyo el grado en uno, puede que los dos polinomios
		//sean iguales, en cuyo caso devuelvo un polinomio de grado cero.
		while (resta.checkPol() & resta.getGrado()>0)
			;
		return resta;
	}

	/**
	 * 
	 */
	public static Polinomio multiplicaPolinomios(Polinomio p, Polinomio q) {
		Polinomio producto = new Polinomio(p.getGrado()+q.getGrado());
		/*Polinomio aux;
		// por cuestiones de el algoritmo necesito que en p este el polinomio de
		// mayor grado
		if (p.getGrado() < q.getGrado()) {
			aux = p;
			p = q;
			q = aux;
		}*/
		float factor;
		for (int i=0; i<=producto.getGrado();i++){
			factor=0;
			for (int j=0; j<=p.getGrado();j++){
				if (j>i) break;
				else {
					for (int k=0;k<=q.getGrado(); k++){
						if (j+k>i) break;
						else {
							if (j+k==i) factor = factor + p.getFactorat(j)*q.getFactorat(k); 
						}
					}
				}
			}
			producto.loadFactor(factor);
		}
		return producto;

	}
	public static Polinomio multiplicaPolinomios(Polinomio p, float k){
		Polinomio producto = new Polinomio(p.getGrado());
		for (int i=0;i<=p.getGrado();i++ ){
			float factor = k*p.getFactorat(i);
			producto.loadFactor(factor);
		}
		return producto;
	}
	/**
	 * Realiza el cociente de dos polinomios, chequea la posibilidad de hacer la operación. Si no es
	 * posible la division devuelve null, es decir si grado(p) < grado(q)
	 * @param p polinomio dividendo
	 * @param q polinomio divisor
	 * @return Devuelve un Array cuyo primer elemento es el cociente y el segundo el resto
	 */
	public static Polinomio[] dividePolinomios(Polinomio p, Polinomio q){
		if (p.getGrado()<q.getGrado()) return null;
		int n=p.getGrado();
		int m=q.getGrado();
		Polinomio[] cr = new Polinomio[2];
		//polinomio cociente de grado n-m
		cr[0] = new Polinomio(n-m);
		//polinomio resto de a lo sumo grado m pero lo inicialiso en grado n para poder copiar a p
		cr[1] = new Polinomio(n);
		for (int i=0; i<=n; i++){
			//copio en el polinomio resto a p
			cr[1].loadFactor((float)p.getFactorat(i));
			//lleno el polinomio cociente de 0 para crear el polinomio si no tengo error de puntero nulo
			if (i<=n-m) cr[0].loadFactor(0); 
		}
		for (int k=n-m;k>=0;k--){
			//obtengo los factores de el polinomio cociente
			float facCoc=cr[1].getFactorat(m+k)/q.getFactorat(m);
			cr[0].setFactorat(k, facCoc);
			for (int j=m+k;j>=k;j--){
				//la division propiamente dicha
				float facResto = cr[1].getFactorat(j)- cr[0].getFactorat(k)*q.getFactorat(j-k);
				cr[1].setFactorat(j, facResto);
			}
		}
		// reviso el polinomio resto para dejarlo en su grado correcto
		while (cr[1].checkPol() & cr[1].getGrado()>0) ;
		return cr;
	}
	public static Polinomio[] horner(Polinomio p, float b) {
		if (p.getGrado()==0) return new Polinomio[]{new Polinomio("0"),p};
		Polinomio resto= new Polinomio(1,true);
		int gradoCociente = p.getGrado()-1;
		Polinomio cocie = new Polinomio(gradoCociente,true);
		cocie.setFactorat(gradoCociente, p.getFactorat(p.getGrado()));
		for (int i=gradoCociente-1;i>=0;i--){
			cocie.setFactorat(i, cocie.getFactorat(i+1)*(-1*b)+p.getFactorat(i+1));
		}
		resto.setFactorat(0, cocie.getFactorat(0)*(-1*b)+p.getFactorat(0));
		Polinomio[] cr = new Polinomio[2];
		cr[0]=cocie;
		cr[1]=resto;
		while (cr[1].checkPol() & cr[1].getGrado()>0);
		return cr;
	}
	public static Polinomio[] hornerDoble(Polinomio p, float a, float b) {
		int n = p.getGrado();
		Polinomio aux = new Polinomio(n,true);
		if (n == 1) {
			Polinomio[] cr = new Polinomio[2];
			cr[0] = new Polinomio(0);
			cr[1] = p;
			return cr;
		}
		aux.setFactorat(n, p.getFactorat(n));
		float factor = ((-1*a)*aux.getFactorat(n))+p.getFactorat(n-1);
		aux.setFactorat(n-1, factor);
		for (int i=n-2;i>=1;i--){
			factor = aux.getFactorat(i+1)*(-1*a)+aux.getFactorat(i+2)*(-1*b)+p.getFactorat(i);
			aux.setFactorat(i,factor);
		}
		factor = aux.getFactorat(2)*(-1*b)+p.getFactorat(0);
		aux.setFactorat(0, factor);
		Polinomio[] cr = new Polinomio[2];
		cr[0] = new Polinomio(n-2);
		cr[1] = new Polinomio(1);
		for (int i=0;i<=n;i++){
			if (i<=1) cr[1].loadFactor(aux.getFactorat(i));
			else cr[0].loadFactor(aux.getFactorat(i));
		}
		return cr;
	}

}
