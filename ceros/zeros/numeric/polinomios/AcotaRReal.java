/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.polinomios;

import zeros.extratool.Numerico;
import zeros.tipodatos.Cota;
import zeros.tipodatos.CotasPolinomio;
import zeros.tipodatos.Polinomio;

public class AcotaRReal {
	public static float[] ceros;
	public static Cota lagrange(Polinomio pin){
		Polinomio p;
		if (pin.getLast()<0){p=OperaPolinomio.multiplicaPolinomios(pin,-1);}
		else p=pin;
		int k=-1;
		float B=0;
		for (int i =p.getGrado()-1;i>=0;i--){
			if (p.getFactorat(i)<0){
				k=i;
				break;
			}
		}
		if (k!=-1)k=p.getGrado()-k;
		if (k!=-1){
			for (int i =p.getGrado()-1;i>=0;i--){
				if (p.getFactorat(i)<B){
					B=p.getFactorat(i);
				}
			}
			float value = (float) (1+Math.pow(((-1*B)/p.getLast()), (double)1/(double)k));
			return new Cota(value,true);
		}
		else {
			System.err.println("No existen factores negativos");
			return new Cota(-1,false);
		}
			
	}
	public static Cota laguerre(Polinomio pin,float cota){
		Polinomio p;
		if (pin.getLast()<0){p=OperaPolinomio.multiplicaPolinomios(pin,-1);}
		else p=pin;
		Polinomio[] cr = OperaPolinomio.horner(p, -1*cota);
		if ((Numerico.positivo(cr[0]))&(Numerico.positivo(cr[1]))){
			return new Cota(cota,true);
		}
		else return new Cota();
	}
	public static Cota laguerre(Polinomio p){
		float cotaProbable=1;
		Cota cota= new Cota();
		boolean foundflag=false;
		while (!foundflag){
			cota= laguerre(p,cotaProbable);
			if (cota.isExiste()) foundflag=true;
			cotaProbable++;
		}
		return cota;
	}
	public static Cota newton(Polinomio pin){
		Polinomio p;
		if (pin.getLast()<0){p=OperaPolinomio.multiplicaPolinomios(pin,-1);}
		else p=pin;
		int tamano = p.getGrado()+1;
		Polinomio[] derivados= new Polinomio[tamano];
		derivados[0]=p;
		for (int i=1;i<tamano;i++){
			derivados[i]=Numerico.deriva(derivados[i-1]);
		}
		float cotaProbable=1;
		Cota cota = new Cota();
		boolean foundflag=false;
		while (!foundflag){
			for (int i=0;i<tamano;i++){
				if (derivados[i].evalua(cotaProbable)<0) break;
				else if (i==tamano-1) {
					foundflag=true;
					cota.setCota(cotaProbable);
					cota.setExiste(foundflag);
				}
			}
			cotaProbable++;
		}
		return cota;
	}
	public static Cota cotaPosMayor(Polinomio p, int opcion){
		switch (opcion) {
		case 0: return lagrange(p);
		case 1: return laguerre(p);
		case 2: return newton(p);
		default:
			System.err.println("Opcion invalida");
			return null;
			
		}
	}
	public static Cota cotaPosMenor(Polinomio p, int opcion){
		Cota cota;
		switch (opcion) {
		case 0: 
			cota = lagrange(p);
			break;
		case 1: 
			cota = laguerre(p);
			break;
		case 2: 
			cota = newton(p);
			break;
		default:
			System.err.println("Opcion invalida");
			return null;
		}
		if (cota.isExiste()) {
			double cot = 1.0/cota.getCota();
			cota.setCota((float)cot);
			return cota;
		}
		else return cota;
	}
	public static Cota cotaNegMenor(Polinomio p, int opcion){
		Cota cota;
		switch (opcion) {
		case 0: 
			cota = lagrange(p);
			break;
		case 1: 
			cota = laguerre(p);
			break;
		case 2: 
			cota = newton(p);
			break;
		default:
			System.err.println("Opcion invalida");
			return null;
		}
		if (cota.isExiste()) {
			double cot = -1*cota.getCota();
			cota.setCota((float)cot);
			return cota;
		}
		else return cota;
	}
	public static Cota cotaNegMayor(Polinomio p, int opcion){
		Cota cota;
		switch (opcion) {
		case 0: 
			cota = lagrange(p);
			break;
		case 1: 
			cota = laguerre(p);
			break;
		case 2: 
			cota = newton(p);
			break;
		default:
			System.err.println("Opcion invalida");
			return null;
		}
		if (cota.isExiste()) {
			double cot = -1.0/cota.getCota();
			cota.setCota((float)cot);
			return cota;
		}
		else return cota;
	}
	public static CotasPolinomio acotaPolinom(Polinomio p, int opcion){
		Polinomio posmenor = Numerico.inviertePolin(p);
		Polinomio negmenor = Numerico.negaPolin(p);
		Polinomio negmmayor = Numerico.invnegPolin(p);
		CotasPolinomio cotas = new CotasPolinomio();
		cotas.setCotaPositivaM(cotaPosMayor(p, opcion));
		cotas.setCotaPositivam(cotaPosMenor(posmenor, opcion));
		cotas.setCotaNegativam(cotaNegMenor(negmenor, opcion));
		cotas.setCotaNegativaM(cotaNegMayor(negmmayor, opcion));
		return cotas;
	}
	public static Polinomio[] succSturn(Polinomio p){
		Polinomio[] sturn = new Polinomio[p.getGrado()+1];
		ceros = new float[p.getGrado()];
		boolean endFlag = false,
				ceroFlag = false;
		int cantsturn = sturn.length;
		while (!endFlag){
			int pos = 0;
			ceroFlag = false;
			sturn[0] = p;
			sturn[1] = Numerico.deriva(p);
			Polinomio[] aux;
			for (int i = 2; i < cantsturn; i++) {
				pos = i;
				aux = OperaPolinomio.dividePolinomios(sturn[i-2], sturn[i-1]);
				aux[1]=OperaPolinomio.multiplicaPolinomios(aux[1], -1);
				if ((aux[1].getGrado() == 0) && 
						(aux[1].getFactorat(0)==0) ){
					ceroFlag = true;
					break;
				}
				if (i!=cantsturn-1) aux[1].normaliza();
				sturn[i]=aux[1];	
			}
			if (ceroFlag){
				aux = OperaPolinomio.dividePolinomios(p, sturn[pos-1]);
				p = aux[0];
				ceros[pos-1]= sturn[pos-1].getFirst();
				cantsturn--;
			}
			else endFlag = true;
		}
		return sturn;
	}
	

}
