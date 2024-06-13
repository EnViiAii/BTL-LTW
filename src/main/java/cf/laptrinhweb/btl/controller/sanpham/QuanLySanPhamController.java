package cf.laptrinhweb.btl.controller.sanpham;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.model.DieuKienSanPham;
import cf.laptrinhweb.btl.service.SanPhamService;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/san-pham")
public class QuanLySanPhamController extends HttpServlet {
    private final SanPhamService sanPhamService = new SanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        DieuKienSanPham dieuKien = DieuKienSanPham.builder().build();
        String tuKhoa = req.getParameter("tuKhoa");
        if (tuKhoa != null && !tuKhoa.isBlank()) {
            dieuKien.setTuKhoa(tuKhoa);
        }
        req.setAttribute("danhSachSanPham", sanPhamService.timTatCa(dieuKien));
        req.getRequestDispatcher("/WEB-INF/quan_ly_san_pham.jsp").forward(req, resp);
    }
}
