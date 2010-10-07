/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zeros.constantes.Constantes;

public class PanelDatosSalida extends PanelDatos{
	Vector<JTextField> datain, dataout;
	JPanel dataholder, dataholder1, labelholder, data_labelholder, butonholder;
	JPanel dataoutholder, labeloutholder, dat_labelholder;
	Vector<JLabel> labelin, labelout, labeldata;
	
	public PanelDatosSalida(DataListener list){
		super(list);
		dataout = new Vector<JTextField>();
		createJTextFields(dataout, Constantes.INICIAL.split(","), 20);
		setEditables(Constantes.EDITABLE_CEROS_OUT.split(","), dataout);
		dataoutholder = new JPanel(new GridLayout(5, 0));
		dataoutholder.add(dataout.elementAt(0));
		dataoutholder.add(dataout.elementAt(1));
		dataoutholder.add(dataout.elementAt(2));
		dataoutholder.add(dataout.elementAt(3));
		dataoutholder.add(dataout.elementAt(4));
		labelout = new Vector<JLabel>();
		createlabels(labelout, Constantes.LABELS_OUT_CEROS.split(","));
		labeloutholder = new JPanel(new GridLayout(5, 0));
		addlabels(labelout, labeloutholder);
		dat_labelholder = new JPanel(new BorderLayout());
		dat_labelholder.add(new JPanel(), BorderLayout.WEST);
		dat_labelholder.add(labeloutholder, BorderLayout.CENTER);
		dat_labelholder.add(dataoutholder, BorderLayout.EAST);
	}
	public void setdata(Vector<String> dat) {
		setdata(dataout, dat);
	}
	public void setTolTip(Vector<String> dat) {
		setTolTip(dataout, dat);
	}
	public JPanel getMainPanel(){
		return dat_labelholder;
	}
}
