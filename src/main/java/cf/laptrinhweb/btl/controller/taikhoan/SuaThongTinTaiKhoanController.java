package cf.laptrinhweb.btl.controller.taikhoan;

import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.exception.xacthuc.ThongTinDangNhapDaTonTaiException;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.LichSuHanhDongService;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.LichSuHanhDongServiceImpl;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauDangNhap;

@WebServlet("/tai-khoan/sua-thong-tin")
public class SuaThongTinTaiKhoanController extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();
    private final LichSuHanhDongService hanhDongService = new LichSuHanhDongServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauDangNhap(req);

        NguoiDung nguoiDung = layThongTinMoi(req);
        try {
            xacThucService.doiThongTinTaiKhoan(nguoiDung);
            resp.sendRedirect(req.getContextPath() + "/tai-khoan");
            hanhDongService.themLichSu(req, LoaiHanhDong.DOI_THONG_TIN_TAI_KHOAN, "Đổi thông tin tài khoản thành" + nguoiDung.toString(), true);
        } catch (ThongTinDangNhapDaTonTaiException e) {
            e.getThongTinTrungLap().forEach(loaiThongTin -> {
                switch (loaiThongTin) {
                    case EMAIL -> req.setAttribute("trungEmail", true);
                    case SO_DIEN_THOAI -> req.setAttribute("trungSdt", true);
                    case TEN_DANG_NHAP -> req.setAttribute("trungTenDangNhap", true);
                }
            });
            req.setAttribute("nguoiDung", nguoiDung);
            req.getRequestDispatcher("/WEB-INF/thong_tin_tai_khoan.jsp").forward(req, resp);
            hanhDongService.themLichSu(req, LoaiHanhDong.DOI_THONG_TIN_TAI_KHOAN, "Đổi thông tin tài khoản thành " + nguoiDung.toString(), false);
        }
    }

    private NguoiDung layThongTinMoi(HttpServletRequest request) {
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
        nguoiDung.setTenHienThi(request.getParameter("tenHienThi"));
        String tenDangNhap = request.getParameter("tenDangNhap");
        nguoiDung.setTenDangNhap(tenDangNhap.isBlank() ? null : tenDangNhap);
        nguoiDung.setEmail(request.getParameter("email"));
        nguoiDung.setSoDienThoai(request.getParameter("soDienThoai"));
        nguoiDung.setMatKhau("matKhau");
        return nguoiDung;
    }
}
