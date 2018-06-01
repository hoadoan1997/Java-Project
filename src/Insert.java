import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.CardLayout;

public class Insert extends JFrame{
	private JTextField ipSTT;
	private JTextField ipDiaChi;
	private JTextField ipChuSoHuu;
	private JTextField ipDienTich;
	private JTextField ipGiaTien;
	private String [] loaiNha = {"---------------------","Cấp 1","Cấp 2","Cấp 3","Cấp 4"};
	private String [] mucDich = {"---------------------","Nhà ở", "Kinh doanh"};
	public JButton btnInsert ;

	private JComboBox<String> cbLoaiNha ;
	private JComboBox<String> cbMucDich ;
	public ThuaDat thuaDatInsert = new ThuaDat();
	
	public Insert() {
		this.initialize();
	}
	//Hàm khởi tạo
	private void initialize() {
		getContentPane().setLayout(null);
		
		JLabel lSTT = new JLabel("STT");
		lSTT.setBounds(12, 42, 56, 16);
		getContentPane().add(lSTT);
		
		ipSTT = new JTextField();
		ipSTT.setBounds(89, 39, 116, 22);
		getContentPane().add(ipSTT);
		ipSTT.setColumns(10);
		ipSTT.setEditable(false);
		
		JLabel lblThmThat = new JLabel("THÊM THỬA ĐẤT");
		lblThmThat.setForeground(Color.BLUE);
		lblThmThat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThmThat.setBounds(143, 8, 156, 30);
		getContentPane().add(lblThmThat);
		
		JLabel lDiaChi = new JLabel("Địa chỉ");
		lDiaChi.setBounds(12, 77, 56, 16);
		getContentPane().add(lDiaChi);
		
		ipDiaChi = new JTextField();
		ipDiaChi.setBounds(89, 74, 116, 22);
		getContentPane().add(ipDiaChi);
		ipDiaChi.setColumns(10);
		
		JLabel lChuSoHuu = new JLabel("Chủ sở hữu");
		lChuSoHuu.setBounds(12, 153, 85, 16);
		getContentPane().add(lChuSoHuu);
		
		ipChuSoHuu = new JTextField();
		ipChuSoHuu.setBounds(89, 150, 116, 22);
		getContentPane().add(ipChuSoHuu);
		ipChuSoHuu.setColumns(10);
		
		JLabel lDienTich = new JLabel("Diện tích");
		lDienTich.setBounds(12, 113, 56, 16);
		getContentPane().add(lDienTich);
		
		ipDienTich = new JTextField();
		ipDienTich.setBounds(89, 109, 116, 22);
		getContentPane().add(ipDienTich);
		ipDienTich.setColumns(10);
		
		JLabel lLoaiNha = new JLabel("Loại nhà");
		lLoaiNha.setBounds(230, 42, 56, 16);
		getContentPane().add(lLoaiNha);
		
		cbLoaiNha = new JComboBox<String>(loaiNha);
		cbLoaiNha.setBounds(298, 39, 116, 22);
		getContentPane().add(cbLoaiNha);
		
		JLabel lMucDich = new JLabel("Mục đích");
		lMucDich.setBounds(230, 77, 56, 16);
		getContentPane().add(lMucDich);
		
		cbMucDich = new JComboBox<String>(mucDich);
		cbMucDich.setBounds(298, 74, 116, 22);
		getContentPane().add(cbMucDich);
		
		JLabel lGiaTien = new JLabel("Giá tiền");
		lGiaTien.setBounds(230, 113, 56, 16);
		getContentPane().add(lGiaTien);
		
		ipGiaTien = new JTextField();
		ipGiaTien.setBounds(298, 110, 116, 22);
		getContentPane().add(ipGiaTien);
		ipGiaTien.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(108, 201, 97, 25);
		getContentPane().add(btnInsert);
		
		JButton btnCancel = new JButton("Hủy");
		btnCancel.setBounds(230, 201, 97, 25);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setSize(450,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	//Hàm trả về button Insert để thực hiện chức năng Insert
	public JButton getButton() {
		return btnInsert;
	}
	//Các hàm trả về giá trị vừa nhập
	public String dc()    {
        return ipDiaChi.getText().toString().trim();
    }
//	public double stt() {
//		return Double.parseDouble(ipSTT.getText().toString().trim());
//	}
	public String csh() {
		return ipChuSoHuu.getText().toString().trim();
	}
	public String ln() {
		return cbLoaiNha.getSelectedItem().toString();
	}
	public String md() {
		return cbMucDich.getSelectedItem().toString();
	}
	public double gt() {
		return Double.parseDouble(ipGiaTien.getText().toString().trim());
	}
	public String dt() {
		return ipDienTich.getText().toString().trim();
	}
}
