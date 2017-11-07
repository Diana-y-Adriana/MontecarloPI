/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlopi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class Grafica extends Component {

    private  String[][] matriz;
    private final Map<Integer, Double> diferencias;

    Grafica(Map<Integer, Double> diferencias) {
	this.diferencias = diferencias;
    }

    public String[][] getMatriz() {
	return matriz;
    }

    public void setMatriz(String[][] matriz) {
	this.matriz = matriz;
    }

    

    @Override
    public void paint(Graphics g) {
        int tamaño = 150;
	int puntos= 10;
	int ancho = getWidth()-50;
	System.out.println(diferencias.size());
	for (int i = 1; i < puntos; i++) {
	    
	    
	    int n = (diferencias.size()/puntos)*i;
	    int n2 = (diferencias.size()/puntos)*(i+1);

	    int x1=(ancho/puntos)*(i);
	    int y1=getHeight()-(int) (diferencias.get(n)*1000);
	    int x2=(ancho/puntos)*(i+1);
	    int y2=getHeight()-(int) (diferencias.get(n2)*1000);
	    g.drawLine(x1, y1-50, x2, y2-50);
	    g.drawString(n+"", (ancho/puntos)*(i), getHeight()-20);
	}
	
	g.drawString((diferencias.size()/puntos)*puntos+"", ancho, getHeight()-20);
	g.drawString("0.01", ancho, getHeight()-70);
	
	
        if (this.matriz !=null) {
	    int pos_x = 10, pos_y = 10;
        //vertices de los cuadrados
	int[][] verticesXY = new int[matriz.length][2];
	
	
        for (int i = 0; i < matriz.length; i+=4) {
	    
            List<Integer> aristas = new ArrayList<>();
	    for (int j = 0; j < matriz.length; j++) {
		String strings = matriz[i][j];
		//si es 1 se cuenta como arista contra otro vertice
		if (!strings.equals("0")) {
		   
		    aristas.add(j);
		}
	    }
	    System.out.println("");
	    //en caso que tenga mas de dos aristas
	     if(aristas.size() > 2)
	    {
		
		
		g.setColor(Color.WHITE);
		g.fillRect(verticesXY[i-4][0]+( tamaño/(aristas.size()-1)), verticesXY[i-4][1]+tamaño, tamaño, tamaño);
                        
	    }
	    
	     //en caso que tenga solo dos aristas
            if (aristas.size() == 2) {
		g.setColor(Color.BLACK);
                //revisa si el punto no esta en la figura para continuar
                int x1 = verticesXY[i][0];
                int y1 = verticesXY[i][1];
                if (x1 == 0 && y1 == 0) {

                

                        //puntos x y y de los vertices en la figura
                        verticesXY[i][0] = pos_x;
                        verticesXY[i][1] = pos_y;
                        verticesXY[i+2][0] = pos_x + tamaño;
                        verticesXY[i+2][1] = pos_y + tamaño;
                        verticesXY[aristas.get(0)][0] = pos_x + tamaño;
                        verticesXY[aristas.get(0)][1] = pos_y;
                        verticesXY[aristas.get(1)][0] = pos_x;
                        verticesXY[aristas.get(1)][1] = pos_y + tamaño;

                        g.fillRect(verticesXY[i][0], verticesXY[i][1], tamaño, tamaño);
                        
                        pos_y +=tamaño+10;
                   

                }

            }
	    
	   

        }
	}

    }

}
