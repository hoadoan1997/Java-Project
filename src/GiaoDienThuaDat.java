import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class GiaoDienThuaDat extends JFrame implements ActionListener{
	private static String excelPath = "";
	
	public static List<ThuaDat> thuaDat = new ArrayList<ThuaDat>();
	
	String [] title = new String[] {"STT", "Địa chỉ", "Diện tích", "Chủ sở hữu", "Loại nhà", "Mục đích", "Giá tiền($)"};
		
	private JTable table = new JTable();
	
	private DefaultTableModel tableModel = new DefaultTableModel();
	
	private JFileChooser fc = new JFileChooser();
	
	private JLabel lbNof = new JLabel();
	/* Phần 1: Xử lý Giao diện */
	//Constructor
	public GiaoDienThuaDat(String title)  {	
		super(title);
		add(createMainPanel(panelTitle("PHẦN MỀM NHÀ ĐẤT"), panelInput(), table));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 680);
		setVisible(true);
		setLocationRelativeTo(null);	 
	}
	//Xử lý Panel chứa các Button
	private JPanel panelInput() {
		//Tạo button xử lý và bỏ vào 1 Grid layout
		JPanel panelButton = new JPanel(new BorderLayout());
		JPanel panelBtn = new JPanel(new GridLayout(2,2,10,10));
		
		panelBtn.add(createButton("Insert","image\\icon_insert.png"));
		panelBtn.add(createButton("Search","image\\\\icon_search.png"));
		panelBtn.add(createButton("Delete","image\\\\icon_delete.png"));
		panelBtn.add(createButton("Merge","image\\\\icon_merge.png"));
		
		JPanel panel1 = new JPanel(new GridLayout(1,2,10,10));
		panel1.add(panelBtn);
		autoSortPanel(panelButton, panel1); // Bỏ panel chứa các button để xử lý khoảng cách nằm ở giữa
		
		JPanel panelFile = new JPanel(new GridLayout(1,3,10,10));
		panelFile.add(createButton("Open File","image\\\\icon_open.png"));
		panelFile.add(createButton("Heap Sort","image\\\\icon_sort.png"));
		panelFile.add(createButton("Save File","image\\\\icon_save.png"));
		
		JPanel panel2 = new JPanel(new GridLayout(1,1,10,10));
		panel2.add(panelFile);
		
		JPanel panelNof = new JPanel(new FlowLayout());
        panelNof.add(lbNof);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		autoSortPanel(mainPanel, panel2);
		mainPanel.add(panelNof, BorderLayout.NORTH);
		mainPanel.add(panel2,BorderLayout.CENTER);
		mainPanel.add(panelButton,BorderLayout.SOUTH);
		return mainPanel;
	}
	//Tạo dòng tiêu đề và set các thuộc tính như font, kích thước,...
	private JPanel panelTitle(String title) {
		JPanel mainPanel = new JPanel();
		JLabel lbTitle = new JLabel(title);
		lbTitle.setForeground(Color.BLUE);
		Font font = new Font("arial", Font.BOLD,25);
		lbTitle.setFont(font);
		mainPanel.add(lbTitle,BorderLayout.NORTH);
		return mainPanel;
	}
	//Cấu trúc giao diện gồm 3 phần : panel Title, panel Input và Table
	private JPanel createMainPanel(JPanel panelTitle, JPanel panelInput , JTable table) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createBlankPanel(), BorderLayout.WEST);
        panel.add(createBlankPanel(), BorderLayout.EAST);
        
        panel.add(panelTitle, BorderLayout.NORTH);
        panel.add(panelInput, BorderLayout.CENTER);
        panel.add(createPanelTable(table), BorderLayout.SOUTH);

        return panel;
    }
	//Tạo một Panel trống
	private JPanel createBlankPanel() {
        JPanel panel = new JPanel();
        return panel;
    }
	//Tạo một table
	private JPanel createPanelTable(JTable table) {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        tableModel.setColumnIdentifiers(title);
        table.setModel(tableModel);
        panel.add(new JScrollPane(table));  
        return panel;
    }
	//Tạo một Button
	private JButton createButton(String text, String defaultIcon) {
        JButton btn = new JButton(text);
        btn.addActionListener(this);
        btn.setIcon(new ImageIcon(defaultIcon));
        return btn;
    }
	//Xử lý khoảng trống giữa các thành phần
	private void autoSortPanel(JPanel main, JPanel panel) { 
        main.add(createBlankPanel(), BorderLayout.NORTH);
        main.add(createBlankPanel(), BorderLayout.WEST);
        main.add(createBlankPanel(), BorderLayout.EAST);
        main.add(panel, BorderLayout.CENTER);
        main.add(createBlankPanel(), BorderLayout.SOUTH);
    }
	/*Hết phần 1 */
	
	/* Phần 2: Xử lý dữ liệu từ Excel lên Table*/
	//Mở File
	private void openFile() throws IOException {
		int select = fc.showOpenDialog(this);
		if(select == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			excelPath = selectedFile.getPath();
			runExcel(excelPath);
		};
	}
	//Đưa dữ liệu vào trong Table 
	public void addRowToJTable(List<ThuaDat> thuaDat) throws ArrayIndexOutOfBoundsException{
		Object rowData[] = new Object[7];
		int number = 0;
		for(int i = 0 ; i < thuaDat.size(); i++) {
//			rowData[0] = thuaDat.get(i).getStt();
			rowData[0] = ++number;
			rowData[1] = thuaDat.get(i).getDiaChi();
			rowData[2] = thuaDat.get(i).getDienTich();
			rowData[3] = thuaDat.get(i).getChuSoHuu();
			rowData[4] = thuaDat.get(i).getLoaiNha();
			rowData[5] = thuaDat.get(i).getMucDich();
			rowData[6] = thuaDat.get(i).getGiaTien();
			tableModel.addRow(rowData);
		}
	}
	//Xóa dữ liệu hiển thị trên table
	private void clearRowJTable() {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.getDataVector().removeAllElements();	
		tableModel.fireTableDataChanged();
	}
	//Bắt sự kiện cho từng button 
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand() == "Open File") {
			try {
				openFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(event.getActionCommand() == "Heap Sort") {
			if(thuaDat.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hiện tại chưa có dữ liệu");
			}else {
				try {			
					heapSort();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(event.getActionCommand() == "Insert") {
			if(thuaDat.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hiện tại chưa có dữ liêu");
			}else {
				try {
					Insert();
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(event.getActionCommand() == "Search") {
			if(thuaDat.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hiện tại chưa có dữ liệu!");
			}else {
					Search();
			}
		}
		if(event.getActionCommand() == "Delete") {
			
			if (thuaDat.isEmpty()) {
				JOptionPane.showMessageDialog(null , "Hiện tại chưa có dữ liệu!");
			}else {
				Delete();
			}
		}
		if(event.getActionCommand() == "Save File") {
			if(thuaDat.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hiện tại chưa có dữ liệu!");
			}else {
				WriteExcel();
			}
		}
		if(event.getActionCommand() == "Merge") {
			if(thuaDat.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hiện tại chưa có dữ liệu!");
			}else {
				Merge();
			}	
		}	
	}
	//Lấy giá trị theo kiểu tương ứng trong Excel
	private Object getCellValue(Cell cell) {
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}
		return null;
	}
	//Đọc dữ liệu từ file Excel
	public List<ThuaDat> readExcelFile(String excelFilePath) throws IOException{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();
		
		while(iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			ThuaDat td = new ThuaDat();

			while(cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				
				switch(columnIndex) {
				case 0: 
					td.setStt( (double) getCellValue(nextCell));
					break;
				case 1:
					td.setDiaChi((String) getCellValue(nextCell));
					break;
				case 2:
					td.setDienTich((String) getCellValue(nextCell));
					break;
				case 3:
					td.setChuSoHuu((String) getCellValue(nextCell));
					break;
				case 4:
					td.setLoaiNha((String) getCellValue(nextCell));
					break;
				case 5:
					td.setMucDich((String) getCellValue(nextCell));
					break;
				case 6:
					td.setGiaTien((double) getCellValue(nextCell));
					break;	
				}
			}
			thuaDat.add(td);
		}
		wb.close();
		inputStream.close();
		return thuaDat;
	}
	//Hàm đưa dữ liệu từ Excel vào Table
	public void runExcel(String excelPath) throws IOException{//
		readExcelFile(excelPath);
		clearRowJTable();
		addRowToJTable(thuaDat);
	}
	//Hàm export ra file Excel
	public void WriteExcel() {
		int save = fc.showSaveDialog(this);
		if(save == JFileChooser.APPROVE_OPTION) {
			String fileName = fc.getSelectedFile().getName();
			String dir = fc.getCurrentDirectory().toString();
			
			String excelFilePath = dir+"\\"+fileName;
			ExcelWriter excelWriter = new ExcelWriter();
			try {
				excelWriter.writeExcel(thuaDat, excelFilePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Đã lưu thành công !");
		}	
	}
	/* Hết phần 2 */
	
	/* Phần 3: Xử lý các chức năng sắp xếp, thêm, xóa,... */
	//Sắp xếp thửa đất theo địa chỉ
	public void heapSort() throws IOException {
		HeapSort.sort(thuaDat);
		clearRowJTable();
		addRowToJTable(thuaDat);		
	}
	//Thêm một thửa đất vào danh sách nhà đất
	public void Insert() {	
		Insert insert = new Insert();
		insert.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ThuaDat td = new ThuaDat();
				if(insert.dc().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng nhập địa chỉ!");
				}else if(insert.csh().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng nhập chủ sở hữu!");
				}else if(insert.dt().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng nhập diện tích!");
				}else if(insert.ln().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng chọn loại nhà!");
				}else if(insert.md().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng chọn mục đích sử dụng!");
				}else if(insert.gt() == 0) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng nhập giá tiền!");
				}else {
					td.setDiaChi(insert.dc());
					//td.setStt(0);
					td.setChuSoHuu(insert.csh());
					td.setDienTich(insert.dt());
					td.setLoaiNha(insert.ln());
					td.setMucDich(insert.md());
					td.setGiaTien(insert.gt());
			
					thuaDat.add(td);
					insert.dispose();	
					HeapSort.sort(thuaDat);
					clearRowJTable();
					try {
						addRowToJTable(thuaDat);		
					} catch (ArrayIndexOutOfBoundsException e2) {
						// TODO: handle exception
					}
					JOptionPane.showMessageDialog(null, "Thêm thành công!");
				}	
			}
		});
	}
	//Xóa một thủa đất trong danh sách nhà đất
	public void Delete() {
		Delete delete = new Delete();
		delete.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = 0;
				if(delete.dataDelete().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần xóa!");
				}else {
					while (i< thuaDat.size()) {
						if(thuaDat.get(i).getDiaChi().contains(delete.dataDelete())){
							thuaDat.remove(i);	
						}else {
							i++;
						}
					}
					delete.dispose();
					clearRowJTable();
					try {
						addRowToJTable(thuaDat);
					} catch (ArrayIndexOutOfBoundsException e) {
						// TODO: handle exception
					}
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
				}		
			}
		});
	}
	//Tìm kiếm các thửa đất theo tiêu chí 
	public void Search() {
		Search search = new Search();
		List<ThuaDat> thuadat = new ArrayList<ThuaDat>();
		search.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				if(search.dataSearch().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng nhập thông tin cần tìm!");
				}else {
					for(int i = 0 ; i< thuaDat.size();i++) {
						if(thuaDat.get(i).getDiaChi().contains(search.dataSearch())) {
							thuadat.add(thuaDat.get(i));
						} 
					}
					clearRowJTable();
					addRowToJTable(thuadat);
					search.dispose();
				}
			}
		});
	}
	//Merge một danh sách khác với danh sách hiện tại theo tiêu chí "Giá tiền"
	public void Merge() {
		Merge mg = new Merge();
		List<ThuaDat> thuadat = new ArrayList<ThuaDat>();
		mg.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mg.getPath().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng chọn File!");
				}else {
					try {
						runExcel(mg.getPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(mg.checkBox().equals("1")) {
						for (int i = 0; i < thuaDat.size(); i++) {
							if(thuaDat.get(i).getGiaTien() < 200000) {
								thuadat.add(thuaDat.get(i));
							}
						}
						clearRowJTable();
						addRowToJTable(thuadat);
						mg.dispose();
					}else if(mg.checkBox().equals("2")) {
						for (int i = 0; i < thuaDat.size(); i++) {
							if(thuaDat.get(i).getGiaTien() >= 200000 && thuaDat.get(i).getGiaTien() < 400000) {
								thuadat.add(thuaDat.get(i));
							}
						}
						clearRowJTable();
						addRowToJTable(thuadat);
						mg.dispose();
					}else if(mg.checkBox().equals("3")) {
						for (int i = 0; i < thuaDat.size(); i++) {
							if(thuaDat.get(i).getGiaTien() >= 400000 && thuaDat.get(i).getGiaTien() < 600000) {
								thuadat.add(thuaDat.get(i));
							}
						}
						clearRowJTable();
						addRowToJTable(thuadat);
						mg.dispose();
					}else if(mg.checkBox().equals("4")) {
						for (int i = 0; i < thuaDat.size(); i++) {
							if(thuaDat.get(i).getGiaTien() >= 600000) {
								thuadat.add(thuaDat.get(i));
							}
						}
						clearRowJTable();
						addRowToJTable(thuadat);
						mg.dispose();
					}
				}
			}
		});
	}
	/* Hết phần 3*/
	
	//Hàm Main để khởi tạo chương trình
	public static void main(String[] args) throws IOException {
		GiaoDienThuaDat giaodien = new GiaoDienThuaDat("Phần mềm nhà đất");		
	}
}