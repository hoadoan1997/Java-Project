import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Search extends JFrame{
	private JTextField txtSearch;
	
	private JButton btnSearch;
	
	public Search() {
		this.init();
	}
	//Hàm khởi tạo
	public void init() {
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM THỬA ĐẤT");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(119, 31, 197, 40);
		getContentPane().add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(198, 119, 116, 22);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblTmKim = new JLabel("Nhập dữ liệu");
		lblTmKim.setBounds(95, 122, 82, 16);
		getContentPane().add(lblTmKim);
		
		btnSearch = new JButton("Xác nhận");
		btnSearch.setBounds(80, 166, 97, 25);
		getContentPane().add(btnSearch);
		
		
		
		JButton btnCancel = new JButton("Hủy");
		btnCancel.setBounds(236, 166, 97, 25);
		getContentPane().add(btnCancel);
		
		JLabel lblVD = new JLabel("Ví dụ : Lạc Long Quân, Trần Hưng Đạo,...");
		lblVD.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblVD.setBounds(80, 73, 249, 16);
		getContentPane().add(lblVD);
		//Xử lý chức năng button Cancel
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
	//Hàm trả về button Search để thực hiện chức năng Search
	public JButton getButton() {
		return btnSearch;
	}
	//Hàm trả về dữ liệu vừa nhập để tìm kiếm
	public String dataSearch() {
		return txtSearch.getText().toString().trim();
	}
}
