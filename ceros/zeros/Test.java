/**
 * @author Nelson Efrain A. Cruz
 *
 */
package zeros;

import zeros.numeric.polinomios.AcotaRReal;
import zeros.tipodatos.Polinomio;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GraficadorClasico graf = new GraficadorClasico();
		//x5 - 3.5x4 + 2.75x3 + 2.125x2 - 3.875x + 1.25
		Polinomio test= new Polinomio("-36 24 -9 1");
		System.out.println("algo");
		Polinomio[] alg = AcotaRReal.succSturn(test);
		for (int i = 0; i < alg.length; i++) {
			System.out.println(alg[i].polinomioAString());
		}
		//String alg =BuscaRaiz.bairstow(test, 0.001, 100);
		//System.out.println(alg);
		//Float[] raizes =;
		//System.out.println(BuscaRaiz.sacaRaices(test, 0.001, 1000));
		
		//"-24 44 -6 -11 3"
		//System.out.println(test.evalua(-1));
		//Polinomio test2= new Polinomio("-1 -2 1");
		//Polinomio test2= new Polinomio("3 -5 3 2");
		//Polinomio[] cr;
		//cr = OperaPolinomio.dividePolinomios(test, test2);
		//System.out.println((AcotaRReal.acotaPolinom(test,2)).CotasAsString());
//		Linealesindirectos calc = new Linealesindirectos();
//		Matriz inf=new Matriz("4 4;10 -1 2 0 -1 11 -1 3 2 -1 10 -1 0 3 -1 8");//(4,true);
//		//int[]aux={3,1,1,5,2,2,1,7,1};
//		//int[]aux={2,3,-1,1,-2,-1,-1,-1,2};
//		//double[]aux={10,-1,2,0,-1,11,-1,3,2,-1,10,-1,0,3,-1,8};
//		//inf.cargar(aux);
//		System.out.println(OperaMatriz.determinante(inf));
//		System.out.println("-------------------------");
//		Matriz ba=new Matriz(4,1);
//		double[]aux1={6,25,-1,15};
//		ba.cargar(aux1);
//		Matriz resul=LinealesDirectos.crout(inf, ba);
//		resul.mostrar();
//		System.out.println("-------------------------");
//		resul = LinealesDirectos.gauss(inf, ba);
//		resul.mostrar();
//		System.out.println("-------------------------");
//		resul = LinealesDirectos.jordan(inf, ba);
//		resul.mostrar();
//		System.out.println("------choleski---------");
//		resul = LinealesDirectos.cholesky(inf, ba);
//		resul.mostrar();
//		System.out.println("--------gausseidel-------");
//		double[] auxs = {1,3,1,2};
//		Matriz aprox =new Matriz(4,1);
//		aprox.cargar(auxs);
//		resul = calc.gausSeidel(0.001, 1000, inf, ba, aprox);
//		resul.mostrar();
//		Matriz lala = OperaMatriz.mul(inf, resul);
//		lala.mostrar();
//		resul = LinealesDirectos.mejoramiento(0.001, 1000, inf, ba, resul);
//		resul.mostrar();
//		lala = OperaMatriz.mul(inf, resul);
//		lala.mostrar();
//		System.out.println("--------relax-------");
//		resul = calc.relax(0.001, 500, inf, ba, aprox);
//		resul.mostrar();
//		inf.mostrar();
//		ba.mostrar();
		//inf.mostrar();
	}
	//-20.0 -5.0 36.0 -8.0 -19.0 12.0  5
}