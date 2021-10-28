package ejercicio8;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream{

	protected MiObjectOutputStream(OutputStream arg0) throws IOException, SecurityException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	protected void writeStreamHeader () throws IOException {}
	
}
