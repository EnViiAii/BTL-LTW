package cf.laptrinhweb.btl.controller.sanpham;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.model.ThongTinSanPham;
import cf.laptrinhweb.btl.service.SanPhamService;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static cf.laptrinhweb.btl.helper.HoTroRequest.layThamSo;
import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/san-pham/luu")
@MultipartConfig
public class LuuSanPhamController extends HttpServlet {
    private final SanPhamService sanPhamService = new SanPhamServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        ThongTinSanPham sanPhamMoi = taoThongTinSanPham(req);
        List<Part> fileParts = req.getParts()
            .stream()
            .filter(part -> "anh".equalsIgnoreCase(part.getName()) && part.getSize() > 0)
            .toList();
        sanPhamService.luuSanPham(sanPhamMoi, fileParts);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private ThongTinSanPham taoThongTinSanPham(HttpServletRequest request) {
        String thamSoMaSanPham = layThamSo(request, "maSanPham");
        Long maSanPham = (thamSoMaSanPham == null)
            ? null
            : Long.parseLong(thamSoMaSanPham);
        String kichThuoc = layThamSoKhongNull(request, "kichThuoc").isBlank()
            ? null
            : request.getParameter("kichThuoc");
        Double trongLuong = layThamSoKhongNull(request, "trongLuong").isBlank()
            ? null
            : Double.parseDouble(layThamSoKhongNull(request, "trongLuong"));
        return ThongTinSanPham.builder()
            .maSanPham(maSanPham)
            .ten(layThamSo(request, "tenSanPham"))
            .moTa(request.getParameter("moTa"))
            .maTheLoai(Long.parseLong(layThamSoKhongNull(request, "theLoai")))
            .gia(Double.parseDouble(layThamSoKhongNull(request, "gia")))
            .soLuong(Integer.parseInt(layThamSoKhongNull(request, "soLuong")))
            .maChatLieu(Long.parseLong(layThamSoKhongNull(request, "chatLieu")))
            .maThuongHieu(Long.parseLong(layThamSoKhongNull(request, "thuongHieu")))
            .kichThuoc(kichThuoc)
            .trongLuong(trongLuong)
            .daAn(Boolean.parseBoolean(layThamSo(request, "daAn")))
            .build();
    }

    private String layThamSoKhongNull(HttpServletRequest request, String tenThamSo) {
        return Objects.requireNonNullElse(layThamSo(request, tenThamSo), "");
    }
}
