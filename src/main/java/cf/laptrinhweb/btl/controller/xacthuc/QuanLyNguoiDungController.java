package cf.laptrinhweb.btl.controller.xacthuc;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.model.DieuKienNguoiDung;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/nguoi-dung")
public class QuanLyNguoiDungController extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.ADMIN));

        List<NguoiDung> dsNguoiDung = xacThucService.timNguoiDung(DieuKienNguoiDung.trichXuat(req));
        req.setAttribute("danhSachNguoiDung", dsNguoiDung);
        req.getRequestDispatcher("/WEB-INF/quan_ly_nguoi_dung.jsp").forward(req, resp);
    }
}
