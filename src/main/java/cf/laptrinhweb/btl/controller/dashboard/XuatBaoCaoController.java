package cf.laptrinhweb.btl.controller.dashboard;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.model.*;
import cf.laptrinhweb.btl.service.DashboardService;
import cf.laptrinhweb.btl.service.impl.DashboardServiceImpl;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/dashboard/xuat-bao-cao")
public class XuatBaoCaoController extends HttpServlet {
    private final DashboardService dashboardService = new DashboardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        int nam = req.getParameter("nam") != null
                ? Integer.parseInt(req.getParameter("nam"))
                : LocalDate.now().getYear();
        int thang = req.getParameter("thang") != null
                ? Integer.parseInt(req.getParameter("thang"))
                : LocalDate.now().getMonthValue();
        GiaiDoan giaiDoan = new GiaiDoan(nam, thang);

//        req.setAttribute("doanhThuTungNgay", DanhSachDuLieu.builder()
//                .tenDanhSach("Biểu đồ doanh thu")
//                .danhSachDuLieu((List<BanGhiDuLieu>) thongKeDoanhThu.get("doanhThuTungNgay"))
//                .build());
//        req.setAttribute("topMuaNhieu",dashboardService.layTopKhachMuaNhieu(giaiDoan) );
//        req.setAttribute("topBanChay", dashboardService.layTopSanPhamBanChay(giaiDoan));
//        req.setAttribute("theLoaiBanChay", dashboardService.layTheLoaiBanChay(giaiDoan));
//        req.setAttribute("thuongHieuBanChay", dashboardService.layThuongHieuBanChay(giaiDoan));

        try (Workbook fileBaoCao = taoFileBaoCao(giaiDoan)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fileBaoCao.write(byteArrayOutputStream);
            resp.getOutputStream().write(byteArrayOutputStream.toByteArray());
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=bao_cao.xlsx");
        }
    }

    private Workbook taoFileBaoCao(GiaiDoan giaiDoan) {
        Workbook fileBaoCao = new XSSFWorkbook();
        Sheet sheetTongQuan = fileBaoCao.createSheet("Tổng quan");

        Map<String, Object> thongKeDoanhThu = dashboardService.layThongKeDoanhThu(giaiDoan);
        Row hang = sheetTongQuan.createRow(0);
        hang.createCell(0).setCellValue("Tổng doanh thu");
        hang.createCell(1).setCellValue((Double) thongKeDoanhThu.get("tongDoanhThu"));

        hang = sheetTongQuan.createRow(1);
        hang.createCell(0).setCellValue("Giá trị trung bình đơn hàng");
        hang.createCell(1).setCellValue((Double) thongKeDoanhThu.get("trungBinhDon"));

        Sheet sheetSanPhamBanChay = fileBaoCao.createSheet("Sản phẩm bán chạy");
        hang = sheetSanPhamBanChay.createRow(0);
        hang.createCell(0).setCellValue("STT");
        hang.createCell(1).setCellValue("Tên sản phẩm");
        hang.createCell(2).setCellValue("Số lượng đã bán");
        List<SanPhamMuaNhieu> spBanChay = dashboardService.layTopSanPhamBanChay(giaiDoan);
        for (int i=0; i<spBanChay.size(); i++) {
            hang = sheetSanPhamBanChay.createRow(i+1);
            hang.createCell(0).setCellValue(spBanChay.get(i).getStt());
            hang.createCell(1).setCellValue(spBanChay.get(i).getTenSanPham());
            hang.createCell(2).setCellValue(spBanChay.get(i).getSoLuong());
        }

        Sheet sheetTheLoaiBanChay = fileBaoCao.createSheet("Thể loại bán chạy");
        hang = sheetTheLoaiBanChay.createRow(0);
        hang.createCell(0).setCellValue("STT");
        hang.createCell(1).setCellValue("Tên thể loại");
        hang.createCell(2).setCellValue("Số lượng đã bán");
        List<TheLoaiMuaNhieu> theLoaiBanChay = dashboardService.layTheLoaiBanChay(giaiDoan);
        for (int i=0; i<theLoaiBanChay.size(); i++) {
            hang = sheetTheLoaiBanChay.createRow(i+1);
            hang.createCell(0).setCellValue(theLoaiBanChay.get(i).getStt());
            hang.createCell(1).setCellValue(theLoaiBanChay.get(i).getTenTheLoai());
            hang.createCell(2).setCellValue(theLoaiBanChay.get(i).getSoLuong());
        }

        Sheet sheetThuongHieuBanChay = fileBaoCao.createSheet("Thương hiệu bán chạy");
        hang = sheetThuongHieuBanChay.createRow(0);
        hang.createCell(0).setCellValue("STT");
        hang.createCell(1).setCellValue("Tên thương hiệu");
        hang.createCell(2).setCellValue("Số lượng đã bán");
        List<ThuongHieuMuaNhieu> thuongHieuBanChay = dashboardService.layThuongHieuBanChay(giaiDoan);
        for (int i=0; i<thuongHieuBanChay.size(); i++) {
            hang = sheetThuongHieuBanChay.createRow(i+1);
            hang.createCell(0).setCellValue(thuongHieuBanChay.get(i).getStt());
            hang.createCell(1).setCellValue(thuongHieuBanChay.get(i).getTenThuongHieu());
            hang.createCell(2).setCellValue(thuongHieuBanChay.get(i).getSoLuong());
        }

        Sheet sheetKhachHangMuaNhieu = fileBaoCao.createSheet("Khách hàng mua nhiều");
        hang = sheetKhachHangMuaNhieu.createRow(0);
        hang.createCell(0).setCellValue("STT");
        hang.createCell(1).setCellValue("Tên đăng nhập");
        hang.createCell(2).setCellValue("Tên hiển thị");
        hang.createCell(3).setCellValue("Lượng mua");
        List<KhachHangMuaNhieu> khachMuaNhieu = dashboardService.layTopKhachMuaNhieu(giaiDoan);
        for (int i=0; i<khachMuaNhieu.size(); i++) {
            hang = sheetKhachHangMuaNhieu.createRow(i+1);
            hang.createCell(0).setCellValue(khachMuaNhieu.get(i).getStt());
            hang.createCell(1).setCellValue(khachMuaNhieu.get(i).getTenDangNhap());
            hang.createCell(2).setCellValue(khachMuaNhieu.get(i).getTenHienThi());
            hang.createCell(3).setCellValue(khachMuaNhieu.get(i).getTongTien());
        }

        return fileBaoCao;
    }
}
