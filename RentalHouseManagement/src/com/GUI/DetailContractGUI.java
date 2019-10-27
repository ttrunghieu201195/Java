package com.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.Dao.CommonDao;
import com.Dao.ContractDao;
import com.Dao.RoomDao;
import com.Dao.RoomRentDao;
import com.Model.CommonModel;
import com.Model.ContractModel;
import com.Model.RoomModel;
import com.Model.RoomRentModel;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailContractGUI {

	private JFrame frameContract;
	private JTextField txtContractID;
	private JTextField txtDeposit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailContractGUI window = new DetailContractGUI();
					window.frameContract.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public DetailContractGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize() {
		frameContract = new JFrame();
		frameContract.setBounds(100, 100, 649, 300);
		frameContract.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameContract.getContentPane().setLayout(null);
		
		JLabel lblThmHpng = new JLabel("TH\u00CAM H\u1EE2P \u0110\u1ED2NG");
		lblThmHpng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThmHpng.setBounds(261, 11, 112, 22);
		frameContract.getContentPane().add(lblThmHpng);
		
		JLabel lblSHpng = new JLabel("S\u1ED1 h\u1EE3p \u0111\u1ED3ng");
		lblSHpng.setBounds(10, 47, 77, 14);
		frameContract.getContentPane().add(lblSHpng);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(87, 47, 14, 14);
		frameContract.getContentPane().add(label);
		
		txtContractID = new JTextField();
		txtContractID.setBounds(142, 44, 152, 20);
		frameContract.getContentPane().add(txtContractID);
		txtContractID.setColumns(10);
		
		JLabel lblPhng = new JLabel("Ph\u00F2ng");
		lblPhng.setBounds(304, 50, 39, 14);
		frameContract.getContentPane().add(lblPhng);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(340, 50, 14, 14);
		frameContract.getContentPane().add(label_2);
		
		JComboBox cbbHost = new JComboBox();
		cbbHost.setBounds(142, 75, 152, 20);
		frameContract.getContentPane().add(cbbHost);
		ContractDao cDao= new ContractDao();
		try {
			List<ContractModel> result = new ArrayList<ContractModel>();
			result = cDao.getHost();
			for (int i = 0; i < result.size(); i++){
			String ma = result.get(i).getHostCode();	
			String ten = result.get(i).getHostName();
			cbbHost.addItem(ma + "-" + ten );
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		
		JComboBox cbbRoom = new JComboBox();
		cbbRoom.setBounds(433, 44, 152, 20);
		frameContract.getContentPane().add(cbbRoom);
		RoomRentDao dao = new RoomRentDao();
		try {
			List<RoomRentModel> result = new ArrayList<RoomRentModel>();
			result = dao.GetRoomFree();
			for (int i = 0; i < result.size(); i++){
			String maPhong = result.get(i).getRoomCode();	
			String tenPhong = result.get(i).getRoomName();
			cbbRoom.addItem(maPhong + "-"+ tenPhong);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		
		JLabel lblNgiThu = new JLabel("B\u00EAn A (cho thu\u00EA)");
		lblNgiThu.setBounds(10, 78, 108, 14);
		frameContract.getContentPane().add(lblNgiThu);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(111, 78, 14, 14);
		frameContract.getContentPane().add(label_1);
		
		JLabel lblBnBngi = new JLabel("B\u00EAn B (ng\u01B0\u1EDDi thu\u00EA)");
		lblBnBngi.setBounds(304, 81, 109, 14);
		frameContract.getContentPane().add(lblBnBngi);
		
		JComboBox cbbCustomer = new JComboBox();
		cbbCustomer.setBounds(433, 75, 152, 20);
		frameContract.getContentPane().add(cbbCustomer);
		try {
			List<ContractModel> result = new ArrayList<ContractModel>();
			result = cDao.getCustomerFree();
			for (int i = 0; i < result.size(); i++){
			String ma = result.get(i).getCustomerCode();	
			String ten = result.get(i).getCustomerName();
			cbbCustomer.addItem(ma + "-" + ten);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(409, 78, 14, 14);
		frameContract.getContentPane().add(label_3);
		
		JLabel lblHnhThcThanh = new JLabel("H\u00ECnh th\u1EE9c thanh to\u00E1n");
		lblHnhThcThanh.setBounds(10, 112, 122, 14);
		frameContract.getContentPane().add(lblHnhThcThanh);
		
		JComboBox cbbPayment = new JComboBox();
		cbbPayment.setBounds(142, 109, 152, 20);
		frameContract.getContentPane().add(cbbPayment);
		CommonDao common = new CommonDao();
		try {
			List<CommonModel> result = new ArrayList<CommonModel>();
			result = common.GetSys("PAYMENT");
			for (int i = 0; i < result.size(); i++){
			String ma = result.get(i).getCode();	
			String ten = result.get(i).getName();
			cbbPayment.addItem(ma + "-" + ten);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		
		JLabel lblTintCc = new JLabel("Ti\u1EC1n \u0111\u1EB7t c\u1ECDc");
		lblTintCc.setBounds(304, 112, 80, 14);
		frameContract.getContentPane().add(lblTintCc);
		
		JLabel lblInvalid = new JLabel("");
		lblInvalid.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblInvalid.setForeground(Color.RED);
		lblInvalid.setBounds(433, 129, 100, 14);
		frameContract.getContentPane().add(lblInvalid);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(385, 112, 14, 14);
		frameContract.getContentPane().add(label_4);
		
		txtDeposit = new JTextField();
		txtDeposit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					int i = Integer.parseInt(txtDeposit.getText());
					lblInvalid.setText("");
				}catch (NumberFormatException e1) {
					lblInvalid.setText("Vui lòng nhập số");
				}
			}
		});
		txtDeposit.setColumns(10);
		txtDeposit.setBounds(433, 106, 152, 20);
		frameContract.getContentPane().add(txtDeposit);
		
		JLabel lblCHiuLc = new JLabel("C\u00F3 hi\u1EC7u l\u1EF1c T\u1EEB ng\u00E0y");
		lblCHiuLc.setBounds(10, 150, 115, 14);
		frameContract.getContentPane().add(lblCHiuLc);
		
		JDateChooser dtpFromDate = new JDateChooser();
		dtpFromDate.setBounds(142, 144, 152, 20);
		frameContract.getContentPane().add(dtpFromDate);
		dtpFromDate.setDateFormatString("dd-MM-YYYY");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(129, 150, 14, 14);
		frameContract.getContentPane().add(label_5);
		
		JLabel lblnNgy = new JLabel("\u0110\u1EBFn ng\u00E0y");
		lblnNgy.setBounds(304, 150, 63, 14);
		frameContract.getContentPane().add(lblnNgy);
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(363, 150, 14, 14);
		frameContract.getContentPane().add(label_6);
		
		JDateChooser dtpToDate = new JDateChooser();
		dtpToDate.setBounds(433, 144, 152, 20);
		frameContract.getContentPane().add(dtpToDate);
		dtpToDate.setDateFormatString("dd-MM-YYYY");
		
		JButton btnDetailRoom = new JButton("");
		btnDetailRoom.setToolTipText("Xem chi ti\u1EBFt ph\u00F2ng");
		btnDetailRoom.setIcon(new ImageIcon(DetailContractGUI.class.getResource("/img/detail.png")));
		btnDetailRoom.setBounds(590, 44, 33, 20);
		frameContract.getContentPane().add(btnDetailRoom);
		btnDetailRoom.setFocusPainted(false);
		
		JButton btnLu = new JButton("L\u01B0u");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContractModel model = new ContractModel();
				
				model.setContractID(txtContractID.getText());
				
				String[] getRoomCode = cbbRoom.getSelectedItem().toString().split("-", -2);
				model.setRoomCode(getRoomCode[0]);
				
				String[] getHostCode = cbbHost.getSelectedItem().toString().split("-", -2);
				model.setHostCode(getHostCode[0]);
				
				String[] getCustomerCode = cbbCustomer.getSelectedItem().toString().split("-", -2);
				model.setCustomerCode(getCustomerCode[0]);
				
				String[] getPaymentCode = cbbPayment.getSelectedItem().toString().split("-", -2);
				model.setPayment(getPaymentCode[0]);
				
				model.setDisposit(Float.parseFloat(txtDeposit.getText()));
				model.setFromDate(sdf.format(dtpFromDate.getDate()));
				model.setToDate(sdf.format(dtpToDate.getDate()));

				ContractDao dao = new ContractDao();
				try {
					int result = dao.Add(model);
					if (result == 0)
					{
						JOptionPane optionPane = new JOptionPane("Lưu thành công",JOptionPane.INFORMATION_MESSAGE);
						JDialog dialog = optionPane.createDialog("Thông báo");
						dialog.setAlwaysOnTop(true); // to show top of all other application
						dialog.setVisible(true);
					}
					else if (result == 1)
					{
						JOptionPane optionPane = new JOptionPane("Mã hợp đồng trùng, vui lòng nhập lại",JOptionPane.ERROR_MESSAGE);
						JDialog dialog = optionPane.createDialog("Lỗi");
						dialog.setAlwaysOnTop(true); // to show top of all other application
						dialog.setVisible(true);
					}
						
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLu.setBounds(534, 213, 89, 23);
		frameContract.getContentPane().add(btnLu);
		
		
	}
}
