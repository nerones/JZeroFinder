/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.fnolineal;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.nfunk.jep.JEP;

import zeros.constantes.Constantes;
import zeros.tipodatos.Datos;

public class CalculaCeros {
	protected double a;
	protected double aux1;
	protected double b;
	protected double aux2;
	private double error;
	private int cant1, cant2, cant3, cant4, cant5;
	protected Datos da;
	private boolean no_cero = false;

	public CalculaCeros(double va, double vb, double verror, String funcion,
			String funcion1, String funcion2) {
		aux1 = a = va;
		aux2 = b = vb;
		error = verror;
		cant1 = cant2 = cant3 = cant4 = 0;
		da = new Datos(funcion, funcion1, funcion2);
	}

	public CalculaCeros(Vector<String> s) {
		da = new Datos(s.elementAt(0), s.elementAt(1), s.elementAt(2));
		aux1 = a = Double.valueOf(s.elementAt(3));
		aux2 = b = Double.valueOf(s.elementAt(4));
		error = Double.valueOf(s.elementAt(5));
	}
	public void setG(String g){
		da.setG(g);
	}

	public double biseccion() {
		double c = 0;
		double f = da.f(a);
		double f_actual = f;
		if (no_cero)
			return 0;
		while (Math.abs(f_actual) > error) {
			if (cant1 > Constantes.MAX_ITERACIONES_MIN) {
				error();
				return 0;
			}
			c = (a + b) / 2;
			f_actual = da.f(c);
			cant1++;
			System.out.println("iteracion " + cant1 + " c= " + c + " | biss");
			if (f_actual * da.f(a) < 0) {
				b = c;
			} else {
				a = c;
			}
			;
		}
		a = aux1;
		b = aux2;
		return c;
	}

	public void repeticiones() {
		System.out.println("cantidad de iteraciones para la biseccion = "
				+ cant1);
	}

	public double newton() {
		if (no_cero)
			return 0;
		double c, c1;
		if (da.f(a) * da.fd2(a) > 0) {
			c = a;
		} else if (da.f(b) * da.fd2(b) > 0) {
			c = b;
		} else {//
			double a1 = a + 0.1;
			while ((da.f(a1) * da.fd2(a1) < 0) & (a1 < (b + 0.3))) {
				a1 += 0.1;
			}
			if (a1 > b) {
				System.out.println("ERROR!!!!!" + a1);
				return a - 100;
			} else
				c = a1;
		}
		c1 = c - (da.f(c) / da.fd1(c));
		while (Math.abs(da.f(c1)) > error) {
			cant2++;
			if (cant2 > Constantes.MAX_ITERACIONES_MIN) {
				error();
				return 0;
			}
			c = c1;
			System.out
					.println("iteracion " + cant2 + " c= " + c + " | newton ");
			c1 = c - (da.f(c) / da.fd1(c));
		}
		a = aux1;
		b = aux2;
		return c1;
	}

	public double regula() {
		if (no_cero)
			return 0;
		double c = a;
		double f = da.f(a);
		double g = da.f(b);
		double f_actual = f;
		while (Math.abs(f_actual) > error) {
			cant3++;
			System.out.println("iteracion " + cant3 + " c= " + c + " | regula");
			c = ((a * g - b * f) / (g - f));
			f_actual = da.f(c);
			if ((f_actual * f) < 0) {
				b = c;
				g = f_actual;
			} else {
				a = c;
				f = f_actual;
			}
		}
		a = aux1;
		b = aux2;
		return c;
	}

	public double regulamodificada() {
		if (no_cero)
			return 0;
		double g = da.f(b);
		double f = da.f(a);
		double c = 0;
		double f_actual = f;
		double f_old = f;
		while (Math.abs(f_actual) > error) {
			cant4++;
			System.out.println("iteracion " + cant4 + " c= " + c
					+ " | regula m");
			c = ((a * g - b * f) / (g - f));
			f_actual = da.f(c);
			if (f_actual * f > 0) {
				b = c;
				g = f_actual;
				if (f_actual * f_old > 0)
					f = f / 2;
			} else {
				a = c;
				f = f_actual;
				if (f_actual * f_old > 0)
					g = g / 2;
			}
			f_old = f_actual;
		}// end of while
		a = aux1;
		b = aux2;
		return c;
	}
	
	public double puntoFijo(){
		double c=a;
		cant5 = 0;
		while ( (Math.abs(da.f(c))>error) && (cant5<Constantes.MAX_ITERACIONES_MIN) ){
			c=da.g(c);
			cant5++;
		}
		return c;
	}

	void error() {
		no_cero = true;
		JOptionPane
				.showMessageDialog(null,
						"error, limites mal ingresados o entre esos limites no existe cero");
	}

	public static boolean revisaDatos(Vector<String> s,String g, boolean op) {
		JEP parser = new JEP();
		parser.addStandardConstants();
		parser.addStandardFunctions();
		parser.addVariable("x", 0);
		parser.setImplicitMul(true);
		parser.parseExpression(s.elementAt(0));
		if (parser.hasError())
			return true;
		if (op)
			return false;
		parser.parseExpression(s.elementAt(1));
		if (parser.hasError())
			return true;
		parser.parseExpression(s.elementAt(2));
		if (parser.hasError())
			return true;
		parser.parseExpression(g);
		if (parser.hasError())
			return true;
		return false;
	}

	public Vector<String> allResultsAsVector() {
		Vector<String> out = new Vector<String>(4);
		out.add(String.valueOf(biseccion()));
		out.add(String.valueOf(regula()));
		out.add(String.valueOf(regulamodificada()));
		out.add(String.valueOf(newton()));
		out.add(String.valueOf(puntoFijo()));
		return out;
	}

	public Vector<String> someResultsAsVector() {
		Vector<String> out = new Vector<String>(4);
		out.add(String.valueOf(biseccion()));
		out.add(String.valueOf(regula()));
		out.add(String.valueOf(regulamodificada()));
		out.add("no se ingresaron las derivadas");
		out.add(String.valueOf(puntoFijo()));
		return out;
	}

	public Vector<String> allCountersAsVector() {
		Vector<String> out = new Vector<String>(4);
		out.add(String.valueOf(cant1) + " " + Constantes.REP);
		out.add(String.valueOf(cant3) + " " + Constantes.REP);
		out.add(String.valueOf(cant4) + " " + Constantes.REP);
		out.add(String.valueOf(cant2) + " " + Constantes.REP);
		out.add(String.valueOf(cant5) + " " + Constantes.REP);
		return out;
	}

	public Vector<String> InformeCalculos(boolean complete) {
		Vector<String> ceros, reps;
		if (complete)
			ceros = allResultsAsVector();
		else
			ceros = someResultsAsVector();
		reps = allCountersAsVector();
		Vector<String> aux = new Vector<String>();
		for (int i = 0; i < 5; i++) {
			aux.add(ceros.elementAt(i) + " - " + reps.elementAt(i));
		}
		return aux;
	}
	
	public double getA(){ return a;}
	
	public double getB(){ return b;}
}
