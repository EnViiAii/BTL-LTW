package cf.laptrinhweb.btl.service.impl;

import java.util.List;

import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamDat;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;
import cf.laptrinhweb.btl.repository.GioHangRepository;
import cf.laptrinhweb.btl.service.DatHangService;
import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.repository.impl.*;
import cf.laptrinhweb.btl.repository.*;
import java.util.List;

public class DatHangServiceImpl implements DatHangService{
	private final DatHangRepository datHangRepository = new DatHangRepositoryImpl();
	private final SanPhamDatRepository sanPhamDatRepository = new SanPhamDatRepositoryImpl();
	private final SanPhamRepository sanPhamRepository = new SanPhamRepositoryImpl();
	private final GioHangRepository gioHangRepository = new GioHangRepositoryImpl();
	
	
	public DatHang layDonTheoMaDatHang(Long maDatHang, NguoiDung nguoidung) {
		return datHangRepository.layDonTheoMaDatHang(maDatHang, nguoidung);
	}

	@Override
	public void themDatHang(DatHang dathang, List<SanPhamTrongGio> listGio, NguoiDung nguoidung) {
		// TODO Auto-generated method stub
		if(listGio.size() == 0)
			return;
		for (SanPhamTrongGio i : listGio) {
			if(i.getSoLuong() > i.getSanPham().getSoLuong())
				return;
		}
        for(SanPhamDat sp : dathang.getDanhSachSanPham()) {
        	sanPhamRepository.giamSoLuong(sp.getSanPham().getMaSanPham(), sp.getSoLuong());
        }
           
        for(SanPhamTrongGio sp : listGio) {
        	gioHangRepository.xoaGioHang(sp.getMaMucGioHang(), nguoidung.getMaNguoiDung());
        }

		datHangRepository.themDatHang(dathang);
		sanPhamDatRepository.themSanPhamDat(dathang);
	}

	@Override
	public List<DatHang> layTatCaCuaNguoiDung(NguoiDung nguoidung) {
		return datHangRepository.layTatCaCuaNguoiDung(nguoidung);
	}

	@Override
	public DatHang layDatHang(Long ma_dat_hang) {
		// TODO Auto-generated method stub
		return datHangRepository.layDatHang(ma_dat_hang);
	}

	public void thayDoiTrangThaiDon(Long maDatHang, int trang_thai_moi) {
		//datHangRepository.thayDoiTrangThai(maDatHang, trang_thai_moi);
	}
}
