
public class ThuaDat {
	private double stt;
	private String diaChi;
	private String dienTich;
	private String chuSoHuu;
	private String loaiNha;
	private String mucDich;
	private double giaTien;
	public int length;
	public ThuaDat() {
		
	}
	public ThuaDat(double stt, String diaChi, String dienTich, String chuSoHuu, String loaiNha, String mucDich,
			double giaTien) {
		super();
		this.stt = stt;
		this.diaChi = diaChi;
		this.dienTich = dienTich;
		this.chuSoHuu = chuSoHuu;
		this.loaiNha = loaiNha;
		this.mucDich = mucDich;
		this.giaTien = giaTien;
	}
	public double getStt() {
		return stt;
	}
	public void setStt(double stt) {
		this.stt = stt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getDienTich() {
		return dienTich;
	}
	public void setDienTich(String dienTich) {
		this.dienTich = dienTich;
	}
	public String getChuSoHuu() {
		return chuSoHuu;
	}
	public void setChuSoHuu(String chuSoHuu) {
		this.chuSoHuu = chuSoHuu;
	}
	public String getLoaiNha() {
		return loaiNha;
	}
	public void setLoaiNha(String loaiNha) {
		this.loaiNha = loaiNha;
	}
	public String getMucDich() {
		return mucDich;
	}
	public void setMucDich(String mucDich) {
		this.mucDich = mucDich;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public String toString() {
		return String.format("%-5s%-25s%-10s%-25s%-15s%-15s%-15s \n",  stt, diaChi, dienTich, chuSoHuu, loaiNha, mucDich, giaTien);
		
	}
	
}
