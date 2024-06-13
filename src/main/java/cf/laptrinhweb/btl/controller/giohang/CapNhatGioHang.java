package cf.laptrinhweb.btl.controller.giohang;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.service.GioHangService;
import cf.laptrinhweb.btl.service.impl.GioHangServiceImpl;

/**
 * Servlet implementation class CapNhatGioHang
 */
@WebServlet("/gio-hang/cap-nhat")
public class CapNhatGioHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final GioHangService gioHangService = new GioHangServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		yeuCauQuyen(request, List.of(QuyenNguoiDung.KHACH_HANG));
		gioHangService.capNhatSoLuong(Long.valueOf(request.getParameter("maGio")), Integer.valueOf(request.getParameter("soLuongMoi")));
		response.sendRedirect(request.getHeader("Referer"));
	}

}
