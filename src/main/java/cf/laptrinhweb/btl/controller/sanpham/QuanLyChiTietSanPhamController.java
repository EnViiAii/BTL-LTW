package cf.laptrinhweb.btl.controller.sanpham;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.service.SanPhamService;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/san-pham/chi-tiet")
public class QuanLyChiTietSanPhamController extends FormSanPhamController {
    private final SanPhamService sanPhamService = new SanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        super.doGet(req, resp);
        Long maSanPham = Long.parseLong(req.getParameter("maSanPham"));
        req.setAttribute("sanPham", sanPhamService.timTheoMa(maSanPham));
        req.getRequestDispatcher("/WEB-INF/form_san_pham.jsp").forward(req, resp);
    }
}
