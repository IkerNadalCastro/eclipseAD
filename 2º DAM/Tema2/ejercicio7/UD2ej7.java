package ejercicio7;


import java.io.*;

public class UD2ej7 {

	public static void main(String[] args) {

		try {
			
			DataOutputStream dos = new DataOutputStream
					(new FileOutputStream("Ficheros\\puntuacion.dat"));
			
			
			for (int i = 0; i < 20; i++) {
				
				int numeroRandom = ((int) (Math.random() * 5))+1;
				
				dos.writeInt(numeroRandom);
				
			}
			
			dos.close();
			DataInputStream dis = new DataInputStream
				(new FileInputStream("Ficheros\\puntuacion.dat"));
			
			int c = 0; 
			
			for (int i = 0; i < 20; i++) {
				
				c = dis.readInt();
				
				System.out.println(c);
				
			}
			
			
			
			dis.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

	}

}
