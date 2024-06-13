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
import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.repository.DatHangRepository;
import cf.laptrinhweb.btl.repository.impl.DatHangRepositoryImpl;
import cf.laptrinhweb.btl.service.DatHangService;
import cf.laptrinhweb.btl.service.impl.DatHangServiceImpl;

/**
 * Servlet implementation class ChiTietDonHangQuanLyController
 */
@WebServlet("/quan-ly/don-hang/chi-tiet")
public class ChiTietDonHangQuanLyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatHangRepository datHangRepository = new DatHangRepositoryImpl();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
    	DatHang datHang = datHangRepository.layDonTheoMaBoiQuanLy(Long.valueOf(req.getParameter("maDatHang")));
    	req.setAttribute("datHang", datHang);
        req.getRequestDispatcher("/WEB-INF/chi_tiet_don_hang_quan_ly.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
