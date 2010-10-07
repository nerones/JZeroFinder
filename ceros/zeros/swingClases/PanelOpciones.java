/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import zeros.constantes.Constantes;

public class PanelOpciones extends JPanel implements ActionListener {

	/**
	 * 
	 */
	String selectedAction;
	String selectedRaices;
	int acota;

	private static final long serialVersionUID = 1208133678475129886L;
	GridLayout grid;
	DataListener listener;
	JCheckBox mejorar;
	String[] acotaoptions = {"Lagrange","Laguerre","Newton","Sturn"};
	String[] modoRaices = {"Newton","Bairstow"};
	JPanel content;
	JComboBox acotaopcion,modoRaiz;
	public PanelOpciones(DataListener dat) {
		super();
		listener = dat;
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		//grid = new GridLayout(3, 1);
		setLayout(new BorderLayout());
		mejorar = new JCheckBox("Aplicar mejora");
		mejorar.addActionListener(listener);
		acotaopcion = new JComboBox(acotaoptions);
		acotaopcion.addActionListener(this);
		selectedRaices = "por Newton";
		generaRadioButt(Constantes.OPCIONES_CALCULO_POLINOMIOS.split(","));
	}
	private void generaRadioButt(String[] names){
		content = new JPanel(new GridLayout(7,1));
		ButtonGroup grupo = new ButtonGroup();
		JRadioButton boton;
		for (int i = 0; i < names.length; i++) {
			boton = new JRadioButton(names[i]);
			boton.addActionListener(listener);
			grupo.add(boton);
			content.add(boton,-1 );
			//add(boton);	
		}
		add(content,BorderLayout.NORTH);
		add(new JSeparator(),BorderLayout.CENTER);
	}
	public void addMejoraOpcion(){
		
		add(mejorar,BorderLayout.SOUTH);
		repaint();
	}
	public void removeOptions(){
		remove(acotaopcion);
		remove(mejorar);
	}
	public void addAcotaOpcion(){
		acotaopcion.setSelectedIndex(0);
		acota=0;
		add(acotaopcion,BorderLayout.SOUTH);
	}
	/**
	 * Este metodo deberia solo ser llamado despues de instanciar el objeto, setea el texto de los botones
	 * @param name Un array con los nombres, de tamanio a lo sumo 7
	 */
	public void setAllNames(String[] name){
		((JRadioButton)content.getComponents()[0]).setSelected(true);
		selectedAction = name[0];
		for (int i = 0; i < name.length; i++) {
			((JRadioButton)content.getComponents()[i]).setText(name[i]);
		}
	}
	public String getSelected() {
		return selectedAction;
	}
	public void setSelected(String selected) {
		this.selectedAction = selected;
	}
	
	public String getSelectedRaices() {
		return selectedRaices;
	}
	public void setSelectedRaices(String selectedRaices) {
		this.selectedRaices = selectedRaices;
	}
	public boolean getMejorar(){
		return mejorar.isSelected();
	}
	public int getModoAcota(){
		return acota;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox cb = (JComboBox) e.getSource();
		String elejido = (String) cb.getSelectedItem();
		if (elejido.matches("Laguerre")){
			acota=1;
		} else if (elejido.matches("Lagrange")){
			acota=0;
		} else if (elejido.matches("Newton")){
			acota=2;
		} else if (elejido.matches("Sturn")){
			acota=3;
		}
	}
}
