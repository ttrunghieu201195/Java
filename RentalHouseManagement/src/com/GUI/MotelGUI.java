package com.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.Biz.HostValidate;
import com.Biz.MotelRoomValidate;
import com.Dao.LandlordDao;
import com.Dao.MotelRoomDao;
import com.Model.LandlordModel;
import com.Model.MotelRoomModel;

public class MotelGUI {

	private static JFrame Hostframe;
	private String code = "";
	private MotelRoomModel model;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtNumOfFloor;
	private JTextField txtAddress;
	private JTextField txtHostId;
	private JComboBox combobox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String validMess = "";
	private boolean isEdit = false;

	MotelRoomDao dao = new MotelRoomDao();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MotelGUI(code);
					MotelGUI.Hostframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public MotelGUI(String code) throws SQLException {
		this.code = code;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		String title = "";
		String modeBtn = "";
		if (code.equals("")) {
			title = "Thêm mới nhà trọ";
			modeBtn = "Thêm";
		} else {
			title = "Chỉnh sửa chủ nhà trọ";
			modeBtn = "Cập nhật";
			isEdit = true;
		}

		Hostframe = new JFrame();
		Hostframe.setTitle(title);
		Hostframe.setBounds(100, 100, 450, 463);
		Hostframe.setLocationRelativeTo(null);
		Hostframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Hostframe.getContentPane().setLayout(null);

		JLabel lblCode = new JLabel(title);
		lblCode.setBounds(0, 0, 434, 26);
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblName = new JLabel("Tên nhà trọ");
		lblName.setBounds(79, 68, 89, 14);

		JLabel lblNumOfFloor = new JLabel("Số tầng");
		lblNumOfFloor.setBounds(79, 103, 73, 14);

		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setBounds(79, 136, 73, 14);

		JLabel lblHostId = new JLabel("Mã chủ nhà");
		lblHostId.setBounds(79, 169, 73, 14);

		JLabel lblMChNh = new JLabel("Mã nhà trọ");
		lblMChNh.setBounds(79, 37, 89, 14);

		txtCode = new JTextField();
		txtCode.setBounds(177, 34, 150, 20);
		txtCode.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(178, 65, 150, 20);

		txtNumOfFloor = new JTextField();
		txtNumOfFloor.setColumns(10);
		txtNumOfFloor.setBounds(177, 100, 150, 20);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(177, 133, 150, 20);

//		txtHostId = new JTextField();
//		txtHostId.setColumns(10);
//		txtHostId.setBounds(178, 166, 150, 20);
		
		combobox = new JComboBox<>(dao.getAllHostId().toArray());
		combobox.setBounds(178, 166, 150, 20);

		JButton btnAdd = new JButton(modeBtn);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validMess = "";
				model = new MotelRoomModel();
				model.setMotelCode(txtCode.getText());
				model.setMotelName(txtName.getText());
				model.setAddress(txtAddress.getText());
				if (txtNumOfFloor.getText().equals("")) {
					validMess += "Số tầng là bắt buộc\n";
				} else if (!MotelRoomValidate.isNumberic(txtNumOfFloor.getText())) {
					validMess += "Số tầng phải là số\n";
				} else {
					model.setNumOfFloor(Integer.valueOf(txtNumOfFloor.getText()));
				}
				model.setHostId(combobox.getSelectedItem().toString());
				try {
					validMess += MotelRoomValidate.validation(model, isEdit);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				if (!validMess.equals("")) {
					JOptionPane.showMessageDialog(null, validMess, "Lỗi", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						if (!isEdit) {
							if (dao.addNewMotelRoom(model)) {
								JOptionPane.showMessageDialog(null, "Dữ liệu đã thêm mới !", "Thành công",
										JOptionPane.INFORMATION_MESSAGE);
								MotelGUI.Hostframe.setVisible(false);
								MotelListGUI.main(new String[0]);
							}
						} else {
							if (dao.editMotelRoom(model)) {
								JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật !", "Thành công",
										JOptionPane.INFORMATION_MESSAGE);
								MotelGUI.Hostframe.setVisible(false);
								MotelListGUI.main(new String[0]);
							}
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnAdd.setBounds(156, 390, 115, 23);

		JButton btnBack = new JButton("Quay lại");
		btnBack.setBounds(281, 390, 115, 23);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MotelGUI.Hostframe.setVisible(false);
				MotelListGUI.main(new String[0]);
			}});

		JButton btnDelete = new JButton("Xoá");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Tobe implemeting.\nComing soon", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnDelete.setBounds(29, 390, 115, 23);

		if (isEdit) {
			txtCode.setEnabled(false);
			txtAddress.setEnabled(false);

			model = dao.getMotelRoomByCode(code);
			txtCode.setText(model.getMotelCode());
			txtName.setText(model.getMotelName());
			txtNumOfFloor.setText(String.valueOf(model.getNumOfFloor()));
			txtAddress.setText(model.getAddress());
			combobox.setSelectedItem(model.getHostId());

			Hostframe.getContentPane().add(btnDelete);
		}

		Hostframe.getContentPane().add(lblCode);
		Hostframe.getContentPane().add(lblName);
		Hostframe.getContentPane().add(lblNumOfFloor);
		Hostframe.getContentPane().add(lblAddress);
		Hostframe.getContentPane().add(lblHostId);
		Hostframe.getContentPane().add(lblMChNh);
		Hostframe.getContentPane().add(txtCode);
		Hostframe.getContentPane().add(txtName);
		Hostframe.getContentPane().add(txtNumOfFloor);
		Hostframe.getContentPane().add(txtAddress);
		Hostframe.getContentPane().add(combobox);
		Hostframe.getContentPane().add(btnAdd);
		Hostframe.getContentPane().add(btnBack);
	}
}
