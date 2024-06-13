package cf.laptrinhweb.btl.controller.xacthuc;

import cf.laptrinhweb.btl.constant.KhoaSession;
import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.exception.xacthuc.SaiThongTinDangNhapException;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.exception.xacthuc.TaiKhoanBiKhoaException;
import cf.laptrinhweb.btl.model.thongbao.ThongBaoSaiThongTinDangNhap;
import cf.laptrinhweb.btl.model.thongbao.ThongBaoTkBiKhoa;
import cf.laptrinhweb.btl.model.xacthuc.NguoiDungUngDung;
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
import java.util.Set;

@WebServlet("/dang-nhap")
public class DangNhapController extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();
    private final LichSuHanhDongService lichSuHanhDongService = new LichSuHanhDongServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/dang_nhap.jsp").forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenDangNhap = req.getParameter("tenDangNhap");
        String matKhau = req.getParameter("matKhau");
        try {
            NguoiDung nguoiDung = xacThucService.dangNhap(tenDangNhap, matKhau);
            req.getSession().setAttribute(KhoaSession.NGUOI_DUNG, new NguoiDungUngDung(nguoiDung));
            ((Set<Long>) req.getServletContext().getAttribute(KhoaSession.BUOC_DANG_XUAT)).remove(nguoiDung.getMaNguoiDung());
            String diaChiDieuHuong = layDiaChiDieuHuong(req, nguoiDung);
            lichSuHanhDongService.themLichSu(req, LoaiHanhDong.DANG_NHAP, true);
            resp.sendRedirect(diaChiDieuHuong);
        } catch (SaiThongTinDangNhapException e) {
            if (e.getMaNguoiDung() != null)
                lichSuHanhDongService.themLichSu(req, e.getMaNguoiDung(), LoaiHanhDong.DANG_NHAP, false);
            req.setAttribute("thongBao", new ThongBaoSaiThongTinDangNhap());
            req.getRequestDispatcher("WEB-INF/dang_nhap.jsp").forward(req, resp);
        } catch (TaiKhoanBiKhoaException e) {
            if (e.getMaNguoiDung() != null)
                lichSuHanhDongService.themLichSu(req, e.getMaNguoiDung(), LoaiHanhDong.DANG_NHAP, false);
            req.setAttribute("thongBao", new ThongBaoTkBiKhoa());
            req.getRequestDispatcher("WEB-INF/dang_nhap.jsp").forward(req, resp);
        }
    }

    private String layDiaChiDieuHuong(HttpServletRequest request, NguoiDung nguoiDung) {
        String diaChiDieuHuong = "/";
        if (nguoiDung.coQuyen(QuyenNguoiDung.ADMIN)) {
            diaChiDieuHuong = "/quan-ly/nguoi-dung";
        } else if (nguoiDung.coQuyen(QuyenNguoiDung.QUAN_LY)) {
            diaChiDieuHuong = "/quan-ly/dashboard";
        } else if (nguoiDung.coQuyen(QuyenNguoiDung.NHAN_VIEN)) {
            diaChiDieuHuong = "/quan-ly/don-hang";
        }
        return request.getContextPath() + diaChiDieuHuong;
    }
}
