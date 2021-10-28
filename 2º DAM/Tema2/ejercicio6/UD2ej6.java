package ejercicio6;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UD2ej6 {

	public static void main(String[] args) {

		String ruta = "Ficheros\\" + args[0];
		String frase;
		
		ArrayList<String> LineaVertical = new ArrayList<String>();
		int contador = 0; 
		
		try {
			
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			
			while ((frase = br.readLine()) != null) {
				
				ArrayList<String> LineaHorizontal = new ArrayList<String>();
				
				for (String p: frase.split(" ")) {
					
					LineaHorizontal.add(p);
					
				}
				
				for (String p: LineaHorizontal) {
					
					LineaVertical.add(p);
					
				}
						
			}
			
			for (String p : LineaVertical) {
				
				if (p.equalsIgnoreCase(args[1])) {
					
					contador ++;
						
				}
				
			}
			
			System.out.println(contador);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
