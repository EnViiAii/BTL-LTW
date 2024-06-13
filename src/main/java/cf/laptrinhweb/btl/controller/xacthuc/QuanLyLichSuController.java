package cf.laptrinhweb.btl.controller.xacthuc;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.LichSuHanhDong;
import cf.laptrinhweb.btl.model.DieuKienLichSu;
import cf.laptrinhweb.btl.model.Trang;
import cf.laptrinhweb.btl.service.LichSuHanhDongService;
import cf.laptrinhweb.btl.service.impl.LichSuHanhDongServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/nguoi-dung/lich-su")
public class QuanLyLichSuController extends HttpServlet {
    private final LichSuHanhDongService lichSuHanhDongService = new LichSuHanhDongServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.ADMIN));

        Long maNguoiDung = Long.parseLong(req.getParameter("maNguoiDung"));
        Trang<LichSuHanhDong> danhSachLichSu = lichSuHanhDongService.xemLichSuHanhDong(maNguoiDung, taoDieuKien(req));
        req.setAttribute("danhSachHanhDong", danhSachLichSu);
        req.getRequestDispatcher("/WEB-INF/lich_su_hanh_dong.jsp").forward(req, resp);
    }

    private DieuKienLichSu taoDieuKien(HttpServletRequest request) {
        DieuKienLichSu dieuKienLichSu = new DieuKienLichSu();
        if (request.getParameter("trang") != null)
            dieuKienLichSu.setTrang(Integer.parseInt(request.getParameter("trang"))-1);
        if (request.getParameter("kichThuoc") != null)
            dieuKienLichSu.setKichThuoc(Integer.parseInt(request.getParameter("kichThuoc")));
        return dieuKienLichSu;
    }
}
