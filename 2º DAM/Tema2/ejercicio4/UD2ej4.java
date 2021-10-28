package ejercicio4;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UD2ej4 {

	public static void main(String[] args) {

		
		
		try {
			
			FileWriter fw; 
			BufferedWriter bw = new BufferedWriter(new FileWriter("Ficheros\\Pares.txt"));
			
			for (int i = 0; i <= 500; i++ ) {
				
				if((i%2) == 0) {
					
					bw.write(String.valueOf(i));
					bw.newLine();
					
				}
				
			}
			bw.close();
			
			FileReader fr;
			BufferedReader br = new BufferedReader(new FileReader("Ficheros\\Pares.txt"));
			String linea; 
			while ((linea = br.readLine()) != null) {
				
				System.out.println(linea);
				
			}
			br.close();
			
		}
		catch(IOException e) {
			
			System.out.println("Error: " + e.getStackTrace());
			
		}

	}

}
