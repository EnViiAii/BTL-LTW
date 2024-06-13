package cf.laptrinhweb.btl.controller.sanpham;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/san-pham/tao-moi")
public class TaoMoiSanPhamController extends FormSanPhamController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.QUAN_LY));

        super.doGet(req, resp);
        req.getRequestDispatcher("/WEB-INF/form_san_pham.jsp").forward(req, resp);
    }
}
