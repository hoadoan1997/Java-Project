import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Delete extends JFrame{

	private JTextField dataDelete;
	private JButton btnDelete;
	
	public Delete() {
		this.init();
	}
	private void init() {
		getContentPane().setLayout(null);

		JLabel lblXoa = new JLabel("XÓA THỬA ĐẤT");
		lblXoa.setForeground(Color.BLUE);
		lblXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblXoa.setBounds(138, 13, 133, 46);
		getContentPane().add(lblXoa);
		
		JLabel lblXaTheo = new JLabel("Ví dụ : Lạc Long Quân, Trần Hưng Đạo, ...");
		lblXaTheo.setBounds(91, 75, 268, 16);
		getContentPane().add(lblXaTheo);

		JLabel lblNhpDLiu = new JLabel("Nhập dữ liệu");
		lblNhpDLiu.setBounds(83, 125, 101, 16);
		getContentPane().add(lblNhpDLiu);
		
		dataDelete = new JTextField();
		dataDelete.setBounds(196, 122, 116, 22);
		getContentPane().add(dataDelete);
		dataDelete.setColumns(10);
		
		btnDelete = new JButton("Xác nhận");
		btnDelete.setBounds(77, 185, 97, 25);
		getContentPane().add(btnDelete);
		
		JButton btnCancel = new JButton("Hủy");
		btnCancel.setBounds(215, 185, 97, 25);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
		
		setSize(450,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	//Hàm trả về button Delete để thực hiện chức năng Delete
	public JButton getButton() {
		return btnDelete;
	}
	//Hàm trả về dữ liệu vừa nhập để xóa
	public String dataDelete() {
		return dataDelete.getText().toString().trim();
	}
}
