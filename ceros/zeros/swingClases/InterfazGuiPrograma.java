/**
 * @author Nelson Efrain A. Cruz
 * 
 */
package zeros.swingClases;

import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;

import zeros.constantes.Constantes;
import zeros.mains.CalculaPolinomio;
import zeros.mains.CalculaSistema;
import zeros.numeric.fnolineal.CalculaCeros;
import zeros.numeric.fnolineal.DummyBuscador;
import zeros.tipodatos.Matriz;
import zeros.tipodatos.Polinomio;

public class InterfazGuiPrograma {
	FramePrin gui;
	CalculaCeros calculador;
	boolean errordatos = false;

	public InterfazGuiPrograma(FramePrin gui) {
		this.gui = gui;
	}

	public void calculate() {
		Vector<String> aux = gui.dataentrada.getData();
		try {
			Double.valueOf(aux.elementAt(3));
			Double.valueOf(aux.elementAt(4));
			Double.valueOf(aux.elementAt(5));
		} catch (Exception e) {
			errordatos = true;
			JOptionPane.showMessageDialog(null,
					"error en los limites o en la tolerancia");
		}

		if ((aux.elementAt(1).matches("")) | (aux.elementAt(2).matches(""))) {
			if ((!CalculaCeros.revisaDatos(aux,gui.dataentrada.getGString(), true)) && (!errordatos)) {
				calculador = new CalculaCeros(aux);
				calculador.setG(gui.dataentrada.getGString());
				gui.datasalida.setdata(calculador.someResultsAsVector());
				gui.datasalida.setTolTip(calculador.allCountersAsVector());
			}
		} else if ((!CalculaCeros.revisaDatos(aux,gui.dataentrada.getGString(), false)) & (!errordatos)) {
			calculador = new CalculaCeros(aux);
			calculador.setG(gui.dataentrada.getGString());
			// gui.setdata(calculador.allResultsAsVector());
			gui.datasalida.setdata(calculador.InformeCalculos(true));
			gui.datasalida.setTolTip(calculador.allCountersAsVector());
		} else if (!errordatos) {
			JOptionPane.showMessageDialog(null,
					"error en las expresiones de las funciones");
			Vector<String> dat = new Vector<String>(Arrays
					.asList(Constantes.ERROR_OUT.split(",")));
			gui.datasalida.setdata(dat);
		}
		errordatos = false;
	}
	
	public void resolvSistem(){
		Vector<String> datos=gui.dataentrada.getData();
		CalculaSistema calculador = new CalculaSistema(datos);
		calculador.opera(gui.options.getSelected(),gui.options.getMejorar());
		AreaTexto areatex = gui.getArea();
		areatex.escribeMatriz(calculador.getA(),calculador.getB(), calculador.getResult());
	}
	
	public void resolvPoloinomio(){
		Vector<String> datos=gui.dataentrada.getData();
		CalculaPolinomio calculador= new CalculaPolinomio(datos);
		calculador.setModoAcota(gui.options.getModoAcota());
		calculador.setModoRaices(gui.options.getSelectedRaices());
		String modeCalculate = gui.options.getSelected();
		AreaTexto areatex = gui.getArea();
		if (calculador.opera(modeCalculate)){
			if (modeCalculate.matches("Raices reales")){
				//if (gui.options.getSelectedRaices().matches("por Newton"))
				areatex.escribe(calculador.getPosiblesraices()+"\n");
				//areatex.escribe(calculador.getPosiblesraices()+"\n");
			} else if (modeCalculate.matches("Acotar raices")){
				//areatex.escribe(calculador.getCotas()+"\n");
				if (gui.options.getModoAcota()==3){
					Polinomio[] sturn = calculador.getsuccSturn();
					areatex.escribelatex("Sucesion -de- Sturn: ", true);
					for (int i = 0; i < sturn.length; i++) {
						areatex.escribelatex("P_"+i+"=",false);
						areatex.escribePolinomio(sturn[i]);
					}
				}
				else areatex.escribelatex(calculador.getCotas(), true);
			} else if (modeCalculate.matches("Evaluar")){
				areatex.escribelatex(calculador.getOp()+calculador.getEvaluado()+"", true);
				//areatex.escribe(""+calculador.getEvaluado()+"\n");
				
			} else if (modeCalculate.matches("Division")){
				areatex.escribelatex("C(x)=", false);
				areatex.escribePolinomio(calculador.getResultado());
				areatex.escribelatex("R(x)=", false);
				areatex.escribePolinomio(calculador.getResto());
			} else {
				areatex.escribelatex(calculador.getOp(), false);
				areatex.escribePolinomio(calculador.getResultado());
			}
		}
		else areatex.escribe("Error");
	}
	
	public void buscar(){
		Vector<String> datosEntrada = gui.dataentrada.getData();
		DummyBuscador buscador = new DummyBuscador(datosEntrada);
		buscador.setG(gui.dataentrada.getGString());
		//System.out.print(buscador.buscarCero());
		gui.datasalida.setdata(buscador.buscarCero());
		datosEntrada.setElementAt(""+buscador.getA(), 3);
		datosEntrada.setElementAt(""+buscador.getB(), 4);
		gui.dataentrada.setData(datosEntrada);
	}
	
	public void modBuscar() {
		gui.modooBuscar();
	}
	public void modCalcular(){
		gui.modooCalcular();
	}
	public void modpolinomio(){
		gui.area.limpia();
		gui.modooPolinomio();
	}
	public void modMatriz(){
		gui.area.limpia();
		gui.modooMatrices();
	}
	public String func(){
		return gui.dataentrada.getData().elementAt(0);
	}
	public String getGuiMode(){
		return gui.getModo();
	}
	public void setmodeOperation(String mode){
		gui.options.setSelected(mode);
	}
	public void updateArea(String modo){
		Vector<String> datos = gui.dataentrada.getData();
		gui.getArea().escribe("\n \n \n \n \n");
		if (modo==Constantes.MATRIZ){
			Matriz a = new Matriz(datos.elementAt(0).substring(0,datos.elementAt(0).length()-1 ));
			Matriz b = new Matriz(datos.elementAt(1).substring(0,datos.elementAt(1).length()-1 ));
			gui.getArea().escribeMatriz(a,b);
		} else if (modo==Constantes.POLINOMIO){
			Polinomio p = new Polinomio(datos.elementAt(0).substring(0,datos.elementAt(0).length()-1 ));
			Polinomio q = new Polinomio(datos.elementAt(1).substring(0,datos.elementAt(1).length()-1 ));
			gui.getArea().escribelatex("P(x)= ", false);
			gui.getArea().escribePolinomio(p);
			gui.getArea().escribelatex("Q(x)= ", false);
			gui.getArea().escribePolinomio(q);
		}
	}

}
class UpdateTexArea implements Runnable {
	InterfazGuiPrograma manager;
	public UpdateTexArea(InterfazGuiPrograma man) {
		manager = man;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		
		manager.updateArea(manager.getGuiMode());
		// TODO Auto-generated method stub
		
	}
	
}
