package com.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.Biz.HostValidate;
import com.Dao.LandlordDao;
import com.Model.LandlordModel;

public class HostGUI {

	private static JFrame Hostframe;
	private static String code = "";
	private static LandlordModel model;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtIdCard;
	private JTextField txtBirthday;
	private JTextField txtFacebook;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String validMess = "";
	private boolean isEdit = false;

	LandlordDao dao = new LandlordDao();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new HostGUI(code);
					HostGUI.Hostframe.setVisible(true);
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
	public HostGUI(String code) throws SQLException {
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
			title = "Thêm mới chủ nhà trọ";
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

		JLabel lblName = new JLabel("Tên chủ nhà trọ");
		lblName.setBounds(79, 68, 89, 14);

		JLabel lblPhone = new JLabel("Số điện thoại");
		lblPhone.setBounds(79, 103, 73, 14);

		JLabel lblIdCard = new JLabel("CMND");
		lblIdCard.setBounds(79, 136, 73, 14);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(79, 240, 73, 14);

		JLabel lblBirthday = new JLabel("Ngày sinh");
		lblBirthday.setBounds(79, 167, 73, 14);

		JLabel lblFacebook = new JLabel("Facebook");
		lblFacebook.setBounds(79, 205, 73, 14);

		JLabel lblGender = new JLabel("Giới tính");
		lblGender.setBounds(79, 273, 73, 14);

		JLabel lblStatus = new JLabel("Trạng thái");
		lblStatus.setBounds(79, 308, 73, 14);

		JLabel lblMChNh = new JLabel("Mã chủ nhà trọ");
		lblMChNh.setBounds(79, 37, 89, 14);

		txtCode = new JTextField();
		txtCode.setBounds(177, 34, 150, 20);
		txtCode.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(178, 65, 150, 20);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(177, 100, 150, 20);

		txtIdCard = new JTextField();
		txtIdCard.setColumns(10);
		txtIdCard.setBounds(177, 133, 150, 20);

		txtBirthday = new JTextField();
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(177, 164, 150, 20);

		txtFacebook = new JTextField();
		txtFacebook.setColumns(10);
		txtFacebook.setBounds(177, 202, 150, 20);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(178, 237, 150, 20);

		JRadioButton rdbtnMale = new JRadioButton("Nam");
		buttonGroup_1.add(rdbtnMale);
		rdbtnMale.setBounds(178, 269, 54, 23);
		rdbtnMale.setSelected(true);

		JRadioButton rdbtnFemale = new JRadioButton("Nữ");
		buttonGroup_1.add(rdbtnFemale);
		rdbtnFemale.setBounds(263, 269, 82, 23);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(79, 338, 73, 14);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(179, 333, 148, 46);

		JRadioButton rdbtnActive = new JRadioButton("Kích hoạt");
		buttonGroup_2.add(rdbtnActive);
		rdbtnActive.setBounds(179, 304, 83, 23);
		rdbtnActive.setSelected(true);

		JRadioButton rdbtnInactive = new JRadioButton("Vô hiệu");
		buttonGroup_2.add(rdbtnInactive);
		rdbtnInactive.setBounds(264, 304, 109, 23);

		JButton btnAdd = new JButton(modeBtn);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new LandlordModel();
				model.setCode(txtCode.getText());
				model.setFullName(txtName.getText());
				model.setIdCardNo(txtIdCard.getText());
				model.setAddress(textArea.getText());
				model.setPhoneNumber(txtPhone.getText());
				model.setEmail(txtEmail.getText());
				model.setFacebook(txtFacebook.getText());
				model.setDateOfBirth(txtBirthday.getText());
				if (rdbtnActive.isSelected()) {
					model.setStatus("ACTIVE");
				} else if (rdbtnInactive.isSelected()) {
					model.setStatus("INACTIVE");
				}

				if (rdbtnMale.isSelected()) {
					model.setGender(1);
				} else if (rdbtnFemale.isSelected()) {
					model.setGender(0);
				}
				try {
					validMess = HostValidate.validation(model, isEdit);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if (!validMess.equals("")) {
					JOptionPane.showMessageDialog(null, validMess, "Lỗi", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						if (!isEdit) {
							if (dao.Add(model)) {
								JOptionPane.showMessageDialog(null, "Dữ liệu đã thêm mới !", "Thành công",
										JOptionPane.INFORMATION_MESSAGE);
								HostGUI.Hostframe.setVisible(false);
								HostListGUI.main(new String[0]);
							}
						} else {
							if (dao.Edit(model)) {
								JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật !", "Thành công",
										JOptionPane.INFORMATION_MESSAGE);
								HostGUI.Hostframe.setVisible(false);
								HostListGUI.main(new String[0]);
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
				HostGUI.Hostframe.setVisible(false);
				HostListGUI.main(new String[0]);
			}});

		JButton btnDelete = new JButton("Xoá");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có đồng ý xóa ?", "Cảnh báo", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					// check dữ liệu có được sử dụng
					try {
						if (dao.IsUsedHost(code)) {
							JOptionPane.showMessageDialog(null, "Dữ liệu đã được sử dụng không thể xóa", "Lỗi",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							if (dao.Delete(code)) {
								HostListGUI.main(new String[0]);
								HostGUI.Hostframe.setVisible(false);
								HostListGUI.main(new String[0]);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBounds(29, 390, 115, 23);

		if (isEdit) {
			txtCode.setEnabled(false);
			txtIdCard.setEnabled(false);

			model = new LandlordModel();
			model = dao.GetByCode(code);
			txtCode.setText(model.getCode());
			txtName.setText(model.getFullName());
			txtPhone.setText(model.getPhoneNumber());
			txtIdCard.setText(model.getIdCardNo());
			txtEmail.setText(model.getEmail());
			txtBirthday.setText(model.getDateOfBirth());
			txtFacebook.setText(model.getFacebook());
			if (model.getGender() == 1) {
				rdbtnMale.setSelected(true);
			} else
				rdbtnFemale.setSelected(true);

			if (model.getStatus().equalsIgnoreCase("active")) {
				rdbtnActive.setSelected(true);
			} else
				rdbtnInactive.setSelected(true);

			textArea.setText(model.getAddress());
			Hostframe.getContentPane().add(btnDelete);
		}

		Hostframe.getContentPane().add(lblCode);
		Hostframe.getContentPane().add(lblName);
		Hostframe.getContentPane().add(lblPhone);
		Hostframe.getContentPane().add(lblIdCard);
		Hostframe.getContentPane().add(lblEmail);
		Hostframe.getContentPane().add(lblBirthday);
		Hostframe.getContentPane().add(lblFacebook);
		Hostframe.getContentPane().add(lblGender);
		Hostframe.getContentPane().add(lblStatus);
		Hostframe.getContentPane().add(lblMChNh);
		Hostframe.getContentPane().add(txtCode);
		Hostframe.getContentPane().add(txtName);
		Hostframe.getContentPane().add(txtPhone);
		Hostframe.getContentPane().add(txtIdCard);
		Hostframe.getContentPane().add(txtBirthday);
		Hostframe.getContentPane().add(txtFacebook);
		Hostframe.getContentPane().add(txtEmail);
		Hostframe.getContentPane().add(rdbtnMale);
		Hostframe.getContentPane().add(rdbtnFemale);
		Hostframe.getContentPane().add(lblaCh);
		Hostframe.getContentPane().add(textArea);
		Hostframe.getContentPane().add(rdbtnActive);
		Hostframe.getContentPane().add(rdbtnInactive);
		Hostframe.getContentPane().add(btnAdd);
		Hostframe.getContentPane().add(btnBack);
	}
}
