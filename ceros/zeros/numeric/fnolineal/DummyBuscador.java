/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.numeric.fnolineal;
import java.util.Vector;



public class DummyBuscador extends CalculaCeros{

	public DummyBuscador(Vector<String> s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public Vector<String> buscarCero(){
		b=0.1;
		double evaluadoencero=da.f(0);
		while (da.f(b)*evaluadoencero>0){
			b+=0.1;
		}
		aux1=a=0;
		aux2=b;
		return InformeCalculos(true);
	}


}
