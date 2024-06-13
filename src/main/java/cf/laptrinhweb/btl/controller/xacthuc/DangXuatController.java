package cf.laptrinhweb.btl.controller.xacthuc;

import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.LichSuHanhDongService;
import cf.laptrinhweb.btl.service.impl.LichSuHanhDongServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dang-xuat")
public class DangXuatController extends HttpServlet {
    private final LichSuHanhDongService lichSuHanhDongService = new LichSuHanhDongServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
        try {
            req.getSession().invalidate();
            lichSuHanhDongService.themLichSu(req, nguoiDung.getMaNguoiDung(), LoaiHanhDong.DANG_XUAT, true);
        } catch (Exception e) {
            lichSuHanhDongService.themLichSu(req, nguoiDung.getMaNguoiDung(), LoaiHanhDong.DANG_XUAT, false);
            throw new RuntimeException("Khong the dang xuat", e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
