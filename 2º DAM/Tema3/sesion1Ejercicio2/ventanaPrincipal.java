package sesion1Ejercicio2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import sesion1Ejercicio3.AccesoBdatos;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ventanaPrincipal {

	private JFrame frame;
	private JTextField textUsuario;
	private JTextField textPassword;
	private JLabel lblResultado = new JLabel("");
	private JButton buttonCheck;
	private static  AccesoBdatos abd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal window = new ventanaPrincipal();
					window.frame.setVisible(true);
					abd = new AccesoBdatos();
					abd.conectar();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(50, 65, 75, 25);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelPassword = new JLabel("Contrase\u00F1a: ");
		labelPassword.setBounds(50, 101, 75, 25);
		frame.getContentPane().add(labelPassword);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(135, 67, 86, 20);
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(135, 103, 86, 20);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JButton buttonCheck = new JButton("Aceptar");
		buttonCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				String nombre = textUsuario.getText(); 
				String passwrd = textPassword.getText();
			
				if(evento.getSource() != null){
					
					//lblResultado.setText(abd.consulta(nombre, passwrd));
					
				if(textUsuario.getText().equals("")) {
						lblResultado.setText("");
					JOptionPane.showMessageDialog(null, "Campos vacios");
				}
				else if(textPassword.getText().equals("")) {
					lblResultado.setText("");
					JOptionPane.showMessageDialog(null, "Campos vacios");
				}
					}
				}
			});
		buttonCheck.setBounds(135, 157, 86, 23);
		frame.getContentPane().add(buttonCheck);
		
	
		lblResultado.setBounds(56, 205, 234, 14);
		frame.getContentPane().add(lblResultado);
		
	}
	
	
	
}
