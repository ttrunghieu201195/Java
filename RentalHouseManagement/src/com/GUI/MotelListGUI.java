package com.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.Dao.LandlordDao;
import com.Dao.MotelRoomDao;
import com.Dao.RoomDao;
import com.Model.LandlordModel;
import com.Model.MotelRoomModel;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class MotelListGUI {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MotelListGUI();
					MotelListGUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MotelListGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 871, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDanhSchPhng = new JLabel("Danh sách nhà trọ");
		lblDanhSchPhng.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblDanhSchPhng.setBounds(10, 11, 212, 14);
		frame.getContentPane().add(lblDanhSchPhng);

		DefaultTableModel model = new DefaultTableModel();
		JTable tblBill = new JTable(model);
		tblBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblBill.getSelectedRow();
				TableModel model1 = tblBill.getModel();
				String code = (String) model1.getValueAt(index, 0);
				MotelGUI edit;
				try {
					MotelListGUI.frame.setVisible(false);
					edit = new MotelGUI(code);
					edit.main(new String[0]);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		JScrollPane scrollPane = new JScrollPane(tblBill);
		scrollPane.setBounds(10, 36, 840, 326);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(tblBill);

		model.addColumn("Mã nhà trọ");
		model.addColumn("Tên nhà trọ");
		model.addColumn("Số tầng");
		model.addColumn("Địa chỉ");
		model.addColumn("Mã chủ nhà trọ");

		MotelRoomDao dao = new MotelRoomDao();
		try {
			List<MotelRoomModel> result = dao.getAll();
			for (int i = 0; i < result.size(); i++) {
				String code = result.get(i).getMotelCode();
				String name = result.get(i).getMotelName();
				int numOfFloor = result.get(i).getNumOfFloor();
				String address = result.get(i).getAddress();
				String hostId = result.get(i).getHostId();
				Object[] data = { code, name, numOfFloor, address, hostId };
				model.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JButton btnAddRent = new JButton("Thêm mới");
		btnAddRent.setIcon(new ImageIcon(MotelListGUI.class.getResource("/img/add.png")));
		btnAddRent.setBounds(230, 8, 116, 23);
		btnAddRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MotelGUI add;
				try {
					MotelListGUI.frame.setVisible(false);
					add = new MotelGUI("");
					add.main(new String[0]);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		frame.getContentPane().add(btnAddRent);
		btnAddRent.setFocusPainted(false);
	}
}