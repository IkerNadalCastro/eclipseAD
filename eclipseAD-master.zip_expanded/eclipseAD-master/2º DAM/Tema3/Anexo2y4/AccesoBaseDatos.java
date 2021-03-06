package Anexo2y4;
/*
 * Revisado para versi?n 8.0.19 de MySQL 
 * Alberto Carrera Mart?n
 * y a?adido comentarios el 17 de marzo de 2020
 * 
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
public class AccesoBaseDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "demodb";
    private static String hostname = "localhost";
    private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database +
									"?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";
    private Connection conecta;
	
	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	public empleado busquedaPorCodigo(int numero)  {
		
		PreparedStatement ppst;
		try {
			ppst = conecta.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?");
			ppst.setInt(1, numero);
			empleado employer;
			ResultSet rs = ppst.executeQuery();
			if (rs.next()) {
				
				employer = new empleado(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
				
				return employer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<empleado> busquedaPorOficio (String oficio) {
		
		try {
			
			PreparedStatement pst = conecta.prepareStatement("SELECT * FROM EMP WHERE JOB = ?");
			pst.setString(1, oficio);
			ResultSet rs = pst.executeQuery();
			ArrayList <empleado> list = new ArrayList<empleado>();
			
			while(rs.next()) {
				
				empleado employer = new empleado (rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
				list.add(employer);
				
			}
			
			return list;
		
		}
		catch (SQLException excep) {
			
			excep.printStackTrace();
			
		}
		return null;
		
	}
	
	public ArrayList<empleado> busquedaPorAntiguedad (java.sql.Date fecha) throws SQLException {
		
		PreparedStatement pst = conecta.prepareStatement("SELECT * FROM EMP WHERE HIREDATE <= ?");
		pst.setDate(1, fecha);
		ResultSet rs = pst.executeQuery();
		ArrayList <empleado> list = new ArrayList<empleado>();
		
		while (rs.next()) {
			
			empleado employer = new empleado (rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getInt(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
			
			list.add(employer);
			
		}
		
		return list;
		
	}
	
	public int insertarConBean(empleado emp) {
		
		try {
			
			PreparedStatement pst = conecta.prepareStatement("INSERT INTO EMP VALUES(?,?,?,?,?,?,?,?)");
			
			pst.setInt(1, emp.getWorkerNumber());
			pst.setString(2, emp.getWorkerName());
			pst.setString(3, emp.getJob());
			pst.setInt(4, emp.getMgr());
			pst.setDate(5, emp.getHiredate()); 
			pst.setDouble(6, emp.getSal());
			pst.setDouble(7, emp.getComm());
			pst.setInt(8, emp.getDepartamentNumber());
			
			pst.executeUpdate();
			
			return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return e.getErrorCode();
		}

		
	}
	
	public int actualizarSalarioconTransacciones(int departamento, double porcentaje) throws SQLException {
		
		try {
			
			conecta.setAutoCommit(false);
			PreparedStatement pst = conecta.prepareStatement("SELECT * FROM EMP WHERE DEPTNO = ? FOR UPDATE");
			pst.setInt(1, departamento);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				pst = conecta.prepareStatement("UPDATE EMP SET SAL = (SAL + (SAL * ?)) WHERE DEPTNO = ?");
				
				pst.setDouble(1, porcentaje);
				pst.setInt(2, departamento);
				
				int affectedRows = pst.executeUpdate();
				
				conecta.commit();
				
				
				return affectedRows;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Ha ocurrido un error en la operacion");
			
			conecta.rollback();
			conecta.close();
			
			return 0;
		
		}
		
		return 0;
		
		
	}
	
	
	public void desconectar() throws SQLException, ClassNotFoundException {
		
		conecta = null;
	}
}
	
