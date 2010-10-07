/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.tipodatos;

import zeros.extratool.Evaluador;

public class Datos {
	private String fu;
	private String fud1;
	private String fud2;
	private String gu;
	private Evaluador e;

	public Datos(String vfun,String vfund1,String vfund2)
	{
	fu=vfun;
	fud1=vfund1;
	fud2=vfund2;
	e = new Evaluador();
	}
	public void setG(String g){
		gu=g;
	}
	public Datos(String vfun){
		fu=vfun;
		e= new Evaluador();
	}
	public double f(double vx){
	    double res= e.evaluar(fu, vx);
	    return res;
	}
	public double fd1(double vx){
		double res= e.evaluar(fud1,vx);
		return res;
	}
	public double fd2(double vx){
		double res= e.evaluar(fud2,vx);
		return res;
	}
	public double g(double vx){
		double res= e.evaluar(gu,vx);
		return res;
	}

}
