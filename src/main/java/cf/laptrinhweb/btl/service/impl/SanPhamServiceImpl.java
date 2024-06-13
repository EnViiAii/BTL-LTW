package cf.laptrinhweb.btl.service.impl;

import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.exception.sanpham.SanPhamKhongTonTai;
import cf.laptrinhweb.btl.helper.HoTroLuuTru;
import cf.laptrinhweb.btl.model.DieuKienSanPham;
import cf.laptrinhweb.btl.model.ThongTinSanPham;
import cf.laptrinhweb.btl.repository.AnhSanPhamRepository;
import cf.laptrinhweb.btl.repository.SanPhamRepository;
import cf.laptrinhweb.btl.repository.impl.AnhSanPhamRepositoryImpl;
import cf.laptrinhweb.btl.repository.impl.SanPhamRepositoryImpl;
import cf.laptrinhweb.btl.service.SanPhamService;

import javax.servlet.http.Part;
import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {
    private final SanPhamRepository sanPhamRepository = new SanPhamRepositoryImpl();
    private final AnhSanPhamRepository anhSanPhamRepository = new AnhSanPhamRepositoryImpl();

    @Override
    public List<SanPham> timTatCa(DieuKienSanPham dieuKien) {
        return sanPhamRepository.timTatCa(dieuKien);
    }

    @Override
    public void luuSanPham(ThongTinSanPham thongTinSanPham, List<Part> dsAnh) {
        // TODO: kiem tra thong tin truoc khi luu gom thong tin san pham & dinh dang file anh
        // luu san pham va anh
        List<String> dsDuongDan = dsAnh.stream().map(HoTroLuuTru::luuFile).toList();
        if (thongTinSanPham.getMaSanPham() != null) {
            sanPhamRepository.capNhat(thongTinSanPham);
            anhSanPhamRepository.themTatCaAnh(
                SanPham.builder()
                    .maSanPham(thongTinSanPham.getMaSanPham())
                    .build(),
                dsDuongDan
            );
        } else {
            if (!dsAnh.isEmpty()) thongTinSanPham.setAnhXemTruoc(dsDuongDan.get(0));
            SanPham sanPham = sanPhamRepository.taoMoi(thongTinSanPham);
            anhSanPhamRepository.themTatCaAnh(sanPham, dsDuongDan);
        }
    }

    @Override
    public SanPham timTheoMa(Long maSanPham) {
        SanPham sanPham = sanPhamRepository.timTheoMa(maSanPham)
            .orElseThrow(SanPhamKhongTonTai::new);
        sanPham.setDanhSachAnh(anhSanPhamRepository.timTheoMaSanPham(maSanPham));
        return sanPham;
    }

	@Override
	public void giamSoLuong(Long maSanPham, int soLuongGiam) {
		sanPhamRepository.giamSoLuong(maSanPham, soLuongGiam);
		
	}
}
