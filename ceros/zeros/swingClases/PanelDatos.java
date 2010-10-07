/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDatos {
	DataListener listener;
	public PanelDatos(DataListener escucha) {
		listener = escucha;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Crea los campos de texto JTextField desde donde se introducen (o muestran) los datos
	 * @param vec El vector que contiene las referencias a los campos
	 * @param dat Un vector con los valores iniciales de los campos
	 * @param tam El tamanio para los campos (el tamanio de JTextField)
	 */
	public void createJTextFields(Vector<JTextField> vec, String[] dat, int tam) {
		for (int i = 0; i < dat.length; i++) {
			vec.add(new JTextField(tam));
			vec.elementAt(i).setText(dat[i]);
			if (i==0 | i==1) {
				vec.elementAt(i).getDocument().addDocumentListener(listener);
				vec.elementAt(i).setName(String.valueOf(i));
			}
		}
	}
//	public void setdata(Vector<String> dat) {
//		setdata(dataout, dat);
//	}
//	
//	public void setdataIn(Vector<String> dat) {
//		setdata(datain, dat);
//	}
	/**
	 * Un metodo generico para setear el texto de JTextFields contenidos en un vector 
	 * @param vec El vector que contiene los fields
	 * @param dat El vector que contiene el texto para cada uno de los campos
	 */
	protected void setdata(Vector<JTextField> vec, String[] dat) {
		for (int i = 0; i < vec.size(); i++) {
			vec.elementAt(i).setText(dat[i]);
		}
	}
	protected void setdata(Vector<JTextField> vec, Vector<String> dat) {
		for (int i = 0; i < vec.size(); i++) {
			vec.elementAt(i).setText(dat.elementAt(i));
		}
	}

//	public void setTolTip(Vector<String> dat) {
//		setTolTip(dataout, dat);
//	}
	
	/**
	 * Un metodo generico para setear los toolTipText de un vector de JTextFields.
	 * @param vec El vector que contiene los JTextFields
	 * @param dat El vector que contiene el texto para cada uno de los ToolsTip
	 */
	protected void setTolTip(Vector<JTextField> vec, Vector<String> dat) {
		for (int i = 0; i < vec.size(); i++) {
			vec.elementAt(i).setToolTipText(dat.elementAt(i));
		}
	}

	public void setEditables(String[] dat, Vector<JTextField> vect) {
		boolean activado;
		for (int i = 0; i < vect.size(); i++) {
			if (dat[i].matches("0"))
				activado = false;
			else
				activado = true;
			vect.elementAt(i).setEditable(activado);
		}
	}

	protected void createlabels(Vector<JLabel> veclabel, String[] dat) {
		for (int i = 0; i < dat.length; i++) {
			veclabel.add(new JLabel(dat[i]));
		}
	}
	
	protected void renamelabels(Vector<JLabel> veclabel, String[] dat) {
		for (int i = 0; i < dat.length; i++) {
			veclabel.elementAt(i).setText(dat[i]);
		}
	}

	protected void addlabels(Vector<JLabel> veclabel, JPanel pan) {
		for (int i = 0; i < veclabel.size(); i++) {
			pan.add(veclabel.elementAt(i));
		}
	}
	
	public void setVisible(String[] dat, Vector<JTextField> vect) {
		boolean activado;
		vect.elementAt(0);
		for (int i = 0; i < vect.size(); i++) {
			if (dat[i].matches("0"))
				activado = false;
			else
				activado = true;
			vect.elementAt(i).setVisible(activado);
		}
	}

}
