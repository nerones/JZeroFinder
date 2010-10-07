/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.mains;

import java.util.Vector;

import zeros.numeric.polinomios.AcotaRReal;
import zeros.numeric.polinomios.BuscaRaiz;
import zeros.numeric.polinomios.OperaPolinomio;
import zeros.tipodatos.CotasPolinomio;
import zeros.tipodatos.Polinomio;

public class CalculaPolinomio {

	private Polinomio p,q,resultado,resto;
	private double evaluado,aevaluar;
	private String cotas,posiblesraices,operacion,modoRaices;
	private int modoAcotar;
	private Polinomio[] succSturn;
	
	public CalculaPolinomio() {
	}

	public CalculaPolinomio(Polinomio p, Polinomio q, double valor) {
		super();
		this.p = p;
		this.q = q;
		aevaluar = valor;
		modoAcotar = 0;
	}
	
	public CalculaPolinomio(Vector<String> data){
		p = new Polinomio(data.elementAt(0).substring(0,data.elementAt(0).length()-1 ));
		q = new Polinomio(data.elementAt(1).substring(0,data.elementAt(1).length()-1 ));
		aevaluar = Double.valueOf(data.elementAt(2));
		modoAcotar = 0;
	}
	
	public String getCotas() {
		return cotas;
	}

	public String getPosiblesraices() {
		return posiblesraices;
	}

	public Polinomio getP() {
		return p;
	}

	public void setP(Polinomio p) {
		this.p = p;
	}

	public Polinomio getQ() {
		return q;
	}

	public void setQ(Polinomio q) {
		this.q = q;
	}

	public Polinomio getResultado() {
		return resultado;
	}

	public double getEvaluado() {
		return evaluado;
	}
	
	
	
	public Polinomio getResto() {
		return resto;
	}
	
	public String getOp(){
		return operacion;
	}
	
	public void setModoAcota(int op){
		modoAcotar = op;
	}
	public void setModoRaices(String s){
		modoRaices = s;
	}
	public Polinomio[] getsuccSturn(){
		return succSturn;
	}

	public boolean opera(String mode){
		if ((p == null) | (q == null)) {
			System.err.println("no se cargaron los Polinomios");
			return false;
		}
		if (mode.equalsIgnoreCase("Suma")) {
			resultado = OperaPolinomio.sumaPolinomio(p, q);
			operacion = ("P(x)+Q(x)=");
			return true;
		} else if (mode.equalsIgnoreCase("Resta")) {
			resultado = OperaPolinomio.restaPolinomio(p, q);
			operacion = ("P(x)-Q(x)=");
			return true;
		} else if (mode.equalsIgnoreCase("Multiplicacion")) {
				resultado = OperaPolinomio.multiplicaPolinomios(p, q);
				operacion = ("P(x)*Q(x)=");
				return true;
		} else if (mode.equalsIgnoreCase("Division")) {
			Polinomio[] aux = OperaPolinomio.dividePolinomios(p, q);
			resultado = aux[0];
			resto = aux[1];
			return true;
		} else if (mode.equalsIgnoreCase("Evaluar")) {
			evaluado = p.evalua(aevaluar);
			operacion = "P("+aevaluar+")=";
			return true;
		} else if (mode.equalsIgnoreCase("Acotar raices")) {
			if (modoAcotar==3){
				succSturn = AcotaRReal.succSturn(p);
				return true;
			}
			CotasPolinomio cot = AcotaRReal.acotaPolinom(p, modoAcotar);
			cotas = cot.CotasAsString();
			return true;
		} else if (mode.equalsIgnoreCase("Raices reales")) {
//			Vector<Integer> enteros = BuscaRaiz.raicesEnteras(p);
//			Vector<Float> racionales = BuscaRaiz.raicesRacional(p);
			if (modoRaices.matches("por Newton")){
			Vector<Float> raices = BuscaRaiz.sacaRaices(p, 0.0001, 1000);
			posiblesraices = raices.toString();//"Posibles raices enteras = "+enteros+"\n"+"Posibles raices racionales = "+racionales;
			return true;
			}
			else {
				posiblesraices = BuscaRaiz.bairstow(p, 0.0001, 1000);
				return true;
			}
		}
		return false;
	}
	//public Polinomio[] divide() {
		
//	}
	

}
