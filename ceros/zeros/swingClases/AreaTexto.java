/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import zeros.extratool.ATextoOLatex;
import zeros.tipodatos.Matriz;
import zeros.tipodatos.Polinomio;
import be.ugent.caagt.jmathtex.TeXConstants;
import be.ugent.caagt.jmathtex.TeXFormula;

public class AreaTexto {
	private String newline = "\n";
	JTextPane areatexto;
	JScrollPane scrolpane;
	StyledDocument doc;
	ATextoOLatex convert;
	NumberFormat nf;
	//DataListener listener;
	public AreaTexto() {
		nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
		/*
		 * 	String s = nf.format(double);
			label.setText(s);
		 */
		areatexto= new JTextPane();
        convert = new ATextoOLatex();
        scrolpane = new JScrollPane(areatexto);
        scrolpane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        scrolpane.setPreferredSize(new Dimension(400,120 ));
        doc = areatexto.getStyledDocument();
        addStylesToDocument(doc);
	}
	
	public JScrollPane getScrolpane(){
		return scrolpane;
	}
	
	public void limpia(){
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void escribeMatriz(Matriz a, Matriz b, Matriz r){
		try {
            for (int i=0; i < a.getN(); i++) {
                doc.insertString(doc.getLength(), a.filaAsString(i)+"  "+b.filaAsString(i)+"      "+r.filaAsString(i)+newline,doc.getStyle("regular"));
            }
            doc.insertString(doc.getLength(), "\n",doc.getStyle("regular"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
	}
	
	public void escribeMatriz(Matriz a, Matriz b){
		try {
            for (int i=0; i < a.getN(); i++) {
                doc.insertString(doc.getLength(), a.filaAsString(i)+"  "+b.filaAsString(i)+"      "+newline,doc.getStyle("regular"));
            }
            doc.insertString(doc.getLength(), "\n",doc.getStyle("regular"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
	}
	
	public void escribePolinomio(Polinomio p) {
		Icon ico = convert.polinomioLatex(p);
		areatexto.insertIcon(ico);
		escribe("\n");
	}
	
	public void escribelatex(String texto,boolean newline){
		areatexto.insertIcon(convert.toIcon(texto));
		if (newline) escribe("\n");
	}
	
	public void escribe(String texto){
		try {
            doc.insertString(doc.getLength(),texto,doc.getStyle("regular"));
    
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
	}
	protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "Monospaced");

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        TeXFormula form= new TeXFormula("V_{oef}^2 =");
        Icon pigIcon = form.createTeXIcon(TeXConstants.ALIGN_CENTER, 15); //createImageIcon("images/Pig.gif","a cute pig");
        if (pigIcon != null) {
            StyleConstants.setIcon(s, pigIcon);
        }

        s = doc.addStyle("button", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon soundIcon = null;//createImageIcon("images/sound.gif","sound icon");
        JButton button = new JButton();
        if (soundIcon != null) {
            button.setIcon(soundIcon);
        } else {
            button.setText("BEEP");
        }
        button.setCursor(Cursor.getDefaultCursor());
        button.setMargin(new Insets(0,0,0,0));
//        button.setActionCommand(buttonString);
//        button.addActionListener(this);
//        StyleConstants.setComponent(s, button);
    }
	
}
