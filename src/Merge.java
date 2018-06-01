import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class Merge extends JFrame{
	
	private JButton btnMerge; 
	
	private JTextField txtPath;
	
	private JFileChooser fc = new JFileChooser();
	
	private JRadioButton rd1,rd2,rd3,rd4;
	
	private ButtonGroup btnGroup = new ButtonGroup();
	
	public Merge() {
		this.init();
	}
	//Hàm khởi tạo
	public void init() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MERGE THỬA ĐẤT");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(126, 26, 164, 36);
		getContentPane().add(lblNewLabel);
		
		btnMerge = new JButton("Xác nhận");
		btnMerge.setBounds(96, 195, 97, 25);
		getContentPane().add(btnMerge);
		
		//Xử lý chức năng của button Hủy
		JButton btnCancel = new JButton("Hủy");
		btnCancel.setBounds(225, 195, 97, 25);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		//Xử lý chức năng của button Open File
		JButton btnOpen = new JButton("Open File");
		btnOpen.setBounds(22, 66, 97, 25);
		getContentPane().add(btnOpen);
		btnOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txtPath.setText(OpenFile());
			}
		});
		
		txtPath = new JTextField();
		txtPath.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtPath.setEditable(false);
		txtPath.setForeground(Color.BLUE);
		txtPath.setBounds(136, 67, 243, 22);
		getContentPane().add(txtPath);
		txtPath.setColumns(10);
		
		rd1 = new JRadioButton("Dưới $200.000 ");
		rd1.setBounds(43, 113, 127, 25);
		getContentPane().add(rd1);
		
		rd2 = new JRadioButton("$200.000 - $400.000");
		rd2.setBounds(225, 113, 180, 25);
		getContentPane().add(rd2);
		
		rd3 = new JRadioButton("$400.000 - $600.000");
		rd3.setBounds(43, 143, 157, 25);
		getContentPane().add(rd3);
		
		rd4 = new JRadioButton("Trên $600.000");
		rd4.setBounds(225, 143, 127, 25);
		getContentPane().add(rd4);
		
		btnGroup.add(rd1);
		btnGroup.add(rd2);
		btnGroup.add(rd3);
		btnGroup.add(rd4);
		
		setSize(450,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	//Hàm để chọn File từ thư mục
	public String OpenFile() {
		int select = fc.showOpenDialog(this);
		String excelPath = "";
		if(select == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			excelPath  = selectedFile.getPath();
		
		};
		return excelPath;
	}
	//Hàm lấy đường dẫn của File vừa Open để chuyển qua GiaoDienThuaDat để thực hiện
	public String getPath() {
		return txtPath.getText();
	}
	//Hàm trả về button Merger để thực hiện chức năng Merge
	public JButton getButton() {
		return btnMerge;
	}
	//Hàm kiểm tra check box nào được chọn và sẽ trả về cửa số chính
	public String checkBox() {
		String check = "";
		if(rd1.isSelected()) {
			check = "1";
		}else if(rd2.isSelected()) {
			check = "2";
		}else if(rd3.isSelected()) {
			check = "3";
		}else if(rd4.isSelected()) {
			check = "4";
		}else check = "";
		return check;
	}
	
}
