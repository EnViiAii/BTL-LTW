package cf.laptrinhweb.btl.controller.donhang;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.constant.TrangThaiDon;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.repository.DatHangRepository;
import cf.laptrinhweb.btl.repository.impl.DatHangRepositoryImpl;

/**
 * Servlet implementation class CapNhatTrangThaiDon
 */
@WebServlet("/don-hang/cap-nhat")
public class CapNhatTrangThaiDon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatHangRepository datHangRepository = new DatHangRepositoryImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		yeuCauQuyen(request, List.of(QuyenNguoiDung.QUAN_LY));

        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
		TrangThaiDon moi = TrangThaiDon.cuaGiaTri(Integer.valueOf(request.getParameter("trangThai") != null ? request.getParameter("trangThai"): "1"));
		Long maDH = Long.parseLong(request.getParameter("maDatHang"));
		datHangRepository.CapNhatTrangThaiDon(maDH, moi);
		response.sendRedirect(request.getHeader("Referer"));
	}

}
