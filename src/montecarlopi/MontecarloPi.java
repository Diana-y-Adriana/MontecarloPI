/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlopi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MontecarloPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// TODO code application logic here

	//map para almacenar los resultado
	Map<Integer, Double> diferencias = new HashMap<>();

	

	diferencias = new MontecarloPi().calcularPi();

	JFrame frame = new JFrame("Grafica Pi");

	frame.add(new Grafica(diferencias));
	frame.setSize(500, 400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

    }

    private Map<Integer, Double> calcularPi() throws NumberFormatException {

	Map<Integer, Double> diferencias = new HashMap<>();
	int aleatorios_bajo_curva = 0;
	double pi = 0.0;
	int N = 1;

	while (Math.abs(pi - Math.PI) > 0.01) {
	    //Genera las coordenadas aleatoriamente
	    double x = Math.random();
	    double y = Math.random();

	    //verifica si la coordenada cae bajo la curva
	    if (Math.pow(x, 2.0) + Math.pow(y, 2.0) <= 1.0) {
		//si el punto cae bajo la curva se aumenta el contador.
		aleatorios_bajo_curva++;
	    }
	    pi = (4.0 * aleatorios_bajo_curva / N);
	    diferencias.put(N, Math.abs(pi - Math.PI));
	    N++;
	}

	JOptionPane.showMessageDialog(null, "Minimo N: " + (diferencias.size())
		+ "\nPI = " + pi);
	System.out.println("n: " + N + "\tPi: " + String.format("%.5f", pi) + "\tDiferencia:" + String.format("%.4f", Math.abs(pi - Math.PI)));
	
	return diferencias;

    }

}
