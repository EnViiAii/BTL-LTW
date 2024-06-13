package cf.laptrinhweb.btl.controller.danhgia;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.DanhGiaService;
import cf.laptrinhweb.btl.service.impl.DanhGiaServiceImpl;
import cf.laptrinhweb.btl.entity.*;


@WebServlet("/them-danh-gia")
public class ThemDanhGiaController extends HttpServlet {
	public final DanhGiaService danhGiaService = new DanhGiaServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		yeuCauQuyen(request, List.of(QuyenNguoiDung.KHACH_HANG));
		NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(request);
		DanhGia danhGia = new DanhGia();
		danhGia.setKhachHangDanhGia(nguoiDung);
		danhGia.setNoi_dung_danh_gia(request.getParameter("noi_dung_binh_luan"));

		danhGia.setNgay_danh_gia(new Date(System.currentTimeMillis()));
		
		danhGia.setSoDiemDanhGia(Integer.parseInt(request.getParameter("danhGia")));

		Long ma_san_pham_dat = Long.parseLong(request.getParameter("ma_san_pham_dat"));
		
		danhGiaService.themDanhGia(danhGia,ma_san_pham_dat);

		response.sendRedirect(request.getContextPath()+"/xem-tat-ca-danh-gia?maSanPham=" + request.getParameter("ma_san_pham"));
	}

}
