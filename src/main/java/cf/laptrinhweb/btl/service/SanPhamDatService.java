package cf.laptrinhweb.btl.service;

import java.util.List;
import cf.laptrinhweb.btl.entity.*;

public interface SanPhamDatService {
	List<SanPhamDat> layTatCaTheoNguoiDung(NguoiDung nguoiDung);
	Long timMaSanPham(Long ma_san_pham_dat);
}
