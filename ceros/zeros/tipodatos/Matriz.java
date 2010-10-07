/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.tipodatos;
/*
 * Matriz es un vector de vectores que se inicializa llenando la
 * matriz de ceros, pues de no hacerlo se crea el objeto vector
 * de vectores pero con elementos vacios (null)
 */
import java.text.NumberFormat;
import java.util.Vector;

/**
 * 
 * @author nelson
 *
 */
public class Matriz {
	
	public Vector<Vector<Double>> matriz;
	private int n;	//Representa las filas
	private int m;	//Representa las columnas
	private int anchor;
	
	public Matriz(int n, boolean option){
		this.n=m=n;
		double init;
		if (option) init = -1.0;
		else init = 0.0;
		matriz=new Vector<Vector<Double>>(n);
		for (int i=0;i<n;i++){
			Vector<Double> aux =new Vector<Double>(m);
			for (int j=0;j<m;j++){
				aux.add(init);}
			matriz.add(aux);
		}
	}
	public Matriz (Vector<Vector<Double>> mat, int nnew, int mnew ){
		matriz= mat;
		n=nnew;
		m=mnew;
	}
	public Matriz (int na,int ma){
		n=na;m=ma;
		matriz=new Vector<Vector<Double>>(n);
		for (int i=0;i<n;i++){
			Vector<Double> aux =new Vector<Double>(m);
			for (int j=0;j<m;j++){
				aux.add(-1.0);
			}
			matriz.add(aux);
		}
	}
	public Matriz(String form){
		String tamanio = form.split(";")[0];
		String data = form.split(";")[1];
		int ns= Integer.valueOf(tamanio.split(" ")[0]);
		int ms= Integer.valueOf(tamanio.split(" ")[1]);
		n=ns;m=ms;
		anchor = 1;
		matriz=new Vector<Vector<Double>>(n);
		int aux1=0;
		for (int i=0;i<n;i++){
			Vector<Double> aux =new Vector<Double>(m);
			for (int j=0;j<m;j++){
				String number = data.split(" ")[aux1];
				if (number.length()>anchor) anchor=number.length();
				aux.add(Double.valueOf(number));
				aux1++;
			}
			matriz.add(aux);
		}
		anchor+=2;
	}
	/**
	 * Carga la matriz mediante un array de enteros, tener en cuenta que la cantidad
	 * total de elementos de la matriz debe ser igual a la de el array.
	 * @param lista Un array de {@link Double} a cargarse en la matriz.
	 */
	public void cargar(double[] lista){
		int aux=0;
		for(int i=0;i<n;i++){
			Vector<Double> vec =new Vector<Double>(m);
			for (int j=0;j<m;j++){
				vec.add(j,(double)lista[aux]);
				aux++;
			}	
			matriz.set(i,vec);
		}
	}
	public Vector<Vector<Double>> getMatriz() {
		return matriz;
	}
	
	/**
	 * Devuelve la cantidad de filas de la matriz, tener en cuenta que internamente se
	 * llega hasta la fila n-1 pues se empieza en la fila cero y no en uno.
	 * @return La cantidad de filas
	 */
	public int getN() {
		return n;
	}
	
	/**
	 * Devuelve la cantidad de columnas de la matriz, tener en cuenta que internamente se
	 * llega hasta la columna m-1 pues se empieza en la columna cero y no en la 
	 * columna uno.
	 * @return La cantidad de columnas
	 */
	public int getM() {
		return m;
	}
	public void setElement(int ne,int me,Double elem){
		Vector<Double> vec ;
		vec=matriz.elementAt(ne);
		vec.set(me, elem);
		matriz.set(ne, vec);	
	}
	public Double getElement(int ne,int me){
		Vector<Double> vec = matriz.elementAt(ne);
		return vec.elementAt(me);
	}
	public void mostrar(){
		for (int i=0;i<n;i++){
		Vector<Double> aux = matriz.elementAt(i);
		for (int j=0;j<m;j++){
			System.out.print(aux.elementAt(j)+" ");
			}
		System.out.println();
		}
	}
	
	public Matriz copiaCompl(){
		Matriz aux= new Matriz(n,m);
		//Matriz aux= new Matriz((Vector<Vector<Double>>) matriz.clone(),n,m);
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
				aux.setElement(i, j,getElement(i,j));
				}
		}
		return aux;
	}
	boolean iguales(Matriz b){
		 boolean ask=true;
		 for (int i=0;i<n;i++){
				for (int j=0;j<m;j++){
					if(getElement(i, j).doubleValue()!=b.getElement(i,j).doubleValue()){return false;}
					}
			}
		 return ask;
	}
	public void swapfila(int i,int j){
		if (i==j) return;
		Vector<Double> aux = matriz.elementAt(i);
		matriz.setElementAt(matriz.elementAt(j), i);
		matriz.setElementAt(aux, j);
	}
	/**
	 * Devuelve una fila de la matriz en el formato <strong>| n n n n n |</strong> donde n son las
	 * componentes de la fila
	 * @param i la fila a convertir en string
	 * @return la fila en formato String
	 */
	public String filaAsString(int i){
		Vector<Double> fila = matriz.elementAt(i);
		String filaAsString = "|";
		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		nf.setMinimumFractionDigits(0);
		String number;
		for (int j = 0; j < fila.size(); j++) {
			if (fila.elementAt(j)<0){
				 number = nf.format(fila.elementAt(j));
				 while (number.length()<anchor) number=" "+number;
			}
			else {
				number = " "+nf.format(fila.elementAt(j));
				while (number.length()<anchor) number=" "+number;
			}
			filaAsString += number;
		}
		filaAsString +=" |";
		return filaAsString;
	}
	public boolean isFila(){
		if (n==1) return true;
		else return false;
	}
	public boolean isColumn(){
		if (m==1) return true;
		else return false;
	} 
	public Vector<Double> getfila(int i){
		return matriz.elementAt(i);
	}
 
}
