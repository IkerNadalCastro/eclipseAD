package ejercicio2;

import java.io.File;
import java.util.Scanner;

public class UD2ej2 {

	public static void main(String[] args) {

		System.out.println("Introduzca la ruta del directorio o fichero: ");
		Scanner s = new Scanner(System.in);
		String ruta = s.next();
		
		File f = new File(ruta);
		System.out.println("Ruta: " + f.getPath());
		
			if (f.exists()) {
				
				System.out.println("Sí existe");
				
			} else {
				
				System.out.println("No existe nada con esta ruta");
				
			}
			
		}
		
	}


