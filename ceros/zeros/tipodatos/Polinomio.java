/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.tipodatos;

import java.util.Vector;

public class Polinomio {
	private int grado;
	private Vector<Float> fac;

	public Polinomio() {
		grado = 10;
		fac= new Vector<Float>(grado);
	}

	public Polinomio(int vgrado) {
		grado = vgrado;
		fac = new Vector<Float>(grado+1);
	}
	public Polinomio(int vgrado,boolean llenar) {
		grado = vgrado;
		fac = new Vector<Float>(grado+1);
		if (llenar) {
			for (int i=0;i<=grado;i++) fac.add((float)0);
			}
	}
	public Polinomio(String factores){
		fac = new Vector<Float>();
		grado = -1;
		for (int i=0;i<factores.split(" ").length;i++){
			fac.add(Float.valueOf((String)(factores.split(" ")[i])));
			grado++;
		}
//	In mejor modo de manjar un string tokenizer lo separa por espacios		
//		 BufferedReader input = new BufferedReader(new FileReader(filename));
//	  	    while( (s = input.readLine()) != null ){
//	  	        StringTokenizer st = new StringTokenizer(s);
//	  	        b = Long.parseLong(st.nextToken());
//	  	        d = Long.parseLong(st.nextToken());
//	  	        p = Long.parseLong(st.nextToken());
	}
	
	public void setFactorat(int pos,float val){
		fac.setElementAt(val,pos);
	}
	public float getFactorat(int pos){
		return fac.elementAt(pos);
	}
	public void loadFactor(float value){
		fac.add(value);
	}

	public void normaliza() {
		float a=fac.elementAt(grado);
		for (int i = 0; i <= grado; i++) {
			float newvalue = fac.elementAt(i)/a;
			fac.setElementAt((Float)newvalue, i);
		}
	}

	public double evalua(double d) {
		double acu = fac.elementAt(grado);
		for (int i = grado-1; i >= 0; i--) {
			acu = acu*d+fac.elementAt(i);
		}
		return acu;
	}
	public int getGrado(){
		return grado;
	}
	public boolean checkPol(){
		if (fac.elementAt(grado)==0) {
			grado--;
			return true;
		}
		else return false;
	}
	public String polinomioAString(){
		String salida="";
		for (int i=0;i<=grado;i++){
			salida=salida+fac.elementAt(i)+" ";
		}
		return salida;
	}
	public float getLast(){
		return fac.elementAt(grado);
	}
	public float getFirst(){
		return fac.elementAt(0);
	}

}
