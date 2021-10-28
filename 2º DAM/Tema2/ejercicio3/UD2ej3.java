package ejercicio3;


import java.io.*;

public class UD2ej3 {

	public static void main (String arg[]) {
		
		boolean resultado; 
		File directorio = new File("C:\\DAM2");
		resultado = directorio.mkdir();
		if (resultado) {
			
			System.out.println("Directorio Creado");
		
		} else {
			
			System.out.println("No se pudo crear el directorio");
			System.exit(-1);
			
		}
		
		try {
			
			File fichero = new File ("C:\\DAM2\\Alberto.txt");
			resultado = fichero.createNewFile();
			if (resultado) {
				
				System.out.println("Archivo creado");
				
			} else {
				
				System.out.println("No se pudo crear el archivo");
				
			}
			
		} catch (IOException e) {
			
			System.out.println("Se produjo el error: " + e.getMessage());
			
		}
		
	}
	
	public void borrarArchivoyDirectorio(String rutaArchivo, String rutaCarpeta) {
		
		try {
			
			boolean resultadoArchivo; 
			File fichero = new File(rutaArchivo);
			resultadoArchivo = fichero.delete();
			if (resultadoArchivo) {
				
				System.out.println("Fichero borrado correctamente");
				
			} else {
				
				System.out.println("El fichero no se ha podido borrar");
				
			}
			
			boolean resultadoCarpeta;
			File carpeta = new File (rutaCarpeta);
			resultadoCarpeta = carpeta.delete();
			if (resultadoCarpeta) {
				
				System.out.println("Carpeta borrada correctamente");
				
			} else {
				
				System.out.println("La carpeta no se ha podido borrar");
				
			}
			
		} catch (Exception e){
			
			System.out.println("Error: " + e.getMessage());
			
		}
		
	}
	
}
