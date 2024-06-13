package cf.laptrinhweb.btl.controller.taikhoan;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroRequest;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.model.DieuKienNguoiDung;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauDangNhap;

@WebServlet("/tai-khoan")
public class ThongTinTaiKhoanController extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauDangNhap(req);

        Long maNguoiDung = HoTroXacThuc.nguoiDungHienTai(req).getMaNguoiDung();
        NguoiDung nguoiDung = xacThucService.timNguoiDung(DieuKienNguoiDung.builder().maNguoiDung(maNguoiDung).build()).get(0);
        req.setAttribute("nguoiDung", nguoiDung);
        req.getRequestDispatcher("/WEB-INF/thong_tin_tai_khoan.jsp").forward(req, resp);
    }
}
