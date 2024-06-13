package cf.laptrinhweb.btl.api;

import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeLoi;
import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeThanhCong;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cf.laptrinhweb.btl.entity.*;
import cf.laptrinhweb.btl.dto.request.ThemBinhLuanDTO;
import cf.laptrinhweb.btl.dto.response.PhanHoiBinhLuan;
import cf.laptrinhweb.btl.dto.response.PhanHoiGioHang;
import cf.laptrinhweb.btl.helper.HoTroJson;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.BinhLuanService;
import cf.laptrinhweb.btl.service.impl.BinhLuanServiceImpl;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

@WebServlet("/api/them-binh-luan")
public class ThemBinhLuanAPI extends HttpServlet {
	private final BinhLuanService binhLuanService = new BinhLuanServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ThemBinhLuanDTO themBinhLuanDTO = HoTroJson.layThongTin(request, ThemBinhLuanDTO.class);
			BinhLuan bl = new BinhLuan();
			bl.setNoi_dung_binh_luan(themBinhLuanDTO.getNoiDungBinhLuan());
			bl.setNguoi_binh_luan(HoTroXacThuc.nguoiDungHienTai(request));
			bl.setMa_binh_luan_goc(themBinhLuanDTO.getMaBinhLuanGoc());
			bl.setNgay_binh_luan(new Date(System.currentTimeMillis()));
			bl.setSan_pham(new SanPhamServiceImpl().timTheoMa(themBinhLuanDTO.getMaSanPham()));
			binhLuanService.themBinhLuan(bl);
			traVeThanhCong(
	                response,
	                new PhanHoiBinhLuan("Da them thanh cong")
	            );
		}
		catch (Exception e) {
			traVeLoi(response, SC_BAD_REQUEST, "Khong them duoc binh luan");
		}
	}

}
