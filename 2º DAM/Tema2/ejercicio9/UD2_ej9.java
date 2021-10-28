package ejercicio9;


import java.io.*;
import java.util.Scanner;

public class UD2_ej9 {

	public static void main (String [] args) throws IOException {
		
		File fichero = new File ("Ficheros\\ProfesFPSierraGuara.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		boolean seguir = true;
		String comprobacion = "";
		Scanner s = new Scanner(System.in);
		int id = 0; 
		int posicion = 0; 
		
		
		while (seguir == true) {
			
			System.out.println("Introduzca el id del profesor a borrar: ");
			id = s.nextInt();
			posicion = (id-1) *56;
			
			if ((posicion > (file.length()-56)) || (posicion < 0)) {
				
				System.out.println("No existe un empleado con ese identificador");
				System.exit(-1);
				
			}
			
			file.seek(posicion);
			
			
			while ((comprobacion.equalsIgnoreCase("s")) || !(comprobacion.equalsIgnoreCase("n")) ) {
				
				System.out.println("¿Quiere eliminar a otro profesor? (s/n || S/N)");
				comprobacion = s.next();
				
				if (comprobacion.equalsIgnoreCase("s")) {
					
					seguir = true;
					
				} else if (comprobacion.equalsIgnoreCase("n")) {
					
					seguir = false;
					
					
				} else {
					
					System.out.println("El carácter introducido no es válido");
					
				}
				
			}
			
		}
		
		
	}
	
}
