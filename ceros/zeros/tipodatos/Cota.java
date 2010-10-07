/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.tipodatos;

public class Cota {
	private float cota;
	private boolean existe;
	
	public Cota() {
		cota=-1;
		existe = false;
	}
	
	public Cota(float cota, boolean existe) {
		this.cota = cota;
		this.existe = existe;
	}

	public float getCota() {
		return cota;
	}
	public void setCota(float cota) {
		this.cota = cota;
	}
	public boolean isExiste() {
		return existe;
	}
	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	public String toString(){
		return cota+"";
	}

}
