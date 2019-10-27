package com.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.Dao.RoomDao;
import com.Dao.RoomRentDao;
import com.Model.RoomModel;
import com.Model.RoomRentModel;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class RoomRentGUI {

	private JFrame frame;
	private JTable tblRentRoom;
	private JTable tblRoomFree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomRentGUI window = new RoomRentGUI();
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
	public RoomRentGUI() {
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
		
		JLabel lblDanhSchPhng = new JLabel("Danh sách phòng đang cho thuê");
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
			    String maPhong = (String) model1.getValueAt(index, 0);
			    EditRentGUI edit = new EditRentGUI(maPhong);
			    edit.main(new String[0]);
			}
		});
		JScrollPane scrollPane = new JScrollPane(tblBill);
		scrollPane.setBounds(10, 36, 840, 130);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(tblBill);
		
		JLabel lblDanhSchPhng_1 = new JLabel("Danh sách phòng trống");
		lblDanhSchPhng_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblDanhSchPhng_1.setBounds(10, 179, 212, 14);
		frame.getContentPane().add(lblDanhSchPhng_1);
		
	   
	    model.addColumn("Mã Phòng");
	    model.addColumn("Tên Phòng");
	    model.addColumn("Tầng");
	    model.addColumn("Người ở");
	    model.addColumn("Loại phòng");
	    model.addColumn("Trạng Thái");
	    
	    RoomRentDao dao = new RoomRentDao();
		try {
			List<RoomRentModel> result = new ArrayList<RoomRentModel>();
			result = dao.GetRoomRent();
			for (int i = 0; i < result.size(); i++){
			String maphong = result.get(i).getRoomCode();
			String tenPhong = result.get(i).getRoomName();
			int tang = result.get(i).getFloor();
			String trangThai = result.get(i).getStatus();
			String nguoiO = result.get(i).getFullName();
			String loaiPhong = result.get(i).getRoomType();
			Object[] data = {maphong, tenPhong, tang, nguoiO, loaiPhong,trangThai};
			model.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		DefaultTableModel modelFree = new DefaultTableModel();
		JTable tblRoomFree = new JTable(modelFree);
		JScrollPane scrollPane_1 = new JScrollPane(tblRoomFree);
		scrollPane_1.setBounds(10, 204, 840, 130);
		frame.getContentPane().add(scrollPane_1);
		
		scrollPane_1.setViewportView(tblRoomFree);
		
		JButton btnAddRent = new JButton("Thêm mới");
		btnAddRent.setIcon(new ImageIcon(RoomRentGUI.class.getResource("/img/add.png")));
		btnAddRent.setBounds(230, 8, 116, 23);
		frame.getContentPane().add(btnAddRent);
		btnAddRent.setFocusPainted(false);
		
		JButton btnAddContract = new JButton("Thêm HĐ");
		btnAddContract.setIcon(new ImageIcon(RoomRentGUI.class.getResource("/img/add.png")));
		btnAddContract.setFocusPainted(false);
		btnAddContract.setBounds(165, 176, 116, 23);
		frame.getContentPane().add(btnAddContract);
		
		modelFree.addColumn("Mã Phòng");
		modelFree.addColumn("Tên Phòng");
		modelFree.addColumn("Tầng");
		modelFree.addColumn("Trạng thái");
		modelFree.addColumn("Số người tối đa");
		modelFree.addColumn("Giá Phòng");
		modelFree.addColumn("Đơn giá điện");
		modelFree.addColumn("Đơn giá nước");
		modelFree.addColumn("Internet");
		dao = new RoomRentDao();
		try {
			List<RoomRentModel> result = new ArrayList<RoomRentModel>();
			result = dao.GetRoomFree();
			for (int i = 0; i < result.size(); i++){
			String maphong = result.get(i).getRoomCode();
			String tenPhong = result.get(i).getRoomName();
			int tang = result.get(i).getFloor();
			String trangThai = result.get(i).getStatus();
			int toiDa = result.get(i).getLimitPerson();
			float giaPhong = result.get(i).getBasicPrice();
			float giaDien = result.get(i).getElectricPrice();
			float giaNuoc = result.get(i).getWaterPrice();
			float giaInternet = result.get(i).getInternetPrice();
			Object[] data = {maphong, tenPhong, tang, trangThai, toiDa,giaPhong, giaDien, giaNuoc, giaInternet};
			modelFree.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	    
	}
}
