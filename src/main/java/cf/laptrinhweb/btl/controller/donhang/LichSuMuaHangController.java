package cf.laptrinhweb.btl.controller.donhang;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

import cf.laptrinhweb.btl.service.*;
import cf.laptrinhweb.btl.service.impl.*;
import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.*;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

/**
 * Servlet implementation class LichSuMuaHang
 */
@WebServlet("/lich-su-mua-hang")
public class LichSuMuaHangController extends HttpServlet {
	private final DatHangService datHangService = new DatHangServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		yeuCauQuyen(request, List.of(QuyenNguoiDung.KHACH_HANG));

        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
		List<DatHang> listDH = datHangService.layTatCaCuaNguoiDung(nguoiDung);
		request.setAttribute("danhSachDonHang", listDH);
		request.getRequestDispatcher("WEB-INF/lich_su_don_hang.jsp").forward(request, response);
	}
}
