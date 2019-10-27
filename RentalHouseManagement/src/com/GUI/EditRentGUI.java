package com.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditRentGUI {

	private JFrame frame;
	private JTextField textField;
	private static String _code;

	
	public EditRentGUI(String maPhong) {
		// TODO Auto-generated constructor stub
		_code = maPhong;
		initialize();
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					EditRentGUI window = new EditRentGUI(_code);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param maPhong 
	 */
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIdRow = new JLabel("ID row");
		lblIdRow.setBounds(69, 25, 46, 14);
		frame.getContentPane().add(lblIdRow);
		
		JTextField txtMa = new JTextField(_code);
		txtMa.setBounds(111, 22, 86, 20);
		frame.getContentPane().add(txtMa);
		txtMa.setColumns(10);
		
	}
}
