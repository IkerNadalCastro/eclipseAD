import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingConstants;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class Vista {

	private JFrame frame;
	private JTextField textField_numeroEmpleado;
	private JTextField textField_apellido;
	private JTextField textField_oficio;
	private JTextField textField_salario;
	private JTextField textField_comision;
	private JTextField textField_fechaAlta;
	private SessionFactory sesion;
	private Session session;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}
	
	public void insertarDepartamentos(JComboBox comboBox) {
		
		String consultaDepartamentos = "FROM Departamentos ORDER BY deptNo";
		List<Departamentos> queryDepartamentos = session.createQuery(consultaDepartamentos).list();
		Iterator iteratorDepartamentos = queryDepartamentos.iterator();
		
		while(iteratorDepartamentos.hasNext()) {
			
			Departamentos de = (Departamentos) iteratorDepartamentos.next();
			comboBox.addItem(de.getDeptNo() + " / " + de.getDnombre());
			
		}
		
	}
	
	public void insertarDirectores(JComboBox comboBox) {
		
		String consultaDirectores = "FROM Empleados ORDER BY empNo";
		List<Empleados> queryDirectores = session.createQuery(consultaDirectores).list();
		Iterator iteratorDepartamentos = queryDirectores.iterator();
		
		while(iteratorDepartamentos.hasNext()) {
			
			Empleados emp = (Empleados) iteratorDepartamentos.next();
			comboBox.addItem(emp.getEmpNo() + " / " + emp.getApellido());
			
		}
		
	}
	
	public void fechaActual(JTextField campoFecha) {
		
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		campoFecha.setText(fecha);
	
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		sesion = SessionFactoryUtil.getSessionFactory();
		session = sesion.openSession(); 
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTI\u00D3N DE EMPLEADOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(45, 11, 172, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00BA EMPLEADO: ");
		lblNewLabel_1.setBounds(10, 48, 79, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("APELLIDO: ");
		lblNewLabel_2.setBounds(10, 73, 79, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("OFICIO:");
		lblNewLabel_3.setBounds(10, 98, 79, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SALARIO: ");
		lblNewLabel_4.setBounds(10, 123, 79, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("COMISION: ");
		lblNewLabel_5.setBounds(10, 148, 79, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_numeroEmpleado = new JTextField();
		textField_numeroEmpleado.setBounds(99, 45, 118, 20);
		frame.getContentPane().add(textField_numeroEmpleado);
		textField_numeroEmpleado.setColumns(10);
		
		textField_apellido = new JTextField();
		textField_apellido.setBounds(99, 70, 118, 20);
		frame.getContentPane().add(textField_apellido);
		textField_apellido.setColumns(10);
		
		textField_oficio = new JTextField();
		textField_oficio.setBounds(99, 95, 118, 20);
		frame.getContentPane().add(textField_oficio);
		textField_oficio.setColumns(10);
		
		textField_salario = new JTextField();
		textField_salario.setBounds(99, 120, 118, 20);
		frame.getContentPane().add(textField_salario);
		textField_salario.setColumns(10);
		
		textField_comision = new JTextField();
		textField_comision.setBounds(99, 145, 118, 20);
		frame.getContentPane().add(textField_comision);
		textField_comision.setColumns(10);
		
		JButton button_consultarEmpleado = new JButton("CONSULTAR EMPLEADO");
		button_consultarEmpleado.setBounds(227, 44, 197, 23);
		button_consultarEmpleado.addActionListener(new ActionListener() { 
			
			  public void actionPerformed(ActionEvent e) { 
				  
				    String textoConsultar;
				    textoConsultar = textField_numeroEmpleado.getText().trim();
				    
				    if(textoConsultar.equalsIgnoreCase("") || textoConsultar.equalsIgnoreCase(null)) {
				    	
				    	JOptionPane.showMessageDialog(null, "No puede buscar un campo vacío o nulo", "Error en la búsqueda", JOptionPane.WARNING_MESSAGE);
				    	
				    }
				    
			  } 
		});
		frame.getContentPane().add(button_consultarEmpleado);
		
		/*
		 * public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c))
					e.consume();
				if(numero.getText().length()>=4) 
					e.consume();		
			}
			
			esto es para limitar el numero de caracteres que deja escribir a 4
			
			numero.setTransferHandler(null);
			
			esto otra cosa que pasó angel, puede que sea necesario para que funcione lo otro
		 * */
		
		JComboBox comboBox_elijeDepartamento = new JComboBox();
		comboBox_elijeDepartamento.setModel(new DefaultComboBoxModel(new String[] {"ELIJE DEPARTAMENTO"}));
		comboBox_elijeDepartamento.setBounds(227, 69, 197, 22);
		insertarDepartamentos(comboBox_elijeDepartamento);
		frame.getContentPane().add(comboBox_elijeDepartamento);
		
		JComboBox comboBox_elijeDirector = new JComboBox();
		comboBox_elijeDirector.setModel(new DefaultComboBoxModel (new String[] {"ELIJE DIRECTOR"}));
		comboBox_elijeDirector.setBounds(227, 94, 197, 22);
		insertarDirectores(comboBox_elijeDirector);
		frame.getContentPane().add(comboBox_elijeDirector);
		
		JLabel lblNewLabel_6 = new JLabel("FECHA ALTA: ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(227, 123, 197, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		textField_fechaAlta = new JTextField();
		textField_fechaAlta.setBounds(227, 145, 99, 20);
		fechaActual(textField_fechaAlta);
		frame.getContentPane().add(textField_fechaAlta);
		textField_fechaAlta.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("(YYYY-MM-DD)");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(336, 148, 88, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton button_insertar = new JButton("Insertar");
		button_insertar.setBounds(10, 173, 100, 23);
		frame.getContentPane().add(button_insertar);
		
		JButton button_eliminar = new JButton("Eliminar");
		button_eliminar.setBounds(167, 173, 100, 23);
		frame.getContentPane().add(button_eliminar);
		
		JButton button_modificar = new JButton("Modificar");
		button_modificar.setBounds(324, 173, 100, 23);
		frame.getContentPane().add(button_modificar);
		
		JButton button_salir = new JButton("Salir");
		button_salir.setBounds(91, 207, 100, 23);
		button_salir.addActionListener(new ActionListener() { 
			
			  public void actionPerformed(ActionEvent e) { 
				  
				    System.exit(-1);
				    
				  } 
		});
		frame.getContentPane().add(button_salir);
		
		JButton button_limpiar = new JButton("Limpiar");
		button_limpiar.setBounds(245, 207, 100, 23);
		button_limpiar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				textField_numeroEmpleado.setText("");
				textField_apellido.setText("");
				textField_oficio.setText("");
				textField_salario.setText("");
				textField_comision.setText("");
				
				comboBox_elijeDepartamento.setSelectedIndex(0);
				comboBox_elijeDirector.setSelectedIndex(0);
				
				fechaActual(textField_fechaAlta);
				
			}
			
		});
		frame.getContentPane().add(button_limpiar);
	}
}
