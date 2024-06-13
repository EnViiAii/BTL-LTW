package cf.laptrinhweb.btl.dto.request;

import java.util.Date;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPham;
import lombok.Data;

@Data
public class ThemBinhLuanDTO {
	private Long maSanPham;
	private Long maBinhLuanGoc;
	private String noiDungBinhLuan;

}
