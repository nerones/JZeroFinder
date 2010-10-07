/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.tipodatos;

public class CotasPolinomio {
	private Cota 	cotaPositivam, 
					cotaPositivaM, 
					cotaNegativam, 
					cotaNegativaM;
	public CotasPolinomio(){
		cotaPositivam = new Cota(); 
		cotaPositivaM = new Cota();
		cotaNegativam = new Cota();
		cotaNegativaM = new Cota();
	}
	

	
	public CotasPolinomio(Cota cotaPositivam, Cota cotaPositivaM2,
			Cota cotaNegativam, Cota cotaNegativaM2) {
		this.cotaPositivam = cotaPositivam;
		cotaPositivaM = cotaPositivaM2;
		this.cotaNegativam = cotaNegativam;
		cotaNegativaM = cotaNegativaM2;
	}



	public Cota getCotaPositivam() {
		return cotaPositivam;
	}


	public void setCotaPositivam(Cota cotaPositivam) {
		this.cotaPositivam = cotaPositivam;
	}


	public Cota getCotaPositivaM() {
		return cotaPositivaM;
	}


	public void setCotaPositivaM(Cota cotaPositivaM) {
		this.cotaPositivaM = cotaPositivaM;
	}


	public Cota getCotaNegativam() {
		return cotaNegativam;
	}


	public void setCotaNegativam(Cota cotaNegativam) {
		this.cotaNegativam = cotaNegativam;
	}


	public Cota getCotaNegativaM() {
		return cotaNegativaM;
	}


	public void setCotaNegativaM(Cota cotaNegativaM) {
		this.cotaNegativaM = cotaNegativaM;
	}


	public String CotasAsString(){
		String posit;
		if (cotaPositivam.isExiste() & cotaPositivaM.isExiste()){
		posit =cotaPositivam.getCota()+"< Raiz+ <"+cotaPositivaM.getCota();}
		else posit="No tiene raices positivas"; 
		String negat;
		if (cotaNegativam.isExiste() & cotaNegativaM.isExiste())
		negat =cotaNegativam.getCota()+"< Raiz- <"+cotaNegativaM.getCota();
		else negat ="No tiene raices negativas";
		return negat+" ; "+posit;
	}
	

}
