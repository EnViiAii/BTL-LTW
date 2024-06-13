package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.model.DieuKienNguoiDung;

import java.util.List;
import java.util.Optional;

public interface NguoiDungRepository extends JdbcRepository {
    Optional<NguoiDung> timBangThongTinDangNhap(String thongTinDangNhap);
    boolean tenDangNhapNguoiKhacDaDung(String tenDangNhap, Long maNguoiMuonKiemTra);
    boolean emailNguoiKhacDaDung(String email, Long maNguoiMuonKiemTra);
    boolean soDienThoaiNguoiKhacDaDung(String soDienThoai, Long maNguoiMuonKiemTra);
    void taoMoiNguoiDung(NguoiDung nguoiDung);
    void doiMatKhau(NguoiDung nguoiDung, String matKhauMoi);
    List<NguoiDung> timTatCa(DieuKienNguoiDung dieuKien);
    void thayDoiTrangThai(Long maNguoiDung, boolean khoa);
    NguoiDung timNguoiDung(Long ma_nguoi_dung);
    void tangCoGangDangNhap(NguoiDung nguoiDung);
    void resetCoGangDangNhap(Long maNguoiDung);
    void capNhatThongTin(NguoiDung nguoiDung);
}
