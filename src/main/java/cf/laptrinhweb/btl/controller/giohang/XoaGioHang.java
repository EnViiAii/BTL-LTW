package cf.laptrinhweb.btl.controller.giohang;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;
import  cf.laptrinhweb.btl.service.impl.GioHangServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.repository.GioHangRepository;
import cf.laptrinhweb.btl.repository.impl.GioHangRepositoryImpl;
import cf.laptrinhweb.btl.service.GioHangService;

/**
 * Servlet implementation class XoaGioHang
 */
@WebServlet("/gio-hang/xoa")
public class XoaGioHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final GioHangService gioHangService = new GioHangServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		yeuCauQuyen(request, List.of(QuyenNguoiDung.KHACH_HANG));

        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
        gioHangService.xoaSanPham(Long.valueOf(request.getParameter("maGio")), nguoiDung.getMaNguoiDung());
        response.sendRedirect(request.getHeader("Referer"));
	}

}
