/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import zeros.GraficadorClasico;
import zeros.constantes.Constantes;

public class DataListener implements ActionListener, DocumentListener {
	InterfazGuiPrograma manager;
	GraficadorClasico graf;

	public DataListener(FramePrin framePrin) {
		// TODO Auto-generated constructor stub
		manager = new InterfazGuiPrograma(framePrin);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AbstractButton but = (AbstractButton) e.getSource();
		String st =but.getText();
		if (but.getText() == "Graficar"){
			graf= new GraficadorClasico(manager.func());
			//JOptionPane.showMessageDialog(null, "no implementado aun :D");
			
		} else if ((but.getText() == "Calcular")&(manager.getGuiMode()==Constantes.CEROS)) {
			manager.calculate();
		} else if (but.getText() == "buscar cero") {
			manager.modBuscar();
		} else if ((but.getText() == "Buscar")&(manager.getGuiMode()==Constantes.BUSCACEROS)) {
			manager.buscar();
		} else if (but.getText() == "calcular ceros") {
			manager.modCalcular();
		} else if (but.getText() == "polinomio") {
			manager.gui.options.removeOptions();
			manager.gui.options.addAcotaOpcion();
			manager.modpolinomio();
		} else if (but.getText() == "matrices") {
			manager.gui.options.removeOptions();
			manager.gui.options.addMejoraOpcion();
			manager.modMatriz();
		} else if ((but.getText() == "Calcular")&(manager.getGuiMode()==Constantes.MATRIZ)) {
			manager.resolvSistem();
		} else if ((but.getText() == "Calcular")&(manager.getGuiMode()==Constantes.POLINOMIO)) {
			manager.resolvPoloinomio();
		}  else if ((st.matches("Resta"))|(st.matches("Suma"))|(st.matches("Multiplicacion"))|
				(st.matches("Division"))|(st.matches("Raices reales"))|
				(st.matches("Evaluar"))|(st.matches("Gauss"))|(st.matches("Jordan"))|
				(st.matches("Crout"))|(st.matches("Cholesky"))|(st.matches("Gauss-Seidel"))|
				(st.matches("Jacobi"))|(st.matches("Relajamiento"))){
			manager.setmodeOperation(but.getText());
			//System.out.print("si");
		} else if (st.matches("Acotar raices")){
			manager.setmodeOperation(but.getText());
		} else if (st.matches("Sobre.")) {
			AboutFrame fram = new AboutFrame();
		} else if (st.matches("por Newton")) {
			manager.gui.options.setSelectedRaices(st);
		} else if (st.matches("por Bairtow")) {
			manager.gui.options.setSelectedRaices(st);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		//System.out.println("doc listen");
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		//if (e.getLength()!=1) return;
		int pos = e.getOffset();
		String contenido = null;
		//if (pos<manager.gui.datain.elementAt(0).getText().charAt(3)) return;
		int i;
		if (manager.gui.dataentrada.datain.elementAt(0).isFocusOwner()) i=0;
		else i=1;
		try {
			contenido = manager.gui.dataentrada.datain.elementAt(i).getText(0, pos+1);
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		if (contenido.endsWith(";"))
			if (!contenido.endsWith(" ;"))
				SwingUtilities.invokeLater( new UpdateTexArea(manager));
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		//System.out.println("doc listen");
	}

}
