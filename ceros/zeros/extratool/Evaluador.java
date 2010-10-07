/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.extratool;
import org.nfunk.jep.JEP;

public class Evaluador {

	private JEP miEvaluador;
	double vx;
	boolean errorEnExpresion;
	boolean errorEnNumero;

	public Evaluador() {
		miEvaluador = new JEP();
		miEvaluador.addStandardFunctions();
		// agrega las funciones comunes
		miEvaluador.addStandardConstants();
		miEvaluador.addComplex();
		miEvaluador.addFunction("sen", new org.nfunk.jep.function.Sine());
		miEvaluador.addVariable("x", 0);
		miEvaluador.setImplicitMul(true);
	}

	public double evaluar(String fun, double valorx) {
		miEvaluador.parseExpression(fun);
		miEvaluador.addVariable("x", valorx);
		errorEnExpresion = miEvaluador.hasError(); // hay error?
		return miEvaluador.getValue();
	}

}

