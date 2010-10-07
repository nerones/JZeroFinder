/**
 * @author Nelson Efrain A. Cruz
 * 
 *
 */

package zeros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import zeros.numeric.fnolineal.CalculaCeros;
import zeros.swingClases.FramePrin;

public class BuscaCeros {

	public static void main(String[] args) throws IOException {
		BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
		CalculaCeros bis;
		String [] aver = args;
		if ((aver.length!=0) ){
			System.out.println(args);
		System.out.println("introdusca la funcion");
		String aux= lee.readLine();
		System.out.println("introdusca la 1� derivada");
		String aux1 = lee.readLine();
		System.out.println("introdusca la 2� derivada");
		String aux2 = lee.readLine();
		bis= new CalculaCeros(-1.5,-1.0,0.0001,aux,aux1,aux2);
		System.out.println("el cero es "+bis.biseccion());
		bis.repeticiones();
		System.out.println("el cero es "+bis.regula());
		//bis.repeticiones();
		System.out.println("el cero es "+bis.newton());
		//bis.repeticiones();
		System.out.println("el cero es "+bis.regulamodificada());}
		else {
			FramePrin vent = new FramePrin();
		}
	}
}
