package zeros;

/**
 * @(#)GraficadorSwing.java
 *
 * Sample Applet application
 *
 * @author Walter Mora F/ITCR/2006
 * @version 1.00 06/03/16
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import org.nfunk.jep.*;
import org.nfunk.jep.type.*;

public class GraficadorClasico extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4661075108813401998L;
	private JEP miEvaluador;
	boolean errorEnExpresion; // si hay error de sintaxis en la funci�n
	boolean errorEnNumero; // si hay error de sintaxis en la funci�n
	Font ft10 = new Font("Arial", Font.PLAIN, 10);
	Font ft12 = new Font("Arial", Font.PLAIN, 12);
	Font ft7 = new Font("Arial", Font.PLAIN, 7);
	JTextField Tffun;
	JLabel Mensaje;
	JPanel SP; // Slider Panel
	JPanel ZG; // aqu� se va a poner la ZonaGrafica
	JPanel ControlPanel; // panel para botones y campos de texto,etc
	JPanel LogoPanel;
	JPanel DisplayPanel1 = new JPanel(); // aqu� se va a poner ZG para obtener
											// un buen borde
	JPanel DisplayPanel2 = new JPanel(); // aqu� se va a poner los Sliders y
											// controlPanel
	JButton BtnAyuda, BtnGraficar;
	JFrame fFrame; // ventana de ayuda
	int Galto, Gancho; // dimesiones de la zona de graficaci�n
	double xmin, xmax, imgx;
	int x0, y0; // origen
	int escalaX, escalaY;
	String func;
	final static BasicStroke grosor1 = new BasicStroke(1.5f); // thickness
	final static float dash1[] = { 5.0f };
	final static BasicStroke dashed = new BasicStroke(1.0f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
	ImageIcon logocrv;
	public GraficadorClasico(String s){
		func=s;
		setSize(500,300);
		init();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void setFunc(String s){
		Tffun.setText(s);
		Tffun.setEditable(false);
	}
	public void init() {
		Container Contenedor = getContentPane();
		logocrv = new ImageIcon(getClass().getResource("images/crvlogo.gif"));
		Gancho = getSize().width - 10;
		Galto = 70 * getSize().height / 100;
		ZG = new ZonaGrafica(); // zona gr�fica
		SP = new SliderPanel(); // panel para Sliders de escala
		ControlPanel = new JPanel();
		DisplayPanel1.setLayout(new BorderLayout());
		DisplayPanel1.add(ZG, BorderLayout.CENTER);
		// Logopanel
		LogoPanel = new JPanel();
		LogoPanel.add(new JLabel(logocrv));
		LogoPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					try {
						URL elURL = new URL(
								"http://www.cidse.itcr.ac.cr/crv/index.html");
						// getAppletContext().showDocument(elURL);
					} catch (MalformedURLException ae) {
					}
				}
			}

		});

		Cursor manitaCursor = new Cursor(Cursor.HAND_CURSOR);
		LogoPanel.setCursor(manitaCursor);
		// fin logoPanel

		ControlPanel.setLayout(new GridBagLayout()); // como hoja de Excel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		Tffun = new JTextField(func, 8);
		c.gridy = 1; // fila
		c.gridx = 0; // columna
		ControlPanel.add(Tffun, c);

		// espacio en blanco
		JLabel esp1 = new JLabel("----- ");
		c.gridy = 1; // fila
		c.gridx = 1; // columna
		ControlPanel.add(esp1, c);

		BtnGraficar = new JButton("Graficar");
		c.gridy = 1; // fila
		c.gridx = 2; // columna
		ControlPanel.add(BtnGraficar, c);
		BtnGraficar.setFont(ft12);

		BtnAyuda = new JButton("Ayuda");
		c.gridy = 2; // fila
		c.gridx = 2; // columna
		ControlPanel.add(BtnAyuda, c);
		BtnAyuda.setFont(ft12);

		Mensaje = new JLabel("", JLabel.LEFT);
		c.gridwidth = 3;
		c.gridy = 4; // fila
		c.gridx = 0; // columna
		ControlPanel.add(Mensaje, c);
		Mensaje.setFont(ft12);
		Mensaje.setForeground(new Color(0, 100, 0));

		// fin del administrador de dise�o ControlPanel

		// Bordes
		Border colorline = BorderFactory.createLineBorder(new Color(220, 220,
				220));
		DisplayPanel1.setBorder(colorline);
		TitledBorder rotulo;
		rotulo = BorderFactory.createTitledBorder(" Escala");
		rotulo.setTitleFont(ft10);
		rotulo.setTitleColor(new Color(0, 0, 128));
		SP.setBorder(rotulo);

		rotulo = BorderFactory.createTitledBorder(" CRV ");
		//rotulo.setTitleFont(ft10);
		rotulo.setTitleColor(new Color(0, 0, 128));
		LogoPanel.setBorder(rotulo);

		rotulo = BorderFactory.createTitledBorder(" Funcion ");
		rotulo.setTitleColor(new Color(0, 0, 128));
		//rotulo.setTitleFont(ft10);
		ControlPanel.setBorder(rotulo);
		// fin de Bordes

		DisplayPanel1.setPreferredSize(new Dimension(Gancho, Galto));
		ControlPanel.setPreferredSize(new Dimension(60 * Gancho / 100,
				30 * getSize().height / 100));
		SP.setPreferredSize(new Dimension(30 * Gancho / 100,
				30 * getSize().height / 100));

		DisplayPanel2.setLayout(new BorderLayout(1,1));
		DisplayPanel2.add(LogoPanel,BorderLayout.WEST);
		DisplayPanel2.add(ControlPanel,BorderLayout.CENTER);
		DisplayPanel2.add(SP,BorderLayout.EAST);

		Contenedor.setLayout(new BorderLayout(1, 1)); // aplicar al applet
		Contenedor.add("Center", DisplayPanel1);
		Contenedor.add("South", DisplayPanel2);

		miEvaluador = new JEP();
		miEvaluador.addStandardFunctions(); // agrega las funciones matematicas
											// comunes
		miEvaluador.addStandardConstants();
		miEvaluador.addComplex();
		miEvaluador.addFunction("sen", new org.nfunk.jep.function.Sine());// habilitar
																			// 'sen'
		miEvaluador.addVariable("x", 0);
		miEvaluador.setImplicitMul(true); // permite 2x en vez de 2*x
		escalaX = 30;
		escalaY = 30;
		x0 = Gancho / 2;
		y0 = Galto / 2;

		ManejadorDeEvento ManejadorDevt = new ManejadorDeEvento();
		Tffun.addActionListener(ManejadorDevt);
		BtnGraficar.addActionListener(ManejadorDevt);
		fFrame = new AyudaJFrame(this);
		BtnAyuda.addActionListener(ManejadorDevt);

	}//

	private class ManejadorDeEvento implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			Object source = evt.getSource();
			// si se presiona el bot�n o se da 'enter' en alg�n campo de texto
			if (source == BtnGraficar || source == Tffun) {
				ZG.repaint();
			}

			if (source == BtnAyuda) {
				fFrame.setVisible(true);
			}//  

		}
	}//

	class ZonaGrafica extends JPanel implements MouseListener,
			MouseMotionListener {
		int offsetX, offsetY;
		boolean dragging;

		ZonaGrafica() {
			setBackground(Color.white);
			offsetX = x0;
			offsetY = y0;
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		public void mousePressed(MouseEvent evt) {

			if (dragging)
				return;
			int x = evt.getX(); // clic inicial
			int y = evt.getY();
			offsetX = x - x0;
			offsetY = y - y0;
			dragging = true;
		}

		public void mouseReleased(MouseEvent evt) {
			dragging = false;
			repaint();
		}

		public void mouseDragged(MouseEvent evt) {
			if (dragging == false)
				return;

			int x = evt.getX(); // posici�n del mouse
			int y = evt.getY();
			x0 = x - offsetX; // mover origen
			y0 = y - offsetY;

			repaint();
		}

		// el resto hace nada
		public void mouseMoved(MouseEvent evt) {
		}

		public void mouseClicked(MouseEvent evt) {
		}

		public void mouseEntered(MouseEvent evt) {
		}

		public void mouseExited(MouseEvent evt) {
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // clear to background color

			Graficar(g, x0, y0);

			// showStatus("http://www.cidse.itcr.ac.cr/");
		}

		void Graficar(Graphics ap, int xg, int yg) {
			int xi = 0, yi = 0, xi1 = 0, yi1 = 0, numPuntos = 1;
			int cxmin, cxmax, cymin, cymax;
			double valxi = 0.0, valxi1 = 0.0, valyi = 0.0, valyi1 = 0.0;

			// convertimos el objeto ap en un objeto Graphics2D para usar los
			// m�todos Java2D
			Graphics2D g = (Graphics2D) ap;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g.setFont(ft10);
			g.setPaint(new Color(0, 0, 150));

			// eje Y
			g.draw(new Line2D.Double(xg, 10, xg, Galto - 10));
			// eje X
			g.draw(new Line2D.Double(10, yg, Gancho - 10, yg));

			xmin = -1.0 * xg / escalaX;
			xmax = (1.0 * (Gancho - xg) / escalaX);
			cxmin = (int) Math.round(xmin); // pantalla
			cxmax = (int) Math.round(xmax);
			cymin = (int) Math.round(1.0 * (yg - Galto) / escalaY);
			cymax = (int) Math.round(1.0 * yg / escalaY);

			numPuntos = Gancho; // num pixels

			g.setPaint(Color.gray);
			g.setFont(ft7);

			// marcas en los ejes (ticks)
			if (escalaX > 5) {
				for (int i = cxmin + 1; i < cxmax; i++) {
					g.draw(new Line2D.Double(xg + i * escalaX, yg - 2, xg + i
							* escalaX, yg + 2));
					if (i > 0)
						g.drawString("" + i, xg + i * escalaX - 2, yg + 12);
					if (i < 0)
						g.drawString("" + i, xg + i * escalaX - 6, yg + 12);
				}
			}

			if (escalaY > 5) {
				for (int i = cymin + 1; i < cymax; i++) {
					g.draw(new Line2D.Double(xg - 2, yg - i * escalaY, xg + 2,
							yg - i * escalaY));
					if (i > 0)
						g.drawString("" + i, xg - 12, yg - i * escalaY + 3);
					if (i < 0)
						g.drawString("" + i, xg - 14, yg - i * escalaY + 3);
				}
			}
			g.setPaint(new Color(50, 100, 0));

			g.setStroke(grosor1);

			miEvaluador.parseExpression(Tffun.getText());
			errorEnExpresion = miEvaluador.hasError(); // hay error?

			if (!errorEnExpresion) {
				Mensaje.setText(":. Arrastre el mouse para mover ejes.");
				Tffun.setForeground(Color.black);

				for (int i = 0; i < numPuntos - 1; i++) {
					valxi = xmin + i * 1.0 / escalaX;
					valxi1 = xmin + (i + 1) * 1.0 / escalaX;
					miEvaluador.addVariable("x", valxi);
					valyi = miEvaluador.getValue();
					miEvaluador.addVariable("x", valxi1);
					valyi1 = miEvaluador.getValue();
					xi = (int) Math.round(escalaX * (valxi));
					yi = (int) Math.round(escalaY * valyi);
					xi1 = (int) Math.round(escalaX * (valxi1));
					yi1 = (int) Math.round(escalaY * valyi1);

					// control de discontinuidades groseras y complejos
					Complex valC = miEvaluador.getComplexValue();
					double imgx = (double) Math.abs(valC.im());
					if (dist(valxi, valyi, valxi1, valyi1) < 1000 && imgx == 0) {
						g.draw(new Line2D.Double(xg + xi, yg - yi, xg + xi1, yg
								- yi1));
					}
				}// fin del for
			} else {
				Mensaje.setText(":. Hay un error.");
				Tffun.setForeground(Color.red);
			}
		}// Graficar

		double dist(double xA, double yA, double xB, double yB) {
			return (xA - xB) * (xA - xB) + (yA - yB) * (yA - yB);
		}//

	} // class

	class SliderPanel extends JPanel {
		JSlider xSlider, ySlider; // Manejo de escala

		SliderPanel() {
			setLayout(new GridLayout(1, 2));

			SliderListener auditor = new SliderListener();

			xSlider = new JSlider(JSlider.VERTICAL, 1, 200, 20);
			xSlider.addChangeListener(auditor);
			add(xSlider);

			ySlider = new JSlider(JSlider.VERTICAL, 1, 200, 20);
			ySlider.addChangeListener(auditor);
			add(ySlider);

			// xSlider.setLabelTable(xSlider.createStandardLabels(20));
			// xSlider.setMajorTickSpacing(200);
			xSlider.setMinorTickSpacing(20);
			xSlider.setPaintTicks(true);
			xSlider.setPaintLabels(true);

			// ySlider.setMajorTickSpacing(200);
			ySlider.setMinorTickSpacing(20);
			ySlider.setPaintTicks(true);
			ySlider.setPaintLabels(true);

		}

		public void ajusteEscala() { // se ejecuta si se 'oy�' alg�n cambio en
										// alg�n Slider
			escalaX = (int) xSlider.getValue();
			escalaY = (int) ySlider.getValue();
			ZG.repaint();
		}//

		class SliderListener implements ChangeListener {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				// if (!source.getValueIsAdjusting())

				ajusteEscala();

			}
		}
	} //            	

	class AyudaJFrame extends JFrame {

		JTextArea p;

		GraficadorClasico fApplet;

		AyudaJFrame(GraficadorClasico applet) {
			super("Ayuda");
			fApplet = applet;
			Container content_pane = getContentPane();

			p = new JTextArea(30, 40);
			p.setText(information());
			p.setEditable(false);

			JScrollPane sp = new JScrollPane(p);
			content_pane.add(sp, BorderLayout.CENTER);

			pack();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		public void actionPerformed(ActionEvent e) {
			// nada por hoy
		}

		String information() {
			String message = " :.\n"
					+ " Mover ejes : arrastre el mouse\n\n"
					+ " ------ ------ EJEMPLO\n"
					+ " + suma x+2\n"
					+ " - resta x-5\n"
					+ " * multiplicaci�n 3*x\n"
					+ " / divisi�n -1/x\n"
					+ " () agrupaci�n (x+2)/(3*x)\n"
					+ " ^ potenciaci�n (-3*x)^2\n"
					+ " % resto de la divisi�n x%5\n"
					+ " RAIZ(x) ra�z cuadrada RAIZ(x)\n"
					+ " sqrt() ra�z cuadrada sqrt(x)\n"
					+ " mod() resto de la divisi�n mod(x,5)\n"
					+ " sen() seno 4*sen(x^2)\n"
					+ " cos() coseno 6*cos(-3*x)\n"
					+ " tan() tangente 3*tan(x)\n"
					+ " atan() arcotangente atan(x-3)\n"
					+ " asin() arcoseno asen((x+5)/(3^x))\n"
					+ " acos() arcocoseno 2-acos(-x+3)\n"
					+ " sinh() seno hiperb�lico sinh(x)\n"
					+ " cosh() coseno hiperb�lico -3*cosh(1/x)\n"
					+ " tanh() tangente hiperb�lica tanh(x)/2\n"
					+ " asinh() arcoseno hiperb�lico 2*asinh(x)/3\n"
					+ " acosh() arcocoseno hiperb�lico (2+acosh(x))/(1-x)\n"
					+ " atanh() arcotangente hiperb�lica atanh(x)*(3-x^(1/x))\n"
					+ " ln() logaritmo natural ln(x)+1\n"
					+ " log() logaritmo decimal -2*log(x)-1\n"
					+ " abs() valor absoluto abs(x-2)\n"
					+ " rand() valor aleatorio rand()\n"
					+ " re() parte real de un Complejo re(2+9*i)\n"
					+ " im() parte imaginaria im(-8+7*i)\n"
					+ " angle() �ngulo en pos. est�ndar angle(x,2)\n\n"
					+ " pi 3,141592653589793 pi+cos(x)\n"
					+ " e 2,718281828459045 e+1\n"
					+ " Usa JEP,(Nathan Funk http://jep.sourceforge.net)\n\n"
					+ " :. Dise�o e implementaci�n: \n \n"
					+ " :. Walter Mora F., wmora2@yahoo.com.mx\n\n"
					+ " :. CRV: Centro de Recursos Virtuales (www.cidse.itcr.ac.cr)\n"
					+ " :. Instituto Tecnol�gico de Costa Rica\n"
					+ " :. Escuela de Matem�tica\n";
			return message;
		}// information

	} // class AyudaFrame

} // end APPLET

