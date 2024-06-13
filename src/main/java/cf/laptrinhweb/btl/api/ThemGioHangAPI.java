package cf.laptrinhweb.btl.api;

import cf.laptrinhweb.btl.dto.request.ThemGioHangDTO;
import cf.laptrinhweb.btl.dto.response.PhanHoiGioHang;
import cf.laptrinhweb.btl.helper.HoTroJson;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.GioHangService;
import cf.laptrinhweb.btl.service.impl.GioHangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeLoi;
import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeThanhCong;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebServlet("/api/gio-hang/them")
public class ThemGioHangAPI extends HttpServlet {
    private final GioHangService gioHangService = new GioHangServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	ThemGioHangDTO themGioHangDTO = HoTroJson.layThongTin(req, ThemGioHangDTO.class);
            gioHangService.themSanPham(HoTroXacThuc.nguoiDungHienTai(req), themGioHangDTO.getMaSanPham(), themGioHangDTO.getSoLuong());
            traVeThanhCong(
                resp,
                new PhanHoiGioHang("Da them thanh cong")
            );
        } catch (Exception e) {
            traVeLoi(resp, SC_BAD_REQUEST, "Khong them duoc gio hang");
        }
    }
}
    
