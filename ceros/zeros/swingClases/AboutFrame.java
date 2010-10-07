/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;


public class AboutFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2845063151549694502L;
	
	public AboutFrame() {
		super("About");
		setSize(250, 100);
		setLayout(new FlowLayout());
		JLabel title = new JLabel("JZeroFinder 0.0.1 ");
		JLabel title1 = new JLabel("Cruz, Nelson Efrain A.");
		JLabel title2 = new JLabel("email: neac03@gmail.com  ");
		setLocationRelativeTo(null);
		getContentPane().add(title);
		getContentPane().add(title1);
		getContentPane().add(title2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	

}