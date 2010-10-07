/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zeros.constantes.Constantes;

public class PanelDatosEntrada extends PanelDatos{
	Vector<JTextField> datain;
	JTextField funciong;
	JLabel fung;
	String fun = "Funcion G";
	JPanel dataholder, dataholder1, labelholder, data_labelholder;
	Vector<JLabel> labelin, labeldata;
	public PanelDatosEntrada(DataListener list){
		super(list);
		funciong = new JTextField();
		fung = new JLabel(fun);
		datain = new Vector<JTextField>();
		createJTextFields(datain, Constantes.EXAMPLES.split(","), 5);
		labeldata = new Vector<JLabel>();
		createlabels(labeldata, Constantes.LABELS_DATA_CEROS.split(","));
		dataholder = new JPanel();
		dataholder.setLayout(new GridLayout(5, 0));
		dataholder.add(datain.elementAt(0));
		dataholder.add(datain.elementAt(1));
		dataholder.add(datain.elementAt(2));
		//dataholder.add(funciong);
		dataholder1 = new JPanel(new FlowLayout());
		dataholder1.add(labeldata.elementAt(0));
		dataholder1.add(datain.elementAt(3));
		dataholder1.add(labeldata.elementAt(1));
		dataholder1.add(datain.elementAt(4));
		dataholder1.add(labeldata.elementAt(2));
		dataholder1.add(datain.elementAt(5));
		dataholder.add(dataholder1);
		labelin = new Vector<JLabel>();
		createlabels(labelin, Constantes.LABELS_IN_CEROS.split(","));
		labelholder = new JPanel();
		labelholder.setLayout(new GridLayout(5, 0));
		addlabels(labelin, labelholder);
		addG();
		//labelholder.add(fung);
		data_labelholder = new JPanel();
		data_labelholder.setLayout(new BorderLayout());
		data_labelholder.add(new JPanel(), BorderLayout.WEST);
		data_labelholder.add(labelholder, BorderLayout.CENTER);
		data_labelholder.add(dataholder, BorderLayout.EAST);
	}
	public JPanel getMainPanel(){
		return data_labelholder;
	}
	public void setEditable(String[] s) {
		setEditables(s, datain);
	}
	public void setVisible(String[] s) {
		setVisible(s, datain);
	}
	public void setData(String[] s){
		setdata(datain,s);
	}
	public void setData(Vector<String> s){
		String[] s1 = new String[s.size()];
		s1=s.toArray(s1);
		setdata(datain,s1);
	}
	public void renameLabelIn(String[] s){
		renamelabels(labelin,s);
	}
	public void renameLabelData(String[] s){
		renamelabels(labeldata,s);
	}
	/**
	 * Obtiene los datos que estan en los campos de entrada de la interfaz.
	 * @return Un vector de strings con los datos de entrada.
	 */
	public Vector<String> getData() {
		Vector<String> aux = new Vector<String>();
		for (int i = 0; i < datain.size(); i++) {
			aux.add(datain.elementAt(i).getText());
		}
		return aux;
	}
	public void removeG(){
		dataholder.remove(funciong);
		labelholder.remove(fung);
		funciong.setText("");
	}
	public void addG(){
		dataholder.add(funciong,3);
		labelholder.add(fung);
		funciong.setText("cos(x)");
	}
	public String getGString(){
		return funciong.getText();
	}
}
