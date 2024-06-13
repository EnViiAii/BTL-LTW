package cf.laptrinhweb.btl.controller.danhgia;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.DanhGia;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.repository.impl.SanPhamRepositoryImpl;
import cf.laptrinhweb.btl.service.DanhGiaService;
import cf.laptrinhweb.btl.service.impl.DanhGiaServiceImpl;
import cf.laptrinhweb.btl.service.impl.SanPhamDatServiceImpl;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

/**
 * Servlet implementation class XemTatCaDanhGia
 */
@WebServlet("/xem-tat-ca-danh-gia")
public class XemTatCaDanhGiaController extends HttpServlet {
	public final DanhGiaService danhGiaService = new DanhGiaServiceImpl(); 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhGia> ldg = danhGiaService.layTatCaDanhGia(Long.parseLong(request.getParameter("maSanPham")));
        SanPham sp = new SanPhamRepositoryImpl().timTheoMa(Long.parseLong(request.getParameter("maSanPham")))
			.orElseThrow(RuntimeException::new);
        request.setAttribute("sanPham", sp);
        request.setAttribute("tat_ca_danh_gia", ldg);
        request.getRequestDispatcher("WEB-INF/danhgia.jsp").forward(request, response);
	}

}
