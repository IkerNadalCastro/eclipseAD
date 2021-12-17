package ejercicio5;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class UD2ej5 {

	public static void main(String[] args) {
		
		try {
			
			String ruta;
			ruta = "Ficheros\\Amigos.txt";

			FileReader fr; 
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String linea; 
			
			ArrayList<String> al = new ArrayList<String>();
			
			while ((linea = br.readLine()) != null) {
				
				al.add(linea);
				
			}
			br.close();
			
			
			
			int longitud = ruta.length();
			String nombreArchivo = ruta.substring(0, longitud - 4);
			String extensionArchivo = ruta.substring(longitud - 4, longitud);  
			
			FileWriter fw; 
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					nombreArchivo + "_sort" + extensionArchivo)); 
			
			Collections.sort(al);
			
			for (String s : al) {
				
				bw.write(s + "\n");
				
			}
			bw.close();

		
		}
		catch (IOException e) {
			
			System.out.println("Error: " + e.getStackTrace());
			
		}
		
	}

}
