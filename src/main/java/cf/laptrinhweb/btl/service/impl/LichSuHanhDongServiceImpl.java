package cf.laptrinhweb.btl.service.impl;

import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import cf.laptrinhweb.btl.entity.LichSuHanhDong;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.model.DieuKienLichSu;
import cf.laptrinhweb.btl.model.Trang;
import cf.laptrinhweb.btl.repository.LichSuHanhDongRepository;
import cf.laptrinhweb.btl.repository.impl.LichSuHanhDongRepositoryImpl;
import cf.laptrinhweb.btl.service.LichSuHanhDongService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LichSuHanhDongServiceImpl implements LichSuHanhDongService {
    private final LichSuHanhDongRepository lichSuHanhDongRepository = new LichSuHanhDongRepositoryImpl();

    @Override
    public void themLichSu(HttpServletRequest request, Long maNguoiDung, LoaiHanhDong loaiHanhDong, boolean thanhCong) {
        String diaChiIP = layDiaChiIP(request);
        LichSuHanhDong lichSuHanhDong = LichSuHanhDong.builder()
            .nguoiDung(NguoiDung.builder().maNguoiDung(maNguoiDung).build())
            .loaiHanhDong(loaiHanhDong)
            .diaChiIP(diaChiIP)
            .thanhCong(thanhCong)
            .build();
        lichSuHanhDongRepository.themLichSu(lichSuHanhDong);
    }

    @Override
    public void themLichSu(HttpServletRequest request, LoaiHanhDong loaiHanhDong, boolean thanhCong) {
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
        themLichSu(request, nguoiDung.getMaNguoiDung(), loaiHanhDong, thanhCong);
    }

    @Override
    public Trang<LichSuHanhDong> xemLichSuHanhDong(Long maNguoiDung, DieuKienLichSu dieuKien) {
        Trang<LichSuHanhDong> trang = new Trang<>();
        trang.setDuLieu(lichSuHanhDongRepository.layTheoMaNguoiDung(maNguoiDung, dieuKien));
        trang.setTongSoTrang(lichSuHanhDongRepository.demTatCaTheoMaNguoiDung(maNguoiDung) / dieuKien.getKichThuoc());
        return trang;
    }

    @Override
    public void themLichSu(HttpServletRequest request, LoaiHanhDong loaiHanhDong, String chiTiet, boolean thanhCong) {
        String diaChiIP = layDiaChiIP(request);
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
        LichSuHanhDong lichSuHanhDong = LichSuHanhDong.builder()
            .nguoiDung(nguoiDung)
            .loaiHanhDong(loaiHanhDong)
            .chiTiet(chiTiet)
            .diaChiIP(diaChiIP)
            .thanhCong(thanhCong)
            .build();
        lichSuHanhDongRepository.themLichSu(lichSuHanhDong);
    }

    private String layDiaChiIP(HttpServletRequest request) {
        String diaChiIP = request.getHeader("X-FORWARDED-FOR");
        if (diaChiIP == null || "".equals(diaChiIP)) {
            diaChiIP = request.getRemoteAddr();
        }
        return diaChiIP;
    }
}
