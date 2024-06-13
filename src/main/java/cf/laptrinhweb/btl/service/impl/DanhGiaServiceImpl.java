package cf.laptrinhweb.btl.service.impl;

import java.util.List;

import cf.laptrinhweb.btl.entity.DanhGia;
import cf.laptrinhweb.btl.repository.DanhGiaRepository;
import cf.laptrinhweb.btl.repository.impl.DanhGiaRepositoryImpl;
import cf.laptrinhweb.btl.service.DanhGiaService;

public class DanhGiaServiceImpl implements DanhGiaService{
	public final DanhGiaRepository danhGiaRepo = new DanhGiaRepositoryImpl();
	@Override
	public void themDanhGia(DanhGia danhGia,Long ma_san_pham_dat) {
		// TODO Auto-generated method stub
		danhGiaRepo.themDanhGia(danhGia,ma_san_pham_dat);
	}

	@Override
	public void xoaDanhGia(Long ma_danh_gia) {
		// TODO Auto-generated method stub
		danhGiaRepo.xoaDanhGia(ma_danh_gia);
	}

	@Override
	public List<DanhGia> layTatCaDanhGia(Long ma_san_pham) {
		// TODO Auto-generated method stub
		return danhGiaRepo.layTatCaDanhGia(ma_san_pham);
	}

}
