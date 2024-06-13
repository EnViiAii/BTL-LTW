package cf.laptrinhweb.btl.controller.binhluan;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.BinhLuan;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.BinhLuanService;
import cf.laptrinhweb.btl.service.impl.BinhLuanServiceImpl;


@WebServlet("/xoa-binh-luan")
public class XoaBinhLuanController extends HttpServlet{
	private final BinhLuanService binhLuanService = new BinhLuanServiceImpl();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
        yeuCauQuyen(req, List.of(QuyenNguoiDung.KHACH_HANG));
		Long ma_binh_luan = Long.parseLong(req.getParameter("ma_binh_luan"));
		binhLuanService.xoaBinhLuan(ma_binh_luan,nguoiDung.getMaNguoiDung());
		resp.sendRedirect(req.getContextPath() + "/binhluan?ma_san_pham=" + req.getParameter("ma_san_pham"));
    }
}
