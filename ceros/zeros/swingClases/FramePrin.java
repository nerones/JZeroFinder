/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import zeros.constantes.Constantes;

public class FramePrin extends JFrame {
	/**
	 * Clase programada de forma penosa y engorrosa
	 */
	DataListener listener;
	JPanel butonholder;
	JButton calculatebuton, plotbuton;
	AreaTexto area;
	GridBagLayout gridl;
	GridBagConstraints gridc;
	PanelOpciones options;
	private BarraMenu barramenu;
	private String modo;
	PanelDatosEntrada dataentrada;
	PanelDatosSalida datasalida;
	
	private static final long serialVersionUID = 5735417221676357323L;

	public FramePrin() {
		super("Busca ceros.. 0.0.1");
		listener = new DataListener(this);
		gridl= new GridBagLayout();
		
		gridc = new GridBagConstraints();
		setLayout(gridl);
		setSize(460, 300);
		options = new PanelOpciones(listener);
		barramenu = new BarraMenu(listener);
		setJMenuBar(barramenu.getBarra());

		dataentrada = new PanelDatosEntrada(listener);
		gridc.gridx=1;
		gridc.gridy=0;
		gridc.insets = new Insets(5, 2, 2, 5);
		gridc.fill= GridBagConstraints.HORIZONTAL;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		add(dataentrada.getMainPanel(),gridc);
		
		gridc.gridx=0;
		gridc.gridy=1;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		gridc.fill=GridBagConstraints.HORIZONTAL;
		//gridl.setConstraints(dat_labelholder, gridc);
		datasalida = new PanelDatosSalida(listener);
		add(datasalida.getMainPanel(),gridc);//,BorderLayout.CENTER);
		
		area = new AreaTexto();

		calculatebuton = new JButton("Calcular");
		calculatebuton.addActionListener(listener);
		plotbuton = new JButton("Graficar");
		plotbuton.addActionListener(listener);
		butonholder = new JPanel(new FlowLayout());
		butonholder.add(calculatebuton);
		butonholder.add(plotbuton);
		gridc.gridx=0;
		gridc.gridy=2;
		gridc.fill=GridBagConstraints.HORIZONTAL;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		add(butonholder,gridc);
		
		modo = Constantes.CEROS;
		pack();
		setMinimumSize(new Dimension(460, 260));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	public void modooBuscar() {
		modo = Constantes.BUSCACEROS;
		dataentrada.removeG();
		calculatebuton.setText("Buscar");
		dataentrada.setEditable(Constantes.EDITABLE_BUSCACEROS_IN.split(","));
		dataentrada.setVisible(Constantes.ENABLED_CEROS_IN.split(","));
		dataentrada.setData(Constantes.EXAMPLES_BUSCACEROS.split(","));
		dataentrada.renameLabelIn(Constantes.LABELS_IN_CEROS.split(","));
		dataentrada.renameLabelData(Constantes.LABELS_DATA_CEROS.split(","));
		remove(options);
		remove(area.getScrolpane());
		gridc.gridx=0;
		gridc.gridy=1;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		add(datasalida.getMainPanel(),gridc);
		pack();
	}
	
	public void modooCalcular() {
		modo = Constantes.CEROS;
		dataentrada.addG();
		calculatebuton.setText("Calcular");
		dataentrada.setEditable(Constantes.EDITABLE_CEROS_IN.split(","));
		dataentrada.setVisible(Constantes.ENABLED_CEROS_IN.split(","));
		dataentrada.setData(Constantes.EXAMPLES.split(","));
		dataentrada.renameLabelIn(Constantes.LABELS_IN_CEROS.split(","));
		dataentrada.renameLabelData(Constantes.LABELS_DATA_CEROS.split(","));
		remove(area.getScrolpane());
		remove(options);
		gridc.gridx=0;
		gridc.gridy=1;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		add(datasalida.getMainPanel(),gridc);
		pack();
	}
	
	public void modooPolinomio() {
		modo = Constantes.POLINOMIO;
		dataentrada.removeG();
		calculatebuton.setText("Calcular");
		gridc.gridx=0;
		gridc.gridy=0;
		gridc.gridheight=1;
		gridc.weighty=1;
		gridc.insets= new Insets(5, 2, 2, 5);
		gridc.gridwidth=1;
		add(options,gridc);
		options.setAllNames(Constantes.OPCIONES_CALCULO_POLINOMIOS.split(","));
		dataentrada.setEditable(Constantes.EDITABLE_POLINOMIOS_IN.split(","));
		dataentrada.setVisible(Constantes.ENABLED_POLINOMIOS_IN.split(","));
		dataentrada.setData(Constantes.EXAMPLES_POLINOMIOS.split(","));
		dataentrada.renameLabelIn(Constantes.LABELS_IN_POLINOMIOS.split(","));
		dataentrada.renameLabelData(Constantes.LABELS_DATA_POLINOMIOS.split(","));
		remove(datasalida.getMainPanel());
		gridc.gridx=0;
		gridc.gridy=1;
		gridc.fill=GridBagConstraints.HORIZONTAL;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		add(area.getScrolpane(),gridc);
		pack();
	}
	
	public void modooMatrices() {
		modo = Constantes.MATRIZ;
		dataentrada.removeG();
		calculatebuton.setText("Calcular");
		gridc.gridx=0;
		gridc.gridy=0;
		gridc.gridheight=1;
		gridc.weighty=1;
		gridc.insets= new Insets(5, 2, 2, 5);
		//gridc.fill= GridBagConstraints.VERTICAL;
		gridc.gridwidth=1;//GridBagConstraints.REMAINDER;
		add(options,gridc);
		options.setAllNames(Constantes.OPCIONES_CALCULO_MATRICES.split(","));
		dataentrada.setEditable(Constantes.EDITABLE_MATRICES_IN.split(","));
		dataentrada.setVisible(Constantes.ENABLED_MATRICES_IN.split(","));
		dataentrada.setData(Constantes.EXAMPLES_MATRICES.split(","));
		dataentrada.renameLabelIn(Constantes.LABELS_IN_MATRICES.split(","));
		dataentrada.renameLabelData(Constantes.LABELS_DATA_MATRICES.split(","));
		remove(datasalida.getMainPanel());
		gridc.gridx=0;
		gridc.gridy=1;
		gridc.fill=GridBagConstraints.HORIZONTAL;
		gridc.gridwidth=GridBagConstraints.REMAINDER;
		add(area.getScrolpane(),gridc);
		pack();
	}
	public String getModo(){
		return modo;
	}
	public AreaTexto getArea() {
		return area;
	}

}
