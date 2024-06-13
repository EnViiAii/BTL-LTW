package cf.laptrinhweb.btl.controller.xacthuc;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.exception.xacthuc.ThongTinDangNhapDaTonTaiException;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/dang-ky")
public class DangKyController extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/dang_ky.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            xacThucService.dangKy(taoNguoiDung(req.getParameterMap()));
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
        } catch (ThongTinDangNhapDaTonTaiException e) {
            e.getThongTinTrungLap().forEach(loaiThongTin -> {
                switch (loaiThongTin) {
                    case EMAIL -> req.setAttribute("trungEmail", true);
                    case SO_DIEN_THOAI -> req.setAttribute("trungSdt", true);
                    case TEN_DANG_NHAP -> req.setAttribute("trungTenDangNhap", true);
                }
            });
            req.getParameterMap().forEach((key, val) -> {
                req.setAttribute(key, val[0]);
            });
            req.getRequestDispatcher("/WEB-INF/dang_ky.jsp").forward(req, resp);
        }
    }

    private NguoiDung taoNguoiDung(Map<String, String[]> thongTinDangKy) {
        return NguoiDung.builder()
            .tenDangNhap(thongTinDangKy.get("tenDangNhap")[0])
            .matKhau(thongTinDangKy.get("matKhau")[0])
            .email(thongTinDangKy.get("email")[0])
            .soDienThoai(thongTinDangKy.get("soDienThoai")[0])
            .tenHienThi(thongTinDangKy.get("ten")[0])
            .build();
    }
}
