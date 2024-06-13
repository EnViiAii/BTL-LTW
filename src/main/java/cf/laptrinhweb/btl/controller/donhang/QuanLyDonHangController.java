package cf.laptrinhweb.btl.controller.donhang;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.constant.SapXepDon;
import cf.laptrinhweb.btl.constant.TrangThaiDon;
import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.model.DieuKienDonHang;
import cf.laptrinhweb.btl.model.Trang;
import cf.laptrinhweb.btl.repository.DatHangRepository;
import cf.laptrinhweb.btl.repository.impl.DatHangRepositoryImpl;
import cf.laptrinhweb.btl.service.DatHangService;
import cf.laptrinhweb.btl.service.impl.DatHangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/don-hang")
public class QuanLyDonHangController extends HttpServlet {
    private final DatHangRepository datHangRepository = new DatHangRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY, QuyenNguoiDung.NHAN_VIEN));

        DieuKienDonHang dieuKienLoc = trichXuatDieuKien(req);
        List<DatHang> dsDonHang = datHangRepository.locTheoDieuKien(dieuKienLoc);
        req.setAttribute("dsDonHang", dsDonHang);
        req.getRequestDispatcher("/WEB-INF/quan_ly_don_hang.jsp").forward(req, resp);
    }
    
    private DieuKienDonHang trichXuatDieuKien(HttpServletRequest request) {
        DieuKienDonHang dieuKienDonHang = new DieuKienDonHang();
        if (request.getParameter("trangThai") != null) {
            dieuKienDonHang.setDsTrangThai(Arrays.stream(request.getParameterValues("trangThai"))
                .map(TrangThaiDon::valueOf)
                .toList());
        }
        if (request.getParameter("sapXep") != null) {
            dieuKienDonHang.setKieuSapXep(SapXepDon.valueOf(request.getParameter("sapXep")));
        }
        if (request.getParameter("trang") != null)
            dieuKienDonHang.setTrang(Integer.parseInt(request.getParameter("trang")) - 1);
        return dieuKienDonHang;
    }
}
