package cf.laptrinhweb.btl.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinhLuan implements Comparable<BinhLuan>{
	private Long id;
	private String noi_dung_binh_luan;
	private SanPham san_pham;
	private Date ngay_binh_luan;
	private NguoiDung nguoi_binh_luan;
	private Long ma_binh_luan_goc;
	@Override
	public int compareTo(BinhLuan o) {
		// TODO Auto-generated method stub
		return o.id.compareTo(this.id);
	}
	
	
}
