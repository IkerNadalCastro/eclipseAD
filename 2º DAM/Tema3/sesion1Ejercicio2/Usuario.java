package sesion1Ejercicio2;

public class Usuario {
	
	private String username;
	private String password;
	private String nombre;
	
	public Usuario() {}
	
	public Usuario(String username, String password, String nombre) {
		
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		
	}

public Usuario( String nombre) {
		
		this.nombre = nombre;
		
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", nombre=" + nombre + "]";
	}
	
	
	
	

}
