package cf.laptrinhweb.btl.controller.giohang;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.GioHangService;
import cf.laptrinhweb.btl.service.impl.GioHangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/gio-hang")
public class GioHangController extends HttpServlet {
    private final GioHangService gioHangService = new GioHangServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.KHACH_HANG));
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
        List<SanPhamTrongGio> danhSachSanPham = gioHangService.layTatCaCuaNguoiDung(nguoiDung);
        req.setAttribute("danhSachSanPham", danhSachSanPham);
        req.getRequestDispatcher("/WEB-INF/gio_hang.jsp").forward(req, resp);
    }
}
