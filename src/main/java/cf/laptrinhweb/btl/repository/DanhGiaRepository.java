package cf.laptrinhweb.btl.repository;
import cf.laptrinhweb.btl.entity.*;
import java.util.List;

import cf.laptrinhweb.btl.entity.DanhGia;

public interface DanhGiaRepository extends JdbcRepository{
	public void themDanhGia(DanhGia danhGia,Long ma_san_pham_dat);
	public void xoaDanhGia(Long ma_danh_gia);
	public List<DanhGia> layTatCaDanhGia(Long ma_san_pham);
	public void capNhatDanhGia(DanhGia dg,Long ma_san_pham);
}
