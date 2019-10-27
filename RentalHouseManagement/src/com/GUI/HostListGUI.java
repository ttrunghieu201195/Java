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
import com.Model.LandlordModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class HostListGUI {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new HostListGUI();
					HostListGUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HostListGUI() {
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

		JLabel lblDanhSchPhng = new JLabel("Danh sách chủ nhà trọ");
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
				HostGUI edit;
				try {
					HostListGUI.frame.setVisible(false);
					edit = new HostGUI(code);
					edit.main(new String[0]);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		JScrollPane scrollPane = new JScrollPane(tblBill);
		scrollPane.setBounds(10, 36, 840, 326);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(tblBill);

		model.addColumn("Mã chủ nhà trọ");
		model.addColumn("Tên chủ nhà trọ");
		model.addColumn("CMND");
		model.addColumn("Giới tính");
		model.addColumn("Trạng Thái");

		LandlordDao dao = new LandlordDao();
		try {
			List<LandlordModel> result = new ArrayList<LandlordModel>();
			result = dao.GetAll();
			for (int i = 0; i < result.size(); i++) {
				String code = result.get(i).getCode();
				String name = result.get(i).getFullName();
				String idCardNo = result.get(i).getIdCardNo();
				int gender = result.get(i).getGender();
				String genderString = "";
				switch (gender) {
				case 1:
					genderString = "Nam";
					break;
				case 0:
					genderString = "Nữ";
					break;
				default:
					break;
				}
				String status = result.get(i).getStatus();
				Object[] data = { code, name, idCardNo, genderString, status };
				model.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JButton btnAddRent = new JButton("Thêm mới");
		btnAddRent.setIcon(new ImageIcon(HostListGUI.class.getResource("/img/add.png")));
		btnAddRent.setBounds(230, 8, 116, 23);
		btnAddRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HostGUI add;
				try {
					HostListGUI.frame.setVisible(false);
					add = new HostGUI("");
					add.main(new String[0]);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		frame.getContentPane().add(btnAddRent);
		btnAddRent.setFocusPainted(false);
	}
}
