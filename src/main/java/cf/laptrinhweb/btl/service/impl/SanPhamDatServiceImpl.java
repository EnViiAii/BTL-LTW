package cf.laptrinhweb.btl.service.impl;

import java.util.List;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamDat;
import cf.laptrinhweb.btl.repository.SanPhamDatRepository;
import cf.laptrinhweb.btl.repository.impl.SanPhamDatRepositoryImpl;
import cf.laptrinhweb.btl.service.SanPhamDatService;

public class SanPhamDatServiceImpl implements SanPhamDatService{
	private final SanPhamDatRepository sanPhamDatRepository = new SanPhamDatRepositoryImpl();

	@Override
	public List<SanPhamDat> layTatCaTheoNguoiDung(NguoiDung nguoiDung) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long timMaSanPham(Long ma_san_pham_dat) {
		// TODO Auto-generated method stub
		return sanPhamDatRepository.timMaSanPham(ma_san_pham_dat);
	}

}
