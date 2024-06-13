package cf.laptrinhweb.btl.controller.dashboard;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.model.BanGhiDuLieu;
import cf.laptrinhweb.btl.model.DanhSachDuLieu;
import cf.laptrinhweb.btl.model.GiaiDoan;
import cf.laptrinhweb.btl.service.DashboardService;
import cf.laptrinhweb.btl.service.impl.DashboardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/dashboard")
public class DashboardController extends HttpServlet {
    private final DashboardService dashboardService = new DashboardServiceImpl();

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        int nam = req.getParameter("nam") != null
                ? Integer.parseInt(req.getParameter("nam"))
                : LocalDate.now().getYear();
        int thang = req.getParameter("thang") != null
                ? Integer.parseInt(req.getParameter("thang"))
                : LocalDate.now().getMonthValue();
        GiaiDoan giaiDoan = new GiaiDoan(nam, thang);

        Map<String, Object> thongKeDoanhThu = dashboardService.layThongKeDoanhThu(giaiDoan);
        req.setAttribute("tongDoanhThu", thongKeDoanhThu.get("tongDoanhThu"));
        req.setAttribute("trungBinhDon", thongKeDoanhThu.get("trungBinhDon"));
        req.setAttribute("doanhThuTungNgay", DanhSachDuLieu.builder()
            .tenDanhSach("Biểu đồ doanh thu")
            .danhSachDuLieu((List<BanGhiDuLieu>) thongKeDoanhThu.get("doanhThuTungNgay"))
            .build());
        req.setAttribute("tiLeHuyDon", DanhSachDuLieu.builder()
            .tenDanhSach("Tỉ lệ đơn hủy/thành công")
            .danhSachDuLieu(dashboardService.thongKeTrangThaiDon(giaiDoan))
            .build());
        req.setAttribute("soDonTungNgay", DanhSachDuLieu.builder()
            .tenDanhSach("Số lượng đơn (không tính đơn hủy)")
            .danhSachDuLieu(dashboardService.thongKeSoDonTungNgay(giaiDoan))
            .build());
        req.setAttribute("topMuaNhieu",dashboardService.layTopKhachMuaNhieu(giaiDoan) );
        req.setAttribute("topBanChay", dashboardService.layTopSanPhamBanChay(giaiDoan));
        req.setAttribute("theLoaiBanChay", dashboardService.layTheLoaiBanChay(giaiDoan));
        req.setAttribute("thuongHieuBanChay", dashboardService.layThuongHieuBanChay(giaiDoan));
        req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
    }
}
