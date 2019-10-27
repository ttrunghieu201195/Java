package com.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.Dao.LandlordDao;
import com.Dao.RoomDao;
import com.Model.LandlordModel;
import com.Model.RoomModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RoomGUI {

	private JFrame Roomframe;
	private JTextField txtMaPhong;
	private JTextField txtTenPhong;
	private JTextField txtDienTich;
	private JTextField txtSoTang;
	private JTextField txtLimitPerson;
	private static String roomCode="";
	private static RoomModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomGUI window = new RoomGUI(roomCode);
					window.Roomframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoomGUI(String roomCode) {
		this.roomCode = roomCode;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		RoomDao dao = new RoomDao();
		model = new RoomModel();
		try {
			model = dao.GetRoomByCode(this.roomCode);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			model = new RoomModel();
		}
		Roomframe = new JFrame();
		Roomframe.setBounds(100, 100, 450, 373);
		Roomframe.setLocationRelativeTo(null);
		Roomframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Roomframe.getContentPane().setLayout(null);
		
		JLabel lblQunLPhng = new JLabel("Th\u00EAm Ph\u00F2ng Tr\u1ECD");
		lblQunLPhng.setBounds(0, 0, 434, 14);
		lblQunLPhng.setHorizontalAlignment(SwingConstants.CENTER);
		Roomframe.getContentPane().add(lblQunLPhng);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn Ph\u00F2ng");
		lblNewLabel.setBounds(79, 56, 73, 14);
		Roomframe.getContentPane().add(lblNewLabel);
		
		JLabel lblTrngThi = new JLabel("Tr\u1EA1ng Th\u00E1i");
		lblTrngThi.setBounds(79, 88, 73, 14);
		Roomframe.getContentPane().add(lblTrngThi);
		
		JLabel lblDinTch = new JLabel("Di\u1EC7n T\u00EDch");
		lblDinTch.setBounds(79, 118, 73, 14);
		Roomframe.getContentPane().add(lblDinTch);
		
		JLabel lblSTng = new JLabel("S\u1ED1 T\u1EA7ng");
		lblSTng.setBounds(79, 153, 73, 14);
		Roomframe.getContentPane().add(lblSTng);
		
		JLabel lblMPhng = new JLabel("M\u00E3 Ph\u00F2ng");
		lblMPhng.setBounds(79, 25, 73, 14);
		Roomframe.getContentPane().add(lblMPhng);
		
		if(model.getRoomCode() == null || model.getRoomCode() == "" ) {
			txtMaPhong = new JTextField();
		}
		else {
			txtMaPhong = new JTextField(model.getRoomCode());
			txtMaPhong.setEditable(false);
			
		}
		txtMaPhong.setBounds(162, 22, 138, 20);
		Roomframe.getContentPane().add(txtMaPhong);
		txtMaPhong.setColumns(10);
		
		if(model.getRoomName() == null || model.getRoomName() == "" ) {
			txtTenPhong = new JTextField();
		}
		else {
			txtTenPhong = new JTextField(model.getRoomName());
		}
		
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(162, 53, 138, 20);
		Roomframe.getContentPane().add(txtTenPhong);
		
		JComboBox cbTrangThai = new JComboBox();
		cbTrangThai.addItem("Sẵn sàng");
		cbTrangThai.addItem("Đang sửa chữa");
		cbTrangThai.addItem("Đang thuê");
		cbTrangThai.addItem("Đã đặt cọc");
		
		if(model.getStatus() != null && model.getStatus() != "" ) {
			String text = "";
			switch (model.getStatus()) {
			case "READY":
				text = "Sẵn sàng";
				break;
			case "REPAIR":
				text = "Đang sửa chữa";
				break;
			case "HAVERENT":
				text = "Đang thuê";
				break;
			case "DESPOSITED":
				text = "Đã đặt cọc";
				break;
			default:
				break;
			}
			
			cbTrangThai.setSelectedItem(text);
		}
		
		cbTrangThai.setBounds(162, 85, 138, 20);
		Roomframe.getContentPane().add(cbTrangThai);
		
		if(model.getArea() == 0 ) {
			txtDienTich = new JTextField();
		}
		else {
			txtDienTich = new JTextField(String.valueOf(model.getArea()));
		}
		txtDienTich.setColumns(10);
		txtDienTich.setBounds(162, 115, 138, 20);
		Roomframe.getContentPane().add(txtDienTich);
		
		if(model.getFloor() == 0 ) {
			txtSoTang = new JTextField();
		}
		else {
			txtSoTang = new JTextField(String.valueOf(model.getFloor()));
		}
		txtSoTang.setColumns(10);
		txtSoTang.setBounds(162, 150, 138, 20);
		Roomframe.getContentPane().add(txtSoTang);
		
		JLabel lblSNgiGii = new JLabel("Sô Lượng Người");
		lblSNgiGii.setBounds(79, 185, 81, 14);
		Roomframe.getContentPane().add(lblSNgiGii);
		
		if(model.getLimitPerson() == 0 ) {
			txtLimitPerson = new JTextField();
		}
		else {
			txtLimitPerson = new JTextField(String.valueOf(model.getLimitPerson()));
		}
		txtLimitPerson.setColumns(10);
		txtLimitPerson.setBounds(162, 181, 138, 20);
		Roomframe.getContentPane().add(txtLimitPerson);
		
		JLabel lblLoiPhng = new JLabel("Loại Phòng");
		lblLoiPhng.setBounds(79, 215, 81, 14);
		Roomframe.getContentPane().add(lblLoiPhng);
		
		JComboBox cbLoaiPhong = new JComboBox();
		cbLoaiPhong.addItem("Phòng thường");
		cbLoaiPhong.addItem("Phòng VIP");
		
		if(model.getRoomType() != null && model.getRoomType() != "" ) {
			String text = "";
			switch (model.getRoomType()) {
			case "NORMALROOM":
				text = "Phòng thường";
				break;
			case "VIPROOM":
				text = "Phòng VIP";
				break;
			default:
				break;
			}
			
			cbLoaiPhong.setSelectedItem(text);
		}
		
		cbLoaiPhong.setBounds(162, 212, 138, 20);
		Roomframe.getContentPane().add(cbLoaiPhong);
		
		JButton btnBack = new JButton("Quay L\u1EA1i");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomListGUI listView = new RoomListGUI();
				listView.main(new String[0]);
				Roomframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				
			}
		});
		btnBack.setBounds(309, 273, 115, 23);
		Roomframe.getContentPane().add(btnBack);
		
		JButton btnAddRoom = new JButton("Save");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new RoomModel();
				
				if(!txtMaPhong.getText().equals("")) {
					model.setCode(txtMaPhong.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Mã phòng bắt buộc !", "Phòng", JOptionPane.INFORMATION_MESSAGE); 
					return;
				}
				
				if(!txtTenPhong.getText().equals("")) {
					model.setRoomName(txtTenPhong.getText());
				}
				
				if(!txtDienTich.getText().equals("")) {
					model.setArea(Float.parseFloat(txtDienTich.getText()));
				}
				
				String status = (String) cbTrangThai.getSelectedItem();
				switch (status) {
				case "Sẵn sàng":
					model.setStatus("READY");
					break;
				case "Đang sửa chữa":
					model.setStatus("REPAIR");
					break;
				case "Đang thuê":
					model.setStatus("HAVERENT");
					break;
				case "Đã đặt cọc":
					model.setStatus("DESPOSITED");
					break;
				default:
					break;
				}
				
				String roomType = (String) cbLoaiPhong.getSelectedItem();
				
				switch (roomType) {
				case "Phòng thường":
					model.setRoomType("NORMALROOM");
					break;
				case "Phòng VIP":
					model.setRoomType("VIPROOM");
					break;
				default:
					break;
				}
				
				if(!txtSoTang.getText().equals("")) {
					model.setFloor(Integer.parseInt(txtSoTang.getText()));
				}
				
				if(!txtLimitPerson.getText().equals("")) {
					model.setLimitPerson(Integer.parseInt(txtLimitPerson.getText()));
				}
				
				
				RoomDao dao = new RoomDao();
				try {
					if(roomCode != "") {
						if(dao.Edit(model))
						{
							JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật !", "Phòng", JOptionPane.INFORMATION_MESSAGE);
							RoomListGUI listView = new RoomListGUI();
							listView.main(new String[0]);
							Roomframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
						}
					}
					else {
						if(dao.CheckExistByRoomCode(roomCode)) {
							JOptionPane.showMessageDialog(null, "Mã phòng đã tồn tại !", "Phòng", JOptionPane.INFORMATION_MESSAGE); 
						}
						else {
							if(dao.Add(model))
							{
								JOptionPane.showMessageDialog(null, "Dữ liệu đã được thêm mới !", "Phòng", JOptionPane.INFORMATION_MESSAGE); 
								RoomListGUI listView = new RoomListGUI();
								listView.main(new String[0]);
								Roomframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
							}
						}
						
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddRoom.setBounds(40, 273, 115, 23);
		Roomframe.getContentPane().add(btnAddRoom);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có đồng ý xóa ?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				  //check dữ liệu có được sử dụng
					RoomDao dao = new RoomDao();
					try {
						if(dao.CheckUsedByRoomCode(roomCode)) {
							JOptionPane.showMessageDialog(null, "Dữ liệu đã được sử dụng không thể xóa", "Phòng", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							if(dao.DeleteByRoomCode(roomCode)){
								RoomListGUI listView = new RoomListGUI();
								listView.main(new String[0]);
								Roomframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
								
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBounds(178, 273, 115, 23);
		Roomframe.getContentPane().add(btnDelete);
		
		
	}
}
