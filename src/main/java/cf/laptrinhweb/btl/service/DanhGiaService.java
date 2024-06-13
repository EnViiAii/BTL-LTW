package cf.laptrinhweb.btl.service;

import java.util.List;

import cf.laptrinhweb.btl.entity.DanhGia;

public interface DanhGiaService {
	public void themDanhGia(DanhGia danhGia,Long ma_san_pham_dat);
	public void xoaDanhGia(Long ma_danh_gia);
	public List<DanhGia> layTatCaDanhGia(Long ma_san_pham);
}
