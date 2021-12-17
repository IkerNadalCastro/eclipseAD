package sesion1Ejercicio3;
/*
 * Revisado para versión 8.19 de MySQL 
 * Alberto Carrera Martín
 * 29 febrero 2020
 * 28 de abril 2021
 */

import java.sql.*;

public class AccesoBdatos {
private String driver = "com.mysql.cj.jdbc.Driver";
private String database = "baloncesto";
private String hostname = "localhost";
private String port = "3306";
private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
private String username = "root";
private String password = "root";

private Connection conecta;

	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	//
	// Este procedimiento que viene a continuación
	// No utiliza sentencias preparadas
	// Es llamado desde ListaLocalidadVisual_ver1.java
	
	public ResultSet consultaLocalidadFormulario (String cadenaSQL){
		Statement stmt;
		try {
			stmt = conecta.createStatement();
			return (stmt.executeQuery(cadenaSQL));
		} catch (SQLException e) {
			return null;
		}
	}
	//
	// Este procedimiento que viene a continuación es el mismo que el anterior
	// pero utilizando sentencias preparadas.
	// Es llamado desde ListaLocalidadVisual_ver2.java
	
	public ResultSet consultaLocalidadFormulario2 (String localidad){
	try {
		PreparedStatement stmt;
		String sentenciaSQL ="SELECT * FROM Socio";
		if (localidad.isEmpty()) 
			stmt = conecta.prepareStatement(sentenciaSQL);
		else {
			sentenciaSQL=" SELECT * FROM Socio WHERE  localidad = ?";
			stmt = conecta.prepareStatement(sentenciaSQL);
			stmt.setString(1, localidad);
		}
		
		return (stmt.executeQuery());
		
		} catch (SQLException e) {
			return null;
		}
	}

	public void desconectar() throws SQLException {
		if (conecta !=null) {
			conecta.close();
		}
	}
}