package cf.laptrinhweb.btl.helper;

import cf.laptrinhweb.btl.constant.KhoaSession;
import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.Quyen;
import cf.laptrinhweb.btl.exception.xacthuc.KhongCoQuyenTruyCapException;
import cf.laptrinhweb.btl.model.xacthuc.NguoiDungUngDung;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HoTroXacThuc {
    public static void yeuCauQuyen(HttpServletRequest req, List<QuyenNguoiDung> dsQuyenDuocPhep) {
        NguoiDungUngDung nguoiDung = (NguoiDungUngDung) req.getSession().getAttribute(KhoaSession.NGUOI_DUNG);
        if (nguoiDung == null ||
            nguoiDung.getDanhSachQuyen().stream().noneMatch(dsQuyenDuocPhep::contains)) {
            throw new KhongCoQuyenTruyCapException();
        }
    }

    public static void yeuCauDangNhap(HttpServletRequest req) {
        if (req.getSession().getAttribute(KhoaSession.NGUOI_DUNG) == null) {
            throw new KhongCoQuyenTruyCapException();
        }
    }

    public static NguoiDung nguoiDungHienTai(HttpServletRequest req) {
        NguoiDungUngDung nguoiDung = (NguoiDungUngDung) req.getSession().getAttribute(KhoaSession.NGUOI_DUNG);
        List<Quyen> dsQuyen = nguoiDung.getDanhSachQuyen().stream().map(Quyen::new).toList();
        return NguoiDung.builder()
            .maNguoiDung(nguoiDung.getMaNguoiDung())
            .dsQuyen(dsQuyen)
            .build();
    }
}
