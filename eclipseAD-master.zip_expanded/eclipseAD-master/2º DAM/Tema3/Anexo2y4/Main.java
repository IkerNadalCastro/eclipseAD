package Anexo2y4;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		AccesoBaseDatos abd = new AccesoBaseDatos();
		
		abd.conectar();
		
		/*System.out.println(abd.busquedaPorCodigo(100));
		System.out.println(abd.busquedaPorCodigo(7788));
		
		System.out.println(abd.busquedaPorOficio("Profesor"));
		System.out.println(abd.busquedaPorOficio("CLERK"));*/
		
		/*System.out.println("Empleados contratados antes del 22 de febrero de 1981");
		System.out.println("=====================================================");
		java.util.Date fecha = null;
		java.sql.Date sqlFecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
		fecha = sdf.parse("1981/02/22");
		sqlFecha = new java.sql.Date(fecha.getTime());
		} catch (ParseException e) {
		System.out.println("Error al convertir fecha");
		}
		System.out.println(abd.busquedaPorAntiguedad(sqlFecha));*/
		
		
		/*java.util.Date fecha = null;
		java.sql.Date sqlFecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
		fecha = sdf.parse("2020/03/18");
		sqlFecha = new java.sql.Date(fecha.getTime());
		} catch (ParseException e) {
		System.out.println("Error al convertir fecha");
		}
		
		empleado e1 = new empleado(1, "CARRERA", "PROFESOR", 7788, sqlFecha, 700, 0, 20);
		System.out.println(abd.insertarConBean(e1));
		System.out.println(abd.insertarConBean(e1));
		
		empleado e2 = new empleado(10, "CARRERA", "FOL", 7788, sqlFecha, 700, 0, 35);
		System.out.println(abd.insertarConBean(e2));*/
		
		/*System.out.println("Subir un 20% el salario de los empleados del Departamento 30");
		System.out.println(abd.actualizarSalarioconTransacciones(30, 0.2));
		System.out.println("Subir un 15% el salario de los empleados del Departamento 44");
		System.out.println(abd.actualizarSalarioconTransacciones(44, 0.15));*/
		
		abd.desconectar();
		
		
	}

}
