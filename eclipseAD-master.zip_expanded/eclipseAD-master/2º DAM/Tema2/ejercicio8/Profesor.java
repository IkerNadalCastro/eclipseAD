package ejercicio8;


import java.io.Serializable;

public class Profesor implements Serializable {

	 private String nombre; 
	 
	 private double antiguedad; 
	
	public Profesor(String nombre, double antiguedad) {

		
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(double antiguedad) {
		this.antiguedad = antiguedad;
	}
	
	

	
}
