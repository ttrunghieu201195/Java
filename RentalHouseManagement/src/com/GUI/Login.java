package com.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.Dao.LoginDao;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField textUserName;
	private JLabel lblMtKhu;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		
		JLabel lblQunLPhng = new JLabel("QU\u1EA2N L\u00DD PH\u00D2NG TR\u1ECC");
		lblQunLPhng.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblQunLPhng.setBounds(140, 38, 195, 22);
		frame.getContentPane().add(lblQunLPhng);
		
		JLabel lblTnngNhp = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblTnngNhp.setBounds(55, 72, 89, 14);
		frame.getContentPane().add(lblTnngNhp);
		
		textUserName = new JTextField();
		textUserName.setBounds(154, 71, 195, 20);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setBounds(55, 109, 63, 14);
		frame.getContentPane().add(lblMtKhu);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(154, 102, 195, 20);
		frame.getContentPane().add(txtPassword);
		
		JButton btnngNhp = new JButton("\u0110\u0103ng nh\u1EADp");
		btnngNhp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   String username=textUserName.getText();
				   String pass=txtPassword.getText();
				   LoginDao service = new LoginDao();
				   boolean result = false;
				   try {
					result = service.Login(username, pass);
				   } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
				   if (result == true)
				   {
					   HomeGUI page=new HomeGUI();
					   page.main(new String[0]);
					   
				   }
				   else {
					   JDialog dialog = new JDialog(frame, "Lỗi"); 
						 JLabel sucessLabel = new JLabel("Tên đăng nhập hoặc mật khẩu sai");
						 dialog.getContentPane().add(sucessLabel);
						 dialog.setSize(300, 200); 
						 dialog.setVisible(true); 
				   }
			}
		});		
		btnngNhp.setBounds(247, 147, 96, 23);
		frame.getContentPane().add(btnngNhp);
		
		
	}
}
