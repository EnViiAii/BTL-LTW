package cf.laptrinhweb.btl.controller.sanpham;

import cf.laptrinhweb.btl.entity.*;
import cf.laptrinhweb.btl.model.DieuKienSanPham;
import cf.laptrinhweb.btl.repository.SanPhamRepository;
import cf.laptrinhweb.btl.repository.impl.SanPhamRepositoryImpl;
import cf.laptrinhweb.btl.service.BinhLuanService;
import cf.laptrinhweb.btl.service.SanPhamService;
import cf.laptrinhweb.btl.service.impl.BinhLuanServiceImpl;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/san-pham")
public class ChiTietSanPhamController extends HttpServlet {
    private final SanPhamService sanPhamService = new SanPhamServiceImpl();
    private final BinhLuanService binhLuanService = new BinhLuanServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long maSanPham = Long.parseLong(req.getParameter("maSanPham"));
        req.setAttribute("danhSachBinhLuan", binhLuanService.layTatCaBinhLuan(maSanPham));	
        req.setAttribute("sanPham", sanPhamService.timTheoMa(maSanPham));
        req.setAttribute("sanPhamLienQuan", sanPhamService.timTatCa(DieuKienSanPham.builder().daAn(false).build()));
        req.getRequestDispatcher("/WEB-INF/chi_tiet_san_pham.jsp").forward(req, resp);
    }
}
