/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.mains;

import java.util.Vector;

import zeros.numeric.matrices.LinealesDirectos;
import zeros.numeric.matrices.Linealesindirectos;
import zeros.tipodatos.Matriz;

public class CalculaSistema {
	private Matriz a,b,result;
	private int iteraciones;
	private double epsilon;
	private Linealesindirectos indirectos = new Linealesindirectos();
	public CalculaSistema() {
		// TODO Auto-generated constructor stub
	}
	
	public CalculaSistema(Matriz a, Matriz b, int iteraciones, double epsilon) {
		super();
		this.a = a;
		this.b = b;
		this.iteraciones = iteraciones;
		this.epsilon = epsilon;
		result = new Matriz(b.getN(), 1);
	}
	public CalculaSistema(Vector<String> datos){
		a = new Matriz(datos.elementAt(0).substring(0,datos.elementAt(0).length()-1 ));
		b = new Matriz(datos.elementAt(1).substring(0,datos.elementAt(1).length()-1 ));
		iteraciones = Integer.valueOf(datos.elementAt(4));
		epsilon = Double.valueOf(datos.elementAt(5));
		result = new Matriz(b.getN(), 1);
	}

	public CalculaSistema(Matriz a, Matriz b) {
		super();
		this.a = a;
		this.b = b;
	}
	public Matriz getA() {
		return a;
	}
	public void setA(Matriz a) {
		this.a = a;
	}
	public Matriz getB() {
		return b;
	}
	public void setB(Matriz b) {
		this.b = b;
	}
	public Matriz getResult(){
		return result;
	}
	
	public boolean opera(String mode){
		if ((a==null)|(b==null)){
			System.err.println("Imposible calcular no se cargaron las matrices");
			return false;
		}
		if (mode.matches("Gauss")){
			result=LinealesDirectos.gauss(a, b);
			return true;
		} else if (mode.matches("Jordan")) {
			result=LinealesDirectos.jordan(a, b);
			return true;
		} else if (mode.matches("Cholesky")) {
			result=LinealesDirectos.cholesky(a, b);
			return true;
		} else if (mode.matches("Crout")) {
			result=LinealesDirectos.crout(a, b);
			return true;
		} else if (mode.matches("Gauss-Seidel")) {
			result= indirectos.gausSeidel(epsilon, iteraciones, a, b, result);
			return true;
		} else if (mode.matches("Jacobi")) {
			result= indirectos.jacobi(epsilon, iteraciones, a, b, result);
			return true;
		} else if (mode.matches("Relajamiento")) {
			result= indirectos.relax(epsilon, iteraciones, a, b, result);
			return true;
		} else return false;
		
	}
	public boolean opera(String mod, boolean mejorar){
		if ((a==null)|(b==null)){
			System.err.println("Imposible calcular no se cargaron las matrices");
			return false;
		}
		if (mejorar) {
			opera(mod);
			result = LinealesDirectos.mejoramiento(epsilon, iteraciones, a, b, result);
			return true;
		}
		else {
			opera(mod);
			return true;
		}
	}

}
