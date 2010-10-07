/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.extratool;

import javax.swing.Icon;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import be.ugent.caagt.jmathtex.TeXConstants;
import be.ugent.caagt.jmathtex.TeXFormula;

import zeros.tipodatos.Matriz;
import zeros.tipodatos.Polinomio;

public class ATextoOLatex {
	
	/**
	 * Usando la libreria JMathTeX-0.7pre se convierte un {@link Polinomio} a un icono que contiene a el
	 * polinomio expresado en forma matematica.
	 * 
	 * @param p Polinomio a ser representado.
	 * @return Un objeto {@link Icon} cuyo contenido es el polinomio.
	 */
	public Icon polinomioLatex(Polinomio p){
		String poli; //= "+"+p.getFirst();
		if (p.getFirst()>=0) poli = "+"+p.getFirst();
		else poli = ""+p.getFirst(); 
		for (int i = 1; i < p.getGrado(); i++) {
			if (p.getFactorat(i)>=0) poli = "+"+p.getFactorat(i)+"x^"+i+poli;
			else poli = p.getFactorat(i)+"x^"+i+poli;
		}
		if (p.getGrado()>0)poli = p.getLast()+"x^"+p.getGrado()+poli;
		
		return toIcon(poli);
	}
	
	public Icon matrizLatex(Matriz a){
		return null;
	}
	
	public Icon test(){
		TeXFormula tex = new TeXFormula();
		tex.addFraction("1 1 1 1", "1 1 1 1", false);
		tex.addFraction(tex, "2 2 2 2", false);
		//tex.
		//tex.addOp(op, low, up)
		Icon ico = tex.createTeXIcon(TeXConstants.ALIGN_CENTER, 30);
		return ico;
	}
	
	
	public Icon toIcon(String s){
		TeXFormula form= new TeXFormula(s);
        Icon icon = form.createTeXIcon(TeXConstants.ALIGN_CENTER, 13);
        return icon;
	}
	
	public void escribeMatriz(Matriz a, Matriz b, Matriz r, StyledDocument doc){
		try {
            for (int i=0; i < a.getN(); i++) {
                doc.insertString(doc.getLength(), ""+a.getfila(i)+"  "+b.getfila(i)+"      "+r.getfila(i)+"\n",doc.getStyle("regular"));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
	}
	
}
