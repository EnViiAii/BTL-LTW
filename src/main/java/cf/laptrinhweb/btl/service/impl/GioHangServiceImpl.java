package cf.laptrinhweb.btl.service.impl;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;
import cf.laptrinhweb.btl.repository.GioHangRepository;
import cf.laptrinhweb.btl.repository.impl.GioHangRepositoryImpl;
import cf.laptrinhweb.btl.service.GioHangService;

import java.util.List;

public class GioHangServiceImpl implements GioHangService {
    private final GioHangRepository gioHangRepository = new GioHangRepositoryImpl();

    @Override
    public void themSanPham(NguoiDung nguoiDung, Long maSanPham, Integer soLuong) {
        if (soLuong <= 0) throw new RuntimeException("So luong khong hop le");
        Long maItem = gioHangRepository.timBangNguoiDungVaSanPham(nguoiDung.getMaNguoiDung(), maSanPham);
        if (maItem != null) {
            gioHangRepository.capNhatSoLuong(maItem, soLuong);
        } else {
            gioHangRepository.themVaoGioHang(nguoiDung.getMaNguoiDung(), maSanPham, soLuong);
        }
    }

    @Override
    public List<SanPhamTrongGio> layTatCaCuaNguoiDung(NguoiDung nguoiDung) {
        return gioHangRepository.timTatCaBangMaNguoiDung(nguoiDung.getMaNguoiDung());
    }

	@Override
	public void xoaSanPham(Long maGio, Long nguoiDung) {
		gioHangRepository.xoaGioHang(maGio, nguoiDung);
	}

	@Override
	public void capNhatSoLuong(Long maMucGioHang, int soLuongMoi) {
		gioHangRepository.capNhatSoLuong(maMucGioHang, soLuongMoi);
		
	}
}
