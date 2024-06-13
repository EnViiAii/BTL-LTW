package cf.laptrinhweb.btl.controller.binhluan;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.impl.BinhLuanServiceImpl;
import cf.laptrinhweb.btl.entity.*;
import cf.laptrinhweb.btl.service.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@WebServlet("/binh-luan")
public class BinhLuanController  extends HttpServlet{
	private final BinhLuanService binhLuanService = new BinhLuanServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
        yeuCauQuyen(req, List.of(QuyenNguoiDung.KHACH_HANG));
		Long ma_san_pham = Long.parseLong(req.getParameter("ma_san_pham"));
		Map<BinhLuan, List<BinhLuan>> lbl = binhLuanService.layTatCaBinhLuan(ma_san_pham);
		req.setAttribute("tat_ca_binh_luan", lbl);
		req.getRequestDispatcher("WEB-INF/binh_luan.jsp").forward(req, resp);
    }
}
