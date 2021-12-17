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
import org.hibernate.Transaction;

import primero.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Vista {

	private JFrame frame;
	private JTextField textField_numeroEmpleado;
	private JTextField textField_apellido;
	private JTextField textField_oficio;
	private JTextField textField_salario;
	private JTextField textField_comision;
	private JTextField textField_fechaAlta;
	private JComboBox comboBox_elijeDepartamento;
	private JComboBox comboBox_elijeDirector;
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
		
		session = sesion.openSession();
		
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		campoFecha.setText(fecha); 
		
		session.close();
	
	}
	
	public void consultarEmpleado(JTextField campoTexto) {
		
		Short texto = Short.valueOf(campoTexto.getText().trim());
		Empleados emp = (Empleados) session.get(Empleados.class, texto);
		
		if (emp == null) {
			
			JOptionPane.showMessageDialog(null, "No se ha encontrado al empleado", "Error en la búsqueda", JOptionPane.WARNING_MESSAGE);
			
		} else {
			
			textField_numeroEmpleado.setText(String.valueOf(emp.getEmpNo()));
			textField_apellido.setText(String.valueOf(emp.getApellido()));
			textField_oficio.setText(String.valueOf(emp.getOficio()));
			textField_salario.setText(String.valueOf(emp.getSalario()));
			
			if (emp.getComision() == null) {
				
				textField_comision.setText("0");
				
			} else {
				
				textField_comision.setText(String.valueOf(emp.getComision()));
				
			}
			
			textField_fechaAlta.setText(String.valueOf(emp.getFechaAlt()));
			
			Departamentos dep = emp.getDepartamentos();
			Byte bit = dep.getDeptNo();
			
			for(int i = 1; i < comboBox_elijeDepartamento.getItemCount(); i++) {
				
				String estrinj = (String) comboBox_elijeDepartamento.getItemAt(i);
				String[] estrinjs = estrinj.split(" / ");
				
				Byte eraBaitBro = Byte.valueOf(estrinjs[0]);
				
				if (bit == eraBaitBro) {
					
					comboBox_elijeDepartamento.setSelectedIndex(i);
					
				}
				
			}
			
			Short dir = emp.getDir();
			
			for(int i = 1; i < comboBox_elijeDirector.getItemCount(); i++) {
				
				String estring = (String) comboBox_elijeDirector.getItemAt(i);
				String[] estrings = estring.split(" / ");
				
				Short cortito = Short.valueOf(estrings[0]);
				
				if (dir.equals(cortito)) {
					
					comboBox_elijeDirector.setSelectedIndex(i);
					
				}
				
			}
			
		}
		
	}
	
	public void insertarEmpleados() {
		
		Transaction t = session.beginTransaction();
		
		if(textField_numeroEmpleado.getText().trim().equalsIgnoreCase("") || textField_apellido.getText().trim().equalsIgnoreCase("") || textField_oficio.getText().trim().equalsIgnoreCase("") || 
				textField_salario.getText().trim().equalsIgnoreCase("") || textField_comision.getText().trim().equalsIgnoreCase("") || textField_fechaAlta.getText().trim().equalsIgnoreCase("")
				|| comboBox_elijeDepartamento.getSelectedIndex() == 0 || comboBox_elijeDirector.getSelectedIndex() == 0) {
			
			JOptionPane.showMessageDialog(null, "No se ha podido insertar al empleado porque algún campo está vacío", "Error al insertar", JOptionPane.WARNING_MESSAGE);
			
			t.rollback();
			
		} else {
			
			Empleados emp = new Empleados();
			
			emp.setEmpNo(Short.valueOf(textField_numeroEmpleado.getText().trim()));
			
			String linea = (String) comboBox_elijeDepartamento.getSelectedItem();
			String[] lineas = linea.split(" / ");
			Departamentos dep = new Departamentos(Byte.valueOf(lineas[0]));
			emp.setDepartamentos(dep);
			
			emp.setApellido(textField_apellido.getText());
			emp.setOficio(textField_oficio.getText());
			
			String linea2 = (String) comboBox_elijeDirector.getSelectedItem();
			String[] lineas2 = linea2.split(" / ");
			emp.setDir(Short.valueOf(lineas2[0]));
			
			emp.setFechaAlt(Date.valueOf(textField_fechaAlta.getText()));
			emp.setSalario(Float.valueOf(textField_salario.getText()));
			emp.setComision(Float.valueOf(textField_comision.getText()));
			
			session.save(emp);
			
			t.commit();
		}
		
		insertarDirectores(comboBox_elijeDirector);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		sesion = SessionFactoryUtil.getSessionFactory();
		
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
		textField_numeroEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(! Character.isDigit(c))
					e.consume();
				if(textField_numeroEmpleado.getText().length()>=4) 
					e.consume();		
				//numero.setTransferHandler(null);*/
			}
		});
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
				  
					session = sesion.openSession(); 
				  
				    String textoConsultar;
				    textoConsultar = textField_numeroEmpleado.getText().trim();
				    
				    if(textoConsultar.equalsIgnoreCase("") || textoConsultar.equalsIgnoreCase(null)) {
				    	
				    	JOptionPane.showMessageDialog(null, "No puede buscar un campo vacío o nulo", "Error en la búsqueda", JOptionPane.WARNING_MESSAGE);
				    	
				    } else {
				    	
				    	consultarEmpleado(textField_numeroEmpleado);
				    	
				    }
				    
				    session.close();
				    
			  } 
		});
		frame.getContentPane().add(button_consultarEmpleado);		
		
		comboBox_elijeDepartamento = new JComboBox();
		comboBox_elijeDepartamento.setModel(new DefaultComboBoxModel(new String[] {"ELIJE DEPARTAMENTO"}));
		comboBox_elijeDepartamento.setBounds(227, 69, 197, 22);
		sesion.openSession();
		insertarDepartamentos(comboBox_elijeDepartamento);
		frame.getContentPane().add(comboBox_elijeDepartamento);
		session.close();
		
		comboBox_elijeDirector = new JComboBox();
		comboBox_elijeDirector.setModel(new DefaultComboBoxModel (new String[] {"ELIJE DIRECTOR"}));
		comboBox_elijeDirector.setBounds(227, 94, 197, 22);
		sesion.openSession();
		insertarDirectores(comboBox_elijeDirector);
		frame.getContentPane().add(comboBox_elijeDirector);
		session.close();
		
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
		button_insertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				session = sesion.openSession(); 
				
				insertarEmpleados();

				session.close();
				
			}
		});
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
				  
				  session = sesion.openSession();
				  
				  System.exit(-1);
				    
				  session.close();  
				 
			  } 
		});
		frame.getContentPane().add(button_salir);
		
		JButton button_limpiar = new JButton("Limpiar");
		button_limpiar.setBounds(245, 207, 100, 23);
		button_limpiar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				session = sesion.openSession();
				
				textField_numeroEmpleado.setText("");
				textField_apellido.setText("");
				textField_oficio.setText("");
				textField_salario.setText("");
				textField_comision.setText("");
				
				comboBox_elijeDepartamento.setSelectedIndex(0);
				comboBox_elijeDirector.setSelectedIndex(0);
				
				fechaActual(textField_fechaAlta);
				
				session.close();
				
			}
			
		});
		frame.getContentPane().add(button_limpiar);
	}
}
