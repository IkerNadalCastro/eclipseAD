import primero.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ListadoEquipo {
	public static void main (String[] args){
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		System.out.println("Datos del Equipo de los Celtics");
		System.out.println("===========================");
		Equipos e = new Equipos();
		e=(Equipos) session.load(Equipos.class, "Celtics");
		System.out.println("Nombre: " + e.getNombre());
		System.out.println("Ciudad: " + e.getCiudad());
		System.out.println("Conferencia: " + e.getConferencia());
		System.out.println("División: " + e.getDivision());
		
		
		session.close();
		System.exit(0);
	}

}
