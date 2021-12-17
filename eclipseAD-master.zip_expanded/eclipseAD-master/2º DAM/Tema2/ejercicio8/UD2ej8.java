package ejercicio8;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UD2ej8 {

	public static void main(String[] args) {

		try {
			
			File fichero = new File("Ficheros\\antiguedad_obj.dat");
			
			MiObjectOutputStream moos = new MiObjectOutputStream 
					(new FileOutputStream (fichero));
			
			ObjectInputStream ois = new ObjectInputStream 
					(new FileInputStream(fichero)); 
			
			
			
			if (fichero.exists() == false) {
				
				Scanner leer = new Scanner(System.in); 
				
				System.out.println("numero");
				int num = leer.nextInt();
				
				ArrayList<Profesor> lista = new ArrayList<Profesor>();
				
				for(int i=1; i<=num; i++) {
					
					System.out.println("Nombre del profesor: ");
					Scanner n = new Scanner(System.in);
					String nombre = n.nextLine();
					
					
					System.out.println("Antiguedad del profesor: ");
					Scanner a = new Scanner(System.in);
					double antiguedad = a.nextDouble();
					
					Profesor p = new Profesor(nombre, antiguedad);
					lista.add(p);
				
				}
				
			} else {
				
				
				
			}
			
			
			
			
			
		} catch (Exception e) {
			
			e.getStackTrace();
			
		}

	}

}
