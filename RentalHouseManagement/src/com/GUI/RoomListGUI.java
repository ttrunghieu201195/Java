package com.GUI;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.Dao.HomeDao;
import com.Dao.RoomDao;
import com.Model.LandlordModel;
import com.Model.RoomModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class RoomListGUI {

	private JFrame frame;
	private JTable table;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField txtRoomCode;
	private JLabel lblNewLabel_1;
	private JTextField txtRoomName;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtLimitPerson;
	private JButton btnSearch;
	private JButton btnAddRoom;
	private JLabel lblNewLabel_4;
	private JComboBox cbStatus;
	private JComboBox cbRoomType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomListGUI window = new RoomListGUI();
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
	public RoomListGUI() {
		RoomModel searchModel = new RoomModel();
		initialize(searchModel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(RoomModel searchModel) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblQunLPhng = new JLabel("Qu\u1EA3n L\u00FD Ph\u00F2ng Tr\u1ECD");
		frame.getContentPane().add(lblQunLPhng, BorderLayout.NORTH);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
			    TableModel modelEdit = table.getModel();
			    String maPhong = (String) modelEdit.getValueAt(index, 0);
			    RoomGUI edit = new RoomGUI(maPhong);
			    edit.main(new String[0]);
			}
		});
		model.addColumn("Mã Phòng");
		model.addColumn("Tên Phòng");
		model.addColumn("Diện Tích");
		model.addColumn("Số Tầng");
		model.addColumn("Trạng Thái");
		model.addColumn("Loại Phòng");
		model.addColumn("Sức Chứa");
		
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		lblNewLabel = new JLabel("Mã Phòng");
		panel.add(lblNewLabel);
		
		if(searchModel.getRoomCode() != null) {
			txtRoomCode = new JTextField(searchModel.getRoomCode());
		}
		else {
			txtRoomCode = new JTextField();
		}

		panel.add(txtRoomCode);
		txtRoomCode.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Tên Phòng");
		panel.add(lblNewLabel_1);
		
		if(searchModel.getRoomName() != null) {
			txtRoomName = new JTextField(searchModel.getRoomName());
		}
		else {
			txtRoomName = new JTextField();
		}
		
		panel.add(txtRoomName);
		txtRoomName.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Loại Phòng");
		panel.add(lblNewLabel_2);
		
		cbRoomType = new JComboBox();
		cbRoomType.addItem("All");
		cbRoomType.addItem("Phòng thường");
		cbRoomType.addItem("Phòng VIP");
		
		if(searchModel.getRoomType() != null && searchModel.getRoomType() != "" ) {
			String text = "";
			switch (searchModel.getRoomType()) {
			case "NORMALROOM":
				text = "Phòng thường";
				break;
			case "VIPROOM":
				text = "Phòng VIP";
				break;
			default:
				text = "All";
				break;
			}
			
			cbRoomType.setSelectedItem(text);
		}
		
		
		panel.add(cbRoomType);
		
		lblNewLabel_3 = new JLabel("Số Người Tối Đa");
		panel.add(lblNewLabel_3);
		
		if(searchModel.getLimitPerson() == 0 ) {
			txtLimitPerson = new JTextField();
		}
		else {
			txtLimitPerson = new JTextField(String.valueOf(searchModel.getLimitPerson()));
		}
		
		panel.add(txtLimitPerson);
		txtLimitPerson.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Trạng Thái");
		panel.add(lblNewLabel_4);
		
		cbStatus = new JComboBox();
		
		cbStatus.addItem("All");
		cbStatus.addItem("Sẵn sàng");
		cbStatus.addItem("Đang sửa chữa");
		cbStatus.addItem("Đang thuê");
		cbStatus.addItem("Đã đặt cọc");
		
		if(searchModel.getStatus() != null && searchModel.getStatus() != "" ) {
			String text = "";
			switch (searchModel.getStatus()) {
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
				text = "All";
				break;
			}
			
			cbStatus.setSelectedItem(text);
		}
		
		panel.add(cbStatus);
		
		btnSearch = new JButton("Tìm Kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomModel modelSearch = new RoomModel();
				
				if(!txtRoomCode.getText().equals("")) {
					modelSearch.setCode(txtRoomCode.getText());
				}
				
				if(!txtRoomName.getText().equals("")) {
					modelSearch.setRoomName(txtRoomName.getText());
				}
				
				String statusSearch = (String) cbStatus.getSelectedItem();
				switch (statusSearch) {
				case "Sẵn sàng":
					modelSearch.setStatus("READY");
					break;
				case "Đang sửa chữa":
					modelSearch.setStatus("REPAIR");
					break;
				case "Đang thuê":
					modelSearch.setStatus("HAVERENT");
					break;
				case "Đã đặt cọc":
					modelSearch.setStatus("DESPOSITED");
					break;
				default:
					modelSearch.setStatus("All");
				}
				
				String roomTypeSearch = (String) cbRoomType.getSelectedItem();
				switch (roomTypeSearch) {
				case "Phòng thường":
					modelSearch.setRoomType("NORMALROOM");
					break;
				case "Phòng VIP":
					modelSearch.setRoomType("VIPROOM");
					break;
				default:
					modelSearch.setRoomType("All");
					break;
				}
				
				if(!txtLimitPerson.getText().equals("")) {
					modelSearch.setLimitPerson(Integer.parseInt(txtLimitPerson.getText()));
				}
				
			    DefaultTableModel model = (DefaultTableModel)table.getModel();
			    model.setRowCount(0);
			    table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
					    TableModel modelEdit = table.getModel();
					    String maPhong = (String) modelEdit.getValueAt(index, 0);
					    RoomGUI edit = new RoomGUI(maPhong);
					    edit.main(new String[0]);
					}
				});
				
				RoomDao dao = new RoomDao();
				
				
				try {
					List<RoomModel> result = new ArrayList<RoomModel>();
					
					result = dao.GetAll(modelSearch);
					
					for (int i = 0; i < result.size(); i++){
					String roomCode = result.get(i).getRoomCode();
					String roomName = result.get(i).getRoomName();
					float area = result.get(i).getArea();
					int floor = result.get(i).getFloor();
					String status = result.get(i).getStatus();
					switch (status) {
					case "READY":
						status = "Sẵn sàng";
						break;
					case "REPAIR":
						status = "Đang sửa chữa";
						break;
					case "HAVERENT":
						status = "Đang thuê";
						break;
					case "DESPOSITED":
						status = "Đã đặt cọc";
						break;
					default:
						break;
					}
					String roomType = result.get(i).getRoomType();
					switch (roomType) {
					case "NORMALROOM":
						roomType = "Phòng thường";
						break;
					case "VIPROOM":
						roomType = "Phòng VIP";
						break;
					default:
						roomType = "All";
					}
					int limitPerson = result.get(i).getLimitPerson();
					Object[] data = {roomCode, roomName, area, floor, status,roomType,limitPerson};
					model.addRow(data);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnSearch);
		
		btnAddRoom = new JButton("Thêm Phòng");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					RoomGUI add = new RoomGUI("");
					add.main(new String[0]);
				
			}
		});
		panel.add(btnAddRoom);
		
		RoomDao dao = new RoomDao();
		
		
		try {
			List<RoomModel> result = new ArrayList<RoomModel>();
			
			result = dao.GetAll(searchModel);
			
			for (int i = 0; i < result.size(); i++){
			String roomCode = result.get(i).getRoomCode();
			String roomName = result.get(i).getRoomName();
			float area = result.get(i).getArea();
			int floor = result.get(i).getFloor();
			String status = result.get(i).getStatus();
			switch (status) {
			case "READY":
				status = "Sẵn sàng";
				break;
			case "REPAIR":
				status = "Đang sửa chữa";
				break;
			case "HAVERENT":
				status = "Đang thuê";
				break;
			case "DESPOSITED":
				status = "Đã đặt cọc";
				break;
			default:
				break;
			}
			String roomType = result.get(i).getRoomType();
			switch (roomType) {
			case "NORMALROOM":
				roomType = "Phòng thường";
				break;
			case "VIPROOM":
				roomType = "Phòng VIP";
				break;
			default:
				roomType = "All";
			}
			int limitPerson = result.get(i).getLimitPerson();
			Object[] data = {roomCode, roomName, area, floor, status,roomType,limitPerson};
			model.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	   
		JScrollPane scrollPane= new  JScrollPane(table);
		scrollPane.setBounds(5, 115, 840, 213);
		frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
	}
	
	

}
