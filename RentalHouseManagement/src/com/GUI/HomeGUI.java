package com.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class HomeGUI {

	private JFrame frame;
	private JTable table;
	private JTable tblBill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGUI window = new HomeGUI();
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
	public HomeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnHost = new JButton("Ch\u1EE7 nh\u00E0 tr\u1ECD");
		btnHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HostListGUI.main(new String[0]);
			}
		});
		btnHost.setIcon(new ImageIcon(HomeGUI.class.getResource("/img/host1.png")));
		btnHost.setToolTipText("");
		btnHost.setBounds(5, 5, 100, 58);
		btnHost.setFocusPainted(false);
		frame.getContentPane().add(btnHost);
		btnHost.setVerticalTextPosition(JButton.BOTTOM);
		btnHost.setHorizontalTextPosition(JButton.CENTER);
		
		JButton btnRoom = new JButton("Ph\u00F2ng tr\u1ECD");
		btnRoom.setIcon(new ImageIcon(HomeGUI.class.getResource("/img/room1.png")));
		btnRoom.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRoom.setToolTipText("");
		btnRoom.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRoom.setFocusPainted(false);
		btnRoom.setBounds(198, 5, 100, 58);
		frame.getContentPane().add(btnRoom);
		
		JButton btnRoomRent = new JButton("Thu\u00EA ph\u00F2ng");
		btnRoomRent.setIcon(new ImageIcon(HomeGUI.class.getResource("/img/room.png")));
		btnRoomRent.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRoomRent.setToolTipText("");
		btnRoomRent.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRoomRent.setFocusPainted(false);
		btnRoomRent.setBounds(295, 5, 100, 58);
		frame.getContentPane().add(btnRoomRent);
		DefaultTableModel model = new DefaultTableModel();
		JTable tblBill = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tblBill);
		scrollPane.setBounds(5, 115, 840, 213);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(tblBill);
		
	   
	    model.addColumn("MÃ£ phÃ²ng");
	    model.addColumn("Táº§ng");
	    model.addColumn("Tiá»�n Ä‘iá»‡n");
	    model.addColumn("Tiá»�n nÆ°á»›c");
	    model.addColumn("Tiá»�n Internet");
	    model.addColumn("Tiá»�n phÃ²ng");
	    model.addColumn("Tá»•ng cá»™ng");
	    model.addColumn("Tráº¡ng thÃ¡i");
	    model.addColumn("NgÆ°á»�i Ä‘Ã³ng");
	    model.addColumn("NgÃ y Ä‘Ã³ng");
		
		JLabel lblDanhSchPhiu = new JLabel("Danh s\u00E1ch phi\u1EBFu thu th\u00E1ng ");
		lblDanhSchPhiu.setBounds(10, 80, 163, 14);
		frame.getContentPane().add(lblDanhSchPhiu);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		JLabel lblMonth = new JLabel(Integer.toString(month));
		lblMonth.setBounds(187, 80, 44, 14);
		frame.getContentPane().add(lblMonth);
		
		JLabel label = new JLabel("/");
		label.setBounds(227, 80, 12, 14);
		frame.getContentPane().add(label);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		JLabel lblYear = new JLabel(Integer.toString(year));
		
		
		
		lblYear.setBounds(241, 80, 33, 14);
		frame.getContentPane().add(lblYear);
		
		JButton btnMotel = new JButton("Nh\u00E0 tr\u1ECD");
		btnMotel.setIcon(new ImageIcon(HomeGUI.class.getResource("/img/motel.png")));
		btnMotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMotel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMotel.setToolTipText("");
		btnMotel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMotel.setFocusPainted(false);
		btnMotel.setBounds(103, 5, 100, 58);
		frame.getContentPane().add(btnMotel);
		
		JButton buttonContract = new JButton("H\u1EE3p \u0111\u1ED3ng");
		buttonContract.setIcon(new ImageIcon(HomeGUI.class.getResource("/img/contract.png")));
		buttonContract.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonContract.setToolTipText("");
		buttonContract.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonContract.setFocusPainted(false);
		buttonContract.setBounds(392, 5, 100, 58);
		frame.getContentPane().add(buttonContract);
		
		JButton btnBill = new JButton("Phi\u1EBFu thu");
		btnBill.setIcon(new ImageIcon(HomeGUI.class.getResource("/img/bill.png")));
		btnBill.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBill.setToolTipText("");
		btnBill.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBill.setFocusPainted(false);
		btnBill.setBounds(490, 5, 100, 58);
		frame.getContentPane().add(btnBill);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
	}
}
